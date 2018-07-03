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
        System.out.println(tree.toStringTree(parser)); // printa árvore

        EvalVisitor eval = new EvalVisitor();
        if(eval.visit(tree)) {
            System.out.println("Autômato é AFND");
            Automato automato = new Automato(
                eval.getEstadoInicial(),
                eval.getSimbolos(),
                eval.getEstadosFinais(),
                eval.getObjEstados()
            );
            String inicial = automato.get_estado_inicial();
            if(automato.Percorre("abba$", automato.getEstado(inicial))){
                System.out.println("Palavra válida");
            } else {
                System.out.println("Palavra não válida");
            }

        } else {
            System.out.println("Autômato não é AFND");
        }
    }
}