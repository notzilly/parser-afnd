import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Automato{
	public String estadoInicial;
	public ArrayList<String> estadosFinais;
	public ArrayList<Estado> estados;
	public ArrayList<String> caminho;

	public Automato(String estadoInicial, ArrayList<String> estadosFinais, ArrayList<Estado> estados){
		this.estadoInicial = estadoInicial;
		this.estadosFinais = estadosFinais;
		this.estados = estados;
		this.caminho = new ArrayList<String>();
	}

	public void printCaminho(){
		for(int i = 0; i < caminho.size(); i++){
			System.out.println(caminho.get(i));
		}
	}

	public ArrayList<Estado> getEstados(){
		return estados;
	}

	public Estado getEstado(String nome){
		for(Estado estado : this.estados){
			if(estado.getNome().equals(nome))
				return estado;
		}
		return null;
	}

	public void resetaCaminho(){
		this.caminho = new ArrayList<String>();
	}

	public Boolean run(String input){
		return percorre(input + "$", getEstado(estadoInicial));
	}

	private Boolean percorre(String input, Estado estado){
		// Se caractere for o final da string ($) e o estado for final
		if(input.equals("$") && estado.getEstadoFinal()){
			caminho.add(estado.getNome() + " é final e recebeu $ | Fim");
			return true;
		}

		// Pega primeiro caractere		
		String caract = String.valueOf(input.charAt(0));

		// Verifica se há regra para o primeiro caractere
		List<String> l = estado.getLista(caract);

		// Se a regra existir, chama percorre com a string
		// a partir do proximo caractere para cada estado
		if(l != null){
			for(String destino : l){
				caminho.add(estado.getNome() + " recebeu " + caract + " -> " + destino + " | Resta " + input.substring(1));
				if(percorre(input.substring(1), getEstado(destino))){
					return true;
				}
			}
		} else {
			caminho.add(estado.getNome() + " não achou regra para " + caract);
		}
		return false;
	}

}
