import java.util.ArrayList;
import java.util.List;

public class EvalVisitor extends AFNDBaseVisitor<Boolean> {

	// ArrayList dos estados
	private ArrayList<String> estados = new ArrayList<String>();
	// ArrayList dos simbolos
	private ArrayList<String> simbolos = new ArrayList<String>();
	// ArrayList de objetos Estado
	private ArrayList<Estado> objEstados = new ArrayList<Estado>();
	// String do estado inicial
	private String estadoInicial;
	// ArrayList dos estados finais
	private ArrayList<String> estadosFinais = new ArrayList<String>();
	// ArrayList dos erros coletados 
	private ArrayList<String> erros = new ArrayList<String>();
	// True se pelo menos uma regra tiver mais de um possível estado
	private Boolean maisDeUmEstado = false;

	public ArrayList<Estado> getObjEstados(){
		return objEstados;
	}

	public String getEstadoInicial(){
		return estadoInicial;
	}

	public ArrayList<String> getEstadosFinais(){
		return estadosFinais;
	}

	public ArrayList<String> getErros(){
		return erros;
	}

	@Override
	public Boolean visitPrintPro(AFNDParser.PrintProContext ctx) {

		Boolean bEstados = visit(ctx.estados()); // Estados
		Boolean bSimbolos = visit(ctx.simbolos()); // Símbolos
		Boolean bTransicao = visit(ctx.transicao()); // Transição
		Boolean bInicial = visit(ctx.inicial()); // Inicial
		Boolean bFinal = visit(ctx.finais()); // Final

		if(!maisDeUmEstado){
			erros.add("Alguma regra precisa possuir pelo menos dois possíveis estados");
		}

		return bEstados && bSimbolos && bTransicao && bInicial && bFinal && maisDeUmEstado;
	}
	
	@Override
	public Boolean visitPrintEst(AFNDParser.PrintEstContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.ESTADO().size(); i++){
			String strEstado = ctx.ESTADO(i).getText();
			if(!estados.contains(strEstado)){
				estados.add(strEstado);
				objEstados.add(new Estado(strEstado)); // Cria objeto Estado e adiciona no array
			} else {
				retorno = false;
				erros.add("Estado duplicado: " + strEstado);
			}
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintSim(AFNDParser.PrintSimContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.CHAR().size(); i++){
			String strChar = ctx.CHAR(i).getText();
			if(!simbolos.contains(strChar)){
				simbolos.add(strChar);
			} else {
				retorno = false;
				erros.add("Símbolo duplicado: " + strChar);
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
		List<String> estadosRegra = new ArrayList<String>();

		if(ctx.ESTADO().size() > 2) maisDeUmEstado = true;

		for(int i = 0; i < ctx.ESTADO().size(); i++){
			String strEstado = ctx.ESTADO(i).getText();
			if(!estados.contains(strEstado)){ // Se estado não existe no array de possíveis estados
				retorno = false;
				erros.add("Estado em regra não declarado: " + strEstado);
			} else if(i > 0) { // Ignora o primeiro estado
				if(estadosRegra.contains(strEstado)) { // Se estado já foi declarado no array de estados da regra
					retorno = false;
					erros.add("Estado em regra duplicado: " + strEstado);
				} else { // Se der tudo certo, adiciona no array de estados da regra
					estadosRegra.add(strEstado);
				}
			}
		}

		if(!simbolos.contains(ctx.CHAR().getText())){
			retorno = false;
			erros.add("Símbolo não declarado: " + ctx.CHAR().getText());
		}

		// Adiciona regra ao objeto Estado
		for(Estado estado : objEstados){
			if(estado.getNome().equals(ctx.ESTADO(0).getText())){
				estado.addTransicao(ctx.CHAR().getText(), estadosRegra);
			}
		}

		return retorno;
	}
	
	@Override
	public Boolean visitPrintIni(AFNDParser.PrintIniContext ctx) {
		Boolean retorno = true;
		if(!estados.contains(ctx.ESTADO().getText())){
			retorno = false;
			erros.add("Estado inicial não declarado: " + ctx.ESTADO().getText());
		} else {
			estadoInicial = ctx.ESTADO().getText();
		}
		return retorno;
	}
	
	@Override
	public Boolean visitPrintFin(AFNDParser.PrintFinContext ctx) {
		Boolean retorno = true;
		for(int i = 0; i < ctx.ESTADO().size(); i++){
			String strEstado = ctx.ESTADO(i).getText();
			if(!estados.contains(strEstado)){ // Se estado não existe no array de possíveis estados
				retorno = false;
				erros.add("Estado final não declarado: " + strEstado);
			} else {
				if(estadosFinais.contains(strEstado)) { // Se estado já foi declarado no array de estados finais
					retorno = false;
					erros.add("Estado final duplicado: " + strEstado);
				} else { // Se der tudo certo, adiciona no array de estados finais
					estadosFinais.add(strEstado);
					// Seta estado final
					for(Estado estado : objEstados){
						if(estado.getNome().equals(ctx.ESTADO(i).getText())){
							estado.setEstadoFinal(true);
						}
					}
				}
			}
		}
		return retorno;
	}
}
