import java.util.ArrayList;

public class EvalVisitor extends AFNDBaseVisitor<Boolean> {

	// ArrayList dos estados
	ArrayList<String> estados = new ArrayList<String>();

	// ArrayList dos simbolos
	ArrayList<String> simbolos = new ArrayList<String>();

	// ArrayList dos erros coletados 
	ArrayList<String> erros = new ArrayList<String>();

	// True se pelo menos uma regra tiver mais de um possível estado
	Boolean maisDeUmEstado = false;

	@Override
	public Boolean visitPrintPro(AFNDParser.PrintProContext ctx) {

		Boolean value;

		value = visit(ctx.estados()); // Estados
        System.out.println("Estados: " + value);

		value = visit(ctx.simbolos()); // Símbolos
        System.out.println("Símbolos: " + value);

		value = visit(ctx.transicao()); // Transição
		System.out.println("Transição: " + value);

		value = visit(ctx.inicial()); // Inicial
		System.out.println("Inicial: " + value);

		value = visit(ctx.finais()); // Final
		System.out.println("Final: " + value);

		System.out.println(estados);
		System.out.println(simbolos);
		System.out.println("É AFND? " + maisDeUmEstado);

		// Printa erros
		for(int i = 0; i < erros.size(); i++){
			System.out.println(erros.get(i));
		}


		return visitChildren(ctx);
	}
	
	@Override
	public Boolean visitPrintEst(AFNDParser.PrintEstContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.ESTADO().size(); i++){
			if(!estados.contains(ctx.ESTADO(i).getText())){
				estados.add(ctx.ESTADO(i).getText());
			} else {
				retorno = false;
				erros.add("Estado duplicado: " + ctx.ESTADO(i).getText());
			}
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintSim(AFNDParser.PrintSimContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.CHAR().size(); i++){
			if(!simbolos.contains(ctx.CHAR(i).getText())){
				simbolos.add(ctx.CHAR(i).getText());
			} else {
				retorno = false;
				erros.add("Símbolo duplicado: " + ctx.CHAR(i).getText());
			}
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintTra(AFNDParser.PrintTraContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.regra().size(); i++){
			if(!visit(ctx.regra(i))) retorno = false;
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintReg(AFNDParser.PrintRegContext ctx) {
		Boolean retorno = true;

		if(ctx.ESTADO().size() > 2){
			maisDeUmEstado = true;
		}

		for(int i = 0; i < ctx.ESTADO().size(); i++){
			if(!estados.contains(ctx.ESTADO(i).getText())){
				retorno = false;
				erros.add("Estado em regra não declarado: " + ctx.ESTADO(i).getText());
			}
		}
		if(!simbolos.contains(ctx.CHAR().getText())){
			retorno = false;
			erros.add("Símbolo não declarado: " + ctx.CHAR().getText());
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintIni(AFNDParser.PrintIniContext ctx) {
		Boolean retorno = true;
		if(!estados.contains(ctx.ESTADO().getText())){
			retorno = false;
			erros.add("Estado inicial não declarado: " + ctx.ESTADO().getText());
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintFin(AFNDParser.PrintFinContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.ESTADO().size(); i++){
			if(!estados.contains(ctx.ESTADO(i).getText())){
				retorno = false;
				erros.add("Estado final não declarado: " + ctx.ESTADO(i).getText());
			}
		}
		return retorno;
	}
}
