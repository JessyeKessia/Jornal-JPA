
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
			Fachada.alterartitulo("NOVA IA REVOLUCIONA MERCADO","POB 2024.2");
			System.out.println("NOVA IA REVOLUCIONA MERCADO para POB 2024.2");
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//altera�ao 2
		try {
			Fachada.alterarData("POB 2024.2", "13/03/2025");
			System.out.println("alteradA DATA DE POB 2024.2  para 13/03/2025");
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

