package br.com.dio.desafio.dominio;

import java.time.LocalDate;

/**
 * Representa uma Mentoria dentro de um {@link Bootcamp}.
 * A Mentoria concede XP fixo adicional ao {@link Dev} que a conclui.
 */
public class Mentoria extends Conteudo {

    private static final double XP_BONUS = 20d;

    private final LocalDate data;

    /**
     * @param titulo    título da mentoria
     * @param descricao descrição da mentoria
     * @param data      data em que a mentoria ocorre (não pode ser nula)
     */
    public Mentoria(String titulo, String descricao, LocalDate data) {
        super(titulo, descricao);
        if (data == null) {
            throw new IllegalArgumentException("Data da mentoria não pode ser nula.");
        }
        this.data = data;
    }

    /**
     * XP = XP_PADRÃO + bônus fixo de mentoria ({@value XP_BONUS})
     */
    @Override
    public double calcularXp() {
        return XP_PADRAO + XP_BONUS;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Mentoria{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", data=" + data +
                '}';
    }
}
