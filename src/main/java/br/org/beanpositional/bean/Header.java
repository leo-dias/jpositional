package br.org.beanpositional.bean;

import br.org.beanpositional.Position;

public class Header {

    @Position(begin = 0, end = 1)
    private String tipoRegistro;

    @Position(begin = 1, end = 6)
    private String codigoSiape;

    @Position(begin = 6, end = 22)
    private String constanteZeros;

    @Position(begin = 22, end = 24)
    private String mesPagamento;

    @Position(begin = 24, end = 28)
    private String anoPagamento;

    @Position(begin = 28, end = 34)
    private String nomeFantasia;

    @Position(begin = 34, end = 49)
    private String espacoBranco;

    @Position(begin = 49, end = 63)
    private String cnpjConsignataria;

    @Position(begin = 63, end = 74)
    private String nomeArquivo;

    @Position(begin = 74, end = 127)
    private String filler;

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getCodigoSiape() {
        return codigoSiape;
    }

    public void setCodigoSiape(String codigoSiape) {
        this.codigoSiape = codigoSiape;
    }

    public String getConstanteZeros() {
        return constanteZeros;
    }

    public void setConstanteZeros(String constanteZeros) {
        this.constanteZeros = constanteZeros;
    }

    public String getMesPagamento() {
        return mesPagamento;
    }

    public void setMesPagamento(String mesPagamento) {
        this.mesPagamento = mesPagamento;
    }

    public String getAnoPagamento() {
        return anoPagamento;
    }

    public void setAnoPagamento(String anoPagamento) {
        this.anoPagamento = anoPagamento;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEspacoBranco() {
        return espacoBranco;
    }

    public void setEspacoBranco(String espacoBranco) {
        this.espacoBranco = espacoBranco;
    }

    public String getCnpjConsignataria() {
        return cnpjConsignataria;
    }

    public void setCnpjConsignataria(String cnpjConsignataria) {
        this.cnpjConsignataria = cnpjConsignataria;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }
}