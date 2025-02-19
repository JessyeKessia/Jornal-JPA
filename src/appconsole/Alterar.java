package appconsole;
/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import regras_negocio.Fachada;

public class Alterar {

	public Alterar(){
		Fachada.inicializar();
		//altera�ao 1
		try {
			Fachada.alterartitulo("Novo filme brasileiro é aclamado em festival internacional","POB 2024.2");
			System.out.println("alterado TITULO  de Novo filme brasileiro é aclamado "
					+ "em festival internacional para POB 2024.2");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//altera�ao 2
		try {
			Fachada.alterarData("POB 2024.2", "04/12/2024");
			System.out.println("alteradA DATA DE POB 2024.2  para 04/12/2024");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("fim do programa");
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}

