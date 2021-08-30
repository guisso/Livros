/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.repositorio;

import java.util.List;

/**
 * Interface para classes DAO.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 22/08/2021
 */
public interface IDao<T, K> {

    public K salvar(T o);

    public T localizarPorId(K id);

    public List<T> localizarTodos();

    public Boolean excluir(T o);
}
