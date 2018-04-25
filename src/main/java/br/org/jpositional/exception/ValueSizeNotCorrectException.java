package br.org.jpositional.exception;

public class ValueSizeNotCorrectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValueSizeNotCorrectException(String msg) {
        super(msg);
    }
}
