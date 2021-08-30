/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.entidade.Comentario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações de persistência com a entidade Comentario.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.2, 22/08/2021
 */
public class ComentarioDao extends AbstractDao<Comentario, Long> {

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO comentario(id, autor, texto, livro_id) VALUES (default, ?, ?, ?);";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM comentario WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM comentario";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE comentario SET autor = ?, texto = ?, livro_id = ? WHERE id = ?;";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM comentario WHERE id = ?";
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Comentario comentario) {
        // Tenta definir valores junto à sentença SQL preparada para execução 
        // no banco de dados.
        try {
            if (comentario.getId() == null || comentario.getId() == 0) {
                pstmt.setString(1, comentario.getAutor());
                pstmt.setString(2, comentario.getTexto());
                pstmt.setLong(3, comentario.getLivroId());
            } else {
                pstmt.setString(1, comentario.getAutor());
                pstmt.setString(2, comentario.getTexto());
                pstmt.setLong(3, comentario.getLivroId());
                pstmt.setLong(4, comentario.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Comentario extrairObjeto(ResultSet resultSet) {
        // Cria referência para montagem do comentário
        Comentario comentario = new Comentario();

        // Tenta recuperar dados do registro retornado pelo banco de dados
        // e ajustar o estado do comentario a ser mapeado
        try {
            comentario.setId(resultSet.getLong("id"));
            comentario.setAutor(resultSet.getString("autor"));
            comentario.setTexto(resultSet.getString("texto"));
            comentario.setLivroId(resultSet.getLong("livro_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolve o comentario mapeado
        return comentario;
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
    public List<Comentario> extrairObjetos(ResultSet resultSet) {

        // Cria referência para inserção das comentarios a serem mapeados
        ArrayList<Comentario> comentarios = new ArrayList<>();

        // Tenta...
        try {
            // ... entquanto houver registros a serem processados
            while (resultSet.next()) {
                // Cria referência para montagem do comentario
                Comentario comentario = new Comentario();

                // Tenta recuperar dados do registro retornado pelo banco 
                // de dados e ajustar o estado do comentario a ser mapeado
                comentario.setId(resultSet.getLong("id"));
                comentario.setAutor(resultSet.getString("autor"));
                comentario.setTexto(resultSet.getString("texto"));
                comentario.setLivroId(resultSet.getLong("livro_id"));

                // Insere o comentario na lista de comentarios recuperados
                comentarios.add(comentario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de comentarios reconstituídos dos registros do banco 
        // de dados
        return comentarios;
    }

}
