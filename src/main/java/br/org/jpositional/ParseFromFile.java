package br.org.jpositional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Leonardo Dias de Oliveira
 */

class ParseFromFile {

    <T> T parse(Class<T> clazz, String filePath) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            T rootBean = createInstance(clazz);

            List list = new ArrayList();

            stream.forEach(s -> {
                for (Field f : rootBean.getClass().getDeclaredFields()) {
                    try {
                        processBeanField(s, rootBean, f);
                        processBeanFieldList(list, s, rootBean, f);
                    } catch (Exception e) {
                        throw new RuntimeException(String.format("Unexpected error in class %s. Error message: %s", this.getClass().getName(), e.getMessage()));
                    }
                }
            });

            return rootBean;
        }
    }

    private <T> void processBeanField(String sb, T rootBean, Field f) throws Exception {
        HeaderPosition headerPosition = f.getAnnotation(HeaderPosition.class);
        if (headerPosition != null && headerPosition.identify().equals(sb.substring(0, 1))) {
            process(sb, rootBean, f);
        }

        TrailerPosition trailerPosition = f.getAnnotation(TrailerPosition.class);
        if (trailerPosition != null && trailerPosition.identify().equals(sb.substring(0, 1))) {
            process(sb, rootBean, f);
        }
    }

    private <T> void process(String sb, T rootBean, Field f) throws Exception {
        Class c = f.getType();
        Object obj = createInstance(c);
        fillObject(c, c.getDeclaredFields(), sb, obj);

        f.setAccessible(true);
        f.set(rootBean, obj);
    }

    private <T> void processBeanFieldList(List list, String sb, T rootBean, Field f) throws Exception {
        BodyPosition bodyPosition = f.getAnnotation(BodyPosition.class);
        if (bodyPosition != null && bodyPosition.identify().equals(sb.substring(0, 1))) {
            ParameterizedType type = (ParameterizedType) f.getGenericType();
            Class<?> referenceType = (Class<?>) type.getActualTypeArguments()[0];

            Object obj = referenceType.newInstance();

            fillObject(referenceType, referenceType.getDeclaredFields(), sb, obj);

            list.add(obj);

            f.setAccessible(true);

            f.set(rootBean, list);
        }
    }

    private <T> T createInstance(Class<T> clazz) {
        T newInstance;
        try {
            newInstance = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error while instantiating class, make sure that is provided a default constructor for class " + clazz, e);
        }
        return newInstance;
    }

    private void fillObject(Class<?> classe, Field[] fields, String sb, Object obj) throws NoSuchFieldException, IllegalAccessException {
        for (Field f2 : fields) {
            LinePosition linePosition = f2.getAnnotation(LinePosition.class);
            if (linePosition != null) {
                Field field = classe.getDeclaredField(f2.getName());
                field.setAccessible(true);
                field.set(obj, sb.substring(linePosition.begin(), linePosition.end()));
            }
        }
    }
}
