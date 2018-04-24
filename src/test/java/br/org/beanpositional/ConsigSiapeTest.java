package br.org.beanpositional;

import br.org.beanpositional.bean.ConsigSiape;
import br.org.beanpositional.bean.Header;
import org.junit.Test;

public class ConsigSiapeTest {

    @Test
    public void lerPosicoes() {

        try {
            BeanPositional beanPositional = new BeanPositional();
//            ConsigSiape consigSiape = beanPositional.parseFromFile(ConsigSiape.class, "etc/txt/2018-04-10.txt");
//            System.out.println(consigSiape);

            ConsigSiape consigSiape = new ConsigSiape();
            Header header = new Header();
            header.setTipoRegistro("0");
            consigSiape.setHeader(header);
            beanPositional.parseToFile(consigSiape, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
