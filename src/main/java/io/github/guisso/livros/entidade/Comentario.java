/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.entidade;

/**
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 22/08/2021
 */
public class Comentario extends Entidade {

    private static final int MAX_LENGTH_NOME = 65;
    private static final int MAX_LENGTH_TEXTO = 350;
    private String autor;
    private String texto;
    private Long livroId;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        // Trunca para o comprimento máximo especificado pela classe
        this.autor = autor.substring(0, Math.min(autor.length(), MAX_LENGTH_NOME));
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        // Trunca para o comprimento máximo especificado pela classe
        this.texto = texto.substring(0, Math.min(texto.length(), MAX_LENGTH_TEXTO));
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "[" + autor + "] " + texto;
    }
}
