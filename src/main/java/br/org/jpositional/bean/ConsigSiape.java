package br.org.jpositional.bean;

import br.org.jpositional.HeaderPosition;
import br.org.jpositional.BodyPosition;
import br.org.jpositional.TrailerPosition;

import java.util.List;

public class ConsigSiape {

    @HeaderPosition(identify = "0")
    private Header header;

    @BodyPosition(identify = "1")
    private List<Consignacao> consignacaoList;

    @TrailerPosition(identify = "9")
    private Trailer trailer;

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

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }
}