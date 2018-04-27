package br.org.jpositional.exception;

public class UnexpectedErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnexpectedErrorException(Class clazz, String error) {
        super(String.format("Unexpected error in class %s. Error message: %s", clazz.getName(), error));
    }
}
