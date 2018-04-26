package br.org.jpositional.exception;

public class NoAnnotationConfiguredException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoAnnotationConfiguredException(String msg) {
        super(msg);
    }
}
