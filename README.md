# **JPositional Framework**

JPositional Framework is an open source Java project for marshaling and unmarshalling Java beans from
a positional file.

## Technologies
* JDK 1.8
* Maven

## Specifications
Sometimes we need to read and/or serialize positional files, and every time is a boring work to cut Strings 
and concatenate to generate or read these kinds of files.

This open source project provides the easy way to read and serialize positional files, leaving developers to focus 
only on business rules.

## Positional File Structure
Positional files are composed by:
* Header
* Details
* Trailer

### Positional File Example

```text
0262710000000000000000042018ABCDEF               00676296000599LEONARDO                                                        
1262713343228032345251000000000000000000000000000000000000000000     8COMPULSORIA0000000000000000000000000000000000000000000000
1262711052967042345251000000000000000000000000000000000000000000     8000000000000000044431500080000000000000000000000000000000
92627199999999999999990000002                                                                                                  

``` 
where:
* first line is the header
* second and third lines are details
* last line is the trailer 

Always the first character will be the identification of the record type.

## How to use?
To use the JPositional in your maven project you need to add:
### Configure Repository URL 
```xml
<repositories>
    <repository>
        <id>jpositional-repo</id>
        <url>https://github.com/leo-dias/repo/raw/master</url>
    </repository>
</repositories>
```
### Add Dependency
```xml
<dependency>
    <groupId>jpositional</groupId>
    <artifactId>br.org.jpositional</artifactId>
    <version>1.0.3</version>
</dependency>
```
### Configuring Bean
To be able to generate or serialize positional files you need to configure four Java classes.
Below an example of Root class, Header class, Detail class and Trailer class:

### Complex Bean
Complex Bean is a bean with children as you can see below:
### MyRoot
Is a Java class file that contains Header, Details, and Trailer. 
This class has to be annotated as @ComplexPositional because has children.
```java
@ComplexPositional
public class MyRoot {

    @Header(identify = "0")
    private MyHeader myHeader;

    @Detail(identify = "1")
    private List<MyDetail> myDetailList;

    @Trailer(identify = "9")
    private MyTrailer myTrailer;
}
```

```text
Observation:
the identify attribute is the identification code for header, detail and trailer line
each one has unique identification.
```

### MyHeader
```java
public class MyHeader {

    @Line(begin = 0, end = 1)
    private String identify;
    
    @Line(begin = 1, end = 20, fill=" ", direction = Direction.RIGHT)
    private String companyName;
    
    @Line(begin = 20, end = 127, fill=" ", direction = Direction.RIGHT)
    private String filer;
}
```

### MyDetail
```java
public class MyDetail {

    @Line(begin = 0, end = 1)
    private String identify;
    
    @Line(begin = 1, end = 10, fill="0", direction = Direction.LEFT)
    private String totalReceived;
    
    @Line(begin = 10, end = 20, fill="0", direction = Direction.LEFT)
    private String totalSpent;
    
    @Line(begin = 20, end = 22)
    private String month;
    
    @Line(begin = 22, end = 26)
    private String year;
    
    @Line(begin = 26, end = 127, fill=" ", direction = Direction.RIGHT)
    private String filer;
}
```

### MyTrailer
```java
public class MyTrailer {

    @Line(begin = 0, end = 1)
    private String identify;
    
    @Line(begin = 1, end = 5, fill="0", direction = Direction.LEFT)
    private String amountRecord;
    
    @Line(begin = 5, end = 127, fill=" ", direction = Direction.RIGHT)
    private String filer;
}
```
```text
Observation:
The default direction attribute value is RIGHT, so you don't need to inform explicitly. 
```

### Simple Bean
Simple Bean is a bean with no children, has only records, and need to be annotated with @SimplePositional.

Below an example of simple positional file:
```text
120180426Abcd                 
220180326Efghij               
320180226Klmnopqrst           
420180326Uvxywz               
```
where:
* has only records

Below an example of Simple Bean:

```java
    @SimplePositional
    public class MySimpleBean {
        @Line(begin = 0, end = 1)
        private Integer id;
    
        @Line(begin = 1, end = 9)
        @DateFormatter(format = "yyyyMMdd")
        private LocalDate dt;
    
        @Line(begin = 9, end = 30, fill = " ")
        private String desc;
    }
```

## Wrapper Class
JPositional is prepared to work with wrappers classes.
It's not required any special configuration, JPositional identifies automatically.   

## Working with Date
JPositional Framework works with java.util.Date, java.time.LocalDate and java.time.LocalDateTime for date fields.
It's required to use @DateFormatter to convert to any date format.

## Validations
JPositional Framework gives you some validations, like:

### NoAnnotationConfiguredException
If you forgot to add @SimplePositional or @ComplexPositional, JPositional Framework will throw NoAnnotationConfiguredException.
To fix it, add one of the annotations that you will work on.

### ValueSizeNotCorrectException
If an attribute is configured like below and the attribute there are not the correct size, JPositional throws ValueSizeNotCorrectException:

Example:
You have mapped the companyName attribute to receive 20 characters.
```java
    @Line(begin = 1, end = 20)
    private String companyName;
```
And trying to put small text like:
```java
    String companyName = "Abc";
```
When you try to generate a positional file, JPositional will throw ValueSizeNotCorrectException.
To fix this, you need to configure the fill attribute.
```java
    @Line(begin = 1, end = 20, fill=" ")
    private String companyName;
```
Now JPositional knows if the attribute is less than (end - begin), need to fill with black space.

### ValueTooLongException
if an attribute is configured like below and the attribute is receiving a String length greater than (end - begin), JPositional throws ValueTooLongException:
```java
    @Line(begin = 1, end = 20)
    private String companyName;
```
To fix this, you need to review your String.

## Generating Positional File
Below an example of how to generate a positional file from a bean.
```java
    MyHeader myHeader = new MyHeader();
    myHeader.setIdentify("0");
    myHeader.setCompanyName("My Company");
    
    MyDetail myDetail1 = new MyDetail();
    myDetail1.setIdentify("1");
    myDetail1.setMonth("01");
    myDetail1.setYear("2018");
         
    MyDetail myDetail2 = new MyDetail();
    myDetail2.setIdentify("1");
    myDetail2.setMonth("02");
    myDetail2.setYear("2018");
    
    List<MyDetail> detailList = new ArrayList<>();
    detailList.add(myDetail1);
    detailList.add(myDetail2);
    
    Trailer trailer = new Trailer();
    trailer.setIdentify("9");
    trailer.setAmountRecord("2");
     
    MyRoot myRoot = new MyRoot();
    myRoot.setMyHeader(myheader);
    myRoot.setMyDetailList(detailList);
    myRoot.setMyTrailer(myTrailer);

    BeanPositional beanPositional = new BeanPositional();
    beanPositional.parseToFile(myRoot, "/home/user/my-positional-file.txt");
```

## Reading Positional File
Below an example of how to read a positional file.
```java
    BeanPositional beanPositional = new BeanPositional();
    MyRoot myRoot = beanPositional.parseFromFile(MyRoot.class, "/home/user/my-positional-file.txt");
```

## Author

* [Leonardo Dias de Oliveira](https://www.linkedin.com/in/leonardo-dias-oliveira/) - https://www.linkedin.com/in/leonardo-dias-oliveira/
