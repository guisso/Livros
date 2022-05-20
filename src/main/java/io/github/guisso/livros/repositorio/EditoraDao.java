/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.entidade.Editora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações de persistência com a entidade Editora.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.2, 22/08/2021
 */
public class EditoraDao extends Dao<Editora, Long> {

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO editora(id, nome) VALUES (default, ?);";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM editora WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM editora";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE editora SET nome = ? WHERE id = ?;";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM editora WHERE id = ?";
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Editora editora) {
        // Tenta definir valores junto à sentença SQL preparada para execução 
        // no banco de dados.
        try {
            pstmt.setString(1, editora.getNome());
            
            if (editora.getId() != null
                    && editora.getId() != 0) {
                pstmt.setLong(2, editora.getId());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Editora extrairObjeto(ResultSet resultSet) {
        // Cria referência para montagem da edirora
        Editora editora = new Editora();

        // Tenta recuperar dados do registro retornado pelo banco de dados
        // e ajustar o estado da editora a ser mapeada
        try {
            editora.setId(resultSet.getLong("id"));
            editora.setNome(resultSet.getString("nome"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolve a editora mapeada
        return editora;
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
    public List<Editora> extrairObjetos(ResultSet resultSet) {

        // Cria referência para inserção das editoras a serem mapeadas
        ArrayList<Editora> editoras = new ArrayList<>();

        // Tenta...
        try {
            // ... entquanto houver registros a serem processados
            while (resultSet.next()) {
                // Insere a editora na lista de editoras recuperadas
                editoras.add(extrairObjeto(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditoraDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de editoras reconstituídas dos registros do banco 
        // de dados
        return editoras;
    }

}
