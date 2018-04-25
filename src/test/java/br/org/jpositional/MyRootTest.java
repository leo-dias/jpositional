package br.org.jpositional;

import br.org.jpositional.bean.MyRoot;
import br.org.jpositional.bean.MyDetail;
import br.org.jpositional.bean.MyHeader;
import br.org.jpositional.bean.MyTrailer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyRootTest {

    @Test
    public void lerPosicoes() {

        try {
            BeanPositional beanPositional = new BeanPositional();

            MyRoot myRoot1 = beanPositional.parseFromFile(MyRoot.class, "etc/txt/2018-04-10.txt");
            System.out.println(myRoot1);


            MyHeader myHeader = new MyHeader();
            myHeader.setTipoRegistro("0");
            myHeader.setCodigoSiape("12345");
            myHeader.setMesPagamento("04");
            myHeader.setAnoPagamento("2018");
            myHeader.setNomeFantasia("Leo");
            myHeader.setCnpjConsignataria("0987654321");
            myHeader.setNomeArquivo("nome-arquiv");

            MyRoot myRoot2 = new MyRoot();
            myRoot2.setMyHeader(myHeader);

            MyTrailer myTrailer = new MyTrailer();
            myTrailer.setOrgaoSIAPE("OSIAP");
            myTrailer.setQuantidadeRegistros("1");
            myTrailer.setTipoRegistro("9");

            MyDetail myDetail1 = new MyDetail();
            myDetail1.setTipoRegistro("1");
            myDetail1.setAnoRubrica("2018");
            myDetail1.setComando("4");

            MyDetail myDetail2 = new MyDetail();
            myDetail2.setTipoRegistro("1");
            myDetail2.setAnoRubrica("2017");
            myDetail2.setComando("3");

            List<MyDetail> myDetailList = new ArrayList<>();
            myDetailList.add(myDetail1);
            myDetailList.add(myDetail2);

            myRoot2.setMyDetailList(myDetailList);

            myRoot2.setMyTrailer(myTrailer);

            beanPositional.parseToFile(myRoot2, "C:\\Users\\n01316994\\from-obj.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
