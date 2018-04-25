# **JPositional**

JPositional is an open source Java Framework for marshalling and unmarshalling Java beans from
a positional file.

## Technologies
* JDK 1.8
* Maven

## Specifications
Sometimes we need to read and/or serialize positional files, and every time is a boring work to cut Strings 
and concatenate to generate or read these kind of files.
 
This open source project provide the easy way to read and serialize positional files, leaving developers 
focus only on business rules.

## Positional File Structure
Positional files are composed by:
* Header
* Details
* Trailer

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
    <version>1.0.0</version>
</dependency>
```
### Configuring Bean
To be able to generate or serialize positional files you need to configure four Java classes.
Below an example of Root class, Header class, Detail class and Trailer class:

### MyRoot
Is a Java class file that contains Header, Details and Trailer
```java
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
    private String id;
    
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
    private String id;
    
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
    private String id;
    
    @Line(begin = 1, end = 5, fill="0", direction = Direction.LEFT)
    private String amountRecord;
    
    @Line(begin = 5, end = 127, fill=" ", direction = Direction.RIGHT)
    private String filer;
}
```
```text
Observation:
The default direction attribute value is RIGHT, so you don't need to inform explicit. 
```

## Validations
JPositional give to you some validations, like:
### ValueSizeNotCorrectException
if an attribute is configured like below and the attribute there are not the correct size, JPositional throws ValueSizeNotCorrectException:
```java
    @Line(begin = 1, end = 20)
    private String companyName;
```
To fix this, you need to configure the fill attribute.
```java
    @Line(begin = 1, end = 20, fill=" ")
    private String companyName;
```
Now JPositional knows if the attribute is less than (end - beging), need to fill with black space.

### ValueTooLongException
if an attribute is configured like below and the attribute is receiving a String length greater than (end - begin), JPositional throws ValueTooLongException:
```java
    @Line(begin = 1, end = 20)
    private String companyName;
```
To fix this, you need to review your String.


## Author

* [Leonardo Dias de Oliveira](https://www.linkedin.com/in/leonardo-dias-oliveira/) - https://www.linkedin.com/in/leonardo-dias-oliveira/
