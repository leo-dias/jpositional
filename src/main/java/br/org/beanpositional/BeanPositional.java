package br.org.beanpositional;

public class BeanPositional {

    public <T> T parseFromFile(Class<T> clazz, String filePath) throws Exception {
        return new ParseFromFile().parse(clazz, filePath);
    }

    public void parseToFile(Object object, String filePath) throws Exception {
        new ParseToFile().parse(object, filePath);
    }
}
