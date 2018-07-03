import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;


public class Estado{
	public String nome;
	public Map<String, List<String>> map_transicoes; //criar HASH MAP DE TRANSIÇOES
	boolean status;
	public Estado(String nome){
		this.nome = nome;
		this.map_transicoes = new LinkedHashMap<String, List<String>>();
		
	}

	public void add_estado(Estado estado){
		lobjeto_estados.add(estado);
	}


	//CRIAR HASHMAP DE TRANSIÇÕES
	public void add_transicoes(String transicao,List<String> destino>{
		//ver como adicionar
	}

	public void set_status(status){
		this.status = status;
	}

	public boolean get_status(){
		return this.status;
	}

	public 




}