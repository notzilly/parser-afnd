import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Automato{
	public ArrayList<String> lista_estados;
	public String estado_inicial;
	public ArrayList<String> lista_alfabeto;
	public ArrayList<String> estados_finais;
	public ArrayList<Estados> lobjeto_estados;


	public Automato(ArrayList<String> lista_estados,String estado_inicial,ArrayList<String> lista_alfabeto,ArrayList<String> estados_finais,HashMap<String, String>hmap_transicoes){
		this.lista_estados = lista_estados;
		this.estado_inicial = estado_inicial;
		this.lista_alfabeto = lista_alfabeto;
		this.estados_finais = estados_finais;
		this.hmap_transicoes = hmap_transicoes;
	}

	public void add_estado(String estado){
		lobjeto_estados.add(Estado(estado));
	}






}

