package appconsole;
/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import modelo.Noticia;
import modelo.Assunto;
import regras_negocio.Fachada;


public class Consultar {

	public Consultar(){

		try {
			Fachada.inicializar();
			System.out.println("Noticias com 3 comentários ");
			for(Noticia n : Fachada.consultarNoticiaPorNumComent(3)) 
				System.out.println(n);

			System.out.println("\nnoticias acontecidas na data 15/11/2024 ");
			for(Noticia n : Fachada.consultarData("15/11/2024")) 
				System.out.println(n);

			System.out.println("\nNoticias com o assunto Brasil");
			for(Noticia n : Fachada.consultarNoticiasPorAssunto("Brasil")) {
				System.out.println(n);
			} 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}


	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
