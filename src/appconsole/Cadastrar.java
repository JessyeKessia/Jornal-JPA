/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranhão Ayres
 **********************************/
 
package appconsole;

import jakarta.persistence.EntityManager;
import modelo.Assunto;
import modelo.Noticia;

public class Cadastrar {
    private EntityManager manager;

    public Cadastrar() {
        try {
            manager = Util.conectarBanco();

            System.out.println("Cadastrando...");

            // Criando assuntos
            Assunto tecnologia = new Assunto("Tecnologia");
            Assunto politica = new Assunto("Política");
            Assunto esporte = new Assunto("Esporte");
            Assunto economia = new Assunto("Economia");

            manager.getTransaction().begin();
            manager.persist(tecnologia);
            manager.persist(politica);
            manager.persist(esporte);
            manager.persist(economia);
            manager.getTransaction().commit();

            // Criando notícias e associando aos assuntos
            manager.getTransaction().begin();
            Noticia noticia1 = new Noticia("Nova IA revoluciona mercado", "2025-02-19", "https://technews.com/ia");
            noticia1.adicionar(tecnologia);
            tecnologia.adicionar(noticia1);
            manager.persist(noticia1);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Noticia noticia2 = new Noticia("Eleições e os novos desafios", "2025-02-19", "https://news.com/politica");
            noticia2.adicionar(politica);
            politica.adicionar(noticia2);
            manager.persist(noticia2);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Noticia noticia3 = new Noticia("Final do campeonato emocionante", "2025-02-19", "https://sports.com/final");
            noticia3.adicionar(esporte);
            esporte.adicionar(noticia3);
            manager.persist(noticia3);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Noticia noticia4 = new Noticia("Bolsa de valores em alta", "2025-02-19", "https://economy.com/bolsa");
            noticia4.adicionar(economia);
            economia.adicionar(noticia4);
            manager.persist(noticia4);
            manager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exceção=" + e.getMessage());
        }
        Util.fecharBanco();
        System.out.println("Fim da aplicação");
    }

    public static void main(String[] args) {
        new Cadastrar();
    }
}
