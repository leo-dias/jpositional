package br.org.jpositional.exception;

public class NoSimplePositionalClassSupportedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoSimplePositionalClassSupportedException() {
        super("The class is not annotated with @SimplePositional annotation.");
    }
}
