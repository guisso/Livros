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
public class Editora extends Entidade {

    private static final int MAX_LENGTH = 100;
    private String nome;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        // Trunca para o comprimento máximo especificado pela classe
        this.nome = nome.substring(0, Math.min(nome.length(), MAX_LENGTH));
    }
    //</editor-fold>

    @Override
    public String toString() {
        return nome;
    }

}
