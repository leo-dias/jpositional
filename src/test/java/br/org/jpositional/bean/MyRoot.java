package br.org.jpositional.bean;

import br.org.jpositional.annotation.ComplexPositional;
import br.org.jpositional.annotation.Detail;
import br.org.jpositional.annotation.Header;
import br.org.jpositional.annotation.Trailer;

import java.util.List;

@ComplexPositional
public class MyRoot {

    @Header(identify = "0")
    private MyHeader myHeader;

    @Detail(identify = "1")
    private List<MyDetail> myDetailList;

    @Trailer(identify = "9")
    private MyTrailer myTrailer;

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public void setMyHeader(MyHeader myHeader) {
        this.myHeader = myHeader;
    }

    public List<MyDetail> getMyDetailList() {
        return myDetailList;
    }

    public void setMyDetailList(List<MyDetail> myDetailList) {
        this.myDetailList = myDetailList;
    }

    public MyTrailer getMyTrailer() {
        return myTrailer;
    }

    public void setMyTrailer(MyTrailer myTrailer) {
        this.myTrailer = myTrailer;
    }
}