package br.org.beanpositional;

import br.org.beanpositional.bean.ConsigSiape;
import org.junit.Test;

public class ConsigSiapeTest {

    @Test
    public void lerPosicoes() {


        ParseFromFile parseFromFile = new ParseFromFile();
        try {
            ConsigSiape consigSiape = parseFromFile.parse(ConsigSiape.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
