.PHONY: build test run clean

build:
	javac -d ./build -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar src/*/java/*.java
test:
	java -cp build:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore EdgeConnectorTest
run:
	java -cp ./build RunEdgeConvert
clean:
	rm -f *.class
	find . -name \*.class -exec rm {} \;
