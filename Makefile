all: run

clean:
	rm -f src/*.class out/Fermat.jar

Fermat.jar: out/parcs.jar src/*.java
	@javac -cp out/parcs.jar src/*.java
	@jar cf out/Fermat.jar -C src .
	@rm -f src/*.class

run: Fermat.jar
	@cd out && java -cp 'parcs.jar:Fermat.jar' Fermat