package br.org.beanpositional;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ParseFromFile {

    private <T> T createInstance(Class<T> clazz) throws Exception {
        T newInstance;
        try {
            newInstance = clazz.newInstance();
        } catch (Exception e) {
            throw new Exception("Error while instantiating record class, make sure that is provided a default constructor for class " + clazz, e);
        }
        return newInstance;
    }


    public <T> T parse(Class<T> clazz) throws Exception {
        try (Stream<String> stream = Files.lines(Paths.get("etc/txt/2018-04-10.txt"))) {
            StringBuilder sb = new StringBuilder();
            stream.forEach(sb::append);


            BeanRootPositional beanRootPositional = clazz.getClass().getAnnotation(BeanRootPositional.class);

            T consigSiape = createInstance(clazz);

            for (Field f : consigSiape.getClass().getDeclaredFields()) {
                System.out.println("######## Field = " + f.getName());
                BeanFieldPositional beanFieldPositional = f.getAnnotation(BeanFieldPositional.class);
                Class c = Class.forName(beanFieldPositional.className());
                for (Field f2 : c.getDeclaredFields()) {
                    Position position = f2.getAnnotation(Position.class);
                    if (position != null) {
                        System.out.println("******* " + f2.getName() + " - " + position.begin() + " / " + position.end());
                    }
                }

//                    if (f.getType().getName().equals(Header.class.getName())) {
//                        Header header = new Header();
//                        fillObject(Header.class, f.getType().getDeclaredFields(), sb, header);
//                        consigSiape.setHeader(header);
//                    } else if (f.getType().getName().equals(Consignacao.class.getName())) {
//                        Consignacao consignacao = new Consignacao();
//                        fillObject(Consignacao.class, f.getType().getDeclaredFields(), sb, consignacao);
//                        consigSiape.setConsignacao(consignacao);
//                    } else if (f.getType().getName().equals(Trailler.class.getName())) {
//                        Trailler trailler = new Trailler();
//                        fillObject(Trailler.class, f.getType().getDeclaredFields(), sb, trailler);
//                        consigSiape.setTrailler(trailler);
//                    }

            }

            System.out.println(consigSiape);
            return consigSiape;
        }
    }

    private void fillObject(Class<?> classe, Field[] fields, StringBuilder sb, Object obj) throws NoSuchFieldException, IllegalAccessException {
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
