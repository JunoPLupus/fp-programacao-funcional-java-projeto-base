package exercicios;

import exercicios.base.Aula;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;

/**
 * Esta é uma classe para você poder implementar as atividades propostas no README.
 * Você <b>NÃO</b> deve alterar:
 * <ul>
 *     <li>a estrutura deste arquivo;</li>
 *     <li>o nome da classe, dos métodos ou dos atributos;</li>
 *     <li>parâmetros e tipo de retorno dos métodos.</li>
 * </ul>
 *
 * <b>Mas você PRECISA alterar valores dos atributos existentes</b>.
 *
 * <p>Você pode alterar o código interno dos métodos, criar métodos auxiliares que podem ser chamados
 * pelos existentes, mas não deve alterar a estrutura dos métodos disponíveis.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Aula06 extends Aula {
    /**
     * {@link Predicate<Estudante>} que seleciona somente as mulheres
     * matriculadas em algum curso e com nota maior ou igual a 6.
     * Este deve ser um predicado composto usando {@link Predicate#and(Predicate)}.
     * Você deve trocar o valor armazenado ao atributo para ele seguir a regra definida acima.
     */
    private final Predicate<Estudante> isMulherAprovada = ((Predicate<Estudante>)Estudante::isMulher).and(Estudante::isAprovado);

    private final Comparator<Estudante> notaCrescente = comparingDouble(Estudante::getNota);
    private final Comparator<Estudante> notaDecrescente = notaCrescente.reversed();

    private final Comparator<Estudante> cursoCrescente = comparing(Estudante::getCurso);
    private final Comparator<Estudante> cursoDecrescente = cursoCrescente.reversed();

    /**
     * Você pode chamar os métodos existentes e outros que você criar aqui,
     * incluir prints e fazer o que desejar neste método para conferir os valores retornados pelo seu método.
     * Para verificar se sua implementação está correta, clique com o botão direito no nome do projeto na aba esquerda
     * do IntelliJ e selecione a opção "Run All Tests".
     */
    public Aula06() {
//        getEstudantesMulheresAprovadas().forEach(System.out::println);
//        getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota().forEach(System.out::println);
        getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente().forEach(System.out::println);
//        getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente().forEach(System.out::println);
//        getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente().forEach(System.out::println);
//        getEstudantesMulheresAprovadasNaoOrdenadasModificavel().clear();
    }

    /**
     * Veja o método construtor {@link #Aula06()}.
     */
    public static void main(String[] args) {
        new Aula06();
    }

    private Stream<Estudante> getStreamMulheresAprovadas() {
        return estudantes
                .stream()
                .filter(isMulherAprovada);
    }

    private Stream<Estudante> getStreamMulheresAprovadasOrdenado(Comparator<Estudante> comparator1, Comparator<Estudante> comparator2) {
        return getStreamMulheresAprovadas()
                .sorted(comparator1.thenComparing(comparator2));
    }

    /**
     * Obtém uma Lista <b>NÃO-MODIFICÁVEL</b> de mulheres matriculadas e aprovadas em algum curso
     * O método usa o predicado {@link #isMulherAprovada} para filtrar a lista de estudantes.
     * Desta forma, você precisa definir um predicado composto com {@link Predicate#and(Predicate)}
     * para tal atributo.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #isMulherAprovada}
     */
    public List<Estudante> getEstudantesMulheresAprovadas() {
        return getStreamMulheresAprovadas()
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada por curso e nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #isMulherAprovada}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota() {
        return getStreamMulheresAprovadasOrdenado(cursoCrescente, notaCrescente)
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada de forma decrescente pelo nome do curso e crescente pela nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #isMulherAprovada}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente() {
        return getStreamMulheresAprovadasOrdenado(cursoDecrescente, notaCrescente)
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas na ordem original retornada pela Stream.
     * A lista deve ser <b>MODIFICÁVEL</b>.
     *
     * @return uma Lista <b>MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #isMulherAprovada}
     */
    public List<Estudante> getEstudantesMulheresAprovadasNaoOrdenadasModificavel() {
        return getStreamMulheresAprovadas()
                .collect(Collectors.toList());
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada de forma decrescente tanto pelo nome do curso quanto pela nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #isMulherAprovada}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente() {
        return getStreamMulheresAprovadasOrdenado(cursoDecrescente, notaDecrescente)
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada de forma crescente pelo nome do curso e descrecente pela nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #isMulherAprovada}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente() {
        return getStreamMulheresAprovadasOrdenado(cursoCrescente, notaDecrescente)
                .toList();
    }
}
