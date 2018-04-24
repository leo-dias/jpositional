package br.org.beanpositional.bean;

import br.org.beanpositional.BeanFieldPositional;
import br.org.beanpositional.BeanRootPositional;

@BeanRootPositional(className = "br.org.beanpositional.bean.ConsigSiape")
public class ConsigSiape {

    @BeanFieldPositional(className = "br.org.beanpositional.bean.Header")
    private Header header;

    @BeanFieldPositional(className = "br.org.beanpositional.bean.Consignacao")
    private Consignacao consignacao;

    @BeanFieldPositional(className = "br.org.beanpositional.bean.Trailler")
    private Trailler trailler;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Consignacao getConsignacao() {
        return consignacao;
    }

    public void setConsignacao(Consignacao consignacao) {
        this.consignacao = consignacao;
    }

    public Trailler getTrailler() {
        return trailler;
    }

    public void setTrailler(Trailler trailler) {
        this.trailler = trailler;
    }
}