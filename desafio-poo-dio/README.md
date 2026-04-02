# 🚀 Desafio POO — Bootcamp DIO

> Projeto Java que modela o domínio de um **Bootcamp** utilizando os quatro pilares da **Programação Orientada a Objetos**: Abstração, Encapsulamento, Herança e Polimorfismo.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Diagrama de Classes](#diagrama-de-classes)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Melhorias Implementadas](#melhorias-implementadas)
- [Conceitos de POO Aplicados](#conceitos-de-poo-aplicados)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuindo](#contribuindo)

---

## 📖 Sobre o Projeto

Este projeto simula o funcionamento de um **Bootcamp de desenvolvimento**, onde Devs podem se inscrever, progredir nos conteúdos (Cursos e Mentorias) e acumular XP (pontos de experiência) ao concluí-los.

O foco é demonstrar na prática como os conceitos de POO se traduzem em código Java limpo e bem estruturado.

---

## 🗂 Diagrama de Classes

```
            ┌─────────────────┐
            │   <<abstract>>  │
            │    Conteudo     │
            │─────────────────│
            │ - titulo        │
            │ - descricao     │
            │─────────────────│
            │ + calcularXp()  │
            └────────┬────────┘
                     │ herança
          ┌──────────┴──────────┐
          │                     │
   ┌──────┴──────┐       ┌──────┴──────┐
   │    Curso    │       │  Mentoria   │
   │─────────────│       │─────────────│
   │ cargaHoraria│       │ data        │
   │─────────────│       │─────────────│
   │ calcularXp()│       │ calcularXp()│
   └─────────────┘       └─────────────┘

   ┌─────────────────────────────┐
   │          Bootcamp           │
   │─────────────────────────────│
   │ - nome                      │
   │ - descricao                 │
   │ - dataInicial               │
   │ - dataFinal                 │
   │ - conteudos: Set<Conteudo>  │
   │ - devsInscritos: Set<Dev>   │
   │─────────────────────────────│
   │ + adicionarConteudo()       │
   │ + inscreverDev()            │
   └─────────────────────────────┘

   ┌─────────────────────────────────┐
   │              Dev                │
   │─────────────────────────────────│
   │ - nome                          │
   │ - conteudosInscritos            │
   │ - conteudosConcluidos           │
   │─────────────────────────────────│
   │ + inscreverBootcamp(Bootcamp)   │
   │ + progredir()                   │
   │ + calcularTotalXp(): double     │
   └─────────────────────────────────┘
```

---

## ✅ Funcionalidades

- Criar **Cursos** (com carga horária) e **Mentorias** (com data)
- Montar um **Bootcamp** adicionando conteúdos a ele
- **Inscrever Devs** em um Bootcamp
- **Progredir** conteúdo a conteúdo (mover de inscrito → concluído)
- **Calcular o XP total** acumulado por um Dev
- Validação de dados na criação dos objetos (título, carga horária, data)
- Tratamento de erro ao tentar progredir sem conteúdos inscritos

---

## 🛠 Pré-requisitos

- Java JDK 11 ou superior
- IDE Java (IntelliJ IDEA, Eclipse, VS Code + extensão Java)
- Git

---

## ▶️ Como Executar

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/desafio-poo-dio.git

# 2. Acesse a pasta do projeto
cd desafio-poo-dio

# 3. Compile os fontes
javac -d out/production/desafio-poo-dio \
      src/br/com/dio/desafio/dominio/*.java \
      src/Main.java

# 4. Execute
java -cp out/production/desafio-poo-dio Main
```

**Saída esperada:**
```
=== Camila ===
Conteúdos inscritos : [Curso Java, Curso JavaScript, Mentoria de Java]
Conteúdos inscritos : [Mentoria de Java]
Conteúdos concluídos: [Curso Java, Curso JavaScript]
XP total            : 120.0

=== João ===
Conteúdos inscritos : [Curso Java, Curso JavaScript, Mentoria de Java]
Conteúdos inscritos : []
Conteúdos concluídos: [Curso Java, Curso JavaScript, Mentoria de Java]
XP total            : 150.0

=== Tentativa de progredir sem conteúdo ===
Erro esperado: O Dev 'João' não está inscrito em nenhum conteúdo.
```

---

## 🔧 Melhorias Implementadas

Em relação ao código original, as seguintes melhorias foram aplicadas:

### 1. Construtores com parâmetros obrigatórios
`Conteudo`, `Curso` e `Mentoria` passaram a usar construtores que exigem os campos essenciais, eliminando a possibilidade de criar objetos em estado inválido (sem título, sem descrição, etc.).

### 2. Validação de dados na entrada
Adicionadas verificações com `IllegalArgumentException` para garantir que título e descrição não sejam nulos/vazios, e que a carga horária de um Curso seja maior que zero.

### 3. Coleções imutáveis nos getters
Os métodos `getConteudos()`, `getDevsInscritos()`, `getConteudosInscritos()` e `getConteudosConcluidos()` agora retornam `Collections.unmodifiableSet(...)`, protegendo o estado interno dos objetos contra modificações externas acidentais.

### 4. Encapsulamento da inscrição no Bootcamp
A inscrição de um `Dev` no `Bootcamp` é controlada pelo método `Dev.inscreverBootcamp()`, que internamente chama `Bootcamp.inscreverDev()` (visibilidade de pacote). Isso evita que código externo manipule diretamente a coleção `devsInscritos`.

### 5. `Dev.progredir()` lança exceção ao invés de imprimir no `System.err`
O método agora lança `IllegalStateException` com uma mensagem clara, permitindo que o chamador decida como tratar o erro — comportamento mais alinhado com boas práticas Java.

### 6. `calcularTotalXp()` substituído por Stream
O iterator manual foi substituído pela expressão mais clara e idiomática:
```java
return conteudosConcluidos.stream()
        .mapToDouble(Conteudo::calcularXp)
        .sum();
```

### 7. `equals` / `hashCode` simplificados
`Bootcamp` passa a se identificar por `nome + dataInicial`, e `Dev` somente por `nome`, tornando as comparações mais enxutas e coerentes com o domínio.

### 8. `toString()` adicionado em `Bootcamp` e `Dev`
Facilita o debug e exibição dos objetos no console.

### 9. Código comentado removido
Trechos comentados foram removidos do `Main.java`, deixando o código limpo.

### 10. Javadoc nas classes e métodos principais
Documentação inline adicionada para facilitar a compreensão do domínio.

---

## 📚 Conceitos de POO Aplicados

| Pilar | Onde é aplicado |
|---|---|
| **Abstração** | Classe abstrata `Conteudo` define o contrato `calcularXp()` sem implementar |
| **Encapsulamento** | Atributos `private`/`final`, getters sem setters desnecessários, coleções protegidas |
| **Herança** | `Curso` e `Mentoria` herdam de `Conteudo` e reaproveitam `titulo`, `descricao` e `XP_PADRAO` |
| **Polimorfismo** | `Dev` trata `Curso` e `Mentoria` como `Conteudo`, chamando `calcularXp()` polimorficamente |

---

## 📁 Estrutura do Projeto

```
desafio-poo-dio/
└── src/
    ├── Main.java
    └── br/com/dio/desafio/dominio/
        ├── Conteudo.java   ← classe abstrata base
        ├── Curso.java      ← conteúdo com carga horária
        ├── Mentoria.java   ← conteúdo com data
        ├── Bootcamp.java   ← agrupador de conteúdos e devs
        └── Dev.java        ← ator principal: se inscreve e progride
```

---

## 🤝 Contribuindo

Este repositório é de fins educacionais — contribuições são bem-vindas!

- ⭐ Deixe uma estrela se o projeto te ajudou
- 🐛 Abra uma *issue* para reportar problemas
- 🔀 Faça um *fork* e envie um *pull request* com melhorias

---

*Projeto baseado no código original de [Camila Cavalcante](https://github.com/cami-la/), criado para a plataforma [Digital Innovation One](https://web.digitalinnovation.one/) como desafio prático de Programação Orientada a Objetos com Java.*
