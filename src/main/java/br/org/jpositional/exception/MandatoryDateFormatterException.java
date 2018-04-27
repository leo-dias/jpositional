package br.org.jpositional.exception;

public class MandatoryDateFormatterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MandatoryDateFormatterException(String className) {
        super(String.format("To use %s class is mandatory to use @DateFormatter.", className));
    }
}
