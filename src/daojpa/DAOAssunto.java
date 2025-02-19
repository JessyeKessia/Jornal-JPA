/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import com.db4o.query.Query;

import modelo.Assunto;

public class DAOAssunto extends DAO<Assunto> {
	public Assunto read(String nome) {
		Query q = manager.query();
		q.constrain(Assunto.class);
		q.descend("nome").constrain(nome).like();
		List<Assunto> resultados = q.execute();
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}

	public void create(Assunto obj) {
		int novoid = super.gerarId(Assunto.class);
		obj.setId(novoid);
		manager.store(obj);
	}

	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE TELEFONE
	 * 
	 **********************************************************/

	public List<Assunto> readAll(String caracter) {
		Query q = manager.query();
		q.constrain(Assunto.class);
		q.descend("nome").constrain(caracter).like();
		return q.execute();
	}

}