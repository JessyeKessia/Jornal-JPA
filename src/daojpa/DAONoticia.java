/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;


import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Noticia;

public class DAONoticia  extends DAO<Noticia>{

 
	public Noticia read (String titulo) {
		Query q = manager.query();
		q.constrain(Noticia.class);
		q.descend("titulo").constrain(titulo).like();
		List<Noticia> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	public void create(Noticia obj){
		int novoid = super.gerarId(Noticia.class);  	
		obj.setId(novoid);				
		manager.store( obj );
	}
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE NOTICIA
	 * 
	 **********************************************************/

	public List<Noticia> readAll(String caracteres) {
		Query q = manager.query();
		q.constrain(Noticia.class);
		q.descend("titulo").constrain(caracteres).like();		
		List<Noticia> result = q.execute(); 
		return result;
	}
	
	public List<Noticia> readComentarios(){
		Query q = manager.query();
		q.constrain(Noticia.class);
		q.descend("comentarios");
		List<Noticia> resultados = q.execute();
		if(resultados.isEmpty())
			return null;
		else
			return resultados;
	}
	
	public List<Noticia> readByData(String data) {
		Query q = manager.query();
		q.constrain(Noticia.class);  
		q.descend("data").constrain(data).contains();
		return q.execute();
	}
	
	public List<Noticia> readByAssunto(String nomeAssunto) {
	    Query q = manager.query();
	    q.constrain(Noticia.class);
	    q.descend("assuntos").descend("nome").constrain(nomeAssunto).like();
	    List<Noticia> resultado = q.execute();
	    if(resultado.isEmpty()) {return null;}	
		else
			return resultado;    
	}
	
	public List<Noticia> readByNumeroComentarios(int n) {
	    Query q = manager.query();
	    q.constrain(Noticia.class);
	    q.constrain(new Filtro(n));
	    List<Noticia> resultado = q.execute();
	    if(resultado.isEmpty())
			return null;
		else
			return resultado;  
	    }
	
}

class Filtro implements Evaluation{
	private int n;
	public Filtro(int n) {
		{this.n = n;}
	}
	public void evaluate(Candidate candidate) {
		Noticia n = (Noticia) candidate.getObject();
		if(n.getComentarios().size()== this.n)
		candidate.include(true);
		else
		candidate.include(false);
		}
}



