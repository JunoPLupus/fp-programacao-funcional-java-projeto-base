package exercicios;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Representa um aluno em uma instituição de ensino.
 * Classe baseada no exemplo do <a href="https://apexapps.oracle.com/pls/apex/f?p=44785:145:0::::P145_EVENT_ID,P145_PREV_PAGE:4887,143">exercicios.Curso JDK 8 MOOC: Lambdas and Streams Introduction</a>.
 *
 * @author Manoel Campos da Silva Filho
 */
@Getter
@Setter
public class Estudante {

    private int id;
    private String nome;
    private char sexo;
    private double nota;
    private int anoGraduacao;
    private Curso curso;

    /**
     * Cidade onde o estudante mora.
     */
    private Cidade cidade;

    /**
     * Cria um estudante.
     * Este construtor possui uma quantidade mais do que recomendável de parâmetros (3).
     * O mesmo é usado apenas para facilitar a geração de dados aleatórios,
     * mas é uma péssima prática em projetos reais.
     * No entanto, observe que o construtor não é público (é package),
     * não podendo ser usado fora do pacote.
     *
     * @param id matrícula do estudante
     * @param nome nome do estudante
     * @param sexo sexo do estudante
     * @param nota nota do estudante
     * @param anoGraduacao ano de graduação
     * @param curso curso matriculado
     */
    Estudante(int id, String nome, char sexo, double nota, int anoGraduacao, Curso curso, Cidade cidade){
        setId(id);
        setNome(nome);
        setSexo(sexo);
        setNota(nota);
        setAnoGraduacao(anoGraduacao);
        setCurso(curso);
        setCidade(cidade);
    }

    @Override
    public String toString(){
        final String cursos = String.format("%-35s", curso == null ? "" : "Curso: " + curso.getNome());
        return String.format(
                "Id: %6d Nome: %-30s Sexo: %c Nota: %5.2f Ano Grad: %4d %s",
                id, nome, sexo, nota, anoGraduacao, cursos);
    }

    public boolean isMulher(){
        return sexo == 'F';
    }

    public boolean isHomem(){
        return sexo == 'M';
    }

    public boolean hasCurso(){
        return curso != null;
    }

    public boolean isAprovado(){
        return nota >= 6;
    }
}
