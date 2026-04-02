package br.com.dio.desafio.dominio;

/**
 * Representa um Curso dentro de um {@link Bootcamp}.
 * O XP gerado é proporcional à carga horária do curso.
 */
public class Curso extends Conteudo {

    private final int cargaHoraria;

    /**
     * @param titulo       título do curso
     * @param descricao    descrição do curso
     * @param cargaHoraria carga horária em horas (deve ser maior que zero)
     */
    public Curso(String titulo, String descricao, int cargaHoraria) {
        super(titulo, descricao);
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * XP = XP_PADRÃO × carga horária
     */
    @Override
    public double calcularXp() {
        return XP_PADRAO * cargaHoraria;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
