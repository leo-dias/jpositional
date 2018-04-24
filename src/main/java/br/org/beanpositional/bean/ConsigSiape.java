package br.org.beanpositional.bean;

import br.org.beanpositional.HeaderPosition;
import br.org.beanpositional.BodyPosition;
import br.org.beanpositional.TraillerPosition;

import java.util.List;

public class ConsigSiape {

    @HeaderPosition(type = "0")
    private Header header;

    @BodyPosition(type = "1")
    private List<Consignacao> consignacaoList;

    @TraillerPosition(type = "9")
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