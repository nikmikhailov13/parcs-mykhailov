all: run

clean:
	rm -f src/main/java/*.class out/Fermat.jar

Fermat.jar: out/parcs.jar src/main/java/*.java
	@javac -cp out/parcs.jar src/main/java/*.java
	@jar cf out/Fermat.jar -C src/main/java .
	@rm -f src/main/java/*.class

run: Fermat.jar
	@cd out && java -cp 'parcs.jar:Fermat.jar' Fermat
