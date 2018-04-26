package br.org.jpositional;

import br.org.jpositional.bean.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JPositionalTest {

    @Test
    public void readComplexPositionalFromFile() {
        try {
            BeanPositional beanPositional = new BeanPositional();
            MyRoot myRoot1 = beanPositional.parseFromFile(MyRoot.class, "etc/txt/2018-04-10.txt");
            Assert.assertNotNull(myRoot1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void writeComplexPositionalToFile() {
        try {
            BeanPositional beanPositional = new BeanPositional();
            MyRoot myRoot1 = beanPositional.parseFromFile(MyRoot.class, "etc/txt/2018-04-10.txt");
            beanPositional.parseToFile(myRoot1, "etc/txt/generated/from-obj.txt");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void readSimplePositionalFromFile() {
        try {
            BeanPositional beanPositional = new BeanPositional();
            MyBeanWithDate myBeanWithDate = beanPositional.parseFromFile(MyBeanWithDate.class, "etc/txt/simple-positional.txt");
            Assert.assertNotNull(myBeanWithDate);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void readManySimplePositionalFromFile() {
        try {
            BeanPositional beanPositional = new BeanPositional();
            List<MyBeanWithDate> myBeanWithDate = beanPositional.parseManyFromFile(MyBeanWithDate.class, "etc/txt/many-simple-positional.txt");
            Assert.assertEquals(4, myBeanWithDate.size());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void writeSimplePositionalToFile() {
        try {
            BeanPositional beanPositional = new BeanPositional();
            MyBeanWithDate myBeanWithDate = beanPositional.parseFromFile(MyBeanWithDate.class, "etc/txt/simple-positional.txt");
            beanPositional.parseToFile(myBeanWithDate, "etc/txt/generated/from-simple-positional.txt");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void writeMultipleSimplePositionalToFile() {
        try {
            BeanPositional beanPositional = new BeanPositional();
            List<MyBeanWithDate> myBeanWithDateList = beanPositional.parseManyFromFile(MyBeanWithDate.class, "etc/txt/many-simple-positional.txt");
            beanPositional.parseManyToFile(myBeanWithDateList, "etc/txt/generated/from-multiple-simple-positional.txt");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void writeSimplePositionFromNewBean() {
        try {

            MyBeanWithDate myBeanWithDate = new MyBeanWithDate();
            myBeanWithDate.setDesc("Teste 12345");
            myBeanWithDate.setDt(LocalDate.now());
            myBeanWithDate.setId(2);

            BeanPositional beanPositional = new BeanPositional();
            beanPositional.parseToFile(myBeanWithDate, "etc/txt/generated/from-simple-positional2.txt");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
