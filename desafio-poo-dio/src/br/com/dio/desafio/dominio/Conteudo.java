package br.com.dio.desafio.dominio;

import java.util.Objects;

/**
 * Classe abstrata que representa um conteúdo de um Bootcamp.
 * Pode ser um {@link Curso} ou uma {@link Mentoria}.
 */
public abstract class Conteudo {

    protected static final double XP_PADRAO = 10d;

    private final String titulo;
    private final String descricao;

    /**
     * Construtor que garante que todo conteúdo tenha título e descrição.
     *
     * @param titulo    título do conteúdo (não pode ser nulo ou vazio)
     * @param descricao descrição do conteúdo (não pode ser nulo ou vazio)
     */
    protected Conteudo(String titulo, String descricao) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
    }

    /**
     * Calcula a quantidade de XP que este conteúdo fornece ao ser concluído.
     *
     * @return valor de XP
     */
    public abstract double calcularXp();

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conteudo conteudo = (Conteudo) o;
        return Objects.equals(titulo, conteudo.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }
}
