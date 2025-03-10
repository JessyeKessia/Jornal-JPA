package appconsole;

/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/



import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Assunto;
import modelo.Noticia;
import regras_negocio.Fachada;


public class Consultar {
	protected EntityManager manager;

	public Consultar(){

		try {
			Fachada.inicializar();
			
			System.out.println("----- Listar noticias com base na data 19/02/2025 -----");
			List<Noticia> noticias = Fachada.consultarNoticiaPorData("19/02/2025");
			for (Noticia n : noticias)
			System.out.println(n);
			
			
			System.out.println("\n----- Listar noticias com base em um assunto de nome N = Economia -----");
			List<Noticia> noticias2 = Fachada.consultarNoticiasPorAssunto("economia");
			for (Noticia n : noticias2)
			    System.out.println(n);
			
			System.out.println("\n----- Listar noticias com base na quantidade N > 3 de comentários -----");
			List<Noticia> noticias3 = Fachada.consultarNoticiaPorNumComent(2);		
			for (Noticia n : noticias3) {
			    System.out.println(n);
			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim da aplicacao");
	}


	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}