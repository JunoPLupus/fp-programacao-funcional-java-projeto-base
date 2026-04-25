package exercicios;

import exercicios.base.Aula;
import lombok.NonNull;

import java.util.stream.Stream;

/**
 * Esta é uma classe para você poder implementar as atividades propostas no README.
 * Você <b>NÃO</b> deve alterar:
 * <ul>
 *     <li>a estrutura deste arquivo;</li>
 *     <li>o nome da classe, dos métodos ou dos atributos;</li>
 *     <li>parâmetros e tipo de retorno dos métodos.</li>
 * </ul>
 *
 * <p>Você pode alterar o código interno dos métodos, criar métodos auxiliares que podem ser chamados
 * pelos existentes, mas não deve alterar a estrutura dos métodos disponíveis.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Aula04 extends Aula {

    /**
     * Você pode chamar os métodos existentes e outros que você criar aqui,
     * incluir prints e fazer o que desejar neste método para conferir os valores retornados pelo seu método.
     * Para verificar se sua implementação está correta, clique com o botão direito no nome do projeto na aba esquerda
     * do IntelliJ e selecione a opção "Run All Tests".
     */
    public Aula04() {
        final var curso = generator.CURSOS[3];
        final char homem = 'M';
        final char mulher = 'F';

        estudantes.forEach(System.out::println);

        System.out.printf("\nMaior nota de todos os Estudantes: %.2f%n", maiorNotaTodosEstudantes(estudantes.stream()));
        System.out.printf("Maior nota dos Estudantes homens: %.2f%n", maiorNotaHomens(estudantes.stream()));
        System.out.printf("Maior nota das mulheres do curso de %s: %.2f%n", curso.getNome(), maiorNotaCursoAndSexo(estudantes.stream(), curso, mulher));
        System.out.printf("Média de notas dos Estudantes do curso de %s: %.2f%n", curso.getNome(), mediaNotaTodosEstudantesCurso(estudantes.stream(), curso));
        System.out.printf("Total dos homens do curso de %s: %d%n", curso.getNome(), totalEstudantesCursoAndSexo(estudantes.stream(), curso, homem));
        System.out.printf("Total das mulheres do curso de %s: %d%n", curso.getNome(), totalEstudantesCursoAndSexo(estudantes.stream(), curso, mulher));
    }

    /**
     * Veja o método construtor {@link #Aula04()}.
     */
    public static void main(String[] args) {
        new Aula04();
    }

    //region Métodos Auxiliares
    protected Stream<Estudante> filtrarEstudantesPorSexo(@NonNull final Stream<Estudante> stream, final char sexo) {
        return stream.filter(e -> e.isEqualSexo(sexo));
    }

    protected Stream<Estudante> filtrarEstudantesPorCurso(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso) {
        return stream.filter(e -> e.isEqualCurso(curso));
    }

    protected Stream<Estudante> filtrarEstudantesPorCursoESexo(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso, char sexo) {
        return filtrarEstudantesPorCurso(
                filtrarEstudantesPorSexo(stream, sexo), curso
        );
    }

    protected double retornarMaiorNota(@NonNull final Stream<Estudante> stream) {
        return stream
                .mapToDouble(Estudante::getNota)
                .max()
                .orElse(0.0);
    }

    protected double retornarMediaNota(@NonNull final Stream<Estudante> stream) {
        return stream
                .mapToDouble(Estudante::getNota)
                .average()
                .orElse(0.0);
    }
    //endregion

    //region Métodos Principais
    protected double maiorNotaCursoAndSexo(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso, final char sexo) {

        Stream<Estudante> alunosFiltrados = filtrarEstudantesPorCursoESexo(stream, curso, sexo);

        return retornarMaiorNota(alunosFiltrados);
    }

    protected long totalEstudantesCursoAndSexo(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso, final char sexo) {

        Stream<Estudante> alunosFiltrados = filtrarEstudantesPorCursoESexo(stream, curso, sexo);

        return alunosFiltrados.count();
    }

    protected double mediaNotaTodosEstudantesCurso(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso) {

        Stream<Estudante> alunosFiltrados = filtrarEstudantesPorCurso(stream, curso);

        return retornarMediaNota(alunosFiltrados);
    }

    protected double maiorNotaTodosEstudantes(@NonNull final Stream<Estudante> stream){
        return retornarMaiorNota(stream);
    }


    protected double maiorNotaHomens(@NonNull final Stream<Estudante> stream){
        return retornarMaiorNota(
                stream.filter(Estudante::isHomem));
    }
    //endregion
}

