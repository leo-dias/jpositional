package br.org.jpositional;

import br.org.jpositional.exception.ValueSizeNotCorrectException;
import br.org.jpositional.exception.ValueTooLongException;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Leonardo Dias de Oliveira
 */

class ParseToFile {

    private static final String UNEXPECTED_ERROR_IN_CLASS = "Unexpected error in class %s. Error message: %s";

    void parse(Object object, String filePath) throws IOException {
        Field[] fields = object.getClass().getDeclaredFields();
        try (FileWriter fw = new FileWriter(filePath)) {
            Arrays.stream(fields).forEach(field -> {
                write(object, field, field.getAnnotation(HeaderPosition.class), fw);
                write(object, field, field.getAnnotation(BodyPosition.class), fw);
                write(object, field, field.getAnnotation(TrailerPosition.class), fw);
            });
        }
    }

    private void write(Object object, Field field, Annotation annotation, FileWriter fw) {
        if (annotation != null) {
            try {
                Method method = object.getClass().getDeclaredMethod(methodGetName(field));
                Object invoked = method.invoke(object);
                if (invoked != null) {

                    if (invoked instanceof Collection) {
                        Collection collection = (Collection) invoked;
                        collection.forEach(record -> {
                            try {
                                writeFromFields(fw, record, record.getClass().getDeclaredFields());
                                fw.write("\n");
                            } catch (Exception e) {
                                throw new RuntimeException(String.format(UNEXPECTED_ERROR_IN_CLASS, this.getClass().getName(), e.getMessage()));
                            }
                        });
                    } else {
                        writeFromFields(fw, invoked, invoked.getClass().getDeclaredFields());
                        fw.write("\n");
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(String.format(UNEXPECTED_ERROR_IN_CLASS, this.getClass().getName(), e.getMessage()));
            }
        }
    }


    private void writeFromFields(FileWriter fw, Object object, Field[] fields) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        for (Field field : fields) {
            LinePosition linePosition = field.getDeclaredAnnotation(LinePosition.class);
            if (linePosition != null) {
                Method methodHeader = object.getClass().getDeclaredMethod(methodGetName(field));
                Object invoked = methodHeader.invoke(object);
                if (invoked != null) {
                    String value = invoked.toString();
                    int size = linePosition.end() - linePosition.begin();
                    validateMaxSize(field, value, size);
                    if (!linePosition.fill().isEmpty()) {
                        value = fill(value, linePosition.fill(), size, linePosition.direction());
                    }
                    validateCorrectSize(field, value, size);
                    fw.write(value);
                }
            }
        }
    }

    private String methodGetName(Field field) {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    private String fill(String str, String character, int size, Direction direction) {
        while (str.length() < size) {
            if (Direction.LEFT.equals(direction)) str = character + str;

            if (Direction.RIGHT.equals(direction)) str = str + character;
        }
        return str;
    }

    private void validateMaxSize(Field field, String str, int size) {
        if (str.length() > size) {
            throw new ValueTooLongException(String.format("The value for field %s is too long. The allowed size is %s", field.getName(), size));
        }
    }

    private void validateCorrectSize(Field field, String str, int size) {
        if (str.length() < size) {
            throw new ValueSizeNotCorrectException(String.format("The value for field %s is not correct, must have %s characters", field.getName(), size));
        }
    }
}
