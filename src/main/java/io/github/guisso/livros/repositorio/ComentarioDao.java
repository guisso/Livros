/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import io.github.guisso.livros.entidade.Comentario;
import io.github.guisso.livros.entidade.Livro;
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
public class ComentarioDao extends Dao<Comentario, Long> {

    /**
     * Recupera todos os objetos mapeados para o banco de dados do tipo
     * específico.
     *
     * @return Lista (geralmente um <code>ArrayList<T></code>) de objetos
     * persistidos.
     */
    public List<Comentario> localizarComentariosPorLivro(Livro livro) {

        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Comentario> comentarios = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try ( PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        getDeclaracaoSelectComentariosPorLivro())) {

            // Prepara a consulta com os parâmetros adequados
            pstmt.setLong(1, livro.getId());

            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();

            // Extrai objeto(s) do(s) respectivo(s) registro(s) do banco de dados
            comentarios = extrairObjetos(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve uma lista vazia (nenhum registro encontrado) 
        // ou a relação de objeto(s) recuperado(s)
        return comentarios;
    }

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
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    private String getDeclaracaoSelectComentariosPorLivro() {
        return "SELECT * FROM comentario WHERE livro_id = ?";
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
            pstmt.setString(1, comentario.getAutor());
            pstmt.setString(2, comentario.getTexto());
            pstmt.setLong(3, comentario.getLivroId());

            if (comentario.getId() != null
                    && comentario.getId() != 0) {
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
                // Insere o comentario na lista de comentarios recuperados
                comentarios.add(extrairObjeto(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Devolve a lista de comentarios reconstituídos dos registros do banco 
        // de dados
        return comentarios;
    }

}
