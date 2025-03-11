package regras_negocio;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.imageio.ImageIO;

import daojpa.DAO;
import daojpa.DAONoticia;
import daojpa.DAOAssunto;
import modelo.Noticia;
import modelo.Assunto;

public class Fachada {
	private Fachada() {
	}

	private static DAONoticia daonoticia = new DAONoticia();
	private static DAOAssunto daoassunto = new DAOAssunto();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Noticia localizarNoticia(String titulo) throws Exception {
		titulo = titulo.trim();
		Noticia n = daonoticia.read(titulo);
		if (n == null) {
			throw new Exception("Noticia inexistente:" + titulo);
		}
		return n;
	}

	public static void criarNoticia(String titulo, String data, String linkWeb, String assunto) throws Exception {
		titulo = titulo.trim();
		data = data.trim();
		assunto = assunto.trim();

		DAO.begin();

		try {
			LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			DAO.rollback();
			throw new Exception("formato data invalido:" + data);
		}

		Noticia n = daonoticia.read(titulo);
		if (n != null) {
			DAO.rollback();
			throw new Exception("criar notícia - nome da nótica ja existe:" + titulo);
		}

		Assunto assunto1 = daoassunto.read(assunto);
		if (assunto1 == null) {
			criarAssunto(assunto);
			assunto1 = daoassunto.read(assunto);
		}
		n = new Noticia(titulo, data, linkWeb);
		daonoticia.create(n);
		n.adicionar(assunto1);
		assunto1.adicionar(n);
		daoassunto.update(assunto1);
		daonoticia.update(n);

		DAO.commit();
	}

	public static void adicionarComentarios(String titulo, String comentario) throws Exception {
		titulo = titulo.trim();
		comentario = comentario.trim();

		DAO.begin();
		Noticia n = daonoticia.read(titulo);
		if (n == null) {
			DAO.rollback();
			throw new Exception("adicionar comentario - nome da nótica não encontrado: " + titulo);
		}
		n.adicionarComentario(comentario);
		daonoticia.update(n);
		DAO.commit();
	}

	public static void alterarAssunto(String nome, String novo) throws Exception {
		nome = nome.trim();

		DAO.begin();

		Assunto a = daoassunto.read(nome);
		if (a == null) {
			DAO.rollback();
			throw new Exception("alterar assunto - assunto inexistente:" + nome);
		}
		a.setNome(novo);
		daoassunto.update(a);
		DAO.commit();
	}

	public static void alterarlink(String titulo, String webLink) throws Exception {
		titulo = titulo.trim();
		DAO.begin();
		Noticia n = daonoticia.read(titulo);
		if (n == null) {
			DAO.rollback();
			throw new Exception("alterar linkWeb - Noticia inexistente:" + titulo);
		}
		n.setLinkWeb(webLink);
		daonoticia.update(n);
		DAO.commit();
	}

	public static void alterarData(String titulo, String data) throws Exception {
		titulo = titulo.trim();
		data = data.trim();

		DAO.begin();
		Noticia n = daonoticia.read(titulo);
		if (n == null) {
			DAO.rollback();
			throw new Exception("alterar noticia - noticia inexistente:" + titulo);
		}

		if (data != null) {
			try {
				LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				n.setData(data);
			} catch (DateTimeParseException e) {
				DAO.rollback();
				throw new Exception("alterar data - formato data invalido:" + data);
			}
		}

		daonoticia.update(n);
		DAO.commit();
	}

	public static void alterartitulo(String titulo, String novoTitulo) throws Exception {
		titulo = titulo.trim();
		novoTitulo = novoTitulo.trim();
		DAO.begin();
		Noticia n = daonoticia.read(titulo);
		if (n == null) {
			DAO.rollback();
			throw new Exception("alterar titulo - titulo inexistente:" + titulo);
		}
		n.setTitulo(novoTitulo);
		daonoticia.update(n);
		DAO.commit();
	}

	public static void apagarNoticia(String titulo) throws Exception {
		titulo = titulo.trim();

		DAO.begin();
		Noticia n = daonoticia.read(titulo);
		if (n == null) {
			DAO.rollback();
			throw new Exception("excluir noticia - titulo inexistente:" + titulo);
		}
		for (Assunto a : n.getAssuntos()) {
			a.remover(n);
			daoassunto.update(a);
		}
		n.getAssuntos().clear();
		daonoticia.delete(n);
		DAO.commit();
	}

	public static void criarAssunto(String nome) throws Exception {
		nome = nome.trim();

		DAO.begin();

		Assunto a = daoassunto.read(nome);
		if (a != null) {
			DAO.rollback();
			throw new Exception("criar assunto - assunto ja Existe:" + nome);
		}
		daoassunto.create(new Assunto(nome));
		DAO.commit();
	}

	public static void AdicionarAssunto(String titulo, String nome) throws Exception {
		nome = nome.trim();
		titulo = titulo.trim();

		DAO.begin();
		Noticia noticia = daonoticia.read(titulo);
		Assunto assunto = daoassunto.read(nome);

		if (noticia == null) {
			DAO.rollback();
			throw new Exception("Nenhuma noticia com esse nome foi encontrada");
		}
		if (assunto == null) {
			criarAssunto(nome);
			assunto = daoassunto.read(nome);
		}
		if (noticia.getAssuntos().contains(assunto)) {
			throw new Exception("Este assunto já está relacionado a esta noticia.");
		}

		noticia.adicionar(assunto);
		assunto.adicionar(noticia);
		daoassunto.update(assunto);
		daonoticia.update(noticia);
		DAO.commit();

	}

	public void removerAssunto(String titulo, String nome) throws Exception {
		nome = nome.trim();
		titulo = titulo.trim();
		DAO.begin();
		Noticia noticia = daonoticia.read(titulo);
		Assunto assunto = daoassunto.read(nome);

		if (noticia == null) {
			DAO.rollback();
			throw new Exception("Nenhuma noticia com esse nome foi encontrada");
		}
		if (assunto == null) {
			DAO.rollback();
			throw new Exception("Nenhuma noticia com esse nome foi encontrada");
		}
		noticia.remover(assunto);
		assunto.remover(noticia);
		daoassunto.update(assunto);
		daonoticia.update(noticia);
		DAO.commit();
	}

	public static void apagarAssunto(String nome) throws Exception {
		nome = nome.trim();
		DAO.begin();
		Assunto a = daoassunto.read(nome);
		if (a == null) {
			DAO.rollback();
			throw new Exception("excluir assunto - assunto inexistente:" + nome);
		}
		for (Noticia n11 : a.getNoticias()) {
			n11.remover(a);
			daonoticia.update(n11);
		}
		a.getNoticias().clear();
		daoassunto.delete(a);
		DAO.commit();
	}

	public static void alterarnome(String nome, String novoNome) throws Exception {
		nome = nome.trim();
		novoNome = novoNome.trim();
		DAO.begin();
		Assunto a = daoassunto.read(nome);
		if (a == null) {
			DAO.rollback();
			throw new Exception("alterar nome do assunto - nome inexistente:" + nome);
		}
		Assunto a2 = daoassunto.read(novoNome);
		if (a2 != null) {
			DAO.rollback();
			throw new Exception("alterar nome do assunto - novo nome ja existe:" + novoNome);
		}
		a.setNome(novoNome); // substituir
		daoassunto.update(a);
		DAO.commit();
	}

	public static List<Noticia> listarNoticias() {
		return daonoticia.readAll();
	}

	public static List<Assunto> listarAssuntos() {
		return daoassunto.readAll();
	}
	
	public static String listarComentarios() throws Exception{
		String saida = "";
		List<Noticia> resultados;
		resultados = daonoticia.readAll();
		if(resultados.isEmpty()) {throw new Exception ("Listar Comentários - Nenhum comentário foi localizado");}
		
		for(Noticia n : resultados) {
			saida += "Titulo: " + n.getTitulo() + "\n";
			for(String coment: n.getComentarios()) {
				saida += coment + "\n";
			}
			saida += "\n";
		}
		return saida;
	}
	
	public static void adicionarImagem(String titulo, String arquivo) throws Exception {
		DAO.begin();
		Noticia n = daonoticia.read(titulo.trim());
		if(n==null) {
			DAO.rollback();
			throw new Exception("Noticia não encontrada...");
		}
		byte[] img = daonoticia.buscarFoto(arquivo.trim());
		if(img.length == 0) {
			DAO.rollback();
			throw new Exception("Arquivo de imagem não encontrado...");
		}
		
		n.setFoto(img);
		daonoticia.update(n);
		DAO.commit();
		
		
	} 
	
	
	/**********************************************************
	 * 
	 * CONSULTAS IMPLEMENTADAS NOS DAO
	 * 
	 **********************************************************/
	public static List<Noticia> consultarNoticias(String caracteres) throws Exception {
		List<Noticia> result;
		if (caracteres.isEmpty())
			result = daonoticia.readAll();
		else
			result = daonoticia.readLikeTitulo(caracteres.trim());
		if (result.size() == 0)
			throw new Exception("Nenhuma noticia encontrada.");
		return result;
	}

	public static List<Assunto> consultarAssuntos(String caracteres) throws Exception {
		List<Assunto> result;
		if (caracteres.isEmpty())
			result = daoassunto.readAll();
		else
			result = daoassunto.readLikeNome(caracteres.trim());
		if (result.size() == 0)
			throw new Exception("Nenhum assunto encontrado.");
		return result;
	}

	public static List<Noticia> consultarNoticiaPorData(String data) throws Exception {
		List<Noticia> result;
		result = daonoticia.readByData(data.trim());
		if (result.size() == 0)
			throw new Exception("Nenhuma noticia corresponde a data informada.");
		return result;
	}

	public static List<Noticia> consultarNoticiasPorAssunto(String nomeAssunto) throws Exception {
		List<Noticia> result;
		result = daonoticia.readByAssunto(nomeAssunto.trim());
		if (!(result.size() >= 1))
			throw new Exception("Nenhma noticia está relacionada com este assunto.");
		return result;
	}

	public static List<Noticia> consultarNoticiaPorNumComent(int n) throws Exception {
		List<Noticia> result;
		result = daonoticia.readByNumeroComentarios(n);
		if (result.size() == 0)
			throw new Exception("Nenhuma noticia possui tantos comentários.");
		return result;
	}

}