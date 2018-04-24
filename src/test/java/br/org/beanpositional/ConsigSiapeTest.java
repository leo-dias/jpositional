package br.org.beanpositional;

import br.org.beanpositional.bean.ConsigSiape;
import org.junit.Test;

public class ConsigSiapeTest {

    @Test
    public void lerPosicoes() {

        try {
            BeanPositional beanPositional = new BeanPositional();
            ConsigSiape consigSiape = beanPositional.parseFromFile(ConsigSiape.class, "etc/txt/2018-04-10.txt");
            System.out.println(consigSiape);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
