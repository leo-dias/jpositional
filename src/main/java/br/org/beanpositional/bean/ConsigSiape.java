package br.org.beanpositional.bean;

import br.org.beanpositional.BeanFieldPositional;
import br.org.beanpositional.BeanFieldPositionalList;

import java.util.List;

public class ConsigSiape {

    @BeanFieldPositional(type = "0")
    private Header header;

    @BeanFieldPositionalList(type = "1")
    private List<Consignacao> consignacaoList;

    @BeanFieldPositional(type = "9")
    private Trailler trailler;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Consignacao> getConsignacaoList() {
        return consignacaoList;
    }

    public void setConsignacaoList(List<Consignacao> consignacaoList) {
        this.consignacaoList = consignacaoList;
    }

    public Trailler getTrailler() {
        return trailler;
    }

    public void setTrailler(Trailler trailler) {
        this.trailler = trailler;
    }
}