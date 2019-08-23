/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerCategoria;
import Controller.ControllerProduto;
import Controller.ControllerRelacaoProdutoCategoria;
import Model.Produto;
import Model.RelacaoProdutoCategoria;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mansoldo
 */
public class jTelaProduto extends javax.swing.JFrame {

    /**
     * Creates new form jTelaProduto
     */
    String modoTela = "novo";

    public jTelaProduto() {
        initComponents();
        formularioInativo();
        //modificação
    }

    //Método para efetuar limpeza do formulário
    public void limparFormulario() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtCompra.setText("");
        txtVenda.setText("");
        txtQuantidade.setText("");
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);
        checkBox5.setSelected(false);
    }

    //método que inativa o formulário
    public void formularioInativo() {
        txtNome.setEnabled(false);
        txtDescricao.setEnabled(false);
        txtCompra.setEnabled(false);
        txtVenda.setEnabled(false);
        txtQuantidade.setEnabled(false);
        jbtEditar.setEnabled(false);
        jbtSalvar.setEnabled(false);
        jbtExcluir.setEnabled(false);
        jbtLimpar.setEnabled(false);
    }

    //método que ativa o formulário
    private void formularioAtivo() {
        txtNome.setEnabled(true);
        txtDescricao.setEnabled(true);
        txtCompra.setEnabled(true);
        txtVenda.setEnabled(true);
        txtQuantidade.setEnabled(true);
        jbtSalvar.setEnabled(true);
        jbtLimpar.setEnabled(true);

    }

    //método que valida o formulário
    public boolean validaFormulario() {

        if (this.txtNome.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Campo Nome obrigatório.");
            return false;
        }
        if (this.txtDescricao.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Campo Descrição obrigatório.");
            return false;
        }
        if (this.txtCompra.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Campo Preço de Compra obrigatório.");
            return false;
        }
        if (this.txtVenda.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Campo Preço de Venda obrigatório.");
            return false;
        }
        if (this.txtQuantidade.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Campo Quantidade obrigatório.");
            return false;
        }

        return true;
    }

    //Método para validar quantidades negativas
    private boolean validaNumero() {
        if (Integer.parseInt(txtQuantidade.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Campo Quantidade não permite valor negativo.");
            txtQuantidade.setText("");
            return false;
        }

        if (Integer.parseInt(txtCompra.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Campo Valor de Compra não permite valor negativo.");
            txtCompra.setText("");
            return false;
        }

        if (Integer.parseInt(txtVenda.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Campo Valor de Compra não permite valor negativo.");
            txtVenda.setText("");
            return false;
        }
        return true;
    }

    //método que valida o formato do dado.
    private boolean validaFormato() {

        try {
            if (!this.txtQuantidade.getText().equalsIgnoreCase("")) {
                Integer.parseInt(txtQuantidade.getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na conversão dos dados no campo Quantidade", "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            txtQuantidade.setText("");
            return false;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

        try {
            if (!this.txtCompra.getText().equalsIgnoreCase("")) {
                Double.parseDouble(txtCompra.getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na conversão dos dados no campo Valor de Compra", "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            txtCompra.setText("");
            return false;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

        try {
            if (!this.txtVenda.getText().equalsIgnoreCase("")) {
                Double.parseDouble(txtVenda.getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na conversão dos dados no campo Valor de Venda", "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            txtVenda.setText("");
            return false;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        return true;
    }

    //Método que valida o status do produto
    public int validacaoStatus() {

        if (jComboStatus.getSelectedItem().equals("Ativo")) {
            return 1;
        } else {
            return 0;
        }
    }

    //Método que cria uma lista para adicionar as Categorias
    public ArrayList<Integer> validacaoCheck() {

        ArrayList<Integer> lista = new ArrayList<>();

        if (checkBox1.isSelected()) {
            lista.add(1);
        }
        if (checkBox2.isSelected()) {
            lista.add(2);
        }
        if (checkBox3.isSelected()) {
            lista.add(3);
        }
        if (checkBox4.isSelected()) {
            lista.add(4);
        }
        if (checkBox5.isSelected()) {
            lista.add(5);
        }
        return lista;
    }

    //método para carregar os dados na tabela
    public void loadTable() {

        ArrayList<String[]> lista = ControllerProduto.getProdutos();

        DefaultTableModel tabela = new DefaultTableModel();
        tabela.addColumn("ID");
        tabela.addColumn("Nome");
        tabela.addColumn("Descrição");
        tabela.addColumn("Preço Compra");
        tabela.addColumn("Preço Venda");
        tabela.addColumn("Qtd.");
        tabela.addColumn("Status");
        tabela.addColumn("Dt_Cad");
        jtableProduto.setModel(tabela);

        for (String[] elementos : lista) {
            tabela.addRow(elementos);
        }

        jtableProduto.getColumnModel().getColumn(0).setPreferredWidth(250); //ID        
        jtableProduto.getColumnModel().getColumn(1).setPreferredWidth(250); //Nome
        jtableProduto.getColumnModel().getColumn(2).setPreferredWidth(250); //Descrição
        jtableProduto.getColumnModel().getColumn(3).setPreferredWidth(250); //Preço Compra
        jtableProduto.getColumnModel().getColumn(4).setPreferredWidth(250); //Preço Venda
        jtableProduto.getColumnModel().getColumn(5).setPreferredWidth(250); //Quantidade
        jtableProduto.getColumnModel().getColumn(6).setPreferredWidth(250); //Status
        jtableProduto.getColumnModel().getColumn(7).setPreferredWidth(250); //Data_Cadastro      

        jbtEditar.setEnabled(true);
        jbtExcluir.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtCompra = new javax.swing.JTextField();
        txtVenda = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboStatus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        checkBox1 = new javax.swing.JCheckBox();
        checkBox2 = new javax.swing.JCheckBox();
        checkBox3 = new javax.swing.JCheckBox();
        checkBox4 = new javax.swing.JCheckBox();
        checkBox5 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jbtnNovo = new javax.swing.JButton();
        jbtSalvar = new javax.swing.JButton();
        jbtEditar = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jbtLimpar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableProduto = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Nome:");

        jLabel2.setText("Descrição:");

        jLabel3.setText("Preço de Compra:");

        jLabel4.setText("Preço de Venda:");

        jLabel5.setText("Quantidade: ");

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });

        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyTyped(evt);
            }
        });

        txtCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCompraKeyTyped(evt);
            }
        });

        txtVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVendaKeyTyped(evt);
            }
        });

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyTyped(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Status Produto:");

        jComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

        jLabel7.setText("Categoria:");

        checkBox1.setText("Categoria 1");

        checkBox2.setText("Categoria 2");

        checkBox3.setText("Categoria 3");

        checkBox4.setText("Categoria 4");

        checkBox5.setText("Categoria 5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(checkBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBox5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(checkBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBox4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(checkBox1)
                    .addComponent(checkBox3)
                    .addComponent(checkBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox2)
                    .addComponent(checkBox4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidade)
                            .addComponent(txtVenda)
                            .addComponent(txtCompra)
                            .addComponent(txtNome)
                            .addComponent(txtDescricao))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jbtnNovo.setText("Novo");
        jbtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNovoActionPerformed(evt);
            }
        });

        jbtSalvar.setText("Salvar");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtEditar.setText("Editar");
        jbtEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditarActionPerformed(evt);
            }
        });

        jbtExcluir.setText("Excluir");
        jbtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExcluirActionPerformed(evt);
            }
        });

        jbtLimpar.setText("Limpar");
        jbtLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(jbtLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(jbtExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jbtnNovo)
                .addGap(4, 4, 4)
                .addComponent(jbtSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtLimpar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Descrição", "Preço Compra", "Preço Venda", "Qtd.", "Status", "Dt_Cad"
            }
        ));
        jScrollPane2.setViewportView(jtableProduto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //
    private void jbtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNovoActionPerformed
        formularioAtivo();
        modoTela = "Salvar";
        limparFormulario();
    }//GEN-LAST:event_jbtnNovoActionPerformed
    //Método para Salvar
    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed

        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date calendario = new Date();
        java.sql.Date data = new java.sql.Date(calendario.getTime());
        ArrayList<Integer> lista = new ArrayList<>();

        
        if (modoTela.equals("Salvar")) {
            //Efetua as validações para prosseguir a ação de salvar
            if (validaFormulario() && validaFormato() && validaNumero()) {
                if (ControllerProduto.salvarProduto(
                        txtNome.getText(),
                        txtDescricao.getText(),
                        Double.parseDouble(this.txtCompra.getText()),
                        Double.parseDouble(this.txtVenda.getText()),
                        Integer.parseInt(this.txtQuantidade.getText()),
                        validacaoStatus(),
                        data)) {
                    this.loadTable();

                    //Verifica se há Categoria associada para inserir no banco
                    if (validacaoCheck() != null) {
                        ControllerCategoria.associarCategoria(validacaoCheck());
                    }
                    limparFormulario();
                    JOptionPane.showMessageDialog(this, "Produto Cadastrado!");

                    //adicionar método para limpar o formulário
                } else {
                    limparFormulario();
                    JOptionPane.showMessageDialog(this, "Produto não cadastrado!");
                }
            }
            //Em caso de Edição, entra nesta condição
        } else if (modoTela.equals("Edicao")) {
            int numlinha = jtableProduto.getSelectedRow();
            Produto id = new Produto();
            id.setIdEditar(Integer.parseInt(jtableProduto.getValueAt(numlinha, 0).toString()));
            int ID = id.getIdEditar();

            if (ControllerProduto.controllerEditarProduto(ID,
                    txtNome.getText(),
                    txtDescricao.getText(),
                    Double.parseDouble(txtCompra.getText()),
                    Double.parseDouble(txtVenda.getText()),
                    Integer.parseInt(txtQuantidade.getText()),
                    validacaoStatus())) {
                this.loadTable();
                ControllerRelacaoProdutoCategoria.excluirRelacao(ID);
                if (validacaoCheck() != null) {
                    ControllerCategoria.associarCategoria(validacaoCheck());
                }
                limparFormulario();
                JOptionPane.showMessageDialog(this, "Produto Editado!");
                modoTela = "Salvar";
            }
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    //Método para Editar ao clicar no botão de Editar
    private void jbtEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditarActionPerformed
        
        limparFormulario();
        ArrayList<Integer> lista = new ArrayList<>();
        modoTela = "Edicao";
        if (jtableProduto.getSelectedRow() != -1) {
            int numlinha = jtableProduto.getSelectedRow();
            //Instancia um produto e armazena o ID dele efeito de comparação no momento de editar
            Produto id = new Produto();
            id.setIdEditar(Integer.parseInt(jtableProduto.getValueAt(numlinha, 0).toString()));
            int ID = id.getIdEditar();

            //Lista de produtos e dos produtos relacionados com Categoria
            ArrayList<Produto> editarProduto = ControllerProduto.getProdutoList();
            ArrayList<RelacaoProdutoCategoria> Prod_Cate = ControllerRelacaoProdutoCategoria.getRelacao();

            //Laço para popular os dados na tela conforme os registros obtidos em banco
            for (Produto elementos : editarProduto) {
                if (ID == elementos.getIdProduto()) {
                    txtNome.setText(String.valueOf(elementos.getNome()));
                    txtDescricao.setText(String.valueOf(elementos.getDescricao()));
                    txtCompra.setText(String.valueOf(elementos.getValorCompra()));
                    txtVenda.setText(String.valueOf(elementos.getValorVenda()));
                    txtQuantidade.setText(String.valueOf(elementos.getQuantidade()));
                    if (elementos.getStatus() == 1) {
                        jComboStatus.setSelectedIndex(0);
                    } else {
                        jComboStatus.setSelectedIndex(1);
                    }

                }
            }
            //Laço para marcar na tela se há categorias associadas ao produto
            for (RelacaoProdutoCategoria relacao : Prod_Cate) {
                if (ID == relacao.getIdProduto()) {
                    if (relacao.getIdCategoria() == 1) {
                        checkBox1.setSelected(true);
                    }
                    if (relacao.getIdCategoria() == 2) {
                        checkBox2.setSelected(true);
                    }
                    if (relacao.getIdCategoria() == 3) {
                        checkBox3.setSelected(true);
                    }
                    if (relacao.getIdCategoria() == 4) {
                        checkBox4.setSelected(true);
                    }
                    if (relacao.getIdCategoria() == 5) {
                        checkBox5.setSelected(true);
                    }
                }
            }

        } else {
            //Em caso de nenhuma linha selecionada para edição de produto
            JOptionPane.showMessageDialog(this, "Não há produto selecionado", "Falha ao editar", JOptionPane.ERROR_MESSAGE);
            limparFormulario();
        }


    }//GEN-LAST:event_jbtEditarActionPerformed

    //Método para efetuar exclusão do produto ao clicar no botão de Excluir
    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        // TODO add your handling code here:

        if (jtableProduto.getSelectedRow() != -1) {

            int numeroLinha = jtableProduto.getSelectedRow();

            //Recebe uma lista de Categorias associadas ao produto, e efetua a exclusão dela
            ArrayList<RelacaoProdutoCategoria> listaRelacao = ControllerRelacaoProdutoCategoria.getRelacao();

            for (RelacaoProdutoCategoria lista : listaRelacao) {

                if (Integer.parseInt(jtableProduto.getValueAt(numeroLinha, 0).toString()) == lista.getIdProduto()) {
                    ControllerRelacaoProdutoCategoria.excluirRelacao(Integer.parseInt(jtableProduto.getValueAt(numeroLinha, 0).toString()));
                }
            }

            //Após efetuar a exclusão da associação, é excluído o produto
            if (ControllerProduto.ExcluirProduto(Integer.parseInt(jtableProduto.getValueAt(numeroLinha, 0).toString()))) {
                DefaultTableModel Tabela = (DefaultTableModel) jtableProduto.getModel();
                Tabela.removeRow(numeroLinha);
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                loadTable();
                limparFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você não selecionou nenhum item!",
                    "Erro de Exclusão", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jbtExcluirActionPerformed
    //Método do botão para efetuar limpeza da tela
    private void jbtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimparActionPerformed
        limparFormulario();
    }//GEN-LAST:event_jbtLimparActionPerformed

    //Validacao dos tamanhos dos campos
    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped

        if (txtNome.getText().length() > 100) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Limite de caracter em 100", "Erro", JOptionPane.ERROR_MESSAGE);
            txtNome.setText("");
        }
        if (txtNome.getText().matches("^[0-9].*")) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Caractere invalido", "Erro", JOptionPane.ERROR_MESSAGE);
            txtNome.setText("");
        }
    }//GEN-LAST:event_txtNomeKeyTyped

    private void txtDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyTyped

        if (txtDescricao.getText().length() > 1000) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Limite de caracter em 1000", "Erro", JOptionPane.ERROR_MESSAGE);
            txtDescricao.setText("");
        }
    }//GEN-LAST:event_txtDescricaoKeyTyped

    private void txtCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompraKeyTyped

        if (txtCompra.getText().length() > 11) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Limite de caracter em 11", "Erro", JOptionPane.ERROR_MESSAGE);
            txtCompra.setText("");
        }
    }//GEN-LAST:event_txtCompraKeyTyped

    private void txtVendaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendaKeyTyped

        if (txtVenda.getText().length() > 11) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Limite de caracter em 11", "Erro", JOptionPane.ERROR_MESSAGE);
            txtVenda.setText("");
        }

    }//GEN-LAST:event_txtVendaKeyTyped

    private void txtQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyTyped
        if (txtQuantidade.getText().length() > 10) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Limite de caractere em 10", "Erro", JOptionPane.ERROR_MESSAGE);
            txtQuantidade.setText("");
        }
    }//GEN-LAST:event_txtQuantidadeKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jTelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jTelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jTelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jTelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new jTelaProduto().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBox1;
    private javax.swing.JCheckBox checkBox2;
    private javax.swing.JCheckBox checkBox3;
    private javax.swing.JCheckBox checkBox4;
    private javax.swing.JCheckBox checkBox5;
    private javax.swing.JComboBox<String> jComboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtEditar;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtLimpar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JButton jbtnNovo;
    private javax.swing.JTable jtableProduto;
    private javax.swing.JTextField txtCompra;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtVenda;
    // End of variables declaration//GEN-END:variables
}
