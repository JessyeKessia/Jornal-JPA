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
            Assunto brasil = new Assunto("Brasil");
            Assunto tecnologia = new Assunto("Tecnologia");
            Assunto politica = new Assunto("Política");
            Assunto esporte = new Assunto("Esporte");
            Assunto economia = new Assunto("Economia");

            manager.getTransaction().begin();
            manager.persist(brasil);
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
            brasil.adicionar(noticia1);
            noticia1.adicionarComentario("Muito interessante essa IA!");
            noticia1.adicionarComentario("A tecnologia está avançando muito rápido.");
            manager.persist(noticia1);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Noticia noticia2 = new Noticia("Eleições e os novos desafios", "2025-02-19", "https://news.com/politica");
            noticia2.adicionar(politica);
            politica.adicionar(noticia2);
            brasil.adicionar(noticia2);
            noticia2.adicionarComentario("Será uma eleição difícil!");
            noticia2.adicionarComentario("A política está cada vez mais polarizada.");
            manager.persist(noticia2);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Noticia noticia3 = new Noticia("Final do campeonato emocionante", "2025-02-19", "https://sports.com/final");
            noticia3.adicionar(esporte);
            esporte.adicionar(noticia3);
            brasil.adicionar(noticia3);
            noticia3.adicionarComentario("Que final! Incrível!");
            noticia3.adicionarComentario("Espero que o próximo campeonato seja ainda melhor.");
            manager.persist(noticia3);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Noticia noticia4 = new Noticia("Stock exchange in the USA", "2025-02-19", "https://economy.com/");
            noticia4.adicionar(economia);
            economia.adicionar(noticia4);
            noticia4.adicionarComentario("Great opportunity!");
            noticia4.adicionarComentario("Stock market on the rise! Investors are optimistic as markets show strong performance this week.");
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