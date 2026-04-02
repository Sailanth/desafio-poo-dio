import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // --- Criação dos conteúdos via construtor ---
        Curso cursoJava = new Curso("Curso Java", "Introdução ao Java e POO", 8);
        Curso cursoJs   = new Curso("Curso JavaScript", "Fundamentos de JavaScript", 4);
        Mentoria mentoria = new Mentoria("Mentoria de Java", "Boas práticas em Java", LocalDate.now());

        // --- Montagem do Bootcamp ---
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Aprenda Java com foco em POO e boas práticas.");
        bootcamp.adicionarConteudo(cursoJava);
        bootcamp.adicionarConteudo(cursoJs);
        bootcamp.adicionarConteudo(mentoria);

        // --- Dev: Camila ---
        Dev devCamila = new Dev();
        devCamila.setNome("Camila");
        devCamila.inscreverBootcamp(bootcamp);

        System.out.println("=== Camila ===");
        System.out.println("Conteúdos inscritos : " + devCamila.getConteudosInscritos());
        devCamila.progredir();
        devCamila.progredir();
        System.out.println("Conteúdos inscritos : " + devCamila.getConteudosInscritos());
        System.out.println("Conteúdos concluídos: " + devCamila.getConteudosConcluidos());
        System.out.println("XP total            : " + devCamila.calcularTotalXp());

        System.out.println();

        // --- Dev: João ---
        Dev devJoao = new Dev();
        devJoao.setNome("João");
        devJoao.inscreverBootcamp(bootcamp);

        System.out.println("=== João ===");
        System.out.println("Conteúdos inscritos : " + devJoao.getConteudosInscritos());
        devJoao.progredir();
        devJoao.progredir();
        devJoao.progredir();
        System.out.println("Conteúdos inscritos : " + devJoao.getConteudosInscritos());
        System.out.println("Conteúdos concluídos: " + devJoao.getConteudosConcluidos());
        System.out.println("XP total            : " + devJoao.calcularTotalXp());

        System.out.println();

        // --- Exemplo de exceção ao progredir sem conteúdo ---
        System.out.println("=== Tentativa de progredir sem conteúdo ===");
        try {
            devJoao.progredir();
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }
}
