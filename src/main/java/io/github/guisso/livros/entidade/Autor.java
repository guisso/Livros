/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.entidade;

import java.util.Objects;

/**
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 22/08/2021
 */
public class Autor extends Entidade {

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

    /**
     * Método requerido para a seleção dos múltiplos autores ligados
     * a um livro em uma lista na GUI. O que torna dois objetos idênticos
     * é o nome do autor, que deve ser único no banco de dados.
     * 
     * @param obj Objeto a ser comparado.
     * @return Identificação entre objetos.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autor other = (Autor) obj;
        return Objects.equals(this.nome, other.nome);
    }

    
    @Override
    public String toString() {
        return nome;
    }
}
