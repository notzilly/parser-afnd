public class EvalVisitor extends AFNDBaseVisitor<String> {

	@Override public String visitPrintPro(AFNDParser.PrintProContext ctx) { return visitChildren(ctx); }
	
	@Override public String visitPrintEst(AFNDParser.PrintEstContext ctx) { return visitChildren(ctx); }
	
	@Override public String visitPrintSim(AFNDParser.PrintSimContext ctx) { return visitChildren(ctx); }
	
	@Override public String visitPrintTra(AFNDParser.PrintTraContext ctx) { return visitChildren(ctx); }
	
	@Override public String visitPrintReg(AFNDParser.PrintRegContext ctx) { return visitChildren(ctx); }
	
	@Override public String visitPrintIni(AFNDParser.PrintIniContext ctx) { return visitChildren(ctx); }
	
	@Override public String visitPrintFin(AFNDParser.PrintFinContext ctx) { return visitChildren(ctx); }
}
