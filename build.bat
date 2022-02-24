javac -cp "lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar;lib/log4j-api-2.17.1.jar;lib/log4j-core-2.17.1.jar" *.java
java -cp .;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore EdgeConnectorTest
java RunEdgeConvert
