package appconsole;

/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.util.List;
import java.util.Scanner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Noticia;



public class Consultar {

    protected EntityManager manager;

    public Consultar(String data, String assuntoNome, int quantidadeComentarios) {



        try {
            manager = Util.conectarBanco();
            TypedQuery<Noticia> query1;
            List<Noticia> noticias;
            String jpql;



            // Listar noticias com base na data passada
            System.out.println("----- Listar noticias com base na data " + data + " -----");
            jpql = "select n from Noticia n where n.data = :data";
            query1 = manager.createQuery(jpql, Noticia.class);
            query1.setParameter("data", data);
            noticias = query1.getResultList();

            for (Noticia n : noticias) {
                System.out.println(n);
            }

            // Listar noticias com base no nome do assunto passado

            System.out.println("\n----- Listar noticias com base em um assunto de nome " + assuntoNome + " -----");
            jpql = "select n from Noticia n join n.assuntos a where a.nome = :assuntoNome";
            query1 = manager.createQuery(jpql, Noticia.class);
            query1.setParameter("assuntoNome", assuntoNome);
            noticias = query1.getResultList();

            for (Noticia n : noticias) {
                System.out.println(n);
            }


            // Listar noticias com base na quantidade de comentários maior que o valor passado
            System.out.println("\n----- Listar noticias com base na quantidade de comentários maior que " + quantidadeComentarios + " -----");
            jpql = "SELECT n FROM Noticia n where size(n.comentarios)> :quantidadeComentarios";
            query1 = manager.createQuery(jpql, Noticia.class);
            query1.setParameter("quantidadeComentarios", quantidadeComentarios);
            noticias = query1.getResultList();

            for (Noticia n : noticias) {
                System.out.println(n);
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Util.fecharBanco();
        System.out.println("\nfim da aplicacao");

    }

    //=================================================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a data (formato AAAA-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Digite o nome do assunto: ");
        String assuntoNome = scanner.nextLine();
        System.out.print("Digite a quantidade mínima de comentários: ");
        int quantidadeComentarios = scanner.nextInt();

        scanner.close();
        new Consultar(data, assuntoNome, quantidadeComentarios);

    }

}