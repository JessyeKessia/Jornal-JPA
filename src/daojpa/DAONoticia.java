/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;


import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Noticia;

public class DAONoticia  extends DAO<Noticia>{

 
	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE NOTICIA
	 * 
	 **********************************************************/

	
	@Override
	public Noticia read(Object chave) {
		try {
			String titulo = (String) chave;
			TypedQuery<Noticia> q = manager.createQuery("select n from Noticia n where n.titulo = :n", Noticia.class);
			q.setParameter("n",titulo.toUpperCase());
			return q.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Noticia> readByData(String data) {
		String jpa = "select n from Noticia n where n.data = :n";
		TypedQuery<Noticia> q = manager.createQuery(jpa,Noticia.class);
		q.setParameter("n", data);
		return q.getResultList();
	}
	
	public List<Noticia> readByAssunto(String nomeAssunto) {
	   String jpa = "Select n from Noticia n join n.assuntos a where a.nome = :n";
	   TypedQuery<Noticia> q = manager.createQuery(jpa, Noticia.class);
	   q.setParameter("n", nomeAssunto.toUpperCase());
	   return q.getResultList();
	}
	
	public List<Noticia> readByNumeroComentarios(int n) {
	    String jpa = "select n from Noticia n where size(n.comentarios) > :n ";
	    TypedQuery<Noticia> q = manager.createQuery(jpa, Noticia.class);
	    q.setParameter("n", n);
	    return q.getResultList();	    
	    }
	
	public List<Noticia> readLikeTitulo(String caracteres) {
		String jpa = "select p from Pessoa p where p.nome like :x ";
		TypedQuery<Noticia> q = manager.createQuery(jpa,Noticia.class);
		q.setParameter("x", "%" + caracteres.toUpperCase() + "%");
		return q.getResultList();
	}
	
}




