/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Assunto;
import modelo.Noticia;

public class DAOAssunto extends DAO<Assunto> {
	
	// Leitura dos assuntos carregando junto as noticias.
	public Assunto read (Object chave) {
		try {
			String assunto = (String) chave;
			TypedQuery<Assunto> q = manager.createQuery("select a from Assunto a where a.nome = :n", Assunto.class);
			q.setParameter("n", assunto.toUpperCase());
			Assunto resultado = q.getSingleResult();
			return resultado;
		}catch (NoResultException e) {
			return null;
		}	
		
		}
	public List<Assunto> readLikeNome(String caracteres) {
		String jpa = "select p from Pessoa p where p.nome like :x ";
		TypedQuery<Assunto> q = manager.createQuery(jpa,Assunto.class);
		q.setParameter("x", "%" + caracteres.toUpperCase() + "%");
		return q.getResultList();
	}
	
	

	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE TELEFONE
	 * 
	 **********************************************************/


}