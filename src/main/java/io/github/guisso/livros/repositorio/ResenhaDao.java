/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.entidade.Resenha;
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
public class ResenhaDao extends AbstractDao<Resenha, Long> {

    /**
     * Método com comportamento diferenciado do exposto na superclasse devido à
     * necessidade de uma id em qualquer operação, mesmo na inserção de um novo
     * registro, dado que a id da Resenha deve corresponder à id do Livro.
     *
     * @param o Objeto a ser salvo no banco de dados.
     * @return Valor da chave primária do objeto persistido.
     */
    @Override
    public Long salvar(Resenha o) {

        // Novo registro: nenhum objeto localizado para atualização
        if (localizarPorId(o.getId()) == null) {

            // try-with-resources libera recurso ao final do bloco (PreparedStatement)
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para inserção de registros
                            getDeclaracaoInsert())) {

                // Prepara a declaração com os dados do objeto passado
                pstmt.setLong(1, o.getId());
                pstmt.setString(2, o.getTexto());

                // Executa o comando SQL
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Mesma operação de atualização da superclasse.
            // Como a id terá um valor, sempre executará a atualização
            // nesta chamada.
            super.salvar(o);
        }

        // Cast requerido para adaptação do tipo pois, mesmo que a id seja sempre
        // longa, esse trecho de código não reconhece tal tipo implicitamente
        return o.getId();
    }

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO resenha(id, texto) VALUES (?, ?);";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM resenha WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM resenha";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE resenha SET texto = ? WHERE id = ?;";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM resenha WHERE id = ?";
    }
    
    /**
     * Insere os valores do objeto na senteça SQL específica para 
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Resenha o) {
        try {
            // Prepara a declaração com os dados do objeto passado
            pstmt.setString(1, o.getTexto());
            pstmt.setLong(2, o.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ResenhaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Resenha extrairObjeto(ResultSet resultSet) {
        // Cria referência para montagem da edirora
        Resenha resenha = new Resenha();

        // Tenta recuperar dados do registro retornado pelo banco de dados
        // e ajustar o estado da resenha a ser mapeada
        try {
            resenha.setId(resultSet.getLong("id"));
            resenha.setTexto(resultSet.getString("texto"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Devolve a resenha mapeada
        return resenha;
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
    public List<Resenha> extrairObjetos(ResultSet resultSet) {

        // Cria referência para inserção das resenhas a serem mapeadas
        ArrayList<Resenha> resenhas = new ArrayList<>();

        // Tenta...
        try {
            // ... entquanto houver registros a serem processados
            while (resultSet.next()) {
                // Cria referência para montagem da resenha
                Resenha resenha = new Resenha();

                // Tenta recuperar dados do registro retornado pelo banco 
                // de dados e ajustar o estado da resenha a ser mapeada
                resenha.setId(resultSet.getLong("id"));
                resenha.setTexto(resultSet.getString("texto"));

                // Insere a resenha na lista de resenhas recuperadas
                resenhas.add(resenha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResenhaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de resenhas reconstituídas dos registros do banco 
        // de dados
        return resenhas;
    }

}
