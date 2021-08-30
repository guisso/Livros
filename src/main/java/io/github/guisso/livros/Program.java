/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros;

import io.github.guisso.livros.entidade.Autor;
import io.github.guisso.livros.entidade.AutorLivro;
import io.github.guisso.livros.entidade.Comentario;
import io.github.guisso.livros.entidade.Editora;
import io.github.guisso.livros.entidade.Livro;
import io.github.guisso.livros.entidade.Resenha;
import io.github.guisso.livros.repositorio.AutorDao;
import io.github.guisso.livros.repositorio.AutorLivroDao;
import io.github.guisso.livros.repositorio.ComentarioDao;
import io.github.guisso.livros.repositorio.EditoraDao;
import io.github.guisso.livros.repositorio.LivroDao;
import io.github.guisso.livros.repositorio.ResenhaDao;
import java.util.Arrays;

/**
 * Testes com as classes do sistema.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 24/08/2021
 */
public class Program {

    public static void main(String[] args) {

        //
        // Editora
        //
        
//        Editora editora = new Editora();
//        editora.setNome("Ática");
//
//        EditoraDao editoraDao = new EditoraDao();
//        Long id = editoraDao.salvar(editora);
//        editora.setId(id);
//
//        System.out.println("Editora : "
//                + editoraDao.localizarPorId(editora.getId()));
//
//        System.out.println("Editoras: "
//                + editoraDao.localizarTodos());
        
        //
        // Autor
        //
        
//        Autor autor = new Autor();
//        autor.setNome("Autor Teste");
//
//        AutorDao autorDao = new AutorDao();
//        Long id = autorDao.salvar(autor);
//        autor.setId(id);
//
//        System.out.println("Autor  : "
//                + autorDao.localizarPorId(autor.getId()));
//
//        System.out.println("Autores: "
//                + autorDao.localizarTodos());
        
        //
        // Comentario
        //
        
//        Comentario comentario = new Comentario();
//        comentario.setAutor("Autor Comentário 1");
//        comentario.setTexto("Um comentário aleatório");
//        comentario.setLivroId(2L);
//
//        ComentarioDao comentarioDao = new ComentarioDao();
//        Long id = comentarioDao.salvar(comentario);
//        comentario.setId(id);
//
//        System.out.println("Comentario : "
//                + comentarioDao.localizarPorId(comentario.getId()));
//
//        System.out.println("Comentários: "
//                + comentarioDao.localizarTodos());
        
        //
        // Resenha
        //
        
//        Resenha resenha = new Resenha();
//        resenha.setId(2L); // =>> A mesma id do livro!
//        resenha.setTexto("Uma resenha teste 999");
//
//        ResenhaDao resenhaDao = new ResenhaDao();
//        Long id = resenhaDao.salvar(resenha);
//        resenha.setId(id);
//
//        System.out.println("Resenha : "
//                + resenhaDao.localizarPorId(resenha.getId()));
//
//        System.out.println("Resenhas: "
//                + resenhaDao.localizarTodos());
        
        //
        // Livro
        //
        
        Livro livro = new Livro();
        
        livro.setTitulo("Padrões de Projeto em Java");
        livro.setAssunto("uml padrões projeto");
        livro.setEdicao((byte) 1);
        livro.setLocalPublicacao("Porto Alegre/RS");
        livro.setAnoPublicacao((short) 2002);
        livro.setExtensao((short) 407);
        
        // Editora
        
        Editora editora = new Editora();
        editora.setNome("Bookman");
        Long editoraId = new EditoraDao().salvar(editora); // <--
        editora.setId(editoraId);
        
        livro.setEditora(editora);
        
        // Salvamento do livro é permitido dado que a única chave estrangeira
        // da qual ele depende é a da editora. A partir disso, a chave
        // primária do livro será chave estrangeira em outros relacionamentos
        
        Long livroId = new LivroDao().salvar(livro); // <--
        livro.setId(livroId);
        
        // Autores
        
        Autor autor1 = new Autor();
        autor1.setNome("Steven John Metsker");
        Long autorId = new AutorDao().salvar(autor1); // <--
        autor1.setId(autorId);
        
        Autor autor2 = new Autor();
        autor2.setNome("Autor Fictício");
        autorId = new AutorDao().salvar(autor2); // <--
        autor2.setId(autorId);
        
        livro.setAutores(Arrays.asList(
                new Autor[]{autor1, autor2}));
        
        // Tabela de junção Autor-Livro deve ser atualizada
        
        for(Autor autor : livro.getAutores()) {
            new AutorLivroDao().salvar( // <--
                    new AutorLivro(autor.getId(), livro.getId()));
        }
        
        // Comentários
        
        Comentario comentario1 = new Comentario();
        comentario1.setAutor("Ana Zaira");
        comentario1.setTexto("Livro conciso e com muitos exemplos.");
        comentario1.setLivroId(livroId);
        
        Long comentarioId = new ComentarioDao().salvar(comentario1); // <--
        comentario1.setId(comentarioId);
        
        Comentario comentario2 = new Comentario();
        comentario2.setAutor("Beatriz Yana");
        comentario2.setTexto("Gostei das explicações e dos exemplos!");
        comentario2.setLivroId(livroId);
        
        comentarioId = new ComentarioDao().salvar(comentario2); // <--
        comentario2.setId(comentarioId);
        
        Comentario comentario3 = new Comentario();
        comentario3.setAutor("Cecília Xerxes");
        comentario3.setTexto("Os diagramas UML facilitaram o entendimento e os códigos-fontes apresentados foram claros. Também gostei dos desafios!");
        comentario3.setLivroId(livroId);
        
        comentarioId = new ComentarioDao().salvar(comentario3); // <--
        comentario3.setId(comentarioId);
        
        livro.setComentarios(Arrays.asList(
                new Comentario[]{comentario1, comentario2, comentario3}));
        
        // Resenha
        
        Resenha resenha = new Resenha();
        resenha.setTexto("Uma das principais razões que levam os pesquisadores da ciência da computação a reconhecerem Padrões de Projeto é a satisfação de se criar algo elegante, porém simples e de uma solução bastante reutilizável. O objetivo dos padrões é codificar conhecimento existente de uma forma que possa ser reaplicado em contextos diferentes.");
        resenha.setId(livroId);
        
        new ResenhaDao().salvar(resenha); // <--
    }
}
