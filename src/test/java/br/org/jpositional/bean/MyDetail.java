package br.org.jpositional.bean;

import br.org.jpositional.Line;

public class MyDetail {

    @Line(begin = 0, end = 1)
    private String tipoRegistro;

    @Line(begin = 1, end = 6)
    private String codigoOrgaoSIAPE;

    @Line(begin = 6, end = 13)
    private String matricula;

    @Line(begin = 13, end = 14)
    private String dvMatricula;

    @Line(begin = 14, end = 15)
    private String comando;

    @Line(begin = 15, end = 16)
    private String indicador;

    @Line(begin = 16, end = 21)
    private String rubrica;

    @Line(begin = 21, end = 33)
    private String valor;

    @Line(begin = 33, end = 36)
    private String prazo;

    @Line(begin = 36, end = 44)
    private String numeroOrigem;

    @Line(begin = 44, end = 46)
    private String mesRubrica;

    @Line(begin = 46, end = 50)
    private String anoRubrica;

    @Line(begin = 50, end = 58)
    private String dataAntiguidade;

    @Line(begin = 58, end = 64)
    private String horaMinutoSegundoAntiguidade;

    @Line(begin = 64, end = 69)
    private String filler;

    @Line(begin = 69, end = 70)
    private String movFina;

    @Line(begin = 70, end = 90)
    private String numeroContrato;

    @Line(begin = 90, end = 92)
    private String assuntoCalculoParametrizado;

    @Line(begin = 92, end = 97)
    private String percentual;

    @Line(begin = 97, end = 102)
    private String rubricaCalculo1;

    @Line(begin = 102, end = 107)
    private String rubricaCalculo2;

    @Line(begin = 107, end = 112)
    private String rubricaCalculo3;

    @Line(begin = 112, end = 117)
    private String rubricaCalculo4;

    @Line(begin = 117, end = 122)
    private String rubricaCalculo5;

    @Line(begin = 122, end = 127)
    private String rubricaCalculo6;

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getCodigoOrgaoSIAPE() {
        return codigoOrgaoSIAPE;
    }

    public void setCodigoOrgaoSIAPE(String codigoOrgaoSIAPE) {
        this.codigoOrgaoSIAPE = codigoOrgaoSIAPE;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDvMatricula() {
        return dvMatricula;
    }

    public void setDvMatricula(String dvMatricula) {
        this.dvMatricula = dvMatricula;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getNumeroOrigem() {
        return numeroOrigem;
    }

    public void setNumeroOrigem(String numeroOrigem) {
        this.numeroOrigem = numeroOrigem;
    }

    public String getMesRubrica() {
        return mesRubrica;
    }

    public void setMesRubrica(String mesRubrica) {
        this.mesRubrica = mesRubrica;
    }

    public String getAnoRubrica() {
        return anoRubrica;
    }

    public void setAnoRubrica(String anoRubrica) {
        this.anoRubrica = anoRubrica;
    }

    public String getDataAntiguidade() {
        return dataAntiguidade;
    }

    public void setDataAntiguidade(String dataAntiguidade) {
        this.dataAntiguidade = dataAntiguidade;
    }

    public String getHoraMinutoSegundoAntiguidade() {
        return horaMinutoSegundoAntiguidade;
    }

    public void setHoraMinutoSegundoAntiguidade(String horaMinutoSegundoAntiguidade) {
        this.horaMinutoSegundoAntiguidade = horaMinutoSegundoAntiguidade;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getMovFina() {
        return movFina;
    }

    public void setMovFina(String movFina) {
        this.movFina = movFina;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getAssuntoCalculoParametrizado() {
        return assuntoCalculoParametrizado;
    }

    public void setAssuntoCalculoParametrizado(String assuntoCalculoParametrizado) {
        this.assuntoCalculoParametrizado = assuntoCalculoParametrizado;
    }

    public String getPercentual() {
        return percentual;
    }

    public void setPercentual(String percentual) {
        this.percentual = percentual;
    }

    public String getRubricaCalculo1() {
        return rubricaCalculo1;
    }

    public void setRubricaCalculo1(String rubricaCalculo1) {
        this.rubricaCalculo1 = rubricaCalculo1;
    }

    public String getRubricaCalculo2() {
        return rubricaCalculo2;
    }

    public void setRubricaCalculo2(String rubricaCalculo2) {
        this.rubricaCalculo2 = rubricaCalculo2;
    }

    public String getRubricaCalculo3() {
        return rubricaCalculo3;
    }

    public void setRubricaCalculo3(String rubricaCalculo3) {
        this.rubricaCalculo3 = rubricaCalculo3;
    }

    public String getRubricaCalculo4() {
        return rubricaCalculo4;
    }

    public void setRubricaCalculo4(String rubricaCalculo4) {
        this.rubricaCalculo4 = rubricaCalculo4;
    }

    public String getRubricaCalculo5() {
        return rubricaCalculo5;
    }

    public void setRubricaCalculo5(String rubricaCalculo5) {
        this.rubricaCalculo5 = rubricaCalculo5;
    }

    public String getRubricaCalculo6() {
        return rubricaCalculo6;
    }

    public void setRubricaCalculo6(String rubricaCalculo6) {
        this.rubricaCalculo6 = rubricaCalculo6;
    }
}