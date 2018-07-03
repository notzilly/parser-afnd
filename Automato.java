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


	public Automato(String estado_inicial,ArrayList<String> lista_alfabeto,ArrayList<String> estados_finais){
		this.estado_inicial = estado_inicial;
		this.lista_alfabeto = lista_alfabeto;
		this.estados_finais = estados_finais;
	}

	public void add_estado(String estado){
		lobjeto_estados.add(Estado(estado));
	}

	public ArrayList<Estado> get_objeto_estados(){
		return  lobjeto_estados;
	}

	public  String get_estado_inicial(){
		return estado_inicial;
	}

	public Estado get_estado_nome(String atual){
		for(Estado estado : this.lobjeto_estados){
			if(estado.getNome().equals(atual))
				return estado;
		}

	}

	public Boolean Percorre(String imput , Estado estado ){
		if(imput.equals("$") && estado.get_est_final() == true)
			return true;

		List<String> l = new List<String>();
		//passar somente a primeira letra e pegar todos as listas
		l = estado.get_lista(imput[0]);

		//verificar se da certo a passagem de imput
		if(l != null){
			for(int i = 0 ; i < l.size(); i++){
				Boolean variavel = Percorre( imput.substring(1), get_estado_nome(l[i]));
				if(variavel == true) return true; 
			}

		}
		return false;
	}



}

