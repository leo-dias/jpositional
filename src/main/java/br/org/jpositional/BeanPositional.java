package br.org.jpositional;

import java.util.List;

/**
 * @author Leonardo Dias de Oliveira
 */

public class BeanPositional {

    public <T> T parseFromFile(Class<T> clazz, String filePath) throws Exception {
        return new ParseFromFile().parse(clazz, filePath);
    }

    public <T> List<T> parseManyFromFile(Class<T> clazz, String filePath) throws Exception {
        return new ParseFromFile().parseManyLines(clazz, filePath);
    }

    public void parseToFile(Object object, String filePath) throws Exception {
        new ParseToFile().parse(object, filePath);
    }

    public void parseManyToFile(List<?> objectList, String filePath) throws Exception {
        new ParseToFile().parseMany(objectList, filePath);
    }
}
