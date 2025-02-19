package appconsole;

import regras_negocio.Fachada;

public class Listar2 {
	
	public Listar2(){
		try {
			Fachada.inicializar();

			System.out.println("*** Listagem de Comentarios:");
			System.out.println(Fachada.listarComentarios());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}
	public static void main(String[] args) {
		new Listar2();

	}

}
