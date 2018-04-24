package br.org.beanpositional.bean;

import br.org.beanpositional.Position;

public class Trailler {

    @Position(begin = 0, end = 1)
    private String tipoRegistro;

    @Position(begin = 1, end = 6)
    private String orgaoSIAPE;

    @Position(begin = 6, end = 22)
    private String constanteNoves;

    @Position(begin = 22, end = 29)
    private String quantidadeRegistros;

    @Position(begin = 29, end = 127)
    private String filler;

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getOrgaoSIAPE() {
        return orgaoSIAPE;
    }

    public void setOrgaoSIAPE(String orgaoSIAPE) {
        this.orgaoSIAPE = orgaoSIAPE;
    }

    public String getConstanteNoves() {
        return constanteNoves;
    }

    public void setConstanteNoves(String constanteNoves) {
        this.constanteNoves = constanteNoves;
    }

    public String getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(String quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }
}