package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String data;
	private String linkWeb;
	private byte[] foto;

	@ManyToMany(mappedBy="noticias", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Assunto> assuntos = new ArrayList<Assunto>();
	private List<String> comentarios = new ArrayList<String>();
	
	public Noticia () {}
	public Noticia(String titulo, String data, String link) {
		this.titulo = titulo.toUpperCase();
		this.data = data;
		this.linkWeb = link;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo.toUpperCase();
	}

	public String getData() {
		return data;
	}

	public void setData(String novaData) {
		this.data = novaData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinkWeb() {
		return linkWeb;
	}

	public void setLinkWeb(String linkWeb) {
		this.linkWeb = linkWeb;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void adicionarComentario(String comentario) {
		this.comentarios.add(comentario);
	}

	// relacionamento
	public void adicionar(Assunto assunto) {
		assuntos.add(assunto);
	}

	public void remover(Assunto assunto) {
		assuntos.remove(assunto);
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Noticia{titulo='").append(titulo)
	      .append("', data='").append(data)
	      .append("', assuntos=[");

	    for (Assunto a : assuntos) {
	        sb.append(a.getNome()).append(", "); // Apenas exibe o nome dos assuntos
	    }
	    if (!assuntos.isEmpty()) {
	        sb.setLength(sb.length() - 2); // Remove a vírgula extra
	    }

	    sb.append("]}");
	    return sb.toString();
	}
	
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
