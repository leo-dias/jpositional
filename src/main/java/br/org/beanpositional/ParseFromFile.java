package br.org.beanpositional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ParseFromFile {

    <T> T parse(Class<T> clazz, String filePath) throws Exception {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            T rootBean = createInstance(clazz);

            List list = new ArrayList();

            stream.forEach(s -> {
                for (Field f : rootBean.getClass().getDeclaredFields()) {
                    try {
                        processBeanField(s, rootBean, f);
                        processBeanFieldList(list, s, rootBean, f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            return rootBean;
        }
    }

    private <T> void processBeanField(String sb, T rootBean, Field f) throws Exception {
        BeanFieldPositional beanFieldPositional = f.getAnnotation(BeanFieldPositional.class);
        if (beanFieldPositional != null && beanFieldPositional.type().equals(sb.substring(0, 1))) {
            Class c = f.getType();
            Object obj = createInstance(c);
            fillObject(c, c.getDeclaredFields(), sb, obj);

            f.setAccessible(true);
            f.set(rootBean, obj);
        }
    }

    private <T> void processBeanFieldList(List list, String sb, T rootBean, Field f) throws Exception {
        BeanFieldPositionalList beanFieldPositionalList = f.getAnnotation(BeanFieldPositionalList.class);
        if (beanFieldPositionalList != null && beanFieldPositionalList.type().equals(sb.substring(0, 1))) {
            ParameterizedType type = (ParameterizedType) f.getGenericType();
            Class<?> referenceType = (Class<?>) type.getActualTypeArguments()[0];

            Object obj = referenceType.newInstance();

            fillObject(referenceType, referenceType.getDeclaredFields(), sb, obj);

            list.add(obj);

            f.setAccessible(true);

            f.set(rootBean, list);
        }
    }

    private <T> T createInstance(Class<T> clazz) throws Exception {
        T newInstance;
        try {
            newInstance = clazz.newInstance();
        } catch (Exception e) {
            throw new Exception("Error while instantiating class, make sure that is provided a default constructor for class " + clazz, e);
        }
        return newInstance;
    }

    private void fillObject(Class<?> classe, Field[] fields, String sb, Object obj) throws NoSuchFieldException, IllegalAccessException {
        for (Field f2 : fields) {
            Position position = f2.getAnnotation(Position.class);
            if (position != null) {
                Field field = classe.getDeclaredField(f2.getName());
                field.setAccessible(true);
                field.set(obj, sb.substring(position.begin(), position.end()));
            }
        }
    }
}
