## Interpretador para AFNDs

##Francisco Tassinari Fajardo e Luis Henrique Medeiros

#### Instalação e utilização

OBS.: Necessário ter antlr4 e java instalado e configurado para se usar os comandos.

```shell
git clone https://github.com/notzilly/parser-afnd.git // Clonar repo
cd parser-afnd                                        // Entrar na pasta
antlr4 -visitor AFND.g4                               // Gerar listeners e visitor do AFND 
javac *.java                                          // Compilar arquivos .java
java AFND entrada.txt                                 // Rodar classe AFND com arquivo de especificação
```
#### Verificação de erros

Verificação de erros através da Classe EvalVisitor.java:

* Estados:
	* Verificamos se os estados não estão repetidos;
	* ~~Verificamos se algum estado declarado não está sendo utilizado.~~
    
* Alfabeto: 
	* Verificamos se os elementos do alfabeto não estão repetidos;

* Transições:
	* Verificamos se os estados na transição, tanto do lado esquerdo como direito da regra de transição, existem nos estados já declarados;
	* Verificamos se o elemento do alfabeto da transição existe no alfabeto já declarado;
	* Verificamos se as regras de transições realmente expressam um AFND, onde pelo menos um estado deve transitar para pelo menos dois estados;
	* Vericamos repetição do lado direito;
	* ~~Transiçoẽs duplicadas.~~

* Estado inicial:
	* Verificamos se o estado existe nos estados declarados;

* Estados finais: 
	* Verificamos se o estado existe nos estados declarados;
	* Verificamos repetição;


### Implementação do AFND

O automato foi implementado na classe Automato.java, 
e usa a função chamada percorre para avaliar recursivamente todas as possibilidades de transições de estados, 
utiliza-se um '$' para saber o fim da entrada e a classe Estado.java que contém os dados de cada estado.



