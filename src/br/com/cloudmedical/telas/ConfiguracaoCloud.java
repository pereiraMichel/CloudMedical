/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.telas;

import br.com.cloudmedical.classes.Configuracao;
import br.com.cloudmedical.classes.UsuarioClasse;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Debug
 */
public class ConfiguracaoCloud extends javax.swing.JDialog {

    Calendar cal;
    Locale local;
    private String macFisico;
    
    /**
     * Creates new form ConfiguracaoCloud
     */
    public ConfiguracaoCloud(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inforMac();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        separador = new javax.swing.JSeparator();
        panelFundo = new javax.swing.JPanel();
        btCadastrar = new javax.swing.JButton();
        labelImage = new javax.swing.JLabel();
        labelMac = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtMac = new javax.swing.JTextField();
        panelDown = new javax.swing.JPanel();
        labelInfo = new javax.swing.JLabel();
        labelMapTi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFundo.setBackground(new java.awt.Color(255, 255, 255));
        panelFundo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/save.png"))); // NOI18N
        btCadastrar.setToolTipText("Salvar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        labelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cloudmedical/images/logoMapTi200x100.png"))); // NOI18N

        labelMac.setText("Registro:");

        labelNome.setText("Nome:");

        labelEmail.setText("E-mail: ");

        javax.swing.GroupLayout panelFundoLayout = new javax.swing.GroupLayout(panelFundo);
        panelFundo.setLayout(panelFundoLayout);
        panelFundoLayout.setHorizontalGroup(
            panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFundoLayout.createSequentialGroup()
                        .addComponent(labelImage)
                        .addGap(39, 39, 39)
                        .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFundoLayout.createSequentialGroup()
                                .addComponent(labelMac)
                                .addGap(18, 18, 18)
                                .addComponent(txtMac, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFundoLayout.createSequentialGroup()
                                .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmail)
                                    .addComponent(labelNome))
                                .addGap(27, 27, 27)
                                .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNome)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelFundoLayout.setVerticalGroup(
            panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFundoLayout.createSequentialGroup()
                .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFundoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelImage))
                    .addGroup(panelFundoLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMac)
                            .addComponent(txtMac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addComponent(btCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addGap(43, 43, 43))
        );

        labelInfo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labelInfo.setText("Cloud Medical - Versão 1.0");

        labelMapTi.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labelMapTi.setText("MAP TI - Desenvolvimento de Softwares");

        javax.swing.GroupLayout panelDownLayout = new javax.swing.GroupLayout(panelDown);
        panelDown.setLayout(panelDownLayout);
        panelDownLayout.setHorizontalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDownLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMapTi)
                .addContainerGap())
        );
        panelDownLayout.setVerticalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMapTi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(separador)
                    .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        Configuracao config = new Configuracao();
        local = new Locale("pt", "BR");
        cal = Calendar.getInstance(local);
        
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        String mesLongo = null;

        if(mes < 10){
            mesLongo = "0" + mes;
        }else{
            mesLongo = String.valueOf(mes);
        }

        String dataHoje = ano + "-" + mesLongo + "-" + dia;

        config.criaXML(txtEmail.getText(), txtMac.getText(), txtNome.getText(), dataHoje);
        sair();

    }//GEN-LAST:event_btCadastrarActionPerformed

    public void sair(){
        dispose();
    }
    
    public void inforMac(){

        try {         
           InetAddress address = InetAddress.getLocalHost();  
           NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
           byte[] mac = ni.getHardwareAddress();
           String macAddress = "";
           for (int i = 0; i < mac.length; i++) {             
               macAddress += (String.format("%02X-", mac[i]));  
           }
           this.macFisico = macAddress.substring(0, macAddress.length()-1);
           
           txtMac.setText(macFisico);
           txtMac.setEnabled(false);
           
//           System.out.println(macAddress.substring(0, macAddress.length()-1));
        } catch (UnknownHostException e) {  
           e.printStackTrace();
        } catch (SocketException e) {  
           e.printStackTrace();  
        }        
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
            java.util.logging.Logger.getLogger(ConfiguracaoCloud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracaoCloud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracaoCloud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracaoCloud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConfiguracaoCloud dialog = new ConfiguracaoCloud(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btCadastrar;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelImage;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelMac;
    private javax.swing.JLabel labelMapTi;
    private javax.swing.JLabel labelNome;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelFundo;
    private javax.swing.JSeparator separador;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMac;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}