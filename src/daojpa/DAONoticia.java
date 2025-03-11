/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

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
	    List<Noticia> info = this.readAll();
	    return info.stream().filter(a -> a.getComentarios().size() > n).toList();	
	    }
	
	public List<Noticia> readLikeTitulo(String caracteres) {
		String jpa = "select n from Noticia n where p.nome like :x ";
		TypedQuery<Noticia> q = manager.createQuery(jpa,Noticia.class);
		q.setParameter("x", "%" + caracteres.toUpperCase() + "%");
		return q.getResultList();
	}
	
	public List<Noticia> readAll() {
		String jpa = "select n from Noticia n";
		TypedQuery<Noticia> q = manager.createQuery(jpa,Noticia.class);
		return q.getResultList();
	}
	
	public byte[] buscarFoto(String arquivo) {
		String caminho = "/imagens/"+arquivo;
		try {
			URL url = getClass().getResource(caminho);
			File f = new File(url.toURI());				// pasta src/fotos (interna)
			BufferedImage buffer = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(buffer, "jpg", baos );
			byte[] bytesfoto = baos.toByteArray();
			baos.close();
			return bytesfoto;
		} catch (IOException e) {
			throw new RuntimeException("arquivo invalido:"+caminho);
		} catch (URISyntaxException e) {
			throw new RuntimeException("caminho invalido:"+caminho);
		}}

	
}




