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


public class Consultar {
	protected EntityManager manager;

	public Consultar(){

		try {
			manager = Util.conectarBanco();
			
			TypedQuery<Noticia> query1;
			List<Noticia> noticias;
			String jpql;
			
			
			System.out.println("----- Listar noticias com base na data 2025-02-19 -----");
			jpql = "select n from Noticia n where n.data = '2025-02-19'";
			query1 = manager.createQuery(jpql, Noticia.class);
			noticias = query1.getResultList();
			for (Noticia n : noticias)
			System.out.println(n);
			
			
			System.out.println("\n----- Listar noticias com base em um assunto de nome N = Economia -----");
			jpql = "select n from Noticia n join n.assuntos a where a.nome = 'Economia'";
			query1 = manager.createQuery(jpql, Noticia.class);
			noticias = query1.getResultList();
			for (Noticia n : noticias)
			    System.out.println(n);
			
			System.out.println("\n----- Listar noticias com base na quantidade N > 3 de comentários -----");
			jpql = "SELECT n FROM Noticia n";
			query1 = manager.createQuery(jpql, Noticia.class);
			noticias = query1.getResultList();

			int quantidadeComentarios = 3; 
			
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