package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Assunto;
import modelo.Noticia;
import regras_negocio.Fachada;
import java.util.List;

public class Listar {
    protected EntityManager manager;

    public Listar() {
        try {
            manager = Util.conectarBanco();
            String jpql;

            

            System.out.println("*** Listagem de Notícias:");
            jpql = "SELECT n FROM Noticia n";
            TypedQuery<Noticia> queryNoticias = manager.createQuery(jpql, Noticia.class);
            List<Noticia> noticias = queryNoticias.getResultList();
            for (Noticia n : noticias) {
                System.out.println(n);
            }

       
            System.out.println("\n*** Listagem de Assuntos:");
            jpql = "SELECT a FROM Assunto a";
            TypedQuery<Assunto> queryAssuntos = manager.createQuery(jpql, Assunto.class);
            List<Assunto> assuntos = queryAssuntos.getResultList();
            for (Assunto a : assuntos) {
                System.out.println(a);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar dados: " + e.getMessage());
        } finally {
            Util.fecharBanco();
        }
    }

    public static void main(String[] args) {
        new Listar();
    }
}
