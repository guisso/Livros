/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.entidade.Autor;
import io.github.guisso.livros.entidade.AutorLivro;
import io.github.guisso.livros.entidade.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações de persistência com a entidade AutorLivro que é responsável pela
 * junção muitos-para-muitos entre as tabelas Autor e Livro.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.1, 20/05/2022
 */
public class AutorLivroDao {

    /**
     * Executa o procedimento de salvamento (inserção ou atualização) do objeto
     * mapeado no banco de dados.
     *
     * @param o Objeto a ser salvo no banco de dados.
     */
    public void salvar(AutorLivro o) {

        AutorLivro autorLivro = localizarPorId(o.getAutorId(), o.getLivroId());

        // Novo registro
        if (autorLivro == null) {

            // try-with-resources libera recurso ao final do bloco (PreparedStatement)
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para inserção de registros
                            getDeclaracaoInsert())) {

                // Prepara a declaração com os dados do objeto passado
                pstmt.setLong(1, o.getAutorId());
                pstmt.setLong(2, o.getLivroId());

                // Executa o comando SQL
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Exclui o registro do objeto no banco de dados.
     *
     * @param o Objeto a ser excluído.<br>
     * <i>OBS.: o único valor útil é a identidade do objeto mapeado.</i>
     * @return Condição de sucesso ou falha na exclusão.
     */
    public Boolean excluir(AutorLivro o) {
        // Recupera a identidade (chave primária composta) 
        // do objeto a ser excluído
        Long autorId = o.getAutorId();
        Long livroId = o.getLivroId();

        // Se há uma identidade válida...
        if (autorId != null && autorId != 0
                && livroId != null && livroId != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            getDeclaracaoDelete())) {

                // Prepara a declaração com os dados do objeto passado
                pstmt.setLong(1, o.getAutorId());
                pstmt.setLong(2, o.getLivroId());

                // Executa o comando SQL
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            return false;
        }

        return true;
    }

    /**
     * Recupera um dado objeto mapeado para o banco de dados por meio de sua
     * chave de identidade.
     *
     * @param id Identidade do objeto.
     * @return Objeto segundo registro persistido.
     */
    public AutorLivro localizarPorId(Long autorId, Long livroId) {
        // Declara referência para reter o objeto a ser recuperado
        AutorLivro objeto = null;

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para busca por chave primária
                        getDeclaracaoSelectPorId())) {

            // Prepara a declaração com os dados do objeto passado
            pstmt.setLong(1, autorId);
            pstmt.setLong(2, livroId);

            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();

            // Se há resultado retornado...
            if (resultSet.next()) {
                // ... extrai objeto do respectivo registro do banco de dados
                objeto = extrairObjeto(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve nulo (objeto não encontrado) ou o objeto recuperado
        return objeto;
    }

    /**
     * Recupera todos os objetos mapeados para o banco de dados do tipo
     * específico.
     *
     * @return Lista (geralmente um <code>ArrayList<T></code>) de objetos
     * persistidos.
     */
    public List<Autor> localizarAutoresPorLivro(Livro o) {

        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Autor> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        getDeclaracaoSelectTodosAutoresPorLivro())) {

            // Prepara a declaração com os dados do objeto passado
            pstmt.setLong(1, o.getId());

            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();

            //
            // Reaproveita o procedimento de extração de objetos
            // autor elaborado em AutorDao
            //
            objetos = new AutorDao().extrairObjetos(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve uma lista vazia (nenhum registro encontrado) 
        // ou a relação de objeto(s) recuperado(s)
        return objetos;
    }

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    public String getDeclaracaoInsert() {
        return "INSERT INTO autorlivro(autor_id, livro_id) VALUES (?, ?);";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM autorlivro WHERE autor_id = ? AND livro_id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM autorlivro";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    public String getDeclaracaoSelectTodosAutoresPorLivro() {
        return "SELECT a.id, a.nome " // Somente (e tudo) de Autor
                + "FROM autor a "
                + "INNER JOIN autorlivro al "
                + "ON a.id = al.autor_id "
                + "WHERE al.livro_id  = ?;";
    }

    /**
     * Operação de atualização não é permitida.
     *
     * @return null.
     */
//    public String getDeclaracaoUpdate() {
//        return null;
//    }
//
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    public String getDeclaracaoDelete() {
        return "DELETE FROM autorlivro WHERE autor_id = ? AND livro_id = ?";
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    public AutorLivro extrairObjeto(ResultSet resultSet) {
        // Cria referência para montagem do autor-livro
        AutorLivro autorLivro = new AutorLivro();

        // Tenta recuperar dados do registro retornado pelo banco de dados
        // e ajustar o estado do autor-livro a ser mapeado
        try {
            autorLivro.setAutorId(resultSet.getLong("autor_id"));
            autorLivro.setLivroId(resultSet.getLong("livro_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolve o autor-livro mapeado
        return autorLivro;
    }

    /**
     * Cria objeto(s) a partir do(s) registro(s) fornecido(s) pelo banco de
     * dados.
     *
     * @param resultSet Resultado(s) proveniente(s) do banco de dados
     * relacional.
     * @return Lista de objeto(s) constituído(s).
     */
    public List<AutorLivro> extrairObjetos(ResultSet resultSet) {

        // Cria referência para inserção das autores-livros a serem mapeados
        ArrayList<AutorLivro> autoresLivros = new ArrayList<>();

        // Tenta...
        try {
            // ... enquanto houver registros a serem processados
            while (resultSet.next()) {
                autoresLivros.add(extrairObjeto(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorLivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de autores-livros reconstituídos
        // dos registros do banco de dados
        return autoresLivros;
    }

}
