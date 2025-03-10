/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranhão Ayres
 **********************************/

package appconsole;

import jakarta.persistence.EntityManager;
import modelo.Assunto;
import modelo.Noticia;
import regras_negocio.Fachada;

public class Cadastrar {
	private EntityManager manager;

	public Cadastrar() {
		try {
			Fachada.inicializar();

			System.out.println("Cadastrando...");

	
			Fachada.criarNoticia("Nova IA revoluciona mercado", "19/02/2025", "https://technews.com/ia", "Tecnologia");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado", "Muito interessante essa IA!");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado", "A tecnologia está avançando muito rápido.");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado",
					"kkkk já já elas se revoltam e aí eu quero ver");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado",
					"Tenho medo de perder meu emprego para uma IA...");

			Fachada.criarNoticia("Eleições e os novos desafios", "19/02/2025", "https://news.com/politica", "Politica");
			Fachada.AdicionarAssunto("Eleições e os novos desafios", "Brasil");
			Fachada.adicionarComentarios("Eleições e os novos desafios", "Será uma eleição difícil!");
			Fachada.adicionarComentarios("Eleições e os novos desafios", "A política está cada vez mais polarizada.");
			Fachada.adicionarComentarios("Eleições e os novos desafios", "Esse careca acha que é o dono do Brasil.");

			Fachada.criarNoticia("Final do campeonato emocionante", "19/02/2025", "https://sports.com/final",
					"Esporte");
			Fachada.AdicionarAssunto("Final do campeonato emocionante", "Entreterimento");
			Fachada.AdicionarAssunto("Final do campeonato emocionante", "Futebol");
			Fachada.AdicionarAssunto("Final do campeonato emocionante", "Brasil");
			Fachada.adicionarComentarios("Final do campeonato emocionante", "Que final! Incrível!");
			Fachada.adicionarComentarios("Final do campeonato emocionante",
					"Espero que o próximo campeonato seja ainda melhor.");

			Fachada.criarNoticia("Stock exchange in the USA", "19/02/2025", "https://economy.com/", "economia");
			Fachada.AdicionarAssunto("Stock exchange in the USA", "USA");
			Fachada.AdicionarAssunto("Stock exchange in the USA", "Mundo");
			Fachada.adicionarComentarios("Stock exchange in the USA", "Great opportunity!");
			Fachada.adicionarComentarios("Stock exchange in the USA",
					"Stock market on the rise! Investors are optimistic as markets show strong performance this week.");

		} catch (Exception e) {
			System.out.println("Exceção=" + e.getMessage());
		}
		Fachada.finalizar();
		System.out.println("Fim da aplicação");
	}

	public static void main(String[] args) {
		new Cadastrar();
	}
}