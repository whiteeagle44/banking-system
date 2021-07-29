# Banking system

## About
A command line application that allows to create accounts, deposit
and transfer money. Data from each session is saved in a database.

### Features
* generate a card number that is [syntactically valid](https://cs50.harvard.edu/x/2021/psets/1/credit/#luhns-algorithm)
* deposit money into an account
* transfer money to another account in a database
* close an account
* store data after closing the program

### Technology
* Java 
* Gradle
* SQLite (via JDBC)

### Skills gained
* SQL
* Communicate with a database from a Java program
* Use a Data Access Object ([DAO](https://www.baeldung.com/java-dao-pattern)) structural pattern

### Further development ideas
* Use Java Persistance API and Hibernate with [Spring Data JPA](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa).