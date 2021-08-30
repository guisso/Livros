/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.entidade;

import java.time.LocalDate;
import java.util.List;

/**
 * Representação de um autor de obras.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 22/08/2021
 */
public class Livro extends Entidade {

    private static final int MAX_LENGTH = 100;
    private String titulo;
    private String assunto;
    private Byte edicao;
    private String localPublicacao;
    private Short anoPublicacao;
    private Short extensao;
    private LocalDate dataCadastro;
    private Editora editora;
    private List<Autor> autores;
    private List<Comentario> comentarios;
    private Resenha resenha;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        // Trunca para o comprimento máximo especificado pela classe
        this.titulo = titulo.substring(0, Math.min(titulo.length(), MAX_LENGTH));
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        // Trunca para o comprimento máximo especificado pela classe
        this.assunto = assunto.substring(0, Math.min(assunto.length(), MAX_LENGTH));
    }

    public Byte getEdicao() {
        return edicao;
    }

    public void setEdicao(Byte edicao) {
        this.edicao = edicao;
    }

    public String getLocalPublicacao() {
        return localPublicacao;
    }

    public void setLocalPublicacao(String localPublicacao) {
        // Trunca para o comprimento máximo especificado pela classe
        this.localPublicacao = localPublicacao.substring(0, Math.min(localPublicacao.length(), MAX_LENGTH));
    }

    public Short getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Short anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Short getExtensao() {
        return extensao;
    }

    public void setExtensao(Short extensao) {
        this.extensao = extensao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Resenha getResenha() {
        return resenha;
    }

    public void setResenha(Resenha resenha) {
        this.resenha = resenha;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return titulo;
    }

}
