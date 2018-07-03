import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;

public class Estado{

	private String nome;
	private Map<String, List<String>> mapTransicoes;
	private Boolean estadoFinal = false;

	public Estado(String nome){
		this.nome = nome;
		this.mapTransicoes = new LinkedHashMap<String, List<String>>();
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
	}

	public void setEstadoFinal(Boolean value){
		this.estadoFinal = value;
	}

	public Boolean getEstadoFinal(){
		return estadoFinal;
	}


	//metodo para pegar a lista do hash map
	public List<String> getLista(String caract){
		return mapTransicoes.get(caract);
	}

	//certo??
	public void addTransicao(String transicao, List<String> destino){
		this.mapTransicoes.put(transicao, destino);
	}

	@Override
	public String toString() {
		String retorno = "";
		if(estadoFinal) retorno = retorno.concat("Final: ");
		for(String chave : mapTransicoes.keySet()){
			retorno = retorno.concat("(" + nome + ", " + chave + "): " + mapTransicoes.get(chave).toString());
		}
		return retorno;
	}

}