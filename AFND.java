import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

import java.util.Scanner;

public class AFND {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        AFNDLexer lexer = new AFNDLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AFNDParser parser = new AFNDParser(tokens);

        ParseTree tree = parser.prog(); // parse
        EvalVisitor eval = new EvalVisitor();

        // Se autômato é validado
        if(eval.visit(tree)) {
            System.out.println("Autômato é um AFND válido");

            // Instância do autômato
            Automato automato = new Automato(
                eval.getEstadoInicial(),
                eval.getEstadosFinais(),
                eval.getObjEstados()
            );

            // Loop de entrada
            while(true){
                System.out.println("Digite uma palavra:");
                Scanner entrada = new Scanner(System.in);
                
                if(automato.run(entrada.next())){
                    automato.printCaminho();
                    System.out.println("Palavra válida");
                } else {
                    automato.printCaminho();
                    System.out.println("Palavra não válida");
                }

                automato.resetaCaminho();

            }

        } else {
            System.out.println("Autômato não é um AFND válido");
            // Printa erros
            for(String erro : eval.getErros()){
                System.out.println(erro);
            }
        }
    }
}