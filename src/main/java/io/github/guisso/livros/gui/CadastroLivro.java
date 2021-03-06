/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.gui;

import io.github.guisso.livros.entidade.Autor;
import io.github.guisso.livros.entidade.AutorLivro;
import io.github.guisso.livros.entidade.Comentario;
import io.github.guisso.livros.entidade.Editora;
import io.github.guisso.livros.entidade.Livro;
import io.github.guisso.livros.entidade.Resenha;
import io.github.guisso.livros.gui.mycomponent.CheckboxListCellRenderer;
import io.github.guisso.livros.repositorio.AutorDao;
import io.github.guisso.livros.repositorio.AutorLivroDao;
import io.github.guisso.livros.repositorio.EditoraDao;
import io.github.guisso.livros.repositorio.LivroDao;
import io.github.guisso.livros.repositorio.ResenhaDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 27/08/2021
 */
public class CadastroLivro extends javax.swing.JInternalFrame {

    private static CadastroLivro instance;

    private Livro livroEdicao;

    private List<Editora> todasEditoras;
    private List<Autor> todosAutores;

    /**
     * Creates new form CadastroLivro
     */
    private CadastroLivro() {
        initComponents();

        // Preenchimento de editoras para seleção no cadastro
        todasEditoras = new EditoraDao().localizarTodos();
        DefaultComboBoxModel<Editora> comboBoxModel
                = new DefaultComboBoxModel<>();
        comboBoxModel.addAll(todasEditoras);
        cboEditora.setModel(comboBoxModel);

        // Preenchimento de autores para seleção no cadastro
        todosAutores = new AutorDao().localizarTodos();
        DefaultListModel<Autor> listModel 
                = new DefaultListModel<>();
        listModel.addAll(todosAutores);
        lstAutor.setModel(listModel);
    }

    /**
     * Creates new form CadastroLivro with a book filled
     *
     * @param livroEdicao
     */
    private CadastroLivro(Livro livroEdicao) {
        this();

        this.livroEdicao = livroEdicao;

        txtTitulo.setText(livroEdicao.getTitulo());
        txtAssunto.setText(livroEdicao.getAssunto());
        txtEdicao.setText(livroEdicao.getEdicao().toString());
        txtLocalPublicacao.setText(livroEdicao.getLocalPublicacao());
        txtAnoPublicacao.setText(livroEdicao.getAnoPublicacao().toString());
        txtExtensao.setText(livroEdicao.getExtensao().toString());
        txtResenha.setText(livroEdicao.getResenha().getTexto());

        cboEditora.getModel().setSelectedItem(
                livroEdicao.getEditora());

        // Pré-seleção de autores do livro corrente
        setSelectedValues(lstAutor, livroEdicao.getAutores());

        DefaultListModel<Comentario> comentariosListModel 
                = new DefaultListModel<>();
        comentariosListModel
                .addAll(livroEdicao.getComentarios());
        lstComentarios.setModel(comentariosListModel);
    }

    /**
     * Give access to a unique instance of the class.
     *
     * @return The unique form
     */
    public static CadastroLivro getInstance() {
        // Caso a janela ainda não tenha sido instanciada
        if (instance == null) {
            instance = new CadastroLivro();
        }
        
        // TODO Carga de todas editoras e todos autores

        return instance;
    }

    /**
     * Give access to a unique instance of the class with a book filled.
     *
     * @param livro
     * @return The unique form
     */
    public static CadastroLivro getInstance(Livro livro) {
        // TODO Carga de todas editoras e todos autores
        
        // Caso a janela ainda não tenha sido instanciada
        if (instance == null) {
            instance = new CadastroLivro(livro);
        } else {
            // Correção de bug: o livro deve ser sempre atribuído 
            // à instância existente para que a edição ocorra de 
            // maneira adequada
            instance.livroEdicao = livro;
        }

        return instance;
    }

    /**
     * Pré-seleciona os autores contido na listagem da GUI.
     *
     * @param list Componente gráfico JList.
     * @param autores Lista de autores a serem selecionados.
     */
    protected void setSelectedValues(JList list, List<Autor> autores) {
        list.clearSelection();
        for (Autor autor : autores) {
            int index = ((DefaultListModel) list.getModel())
                    .indexOf(autor);
            if (index >= 0) {
                list.addSelectionInterval(index, index);
            }
        }
        list.ensureIndexIsVisible(list.getSelectedIndex());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabLivro = new javax.swing.JTabbedPane();
        pnlLivro = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblEditora = new javax.swing.JLabel();
        lblAssunto = new javax.swing.JLabel();
        lblEdicacao = new javax.swing.JLabel();
        lblLocalPublicacao = new javax.swing.JLabel();
        lblAnoPublicacao = new javax.swing.JLabel();
        cboEditora = new javax.swing.JComboBox<>();
        lblExtensao = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtAssunto = new javax.swing.JTextField();
        txtEdicao = new javax.swing.JTextField();
        txtLocalPublicacao = new javax.swing.JTextField();
        txtAnoPublicacao = new javax.swing.JTextField();
        txtExtensao = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        bntCancelar = new javax.swing.JButton();
        pnlAutor = new javax.swing.JPanel();
        scrAutor = new javax.swing.JScrollPane();
        lstAutor = new javax.swing.JList<>();
        pnlResenha = new javax.swing.JPanel();
        scrResenha = new javax.swing.JScrollPane();
        txtResenha = new javax.swing.JTextArea();
        pnlComentarios = new javax.swing.JPanel();
        scrComentarios = new javax.swing.JScrollPane();
        lstComentarios = new javax.swing.JList<>();
        scrComantarioSelecionado = new javax.swing.JScrollPane();
        txtComentarioSelecionado = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Livro");

        tabLivro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Título:");

        lblEditora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEditora.setText("Editora:");

        lblAssunto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAssunto.setText("Assunto:");

        lblEdicacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEdicacao.setText("Edição:");

        lblLocalPublicacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLocalPublicacao.setText("Local de publicação:");

        lblAnoPublicacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAnoPublicacao.setText("Ano de publicação:");

        cboEditora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblExtensao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblExtensao.setText("Extensão:");

        txtTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtAssunto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtEdicao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtLocalPublicacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtAnoPublicacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtExtensao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        bntCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bntCancelar.setText("Cancelar");
        bntCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLivroLayout = new javax.swing.GroupLayout(pnlLivro);
        pnlLivro.setLayout(pnlLivroLayout);
        pnlLivroLayout.setHorizontalGroup(
            pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLivroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLivroLayout.createSequentialGroup()
                        .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlLivroLayout.createSequentialGroup()
                                .addComponent(bntCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLivroLayout.createSequentialGroup()
                        .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAssunto)
                            .addComponent(lblTitulo))
                        .addGap(18, 18, 18)
                        .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEditora)
                            .addComponent(lblEdicacao)
                            .addComponent(cboEditora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLivroLayout.createSequentialGroup()
                        .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlLivroLayout.createSequentialGroup()
                                .addComponent(txtLocalPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlLivroLayout.createSequentialGroup()
                                .addComponent(lblLocalPublicacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAnoPublicacao)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExtensao)
                            .addComponent(txtExtensao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlLivroLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAnoPublicacao, txtExtensao});

        pnlLivroLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bntCancelar, btnSalvar});

        pnlLivroLayout.setVerticalGroup(
            pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLivroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(lblEditora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAssunto)
                    .addComponent(lblEdicacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalPublicacao)
                    .addComponent(lblAnoPublicacao)
                    .addComponent(lblExtensao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocalPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExtensao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntCancelar)
                    .addComponent(btnSalvar))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        tabLivro.addTab("Livro", pnlLivro);

        scrAutor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lstAutor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lstAutor.setCellRenderer(new CheckboxListCellRenderer<Autor>());
        scrAutor.setViewportView(lstAutor);

        javax.swing.GroupLayout pnlAutorLayout = new javax.swing.GroupLayout(pnlAutor);
        pnlAutor.setLayout(pnlAutorLayout);
        pnlAutorLayout.setHorizontalGroup(
            pnlAutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
        );
        pnlAutorLayout.setVerticalGroup(
            pnlAutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabLivro.addTab("Autores", pnlAutor);

        txtResenha.setColumns(20);
        txtResenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtResenha.setLineWrap(true);
        txtResenha.setRows(5);
        txtResenha.setWrapStyleWord(true);
        scrResenha.setViewportView(txtResenha);

        javax.swing.GroupLayout pnlResenhaLayout = new javax.swing.GroupLayout(pnlResenha);
        pnlResenha.setLayout(pnlResenhaLayout);
        pnlResenhaLayout.setHorizontalGroup(
            pnlResenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrResenha, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlResenhaLayout.setVerticalGroup(
            pnlResenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrResenha, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabLivro.addTab("Resenha", pnlResenha);

        pnlComentarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lstComentarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        scrComentarios.setViewportView(lstComentarios);

        txtComentarioSelecionado.setColumns(20);
        txtComentarioSelecionado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtComentarioSelecionado.setLineWrap(true);
        txtComentarioSelecionado.setRows(5);
        txtComentarioSelecionado.setWrapStyleWord(true);
        scrComantarioSelecionado.setViewportView(txtComentarioSelecionado);

        javax.swing.GroupLayout pnlComentariosLayout = new javax.swing.GroupLayout(pnlComentarios);
        pnlComentarios.setLayout(pnlComentariosLayout);
        pnlComentariosLayout.setHorizontalGroup(
            pnlComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComentariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrComantarioSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlComentariosLayout.setVerticalGroup(
            pnlComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComentariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrComantarioSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(scrComentarios))
                .addContainerGap())
        );

        tabLivro.addTab("Comentários", pnlComentarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_bntCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Livro livro = new Livro();
        livro.setAutores(new ArrayList<>());
        livro.setComentarios(new ArrayList<>());

        // Mantém as listas de autores e comentários antes da edição
        if (livroEdicao != null) {
            livro = livroEdicao;
        }

        // Dados básicos
        livro.setTitulo(txtTitulo.getText());
        livro.setAssunto(txtAssunto.getText());
        livro.setEdicao(Byte.valueOf(txtEdicao.getText()));
        livro.setLocalPublicacao(txtLocalPublicacao.getText());
        livro.setAnoPublicacao(Short.valueOf(txtAnoPublicacao.getText()));
        livro.setExtensao(Short.valueOf(txtExtensao.getText()));
        livro.setEditora((Editora) cboEditora.getSelectedItem());

        Long livroId = new LivroDao().salvar(livro);
        livro.setId(livroId);

        // Resenha
        Resenha resenha = new Resenha(txtResenha.getText());
        resenha.setId(livroId);

        new ResenhaDao().salvar(resenha);

        // Autores selecionados para ligação ao livro
        List<Autor> autoresSelecionados 
                = lstAutor.getSelectedValuesList();

        // Exclusão de autores do livro
        for (Autor autor : livro.getAutores()) {
            if (!autoresSelecionados.contains(autor)) {
                new AutorLivroDao().excluir(
                        new AutorLivro(autor.getId(), livro.getId()));
            }
        }

        // Inclusão de autores do livro
        // O método salvar somente insere valores não pré-existentes no
        // banco de dados ao custo de uma consulta a cada nova tentativa
        // de inserção.
//
//        for (Autor autor : autores) {
//            new AutorLivroDao().salvar(
//                    new AutorLivro(autor.getId(), livro.getId()));
//        }
//
        // Alternativamente, pode-se verificar se o autor não está ligado
        // ao livro antes de sua inserção, economizando-se algumas
        // consultas, ou seja, menor desperdício de processamento.
        for (Autor autorSelecionado : autoresSelecionados) {
            if (!livro.getAutores().contains(autorSelecionado)) {
                new AutorLivroDao().salvar(
                        new AutorLivro(autorSelecionado.getId(),
                                livro.getId()));
            }
        }

        limparCampos();

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void limparCampos() {
        livroEdicao = null;

        txtTitulo.setText(null);
        txtAssunto.setText(null);
        txtEdicao.setText(null);
        txtLocalPublicacao.setText(null);
        txtAnoPublicacao.setText(null);
        txtExtensao.setText(null);
        txtResenha.setText(null);

        cboEditora.getModel().setSelectedItem(null);

        lstAutor.setSelectedValue(null, true);

        lstComentarios.setModel(new DefaultListModel<>());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Editora> cboEditora;
    private javax.swing.JLabel lblAnoPublicacao;
    private javax.swing.JLabel lblAssunto;
    private javax.swing.JLabel lblEdicacao;
    private javax.swing.JLabel lblEditora;
    private javax.swing.JLabel lblExtensao;
    private javax.swing.JLabel lblLocalPublicacao;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<Autor> lstAutor;
    private javax.swing.JList<Comentario> lstComentarios;
    private javax.swing.JPanel pnlAutor;
    private javax.swing.JPanel pnlComentarios;
    private javax.swing.JPanel pnlLivro;
    private javax.swing.JPanel pnlResenha;
    private javax.swing.JScrollPane scrAutor;
    private javax.swing.JScrollPane scrComantarioSelecionado;
    private javax.swing.JScrollPane scrComentarios;
    private javax.swing.JScrollPane scrResenha;
    private javax.swing.JTabbedPane tabLivro;
    private javax.swing.JTextField txtAnoPublicacao;
    private javax.swing.JTextField txtAssunto;
    private javax.swing.JTextArea txtComentarioSelecionado;
    private javax.swing.JTextField txtEdicao;
    private javax.swing.JTextField txtExtensao;
    private javax.swing.JTextField txtLocalPublicacao;
    private javax.swing.JTextArea txtResenha;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
