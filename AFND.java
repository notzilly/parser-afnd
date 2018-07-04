import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

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
        //System.out.println(tree.toStringTree(parser)); // printa árvore

        EvalVisitor eval = new EvalVisitor();

        // Se autômato é validado
        if(eval.visit(tree)) {
            System.out.println("Autômato é AFND");

            // Instância do autômato
            Automato automato = new Automato(
                eval.getEstadoInicial(),
                eval.getEstadosFinais(),
                eval.getObjEstados()
            );

            if(automato.run("abbbababba")){
                automato.printCaminho();
                System.out.println("Palavra válida");
            } else {
                automato.printCaminho();
                System.out.println("Palavra não válida");
            }

        } else {
            System.out.println("Autômato não é AFND");
            // Printa erros
            for(String erro : eval.getErros()){
                System.out.println(erro);
            }
        }
    }
}