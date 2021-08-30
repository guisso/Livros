/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.entidade.Autor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações de persistência com a entidade Autor.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.2, 22/08/2021
 */
public class AutorDao extends AbstractDao<Autor, Long> {

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO autor(id, nome) VALUES (default, ?);";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM autor WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM autor";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE autor SET nome = ? WHERE id = ?;";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM autor WHERE id = ?";
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Autor autor) {
        // Tenta definir valores junto à sentença SQL preparada para execução 
        // no banco de dados.
        try {
            if (autor.getId() == null || autor.getId() == 0) {
                pstmt.setString(1, autor.getNome());
            } else {
                pstmt.setString(1, autor.getNome());
                pstmt.setLong(2, autor.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Autor extrairObjeto(ResultSet resultSet) {
        // Cria referência para montagem do autor
        Autor autor = new Autor();

        // Tenta recuperar dados do registro retornado pelo banco de dados
        // e ajustar o estado do autor a ser mapeado
        try {
            autor.setId(resultSet.getLong("id"));
            autor.setNome(resultSet.getString("nome"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolve o autor mapeado
        return autor;
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
    public List<Autor> extrairObjetos(ResultSet resultSet) {

        // Cria referência para inserção das autors a serem mapeados
        ArrayList<Autor> autores = new ArrayList<>();
        
        // Tenta...
        try {
            // ... enquanto houver registros a serem processados
            while (resultSet.next()) {
                // Cria referência para montagem do autor
                Autor autor = new Autor();

                // Tenta recuperar dados do registro retornado pelo banco 
                // de dados e ajustar o estado do autor a ser mapeado
                autor.setId(resultSet.getLong("id"));
                autor.setNome(resultSet.getString("nome"));

                // Insere o autor na lista de autores recuperados
                autores.add(autor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de autores reconstituídos dos registros do banco 
        // de dados
        return autores;
    }

}
