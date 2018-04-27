package br.org.jpositional;

import br.org.jpositional.annotation.*;
import br.org.jpositional.annotation.decorator.DateFormatter;
import br.org.jpositional.exception.MandatoryDateFormatterException;
import br.org.jpositional.exception.NoAnnotationConfiguredException;
import br.org.jpositional.exception.NoSimplePositionalClassSupportedException;
import br.org.jpositional.exception.UnexpectedErrorException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Leonardo Dias de Oliveira
 */

class ParseFromFile {

    <T> T parse(Class<T> clazz, String filePath) throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            T rootBean = createInstance(clazz);

            final Class<?> aClass = rootBean.getClass();
            ComplexPositional complexPositional = aClass.getDeclaredAnnotation(ComplexPositional.class);
            SimplePositional simplePositional = aClass.getAnnotation(SimplePositional.class);

            if (complexPositional != null) {
                processComplexPositional(stream, rootBean);
            } else if (simplePositional != null) {
                processSimplePositional(stream, rootBean);
            } else {
                throw new NoAnnotationConfiguredException(String.format("There are no positional configuration for class %s. Please, consider use @ComplexPositional or @SimplePositional annotation.", aClass.getName()));
            }

            return rootBean;
        }
    }

    <T> List<T> parseManyLines(Class<T> clazz, String filePath) throws IOException {
        T rootBean = createInstance(clazz);
        final Class<?> aClass = rootBean.getClass();
        SimplePositional simplePositional = aClass.getAnnotation(SimplePositional.class);
        if (simplePositional == null) {
            throw new NoSimplePositionalClassSupportedException();
        }

        List<T> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            stream.forEach(s -> {
                T multiBean = createInstance(clazz);
                iterateDeclaredFields(s, multiBean);
                list.add(multiBean);
            });

        }
        return list;
    }

    private <T> void iterateDeclaredFields(String s, T multiBean) {
        for (Field f2 : multiBean.getClass().getDeclaredFields()) {
            Line line = f2.getAnnotation(Line.class);
            if (line != null) {
                try {
                    Field field = multiBean.getClass().getDeclaredField(f2.getName());
                    field.setAccessible(true);
                    field.set(multiBean, convertToInstance(field, line, s));
                } catch (Exception e) {
                    throw new UnexpectedErrorException(this.getClass(), e.getMessage());
                }
            }
        }
    }

    private <T> void processSimplePositional(Stream<String> stream, T rootBean) {
        stream.forEach(s -> iterateDeclaredFields(s, rootBean));
    }

    private <T> void processComplexPositional(Stream<String> stream, T rootBean) {
        List<Object> list = new ArrayList<>();

        stream.forEach(s -> {
            for (Field f : rootBean.getClass().getDeclaredFields()) {
                try {
                    processBeanField(s, rootBean, f);
                    processBeanFieldList(list, s, rootBean, f);
                } catch (Exception e) {
                    throw new UnexpectedErrorException(this.getClass(), e.getMessage());
                }
            }
        });
    }

    private <T> void processBeanField(String sb, T rootBean, Field f) throws IllegalAccessException, NoSuchFieldException, ParseException {
        Header header = f.getAnnotation(Header.class);
        if (header != null && header.identify().equals(sb.substring(0, 1))) {
            process(sb, rootBean, f);
        }

        Trailer trailer = f.getAnnotation(Trailer.class);
        if (trailer != null && trailer.identify().equals(sb.substring(0, 1))) {
            process(sb, rootBean, f);
        }
    }

    private <T> void process(String sb, T rootBean, Field f) throws IllegalAccessException, NoSuchFieldException, ParseException {
        Class<?> c = f.getType();
        Object obj = createInstance(c);
        fillObject(c, c.getDeclaredFields(), sb, obj);

        f.setAccessible(true);
        f.set(rootBean, obj);
    }

    private <T> void processBeanFieldList(List<Object> list, String sb, T rootBean, Field f) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        Detail detail = f.getAnnotation(Detail.class);
        if (detail != null && detail.identify().equals(sb.substring(0, 1))) {
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

    private void fillObject(Class<?> clazz, Field[] fields, String sb, Object obj) throws NoSuchFieldException, IllegalAccessException, ParseException {
        for (Field f2 : fields) {
            Line line = f2.getAnnotation(Line.class);
            if (line != null) {
                Field field = clazz.getDeclaredField(f2.getName());
                field.setAccessible(true);

                field.set(obj, convertToInstance(field, line, sb));
            }
        }
    }

    private Object convertToInstance(Field f, Line line, String s) throws ParseException {
        String instanceName = f.getType().getName();

        if ("java.lang.Integer".equals(instanceName)) {
            return Integer.parseInt(s.substring(line.begin(), line.end()));

        } else if ("java.lang.Long".equals(instanceName)) {
            return Long.parseLong(s.substring(line.begin(), line.end()));

        } else if ("java.lang.Double".equals(instanceName)) {
            return Double.parseDouble(s.substring(line.begin(), line.end()));

        } else if ("java.lang.Float".equals(instanceName)) {
            return Float.parseFloat(s.substring(line.begin(), line.end()));

        } else if ("java.lang.Short".equals(instanceName)) {
            return Short.parseShort(s.substring(line.begin(), line.end()));

        } else if ("java.lang.Byte".equals(instanceName)) {
            return Byte.parseByte(s.substring(line.begin(), line.end()));

        } else if ("java.util.Date".equals(instanceName)) {
            DateFormatter dateFormatter = f.getAnnotation(DateFormatter.class);
            if (dateFormatter != null) {
                return parseToDate(s.substring(line.begin(), line.end()), dateFormatter.format());
            }
            throw new MandatoryDateFormatterException("java.util.Date");

        } else if ("java.time.LocalDate".equals(instanceName)) {
            DateFormatter dateFormatter = f.getAnnotation(DateFormatter.class);
            if (dateFormatter != null) {
                return LocalDate.parse(s.substring(line.begin(), line.end()), DateTimeFormatter.ofPattern(dateFormatter.format()));
            }
            throw new MandatoryDateFormatterException("java.time.LocalDate");

        } else if ("java.time.LocalDateTime".equals(instanceName)) {
            DateFormatter dateFormatter = f.getAnnotation(DateFormatter.class);
            if (dateFormatter != null) {
                return LocalDateTime.parse(s.substring(line.begin(), line.end()), DateTimeFormatter.ofPattern(dateFormatter.format()));
            }
            throw new MandatoryDateFormatterException("java.time.LocalDateTime");

        } else {
            return s.substring(line.begin(), line.end());
        }
    }

    private Date parseToDate(String strDate, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(strDate);
    }
}