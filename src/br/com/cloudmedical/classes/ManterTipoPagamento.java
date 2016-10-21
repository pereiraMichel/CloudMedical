/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.classes;

import cloudmedical.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Debug
 */
public class ManterTipoPagamento {
    
    private int idTipoPagamento;
    private String nomePagamento;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public int getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(int idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getNomePagamento() {
        return nomePagamento;
    }

    public void setNomePagamento(String nomePagamento) {
        this.nomePagamento = nomePagamento;
    }
    
    public void preencheComboTipoPagamento(JComboBox combo){
        DefaultComboBoxModel tabela = (DefaultComboBoxModel) combo.getModel();
        try{
            String sql = "SELECT * FROM tipopagamento";
            conn = new ConexaoBanco().getConnection();
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
//            rs.first();
//            if(rs.getRow() >= 0){
                while(rs.next()){
                    tabela.addElement(rs.getString("nomePagamento"));
                    combo.setModel(tabela);
                }
//            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idTipoPagamento) AS idTipoPagamento FROM tipopagamento";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idTipoPagamento") + 1;
            
            this.setIdTipoPagamento(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void verificaTipoPagamento(){
        String sql = "SELECT * FROM tipopagamento WHERE idTipoPagamento = " + this.idTipoPagamento;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            if(rs.getRow() > 0){
                alteraTipoPagamento();
            }else{
                insereTipoPagamento();
            }
            
            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    public void insereTipoPagamento(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_TIPO_PAGAMENTO(" + this.idTipoPagamento + ", '" + this.nomePagamento + "')";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void alteraTipoPagamento(){
        try{
            String sql = "SELECT ALTERA_TIPO_PAGAMENTO(" + this.idTipoPagamento + ", '" + this.nomePagamento + "')";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public void excluiTipoPagamento(){
        try{
            verificaUltimoId();
            String sql = "SELECT EXCLUI_TIPO_PAGAMENTO(" + this.idTipoPagamento + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public int returnIdTipoPagamento(String selecao){
        String sql = "SELECT idTipoPagamento FROM tipopagamento WHERE nomePagamento='"+selecao+"'";
        conn = new ConexaoBanco().getConnection();
        try{
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
//            rs.first();
//            rs.next();
            rs.last();
            
            return rs.getInt("idTipoPagamento");
            
        }catch(Exception ex){
            System.out.println("Houve um erro na consulta do id do tipo de pagamento. Erro: " + ex.getMessage());
        }
        return 0;
    }

    public void pesquisaTipoPagamentoMedico(JTable tabelaMedico, String campo){
//        System.out.println("Seleção: " + campo);
        String sql = "SELECT m.idMedico, m.nomeMedico, m.crm, m.valor, tp.idTipoPagamento, tp.nomePagamento, u.idUsuario, u.nomeUsuario "
                + "   FROM medicos m"
                + "   INNER JOIN tipopagamento tp ON m.codtipoPagamento = tp.idTipoPagamento"
                + "   INNER JOIN tblusuarios u ON m.codUsuario = u.idUsuario"
                + "   WHERE tp.nomePagamento = '" + campo + "'";
        conn = new ConexaoBanco().getConnection();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
//            rs.first();
//            rs.next();
//            rs.last();
            
//            System.out.println("Nome do médico: " + rs.getString("nomeMedico"));
            
                DefaultTableModel tabela = new DefaultTableModel();
                tabelaMedico.setModel(tabela);
                tabela.addColumn("Código");
                tabela.addColumn("Nome do Médico");
                tabela.addColumn("CRM");

                tabelaMedico.getColumnModel().getColumn(0).setPreferredWidth(30);//tamanho da tabela
                tabelaMedico.getColumnModel().getColumn(1).setPreferredWidth(100);//tamanho da tabela
                tabelaMedico.getColumnModel().getColumn(2).setPreferredWidth(50);//tamanho da tabela

//            if(rs.getRow() > 0){
                while(rs.next()){
                    int campoIdMedico = rs.getInt("idMedico");
                    String campoNome = rs.getString("nomeMedico");
                    String campoCRM = rs.getString("crm");
                    tabela.addRow(new Object[]{ campoIdMedico, campoNome, campoCRM });
                }
//            }else{
//                tabela.addRow(new Object[]{ "Não localizado", "Não localizado", "Não localizado" });
                
//            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }

    
}
