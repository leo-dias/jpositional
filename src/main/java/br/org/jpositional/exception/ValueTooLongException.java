package br.org.jpositional.exception;

public class ValueTooLongException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValueTooLongException(String msg) {
        super(msg);
    }
}
