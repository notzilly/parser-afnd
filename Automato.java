import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;

public class Automato{
	public String estado_inicial;
	public ArrayList<String> lista_alfabeto;
	public ArrayList<String> estados_finais;
	public ArrayList<Estado> lobjeto_estados;


	public Automato(String estado_inicial, ArrayList<String> lista_alfabeto, ArrayList<String> estados_finais, ArrayList<Estado> lobjeto_estados){
		this.estado_inicial = estado_inicial;
		this.lista_alfabeto = lista_alfabeto;
		this.estados_finais = estados_finais;
		this.lobjeto_estados = lobjeto_estados;
	}

	public ArrayList<Estado> get_objeto_estados(){
		return lobjeto_estados;
	}

	public String get_estado_inicial(){
		return estado_inicial;
	}

	public Estado getEstado(String nome){
		for(Estado estado : this.lobjeto_estados){
			if(estado.getNome().equals(nome))
				return estado;
		}
		return null; // nunca entra aki rever
	}

	public Boolean Percorre(String input, Estado estado){
		if(input.equals("$") && estado.getEstadoFinal()){
			return true;
		}

		//passar somente a primeira letra e pegar todos as listas
		String caract = String.valueOf(input.charAt(0));
		List<String> l = estado.getLista(caract);

		//verificar se da certo a passagem de input
		if(l != null){
			for(int i = 0 ; i < l.size(); i++){
				Boolean variavel = Percorre(input.substring(1), getEstado(l.get(i)));
				if(variavel == true) return true; 
			}

		}
		return false;
	}



}

