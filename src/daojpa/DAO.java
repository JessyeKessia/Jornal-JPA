/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package daojpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;

	public static void open() {
		manager = Util.conectarBanco(); // banco local
		//manager = Util.conectarBancoRemoto(); //banco remoto
	}

	public static void close() {
		Util.fecharBanco();
		manager = null;
	}

	// ----------CRUD-----------------------

	public void create(T obj) {
		manager.persist(obj);
	}

	public T update(T obj) {
		return manager.merge(obj);
		
	}

	public void delete(T obj) {
		manager.remove(obj);
	}
	
	public abstract T read(Object chave);
	
	@SuppressWarnings("unchecked")
	public List<T> readAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		TypedQuery<T> query = manager.createQuery("select x from " + type.getSimpleName() + " x",type);
		return  query.getResultList();
	}

	// --------transa��o---------------
	public static void begin() {
		if(!manager.getTransaction().isActive()) {
			manager.getTransaction().begin();
		}
	}

	public static void commit() {
		if(manager.getTransaction().isActive())
			manager.getTransaction().commit();
	}

	public static void rollback() {
		manager.getTransaction().rollback();
	}

	
}