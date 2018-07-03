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

	public void add_estado(String estado){
		lobjeto_estados.add(new Estado(estado));
	}

	public ArrayList<Estado> get_objeto_estados(){
		return lobjeto_estados;
	}

	public String get_estado_inicial(){
		return estado_inicial;
	}

	public Estado get_estado_nome(String atual){
		for(Estado estado : this.lobjeto_estados){
			if(estado.getNome().equals(atual))
				return estado;
		}
		return (new Estado ("erro")); // nunca entra aki rever
	}

	public Boolean Percorre(String input, Estado estado){
		if(input.equals("$") && estado.getEstadoFinal() == true){
			return true;
		}

		//passar somente a primeira letra e pegar todos as listas
		String caract = String.valueOf(input.charAt(0));
		List<String> l = estado.getLista(caract);

		//verificar se da certo a passagem de input
		if(l != null){
			for(int i = 0 ; i < l.size(); i++){
				Boolean variavel = Percorre( input.substring(1), get_estado_nome(l.get(i)));
				if(variavel == true) return true; 
			}

		}
		return false;
	}



}

