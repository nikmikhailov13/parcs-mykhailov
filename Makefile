all: run

clean:
	rm -f src/main/*.class out/Fermat.jar

Fermat.jar: out/parcs.jar src/main/*.java
	@javac -cp out/parcs.jar src/main/*.java
	@jar cf out/Fermat.jar -C src/main .
	@rm -f src/main/*.class

run: Fermat.jar
	@cd out && java -cp 'parcs.jar:Fermat.jar' Fermat
