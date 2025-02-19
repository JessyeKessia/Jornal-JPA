package appconsole;

import org.apache.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Util {
	private static EntityManager manager;
	private static EntityManagerFactory factory;

	private static final Logger logger = Logger.getLogger(Util.class);

	
	public static EntityManager conectarBanco(){
		if(manager == null) {
			factory = Persistence.createEntityManagerFactory("hibernate-postgresql");
			manager = factory.createEntityManager();
			logger.debug("-------- conectou banco");
		}
		return manager;
	}

	public static void fecharBanco(){
		if(manager != null && manager.isOpen()) {
			manager.close();
			factory.close();
			manager=null;
			logger.debug("-------- desconectou banco");
		}
	

	}
}
