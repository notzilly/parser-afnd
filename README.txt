-Necessário ter antlr4 e java instalado e configurado para se usar os comandos.

-antlr4 -no-listener -visitor AFND.g4   
-javac *.java 						 		
-java AFND entrada.txt      			





Verificação de erros atraves da Classe EvalVisitor.java:


-Para estados:
	-Verificamos se os estados não estão repetidos;
	#-Verificamos se algum estado declarado não está sendo utilizado;
Para alfbeto: 
	-Verificamos se os elementos do alfabeto não estão repetidos;

- Para transições:
	-Verificamos se os estados na transição(tanto do lado esquerdo como direito da regra de transição) existem nos estados já declarados;
	-Verificamos se o elemento do alfabeto da transição existe no alfabeto já declarado;
	-Verificamos se as regras de transições realmente expressam um AFND, um elemento transita para mais de um estado
	-Vericamos repetição do lado direito;
	#-Transiçoẽs duplicadas;

- Para estado inicial:
	-Verificamos se o estado existe nos estados declarados;

- Para estado final: 
	-Verificamos se o estado existe nos estados declarados;
	-Verificamos repetição;


