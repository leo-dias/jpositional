package br.org.jpositional.bean;

import br.org.jpositional.Direction;
import br.org.jpositional.LinePosition;

public class Header {

    @LinePosition(begin = 0, end = 1)
    private String tipoRegistro;

    @LinePosition(begin = 1, end = 6)
    private String codigoSiape;

    @LinePosition(begin = 6, end = 22, fill = "0")
    private String constanteZeros;

    @LinePosition(begin = 22, end = 24)
    private String mesPagamento;

    @LinePosition(begin = 24, end = 28)
    private String anoPagamento;

    @LinePosition(begin = 28, end = 34, fill = " ")
    private String nomeFantasia;

    @LinePosition(begin = 34, end = 49, fill = " ")
    private String espacoBranco;

    @LinePosition(begin = 49, end = 63, fill = "0", direction = Direction.LEFT)
    private String cnpjConsignataria;

    @LinePosition(begin = 63, end = 74)
    private String nomeArquivo;

    @LinePosition(begin = 74, end = 127, fill = " ")
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