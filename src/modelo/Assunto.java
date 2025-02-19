package modelo;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import modelo.Noticia;
import java.util.ArrayList;

@Entity
public class Assunto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Noticia> noticias = new ArrayList<Noticia>();


	public Assunto (String nome){
		//this.id = ???;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Noticia> getNoticias() {
		return this.noticias;
	}
	
	//	--------------------RELACIONAMENTO--------------------------------
	public void adicionar(Noticia noticia) {
		this.noticias.add(noticia);
	}
	
	public void remover(Noticia noticia){
		noticias.remove(noticia);
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Assunto{nome='").append(nome)
	      .append("', noticias=[");

	    for (Noticia n : noticias) {
	        sb.append(n.getTitulo()).append(", "); // Apenas exibe o título das notícias
	    }
	    if (!noticias.isEmpty()) {
	        sb.setLength(sb.length() - 2); // Remove a vírgula extra
	    }

	    sb.append("]}");
	    return sb.toString();
	}


	
}