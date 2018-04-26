package br.org.jpositional.bean;

import br.org.jpositional.annotation.Line;
import br.org.jpositional.annotation.SimplePositional;
import br.org.jpositional.annotation.decorator.DateFormatter;

import java.time.LocalDate;
import java.util.Date;

@SimplePositional
public class MyBeanWithDate {
    // 120180426Teste com isso

    @Line(begin = 0, end = 1)
    private Integer id;

    @Line(begin = 1, end = 9)
    @DateFormatter(format = "yyyyMMdd")
    private LocalDate dt;

    @Line(begin = 9, end = 30, fill = " ")
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
