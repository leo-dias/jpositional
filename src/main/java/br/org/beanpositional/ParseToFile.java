package br.org.beanpositional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ParseToFile {

    void parse(Object object, String filePath) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            HeaderPosition headerPosition = field.getAnnotation(HeaderPosition.class);
            if (headerPosition != null) {
                try {
                    Method[] methods = object.getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        if (Modifier.isPublic(method.getModifiers())
                                && method.getParameterTypes().length == 0
                                && method.getReturnType() != void.class
                                && (method.getName().startsWith("get") || method.getName().startsWith("is"))
                                ) {

                            method.invoke(object);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
