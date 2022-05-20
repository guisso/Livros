/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.util.Util;
import io.github.guisso.livros.entidade.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações de persistência com a entidade Livro.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.1, 20/05/2022
 */
public class LivroDao extends Dao<Livro, Long> {

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO livro (id, titulo, assunto, edicao, localpublicacao, anopublicacao, extensao, dtcadastro, editora_id) VALUES (default, ?, ?, ?, ?, ?, ?, default, ?);";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM livro WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM livro";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE livro SET titulo = ?, assunto = ?, edicao = ?, localpublicacao = ?, anopublicacao = ?, extensao = ?, editora_id = ?  WHERE id = ?;";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        // IMPORTANTE: a exclusão pode ser bloqueada por haver chaves
        // estrangeiras vinculadas!
        return "DELETE FROM livro WHERE id = ?";
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param livro Entidade a ser inserida no banco de dados
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Livro livro) {
        // Tenta definir valores junto à sentença SQL preparada para execução 
        // no banco de dados.
        try {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAssunto());
            pstmt.setByte(3, livro.getEdicao());
            pstmt.setString(4, livro.getLocalPublicacao());
            pstmt.setShort(5, livro.getAnoPublicacao());
            pstmt.setShort(6, livro.getExtensao());
            pstmt.setLong(7, livro.getEditora().getId());

            if (livro.getId() != null && livro.getId() != 0) {
                pstmt.setLong(8, livro.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Livro extrairObjeto(ResultSet resultSet) {
        // Cria referência para montagem do livro
        Livro livro = new Livro();

        // Tenta recuperar dados do registro retornado pelo banco de dados
        // e ajustar o estado do livro a ser mapeado
        try {
//            livro.set...(resultSet.get...("???"));
            livro.setId(resultSet.getLong("id"));
            livro.setTitulo(resultSet.getString("titulo"));
            livro.setAssunto(resultSet.getString("assunto"));
            livro.setEdicao(resultSet.getByte("edicao"));
            livro.setLocalPublicacao(resultSet.getString("localpublicacao"));
            livro.setAnoPublicacao(resultSet.getShort("anopublicacao"));
            livro.setExtensao(resultSet.getShort("extensao"));
            livro.setDataCadastro(Util.convertDateToLocalDate(
                    resultSet.getDate("dtcadastro")));

            // Objetos provenientes de outras tabelas do banco de dados
            Long editoraId = resultSet.getLong("editora_id");
            livro.setEditora(new EditoraDao().localizarPorId(editoraId));

            // Localizar apenas os autores de um livro específico
            livro.setAutores(new AutorLivroDao()
                    .localizarAutoresPorLivro(livro));

            // Localizar apenas os comentários de um livro específico
            livro.setComentarios(new ComentarioDao()
                    .localizarComentariosPorLivro(livro));

            // A id do comentário (relacionamento um-para-um) é a mesma
            // do próprio livro
            livro.setResenha(new ResenhaDao().localizarPorId(livro.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolve o livro mapeado
        return livro;
    }

    /**
     * Cria objeto(s) a partir do(s) registro(s) fornecido(s) pelo banco de
     * dados.
     *
     * @param resultSet Resultado(s) proveniente(s) do banco de dados
     * relacional.
     * @return Lista de objeto(s) constituído(s).
     */
    @Override
    public List<Livro> extrairObjetos(ResultSet resultSet) {

        // Cria referência para inserção dos livros a serem mapeados
        ArrayList<Livro> livros = new ArrayList<>();

        // Tenta...
        try {
            // ... enquanto houver registros a serem processados
            while (resultSet.next()) {

                // Insere o livro na lista de livros recuperados
                livros.add(extrairObjeto(resultSet));
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de livros reconstituídos dos registros do banco 
        // de dados
        return livros;
    }

}
