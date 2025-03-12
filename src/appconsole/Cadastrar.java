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


	public Cadastrar() {
		try {
			Fachada.inicializar();

			System.out.println("Cadastrando...");

	
			Fachada.criarNoticia("Nova IA revoluciona mercado", "19/02/2025", "https://technews.com/ia", "Tecnologia");
			Fachada.adicionarImagem("Nova IA revoluciona mercado", "journal.jpg");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado", "Muito interessante essa IA!");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado", "A tecnologia está avançando muito rápido.");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado",
					"kkkk já já elas se revoltam e aí eu quero ver");
			Fachada.adicionarComentarios("Nova IA revoluciona mercado",
					"Tenho medo de perder meu emprego para uma IA...");

			Fachada.criarNoticia("Eleições e os novos desafios", "19/02/2025", "https://news.com/politica", "Politica");
			Fachada.adicionarImagem("Eleições e os novos desafios", "journal.jpg");
			Fachada.AdicionarAssunto("Eleições e os novos desafios", "Brasil");
			Fachada.adicionarComentarios("Eleições e os novos desafios", "Será uma eleição difícil!");
			Fachada.adicionarComentarios("Eleições e os novos desafios", "A política está cada vez mais polarizada.");
			Fachada.adicionarComentarios("Eleições e os novos desafios", "Esse careca acha que é o dono do Brasil.");

			Fachada.criarNoticia("Final do campeonato emocionante", "19/02/2025", "https://sports.com/final",
					"Esporte");
			Fachada.adicionarImagem("Final do campeonato emocionante", "journal.jpg");
			Fachada.AdicionarAssunto("Final do campeonato emocionante", "Entreterimento");
			Fachada.AdicionarAssunto("Final do campeonato emocionante", "Futebol");
			Fachada.AdicionarAssunto("Final do campeonato emocionante", "Brasil");
			Fachada.adicionarComentarios("Final do campeonato emocionante", "Que final! Incrível!");
			Fachada.adicionarComentarios("Final do campeonato emocionante",
					"Espero que o próximo campeonato seja ainda melhor.");

			Fachada.criarNoticia("Stock exchange in the USA", "19/02/2025", "https://economy.com/", "economia");
			Fachada.adicionarImagem("Stock exchange in the USA", "journal.jpg");
			Fachada.AdicionarAssunto("Stock exchange in the USA", "USA");
			Fachada.AdicionarAssunto("Stock exchange in the USA", "Mundo");
			Fachada.adicionarComentarios("Stock exchange in the USA", "Great opportunity!");
			Fachada.adicionarComentarios("Stock exchange in the USA",
					"Stock market on the rise! Investors are optimistic as markets show strong performance this week.");
			
			Fachada.criarNoticia("Novo marco regulatório do gás natural no Brasil", "15/10/2023", "https://energia.com/novo-marco-gas", "energia");
			Fachada.adicionarImagem("Novo marco regulatório do gás natural no Brasil", "gas_pipeline.jpg");
			Fachada.AdicionarAssunto("Novo marco regulatório do gás natural no Brasil", "economia");
			Fachada.AdicionarAssunto("Novo marco regulatório do gás natural no Brasil", "meio ambiente");
			Fachada.adicionarComentarios("Novo marco regulatório do gás natural no Brasil", "Ótima notícia para o setor energético!");
			Fachada.adicionarComentarios("Novo marco regulatório do gás natural no Brasil", "Espero que isso reduza os preços para o consumidor final.");

			Fachada.criarNoticia("Lançamento do novo smartphone da marca X", "20/10/2023", "https://technews.com/novo-smartphone", "tecnologia");
			Fachada.adicionarImagem("Lançamento do novo smartphone da marca X", "smartphone_x.jpg");
			Fachada.AdicionarAssunto("Lançamento do novo smartphone da marca X", "inovação");
			Fachada.AdicionarAssunto("Lançamento do novo smartphone da marca X", "mercado");
			Fachada.adicionarComentarios("Lançamento do novo smartphone da marca X", "Design incrível!");
			Fachada.adicionarComentarios("Lançamento do novo smartphone da marca X", "Preço muito alto.");
			Fachada.adicionarComentarios("Lançamento do novo smartphone da marca X", "Esperava mais inovações.");

			Fachada.criarNoticia("Copa do Mundo 2026: Sedes anunciadas", "25/10/2023", "https://esportes.com/copa-2026", "esportes");
			Fachada.adicionarImagem("Copa do Mundo 2026: Sedes anunciadas", "copa_2026.jpg");
			Fachada.AdicionarAssunto("Copa do Mundo 2026: Sedes anunciadas", "futebol");
			Fachada.AdicionarAssunto("Copa do Mundo 2026: Sedes anunciadas", "turismo");
			Fachada.adicionarComentarios("Copa do Mundo 2026: Sedes anunciadas", "Já estou planejando minha viagem!");
			Fachada.adicionarComentarios("Copa do Mundo 2026: Sedes anunciadas", "Ótima escolha de sedes.");

			Fachada.criarNoticia("Novo tratamento para diabetes mostra resultados promissores", "30/10/2023", "https://saude.com/novo-tratamento-diabetes", "saúde");
			Fachada.adicionarImagem("Novo tratamento para diabetes mostra resultados promissores", "diabetes_treatment.jpg");
			Fachada.AdicionarAssunto("Novo tratamento para diabetes mostra resultados promissores", "medicina");
			Fachada.AdicionarAssunto("Novo tratamento para diabetes mostra resultados promissores", "pesquisa");
			Fachada.adicionarComentarios("Novo tratamento para diabetes mostra resultados promissores", "Esperança para milhões de pessoas!");
			Fachada.adicionarComentarios("Novo tratamento para diabetes mostra resultados promissores", "Precisamos de mais investimentos em saúde.");

			Fachada.criarNoticia("Mercado de criptomoedas em alta", "05/11/2023", "https://financas.com/criptomoedas-alta", "finanças");
			Fachada.adicionarImagem("Mercado de criptomoedas em alta", "criptomoedas.jpg");
			Fachada.AdicionarAssunto("Mercado de criptomoedas em alta", "investimentos");
			Fachada.AdicionarAssunto("Mercado de criptomoedas em alta", "tecnologia");
			Fachada.adicionarComentarios("Mercado de criptomoedas em alta", "Momento ideal para investir!");
			Fachada.adicionarComentarios("Mercado de criptomoedas em alta", "Cuidado com a volatilidade.");

			Fachada.criarNoticia("Novo filme da Marvel bate recordes de bilheteria", "10/11/2023", "https://cinema.com/marvel-record", "cinema");
			Fachada.adicionarImagem("Novo filme da Marvel bate recordes de bilheteria", "marvel_filme.jpg");
			Fachada.AdicionarAssunto("Novo filme da Marvel bate recordes de bilheteria", "entretenimento");
			Fachada.AdicionarAssunto("Novo filme da Marvel bate recordes de bilheteria", "cultura");
			Fachada.adicionarComentarios("Novo filme da Marvel bate recordes de bilheteria", "Filme incrível, vale cada minuto!");
			Fachada.adicionarComentarios("Novo filme da Marvel bate recordes de bilheteria", "Efeitos especiais de tirar o fôlego.");

			Fachada.criarNoticia("Novas regras para o teletrabalho na UE", "15/11/2023", "https://trabalho.com/teletrabalho-ue", "trabalho");
			Fachada.adicionarImagem("Novas regras para o teletrabalho na UE", "teletrabalho.jpg");
			Fachada.AdicionarAssunto("Novas regras para o teletrabalho na UE", "legislação");
			Fachada.AdicionarAssunto("Novas regras para o teletrabalho na UE", "tecnologia");
			Fachada.adicionarComentarios("Novas regras para o teletrabalho na UE", "Ótimo para o equilíbrio entre vida pessoal e profissional.");
			Fachada.adicionarComentarios("Novas regras para o teletrabalho na UE", "Precisamos de mais flexibilidade.");

			Fachada.criarNoticia("Estudo revela impacto das mudanças climáticas na agricultura", "20/11/2023", "https://agricultura.com/mudancas-climaticas", "agricultura");
			Fachada.adicionarImagem("Estudo revela impacto das mudanças climáticas na agricultura", "agricultura_clima.jpg");
			Fachada.AdicionarAssunto("Estudo revela impacto das mudanças climáticas na agricultura", "meio ambiente");
			Fachada.AdicionarAssunto("Estudo revela impacto das mudanças climáticas na agricultura", "economia");
			Fachada.adicionarComentarios("Estudo revela impacto das mudanças climáticas na agricultura", "Preocupante para o futuro da alimentação.");
			Fachada.adicionarComentarios("Estudo revela impacto das mudanças climáticas na agricultura", "Precisamos de políticas urgentes.");

			Fachada.criarNoticia("Novo aplicativo de mobilidade urbana chega ao Brasil", "25/11/2023", "https://mobilidade.com/novo-app", "mobilidade");
			Fachada.adicionarImagem("Novo aplicativo de mobilidade urbana chega ao Brasil", "mobilidade_app.jpg");
			Fachada.AdicionarAssunto("Novo aplicativo de mobilidade urbana chega ao Brasil", "tecnologia");
			Fachada.AdicionarAssunto("Novo aplicativo de mobilidade urbana chega ao Brasil", "transporte");
			Fachada.adicionarComentarios("Novo aplicativo de mobilidade urbana chega ao Brasil", "Facilitará muito a vida nas cidades.");
			Fachada.adicionarComentarios("Novo aplicativo de mobilidade urbana chega ao Brasil", "Espero que seja acessível.");

			Fachada.criarNoticia("Festival de música atrai milhares de turistas", "30/11/2023", "https://cultura.com/festival-musica", "cultura");
			Fachada.adicionarImagem("Festival de música atrai milhares de turistas", "festival_musica.jpg");
			Fachada.AdicionarAssunto("Festival de música atrai milhares de turistas", "turismo");
			Fachada.AdicionarAssunto("Festival de música atrai milhares de turistas", "entretenimento");
			Fachada.adicionarComentarios("Festival de música atrai milhares de turistas", "Experiência incrível!");
			Fachada.adicionarComentarios("Festival de música atrai milhares de turistas", "Organização impecável.");
			Fachada.adicionarComentarios("Festival de música atrai milhares de turistas", "Já quero ir no próximo ano!");
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