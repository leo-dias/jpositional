package br.org.jpositional;

import br.org.jpositional.bean.ConsigSiape;
import br.org.jpositional.bean.Consignacao;
import br.org.jpositional.bean.Header;
import br.org.jpositional.bean.Trailer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsigSiapeTest {

    @Test
    public void lerPosicoes() {

        try {
            BeanPositional beanPositional = new BeanPositional();

//            ConsigSiape consigSiape = beanPositional.parseFromFile(ConsigSiape.class, "etc/txt/2018-04-10.txt");
//            System.out.println(consigSiape);


            Header header = new Header();
            header.setTipoRegistro("0");
            header.setCodigoSiape("12345");
            header.setMesPagamento("04");
            header.setAnoPagamento("2018");
            header.setNomeFantasia("Leo");
            header.setCnpjConsignataria("0987654321");
            header.setNomeArquivo("nome-arquiv");

            ConsigSiape consigSiape = new ConsigSiape();
            consigSiape.setHeader(header);

            Trailer trailer = new Trailer();
            trailer.setOrgaoSIAPE("OSIAP");
            trailer.setQuantidadeRegistros("1");
            trailer.setTipoRegistro("9");

            Consignacao consignacao1 = new Consignacao();
            consignacao1.setTipoRegistro("1");
            consignacao1.setAnoRubrica("2018");
            consignacao1.setComando("4");

            Consignacao consignacao2 = new Consignacao();
            consignacao2.setTipoRegistro("1");
            consignacao2.setAnoRubrica("2017");
            consignacao2.setComando("3");

            List<Consignacao> consignacaoList = new ArrayList<>();
            consignacaoList.add(consignacao1);
            consignacaoList.add(consignacao2);

            consigSiape.setConsignacaoList(consignacaoList);

            consigSiape.setTrailer(trailer);

            beanPositional.parseToFile(consigSiape, "C:\\Users\\n01316994\\from-obj.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
