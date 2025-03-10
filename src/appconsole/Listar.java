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
            Fachada.inicializar();

            System.out.println("*** Listagem de Notícias:");
            List<Noticia> noticias = Fachada.listarNoticias();
            for(Noticia n: noticias) {
            	System.out.println(n);
            }
    
            System.out.println("\n*** Listagem de Assuntos:");
            List<Assunto> assuntos = Fachada.listarAssuntos();
            for(Assunto a: assuntos) {
            	System.out.println(a);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar dados: " + e.getMessage());
        } finally {
            Fachada.finalizar();
        }
    }


	public static void main(String[] args) {
		
        new Listar();
    }
}
