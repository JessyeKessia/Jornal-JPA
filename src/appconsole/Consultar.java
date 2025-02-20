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
			manager = Util.conectarBanco();
			
			TypedQuery<Noticia> query1;
			TypedQuery<Assunto> query2;
			List<Noticia> noticias;
			List<Assunto> assuntos;
			String jpql;
			
			
			System.out.println("----- Listar noticias com base na data X -----");
			jpql = "select n from Noticia n where n.data = '2025-02-19'";
			query1 = manager.createQuery(jpql, Noticia.class);
			noticias = query1.getResultList();
			for (Noticia n : noticias)
			System.out.println(n);
			
			
			System.out.println("----- Listar noticias com base em um assunto de nome N -----");
			jpql = "select n from Noticia n join n.assuntos a where a.nome = 'Economia'";
			query1 = manager.createQuery(jpql, Noticia.class);
			noticias = query1.getResultList();
			for (Noticia n : noticias)
			    System.out.println(n);
			
			System.out.println("----- Listar noticias com base na quantidade N de comentários -----");
			jpql = "SELECT n FROM Noticia n";
			query1 = manager.createQuery(jpql, Noticia.class);
			noticias = query1.getResultList();

			int quantidadeComentarios = 1; 
			
			List<Noticia> noticiasFiltradas = noticias.stream()
			    .filter(n -> n.getComentarios().size() > quantidadeComentarios)
			    .toList();
			
			for (Noticia n : noticiasFiltradas) {
			    System.out.println(n);
			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.fecharBanco();
		System.out.println("\nfim da aplicacao");
	}


	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}