package br.com.dio.desafio.dominio;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representa um desenvolvedor (Dev) que pode se inscrever em {@link Bootcamp}s,
 * progredir nos conteúdos e acumular XP.
 */
public class Dev {

    private String nome;
    private final Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    /**
     * Inscreve este Dev em um {@link Bootcamp}, copiando todos os seus conteúdos
     * para a lista de conteúdos inscritos.
     *
     * @param bootcamp bootcamp no qual o dev será inscrito
     */
    public void inscreverBootcamp(Bootcamp bootcamp) {
        Objects.requireNonNull(bootcamp, "Bootcamp não pode ser nulo.");
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.inscreverDev(this);
    }

    /**
     * Avança para o próximo conteúdo inscrito, movendo-o para a lista de concluídos.
     *
     * @throws IllegalStateException se o Dev não estiver inscrito em nenhum conteúdo
     */
    public void progredir() {
        conteudosInscritos.stream()
                .findFirst()
                .ifPresentOrElse(conteudo -> {
                    conteudosConcluidos.add(conteudo);
                    conteudosInscritos.remove(conteudo);
                }, () -> {
                    throw new IllegalStateException(
                            "O Dev '" + nome + "' não está inscrito em nenhum conteúdo.");
                });
    }

    /**
     * Calcula o total de XP acumulado com base nos conteúdos concluídos.
     *
     * @return soma de XP de todos os conteúdos concluídos
     */
    public double calcularTotalXp() {
        return conteudosConcluidos.stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma visão não modificável dos conteúdos inscritos.
     */
    public Set<Conteudo> getConteudosInscritos() {
        return Collections.unmodifiableSet(conteudosInscritos);
    }

    /**
     * Retorna uma visão não modificável dos conteúdos concluídos.
     */
    public Set<Conteudo> getConteudosConcluidos() {
        return Collections.unmodifiableSet(conteudosConcluidos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Dev{" +
                "nome='" + nome + '\'' +
                ", conteudosInscritos=" + conteudosInscritos.size() +
                ", conteudosConcluidos=" + conteudosConcluidos.size() +
                ", totalXp=" + calcularTotalXp() +
                '}';
    }
}
