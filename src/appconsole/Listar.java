package appconsole;
/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/



import modelo.Noticia;
import modelo.Assunto;
import regras_negocio.Fachada;

public class Listar {

	public Listar(){
		try {
			Fachada.inicializar();

			System.out.println("*** Listagem de Noticias:");
			for(Noticia n : Fachada.listarNoticias())		
				System.out.println(n);


			System.out.println("\n*** Listagem de Assuntos:");
			for(Assunto a : Fachada.listarAssuntos())
				System.out.println(a);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}


	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

