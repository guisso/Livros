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
public class Resenha extends Entidade {

    private static final int MAX_LENGTH = 2000;
    private String texto;

    public Resenha(String texto) {
        this.texto = texto;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        // Trunca para o comprimento máximo especificado pela classe
        this.texto = texto.substring(0, Math.min(texto.length(), MAX_LENGTH));
    }
    //</editor-fold>

    @Override
    public String toString() {
        return texto;
    }

}
