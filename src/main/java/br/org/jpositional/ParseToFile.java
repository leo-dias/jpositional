package br.org.jpositional;

import br.org.jpositional.annotation.*;
import br.org.jpositional.annotation.decorator.DateFormatter;
import br.org.jpositional.annotation.domain.Direction;
import br.org.jpositional.exception.*;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Leonardo Dias de Oliveira
 */

class ParseToFile {

    void parse(Object object, String filePath) throws IOException {
        final Class<?> aClass = object.getClass();
        ComplexPositional complexPositional = aClass.getDeclaredAnnotation(ComplexPositional.class);
        SimplePositional simplePositional = aClass.getAnnotation(SimplePositional.class);

        if (complexPositional != null) {
            writeComplexPositional(object, filePath);
        } else if (simplePositional != null) {
            writeSimplePositional(object, filePath);
        } else {
            throw new NoAnnotationConfiguredException(String.format("There are no positional configuration for class %s. Please, consider use @ComplexPositional or @SimplePositional annotation.", aClass.getName()));
        }
    }

    void parseMany(List<?> objectList, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            objectList.forEach(object -> {
                final Class<?> aClass = object.getClass();
                SimplePositional simplePositional = aClass.getAnnotation(SimplePositional.class);
                if (simplePositional == null) {
                    throw new NoSimplePositionalClassSupportedException();
                }

                try {
                    writeFromFields(fw, object, object.getClass().getDeclaredFields());
                    fw.write("\n");
                } catch (Exception e) {
                    throw new UnexpectedErrorException(this.getClass(), e.getMessage());
                }
            });
        } catch (IOException e) {
            throw new UnexpectedErrorException(this.getClass(), e.getMessage());
        }
    }

    private void writeComplexPositional(Object object, String filePath) throws IOException {
        Field[] fields = object.getClass().getDeclaredFields();
        try (FileWriter fw = new FileWriter(filePath)) {
            Arrays.stream(fields).forEach(field -> {
                write(object, field, field.getAnnotation(Header.class), fw);
                write(object, field, field.getAnnotation(Detail.class), fw);
                write(object, field, field.getAnnotation(Trailer.class), fw);
            });
        }
    }

    private void writeSimplePositional(Object object, String filePath) throws IOException {
        Field[] fields = object.getClass().getDeclaredFields();
        try (FileWriter fw = new FileWriter(filePath)) {
            try {
                writeFromFields(fw, object, fields);
            } catch (Exception e) {
                throw new UnexpectedErrorException(this.getClass(), e.getMessage());
            }
        }
    }

    private void write(Object object, Field field, Annotation annotation, FileWriter fw) {
        if (annotation != null) {
            try {
                Method method = object.getClass().getDeclaredMethod(methodGetName(field));
                Object invoked = method.invoke(object);
                if (invoked != null) {

                    if (invoked instanceof Collection) {
                        Collection<Object> collection = (Collection) invoked;
                        collection.forEach(record -> {
                            try {
                                writeFromFields(fw, record, record.getClass().getDeclaredFields());
                                fw.write("\n");
                            } catch (Exception e) {
                                throw new UnexpectedErrorException(this.getClass(), e.getMessage());
                            }
                        });
                    } else {
                        writeFromFields(fw, invoked, invoked.getClass().getDeclaredFields());
                        fw.write("\n");
                    }

                }
            } catch (Exception e) {
                throw new UnexpectedErrorException(this.getClass(), e.getMessage());
            }
        }
    }

    private void writeFromFields(FileWriter fw, Object object, Field[] fields) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        for (Field field : fields) {
            Line line = field.getDeclaredAnnotation(Line.class);
            if (line != null) {
                Method methodHeader = object.getClass().getDeclaredMethod(methodGetName(field));
                Object invoked = methodHeader.invoke(object);
                if (invoked != null) {
                    DateFormatter dateFormatter = field.getDeclaredAnnotation(DateFormatter.class);
                    if (dateFormatter != null) {
                        writeDateFormat(fw, field, line, invoked, dateFormatter);
                    } else {
                        validateAndWrite(fw, field, line, invoked.toString());
                    }
                }
            }
        }
    }

    private void writeDateFormat(FileWriter fw, Field field, Line line, Object invoked, DateFormatter dateFormatter) throws IOException {
        String instanceName = field.getType().getName();
        if ("java.util.Date".equals(instanceName)) {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormatter.format());
            String value = formatter.format(invoked);
            validateAndWrite(fw, field, line, value);
        } else if ("java.time.LocalDate".equals(instanceName) || "java.time.LocalDateTime".equals(instanceName)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatter.format());
            String value = ((LocalDate) invoked).format(formatter);
            validateAndWrite(fw, field, line, value);
        }
    }

    private void validateAndWrite(FileWriter fw, Field field, Line line, String value) throws IOException {
        int size = line.end() - line.begin();
        validateMaxSize(field, value, size);
        if (!line.fill().isEmpty()) {
            value = fill(value, line.fill(), size, line.direction());
        }
        validateCorrectSize(field, value, size);
        fw.write(value);
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
