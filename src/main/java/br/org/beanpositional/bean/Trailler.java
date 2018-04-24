package br.org.beanpositional.bean;

import br.org.beanpositional.Position;

public class Trailler {
    @Position(begin = 0, end = 1)
    private String cod;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}