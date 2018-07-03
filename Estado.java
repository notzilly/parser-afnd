import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;

public class Estado{

	private String nome;
	private Map<String, List<String>> mapTransicoes;
	private boolean est_final = false;

	public Estado(String nome){
		this.nome = nome;
		this.mapTransicoes = new LinkedHashMap<String, List<String>>();
	}

	public void set_est_final(boolean value){
		this.est_final = value;

	}

	public void get_est_final(){
		return est_final;
	}


	//metodo para pegar a lista do hash map
	public void get_lista(){


	}

	//certo??
	public void addTransicao(String transicao, List<String> destino){
		this.mapTransicoes.put(transicao, destino);
	}

	@Override
	public String toString() {
		String retorno = "";
		for(String chave : mapTransicoes.keySet()){
			retorno = retorno.concat("(" + nome + ", " + chave + "): " + mapTransicoes.get(chave).toString());
		}
		return retorno;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
	}



	

}