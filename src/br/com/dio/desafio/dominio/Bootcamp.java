package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representa um Bootcamp, que agrupa {@link Conteudo}s (Cursos e Mentorias)
 * e mantém um registro dos {@link Dev}s inscritos.
 *
 * <p>A duração padrão de um Bootcamp é de 45 dias a partir da data de criação.</p>
 */
public class Bootcamp {

    private static final int DURACAO_DIAS = 45;

    private String nome;
    private String descricao;
    private final LocalDate dataInicial = LocalDate.now();
    private final LocalDate dataFinal = dataInicial.plusDays(DURACAO_DIAS);
    private final Set<Dev> devsInscritos = new HashSet<>();
    private final Set<Conteudo> conteudos = new LinkedHashSet<>();

    /**
     * Adiciona um {@link Conteudo} ao Bootcamp.
     *
     * @param conteudo conteúdo a ser adicionado
     */
    public void adicionarConteudo(Conteudo conteudo) {
        Objects.requireNonNull(conteudo, "Conteúdo não pode ser nulo.");
        this.conteudos.add(conteudo);
    }

    /**
     * Inscreve um {@link Dev} no Bootcamp.
     * Chamado internamente por {@link Dev#inscreverBootcamp(Bootcamp)}.
     *
     * @param dev dev a ser inscrito
     */
    void inscreverDev(Dev dev) {
        Objects.requireNonNull(dev, "Dev não pode ser nulo.");
        this.devsInscritos.add(dev);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    /**
     * Retorna uma visão não modificável dos Devs inscritos.
     */
    public Set<Dev> getDevsInscritos() {
        return Collections.unmodifiableSet(devsInscritos);
    }

    /**
     * Retorna uma visão não modificável dos conteúdos do Bootcamp.
     */
    public Set<Conteudo> getConteudos() {
        return Collections.unmodifiableSet(conteudos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bootcamp bootcamp = (Bootcamp) o;
        return Objects.equals(nome, bootcamp.nome) &&
                Objects.equals(dataInicial, bootcamp.dataInicial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataInicial);
    }

    @Override
    public String toString() {
        return "Bootcamp{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", totalConteudos=" + conteudos.size() +
                ", totalDevsInscritos=" + devsInscritos.size() +
                '}';
    }
}
