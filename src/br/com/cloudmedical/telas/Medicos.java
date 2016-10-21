/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.telas;

import br.com.cloudmedical.classes.ManterEndereco;
import br.com.cloudmedical.classes.ManterInformacoes;
import br.com.cloudmedical.classes.ManterMedicos;
import br.com.cloudmedical.classes.ManterPagamento;
import br.com.cloudmedical.classes.ManterTelefone;
import br.com.cloudmedical.classes.ManterTipoPagamento;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Debug
 */
public class Medicos extends javax.swing.JDialog {

    ManterMedicos mmedico;
    ManterEndereco endereco;
    ManterTelefone telefone;
    ManterInformacoes informacoes;
    ManterPagamento pagamento;
    ManterTipoPagamento tipoPagamento;
    
    private int codUsuario;
    private String nomeUsuario;

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    /**
     * Creates new form Medicos
     */
    public Medicos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        preencheTabela();
        limpaCampos();
        preencheCombo();
        desativaCamposPesquisa();
        bloqueiaCampoPesquisa(txtPesqNome);
        preencheComboTipoPagamento();
        preencheTabelaTelefones();
        preencheComboTelefone();
    }
    
    public void sair(){
        dispose();
    }
    
    public void preencheTabela(){
        mmedico = new ManterMedicos();
        mmedico.preencheTabelaMedicos(tabelaMedico);
    }
    
    public void preencheComboTelefone(){
        telefone = new ManterTelefone();
        telefone.preencheComboTipoTelefone(comboTipoTelefone);
    }
    
    public void preencheTabelaTelefones(){
        telefone = new ManterTelefone();
        
        telefone.setCodPerfil(1);
        
        if(txtIdMedico.getText().equals("")){
            telefone.setCodProprietario(0);
        }else{
            telefone.setCodProprietario(Integer.valueOf(txtIdMedico.getText()));
        }
        
        telefone.preencheTabelaTelefone(tabelaTelefone);
    }
    
    public void preencheCampos(){

        mmedico = new ManterMedicos();
        endereco = new ManterEndereco();
        telefone = new ManterTelefone();
        informacoes = new ManterInformacoes();
        pagamento = new ManterPagamento();
        
        String selecao = String.valueOf(tabelaMedico.getValueAt(tabelaMedico.getSelectedRow(), 0));
        txtIdMedico.setText(selecao);
        int id = Integer.valueOf(selecao);
        
        mmedico.setIdMedico(id);
        endereco.setCodMedico(id);
        telefone.setCodProprietario(id);
        informacoes.setCodMedico(id);
        pagamento.setCodMedico(id);
        
        mmedico.preencheCamposMedicos();
        endereco.preencheEndereco();
        informacoes.preencheInformacoes();
        
        //Preenche as dados do Médico
        
        txtNome.setText(mmedico.getNomeMedico());
        txtCrm.setText(mmedico.getCrm());
        txtValor.setText(String.valueOf(mmedico.getValor()));
//        txtTipoPagto.setText(mmedico.getNomePagamento());
        txtIdUsuario.setText(String.valueOf(mmedico.getCodUsuario()));
        
        //Preenche o endereço
        
        txtEndereco.setText(endereco.getNomeEndereco());
        txtNumero.setText(String.valueOf(endereco.getNumero()));
        txtComplemento.setText(endereco.getComplemento());
        txtBairro.setText(endereco.getBairro());
        txtCidade.setText(endereco.getCidade());
        comboEstado.addItem(endereco.getEstado());
        
        //Preenche os telefones
//        tabelaTelefone.add(telefone.getTelefone());
        //Preenche as informações
        txtInformacoes.setText(informacoes.getTexto());
        
    }

    public void desativaCamposPesquisa(){
        comboTpPgto.setVisible(false);
        labelMensagem.setVisible(false);
    }
    
    public void bloqueiaCampoPesquisa(JTextField campo){
        campo.setEnabled(false);
    }
    public void desbloqueiaCampoPesquisa(JTextField campo){
        campo.setEnabled(true);
    }
    
    public void teclasAcesso(KeyEvent e){
        
        if((e.getKeyCode() == KeyEvent.VK_N) && (e.getModifiers() == KeyEvent.CTRL_MASK)){
            JOptionPane.showMessageDialog(null, "Adicionou o novo");
        }
        else if((e.getKeyCode() == KeyEvent.VK_S) && (e.getModifiers() == KeyEvent.CTRL_MASK)){
            JOptionPane.showMessageDialog(null, "Adicionou a alteração");
        }
        else if((e.getKeyCode() == KeyEvent.VK_E) && (e.getModifiers() == KeyEvent.CTRL_MASK)){
            JOptionPane.showMessageDialog(null, "Adicionou a exclusão");
        }
        else if((e.getKeyCode() == KeyEvent.VK_R) && (e.getModifiers() == KeyEvent.CTRL_MASK)){
            if(JOptionPane.showConfirmDialog(null, "Deseja Sair", "SAIR", JOptionPane.YES_NO_OPTION) == 0){
                sair();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panelFundo = new javax.swing.JPanel();
        scrollTabelaMedico = new javax.swing.JScrollPane();
        tabelaMedico = new javax.swing.JTable();
        TabDados = new javax.swing.JTabbedPane();
        panelDados = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtCrm = new javax.swing.JTextField();
        labelCrm = new javax.swing.JLabel();
        labelTipoPagto = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtIdMedico = new javax.swing.JTextField();
        txtIdUsuario = new javax.swing.JTextField();
        comboTipoPagamento = new javax.swing.JComboBox();
        txtIdTpPagto = new javax.swing.JTextField();
        panelEndereco = new javax.swing.JPanel();
        labelCidade = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        labelComplemento = new javax.swing.JLabel();
        labelBairro = new javax.swing.JLabel();
        labelEndereco = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        txtNumero = new javax.swing.JFormattedTextField();
        txtComplemento = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        comboEstado = new javax.swing.JComboBox();
        panelInformacoes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInformacoes = new javax.swing.JTextArea();
        panelTelefone = new javax.swing.JPanel();
        labelTipo = new javax.swing.JLabel();
        comboTipoTelefone = new javax.swing.JComboBox();
        labelTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        btExcluirTelefone = new javax.swing.JButton();
        btAdicionarTelefone = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        panelButton = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panelBusca = new javax.swing.JPanel();
        radioNome = new javax.swing.JRadioButton();
        radioTipoPagamento = new javax.swing.JRadioButton();
        labelPesquisa = new javax.swing.JLabel();
        txtPesqNome = new javax.swing.JTextField();
        comboTpPgto = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        panelFundo.setBackground(new java.awt.Color(255, 255, 255));
        panelFundo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        panelFundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabelaMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMedicoMouseClicked(evt);
            }
        });
        scrollTabelaMedico.setViewportView(tabelaMedico);

        panelDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelNome.setText("Nome do Médico:");

        labelCrm.setText("CRM:");

        labelTipoPagto.setText("Tipo Pagamento:");

        labelValor.setText("Valor:");

        comboTipoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDadosLayout = new javax.swing.GroupLayout(panelDados);
        panelDados.setLayout(panelDadosLayout);
        panelDadosLayout.setHorizontalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNome)
                    .addComponent(labelCrm)
                    .addComponent(labelTipoPagto)
                    .addComponent(labelValor))
                .addGap(18, 18, 18)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDadosLayout.createSequentialGroup()
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdUsuario)
                            .addComponent(txtIdMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(txtIdTpPagto))
                        .addContainerGap())
                    .addGroup(panelDadosLayout.createSequentialGroup()
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDadosLayout.setVerticalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDadosLayout.createSequentialGroup()
                        .addComponent(txtIdMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtIdTpPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDadosLayout.createSequentialGroup()
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCrm)
                            .addComponent(txtCrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTipoPagto)
                            .addComponent(comboTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValor)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        TabDados.addTab("  Dados  ", panelDados);

        panelEndereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelCidade.setText("Cidade:");

        labelEstado.setText("Estado:");

        labelComplemento.setText("Complemento:");

        labelBairro.setText("Bairro:");

        labelEndereco.setText("Endereço:");

        labelNumero.setText("Número:");

        txtNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        javax.swing.GroupLayout panelEnderecoLayout = new javax.swing.GroupLayout(panelEndereco);
        panelEndereco.setLayout(panelEnderecoLayout);
        panelEnderecoLayout.setHorizontalGroup(
            panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNumero)
                    .addComponent(labelBairro)
                    .addComponent(labelCidade)
                    .addComponent(labelEndereco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEnderecoLayout.createSequentialGroup()
                        .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEnderecoLayout.createSequentialGroup()
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(labelComplemento)
                        .addGap(18, 18, 18)
                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(163, 163, 163))
        );
        panelEnderecoLayout.setVerticalGroup(
            panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEndereco)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumero)
                    .addComponent(labelComplemento)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBairro)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCidade)
                    .addComponent(labelEstado)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        TabDados.addTab("  Endereço  ", panelEndereco);

        txtInformacoes.setColumns(20);
        txtInformacoes.setRows(5);
        txtInformacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(txtInformacoes);

        javax.swing.GroupLayout panelInformacoesLayout = new javax.swing.GroupLayout(panelInformacoes);
        panelInformacoes.setLayout(panelInformacoesLayout);
        panelInformacoesLayout.setHorizontalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInformacoesLayout.setVerticalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabDados.addTab("  Informações  ", panelInformacoes);

        labelTipo.setText("Tipo:");

        comboTipoTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoTelefoneActionPerformed(evt);
            }
        });

        labelTelefone.setText("Telefone:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        tabelaTelefone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Telefone", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaTelefone.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tabelaTelefone);
        tabelaTelefone.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabelaTelefone.getColumnModel().getColumnCount() > 0) {
            tabelaTelefone.getColumnModel().getColumn(0).setResizable(false);
            tabelaTelefone.getColumnModel().getColumn(1).setResizable(false);
        }

        btExcluirTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/Trashcan.png"))); // NOI18N

        btAdicionarTelefone.setBackground(new java.awt.Color(255, 255, 255));
        btAdicionarTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/IL_RefreshDevice.png"))); // NOI18N
        btAdicionarTelefone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btAdicionarTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarTelefoneActionPerformed(evt);
            }
        });

        labelMensagem.setText("jLabel1");

        javax.swing.GroupLayout panelTelefoneLayout = new javax.swing.GroupLayout(panelTelefone);
        panelTelefone.setLayout(panelTelefoneLayout);
        panelTelefoneLayout.setHorizontalGroup(
            panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTelefoneLayout.createSequentialGroup()
                        .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTelefoneLayout.createSequentialGroup()
                        .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTelefoneLayout.createSequentialGroup()
                                .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTelefoneLayout.createSequentialGroup()
                                        .addComponent(labelTelefone)
                                        .addGap(18, 18, 18))
                                    .addGroup(panelTelefoneLayout.createSequentialGroup()
                                        .addComponent(labelTipo)
                                        .addGap(40, 40, 40)))
                                .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboTipoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btExcluirTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );
        panelTelefoneLayout.setVerticalGroup(
            panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTelefoneLayout.createSequentialGroup()
                        .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTipo)
                            .addComponent(comboTipoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTelefone)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(btAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btExcluirTelefone)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMensagem)
                .addContainerGap())
        );

        TabDados.addTab(" Telefones  ", panelTelefone);

        panelButton.setBackground(new java.awt.Color(255, 255, 255));
        panelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/saveMedico.png"))); // NOI18N
        btSalvar.setToolTipText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/trashMedico.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/novoMedico.png"))); // NOI18N
        btNovo.setToolTipText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/botaoexcluir.png"))); // NOI18N
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelFundoLayout = new javax.swing.GroupLayout(panelFundo);
        panelFundo.setLayout(panelFundoLayout);
        panelFundoLayout.setHorizontalGroup(
            panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTabelaMedico)
                    .addComponent(TabDados))
                .addGap(18, 18, 18)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelFundoLayout.setVerticalGroup(
            panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFundoLayout.createSequentialGroup()
                        .addComponent(scrollTabelaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TabDados, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labelTitulo.setText("Cloud Medical - Médicos");

        panelBusca.setBackground(new java.awt.Color(255, 255, 255));
        panelBusca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        radioNome.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup.add(radioNome);
        radioNome.setText("Por nome ou CRM");
        radioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomeActionPerformed(evt);
            }
        });

        radioTipoPagamento.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup.add(radioTipoPagamento);
        radioTipoPagamento.setText("Por tipo de pagamento");
        radioTipoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTipoPagamentoActionPerformed(evt);
            }
        });

        labelPesquisa.setText("Campo de Pesquisa");

        txtPesqNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqNomeKeyReleased(evt);
            }
        });

        comboTpPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTpPgtoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscaLayout = new javax.swing.GroupLayout(panelBusca);
        panelBusca.setLayout(panelBuscaLayout);
        panelBuscaLayout.setHorizontalGroup(
            panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPesquisa)
                .addGap(18, 18, 18)
                .addComponent(txtPesqNome, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTpPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioNome)
                .addGap(18, 18, 18)
                .addComponent(radioTipoPagamento)
                .addGap(15, 15, 15))
        );
        panelBuscaLayout.setVerticalGroup(
            panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesquisa)
                    .addComponent(txtPesqNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTpPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioNome)
                    .addComponent(radioTipoPagamento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelFundo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBusca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed

        if(txtIdMedico.getText() == ""){
            JOptionPane.showMessageDialog(null, "Por favor, selecione o médico para exclusão!", "Erro", 0);
        }else{
            confirmaExclusao(Integer.valueOf(txtIdMedico.getText()));
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_btExcluirActionPerformed

    public void confirmaExclusao(int idMedicoSelecionado){
        if(JOptionPane.showConfirmDialog(null, "Deseja excluir o médico selecionado? As informações serão perdidas!", "Exclusão", JOptionPane.YES_NO_OPTION) == 0){
            mmedico.setIdMedico(idMedicoSelecionado);
            mmedico.excluiMedico();
            mmedico.preencheTabelaMedicos(tabelaMedico);
        }
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtCrm.setText("");
        txtValor.setText("");
        txtEndereco.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtIdMedico.setText("");
        txtNome.requestFocus();
    }
    
    public void preencheCombo(){
        tipoPagamento = new ManterTipoPagamento();
        tipoPagamento.preencheComboTipoPagamento(comboTipoPagamento);
        comboEstado.setSelectedItem("RJ");
    }
    
    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        
        limpaCampos();
// TODO add your handling code here:
    }//GEN-LAST:event_btNovoActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
//        if(JOptionPane.showConfirmDialog(null, "Deseja Fechar?", "Fechar Cadastro de Médicos", JOptionPane.YES_NO_OPTION) == 0){
            sair();
//        }
// TODO add your handling code here:
    }//GEN-LAST:event_btCancelarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        teclasAcesso(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        if(txtNome.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite os campos para inclusão ou alteração!");
        }else{
            mmedico = new ManterMedicos();
            endereco = new ManterEndereco();
            telefone = new ManterTelefone();
            informacoes = new ManterInformacoes();
            pagamento = new ManterPagamento();

            //Inclusão das informações do médico
            if(txtIdMedico.getText().equals("")){
                mmedico.setIdMedico(0);
            }else{
                mmedico.setIdMedico(Integer.parseInt(txtIdMedico.getText()));
            }
            mmedico.setNomeMedico(txtNome.getText());
            mmedico.setCrm(txtCrm.getText());
            mmedico.setTipoPagamento(comboTipoPagamento.getSelectedIndex() + 1);
            mmedico.setValor(Double.parseDouble(txtValor.getText()));
            mmedico.setCodUsuario(this.codUsuario);

            mmedico.verificaExisteMedico();

            if(!txtEndereco.getText().equals("")){
                //Inclusão das informações do endereço
                endereco.setNomeEndereco(txtEndereco.getText());
                endereco.setNumero(Integer.valueOf(txtNumero.getText()));
                endereco.setComplemento(txtComplemento.getText());
                endereco.setBairro(txtBairro.getText());
                endereco.setCidade(txtCidade.getText());
                endereco.setEstado(String.valueOf(comboEstado.getSelectedItem()));
                endereco.setCodMedico(mmedico.getIdMedico());
                endereco.verificaExisteEndereco();
            }
            //Inclusão das informações do médico
            if(!txtInformacoes.getText().equals("")){
                informacoes.setTexto(txtInformacoes.getText());
                informacoes.setCodMedico(mmedico.getIdMedico());
                informacoes.verificaExisteInformacao();
            }
            if(!txtNome.getText().equals("")){
//                if (tabelaTelefone.getRowCount() > 0){

                    telefone = new ManterTelefone();
                    
                    for (int i = 0; i < tabelaTelefone.getRowCount(); i ++){
                        String telefones = String.valueOf(tabelaTelefone.getValueAt(i, 1));
                        String tipotelefone = (String) comboTipoTelefone.getSelectedItem();
                        
                        telefone.setTelefone(telefones);
                        telefone.setCodPerfil(1);
                        telefone.setCodTipoTelefone(telefone.retornaIdTipoTelefone(tipotelefone));
                        if(txtIdMedico.getText().equals("")){
                            telefone.setCodProprietario(mmedico.getIdMedico());
                        }else{
                            telefone.setCodProprietario(Integer.valueOf(txtIdMedico.getText()));
                        }

                        telefone.verificaExisteTelefone();

                    }
//                }else{
//                    
//                }
            }else{
                labelMensagem.setText("Preencha o nome do médico.");
            }


            mmedico.preencheTabelaMedicos(tabelaMedico);

            limpaCampos();
            desativaCamposPesquisa();
            bloqueiaCampoPesquisa(txtPesqNome);
            txtPesqNome.setVisible(true);
            radioNome.setSelected(false);
            radioTipoPagamento.setSelected(false);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tabelaMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMedicoMouseClicked

        mmedico = new ManterMedicos();
        endereco = new ManterEndereco();
        telefone = new ManterTelefone();
        informacoes = new ManterInformacoes();
        pagamento = new ManterPagamento();
        
        String selecao = String.valueOf(tabelaMedico.getValueAt(tabelaMedico.getSelectedRow(), 0));
        txtIdMedico.setText(selecao);
        int id = Integer.valueOf(selecao);
        
        mmedico.setIdMedico(id);
        endereco.setCodMedico(id);
        telefone.setCodProprietario(id);
        informacoes.setCodMedico(id);
        pagamento.setCodMedico(id);
        
        mmedico.preencheCamposMedicos();
        endereco.preencheEndereco();
        informacoes.preencheInformacoes();
        
        
        //Preenche as dados do Médico
        
        txtNome.setText(mmedico.getNomeMedico());
        txtCrm.setText(mmedico.getCrm());
        txtValor.setText(String.valueOf(mmedico.getValor()));
//        txtTipoPagto.setText(mmedico.getNomePagamento());
        txtIdUsuario.setText(String.valueOf(mmedico.getCodUsuario()));
        
        //Preenche o endereço
        
        txtEndereco.setText(endereco.getNomeEndereco());
        txtNumero.setText(String.valueOf(endereco.getNumero()));
        txtComplemento.setText(endereco.getComplemento());
        txtBairro.setText(endereco.getBairro());
        txtCidade.setText(endereco.getCidade());
        comboEstado.addItem(endereco.getEstado());
        
        //Preenche os telefones
        telefone.preencheTabelaTelefone(tabelaTelefone);
//        tabelaTelefone.add(telefone.getTelefone());
        
        
        //Preenche as informações
        
        txtInformacoes.setText(informacoes.getTexto());
        
// TODO add your handling code here:
    }//GEN-LAST:event_tabelaMedicoMouseClicked

    private void radioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomeActionPerformed

        comboTpPgto.setVisible(false);

        txtPesqNome.setSize(170, 20);//PreferredSize(dim); (w, h)
        txtPesqNome.setVisible(true);
        txtPesqNome.setEnabled(true);
        txtPesqNome.requestFocus();
    }//GEN-LAST:event_radioNomeActionPerformed

    public void preencheComboTipoPagamento(){
        pagamento = new ManterPagamento();
        pagamento.preencheCombo(comboTpPgto);
    }
    
    private void radioTipoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTipoPagamentoActionPerformed
//        pagamento = new ManterPagamento();
//        pagamento.preencheCombo(comboTpPgto);
        txtPesqNome.setText("");
        txtPesqNome.setVisible(false);
        comboTpPgto.setVisible(true);
        comboTpPgto.setEnabled(true);
        comboTpPgto.requestFocus();
        
        
    }//GEN-LAST:event_radioTipoPagamentoActionPerformed

    private void txtPesqNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqNomeKeyReleased

        //            config = new Configuracao();
        //            config.geraTxtError(txtPesqNome.getText());
        // && -> E -e- || -> OU
        mmedico = new ManterMedicos();

        if(radioNome.isSelected()){
            if(txtPesqNome.getText().length() >= 3){
                mmedico.pesquisaNomeMedico(tabelaMedico, txtPesqNome.getText());
                if(txtPesqNome.getText().equals("todos") || txtPesqNome.getText().equals("TODOS") || txtPesqNome.getText().equals("")){
                    preencheTabela();
                }
//                if(KeyEvent.VK_ENTER == evt.getKeyCode()){
//                    mmedico.pesquisaNomeMedico(tabelaMedico, txtPesqNome.getText());
//                }
            }else if(KeyEvent.VK_ENTER == evt.getKeyCode()){
                mmedico.pesquisaNomeMedico(tabelaMedico, txtPesqNome.getText());
                if(txtPesqNome.getText().equals("todos") || txtPesqNome.getText().equals("TODOS") || txtPesqNome.getText().equals("")){
                    preencheTabela();
                }
            }
        }

    }//GEN-LAST:event_txtPesqNomeKeyReleased

    private void comboTpPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTpPgtoActionPerformed

        if(radioTipoPagamento.isSelected()){
            tipoPagamento = new ManterTipoPagamento();
            tipoPagamento.pesquisaTipoPagamentoMedico(tabelaMedico, comboTpPgto.getSelectedItem().toString());
        }
// TODO add your handling code here:
    }//GEN-LAST:event_comboTpPgtoActionPerformed

    private void comboTipoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoPagamentoActionPerformed
        tipoPagamento = new ManterTipoPagamento();
        int idTipoPagamentoSelecionado = tipoPagamento.returnIdTipoPagamento(comboTipoPagamento.getSelectedItem().toString());
        txtIdTpPagto.setText(String.valueOf(idTipoPagamentoSelecionado));
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoPagamentoActionPerformed

    private void btAdicionarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarTelefoneActionPerformed
        preencheTabelaTelefoneProvisorio(txtTelefone.getText());
        txtTelefone.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btAdicionarTelefoneActionPerformed

    private void comboTipoTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoTelefoneActionPerformed
        MaskFormatter maskaraTel = null;
        MaskFormatter maskaraCel = null;
        Object selecao = comboTipoTelefone.getSelectedItem();

        try{
            maskaraTel = new MaskFormatter("(##) ####-####");
            maskaraCel = new MaskFormatter("(##) #####-####");
            maskaraTel.setPlaceholderCharacter('_');
            maskaraCel.setPlaceholderCharacter('_');

            if(selecao.equals("Residencial")){
                txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(maskaraTel));
            }else if(selecao.equals("Celular")){
                txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(maskaraCel));
            }
            txtTelefone.setValue(null);
        }catch(Exception ex){
            System.out.println("Erro na máscara. Erro: " + ex.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoTelefoneActionPerformed

    public void preencheTabelaTelefoneProvisorio(String telefone){
        
            DefaultTableModel dtm = (DefaultTableModel) tabelaTelefone.getModel();
            
//            dtm.addColumn("Código");
//            dtm.addColumn("Telefone");
//            dtm.addColumn("Tipo");
//
            dtm.addRow(new Object[]{"", txtTelefone.getText(), comboTipoTelefone.getSelectedItem()});

//            DefaultTableModel tabela = new DefaultTableModel();
//            tabelaTelefone.setModel(tabela);
//            
//            tabela.addColumn("Código");
//            tabela.addColumn("Telefone");
//            tabela.addColumn("Tipo");
//            
//            tabelaTelefone.getColumnModel().getColumn(0).setPreferredWidth(30);//coluna códigos
//            tabelaTelefone.getColumnModel().getColumn(0).setPreferredWidth(80);//coluna telefones
//            tabelaTelefone.getColumnModel().getColumn(1).setPreferredWidth(80);//coluna telefones
//            
//            tabela.addRow(new Object[] { txtTelefone.getText(), comboTipoTelefone.getSelectedItem() });
//            tabela.addRow(new Object[] { "", "" });


    }
    
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
            java.util.logging.Logger.getLogger(Medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Medicos dialog = new Medicos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabDados;
    private javax.swing.JButton btAdicionarTelefone;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btExcluirTelefone;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboTipoPagamento;
    private javax.swing.JComboBox comboTipoTelefone;
    private javax.swing.JComboBox comboTpPgto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelCrm;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelPesquisa;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTipoPagto;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel panelBusca;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelEndereco;
    private javax.swing.JPanel panelFundo;
    private javax.swing.JPanel panelInformacoes;
    private javax.swing.JPanel panelTelefone;
    private javax.swing.JRadioButton radioNome;
    private javax.swing.JRadioButton radioTipoPagamento;
    private javax.swing.JScrollPane scrollTabelaMedico;
    private javax.swing.JTable tabelaMedico;
    private javax.swing.JTable tabelaTelefone;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtCrm;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtIdMedico;
    private javax.swing.JTextField txtIdTpPagto;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextArea txtInformacoes;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtNumero;
    private javax.swing.JTextField txtPesqNome;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
