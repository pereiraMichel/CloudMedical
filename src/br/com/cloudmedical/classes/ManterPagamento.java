/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.classes;

import cloudmedical.ConexaoBanco;
import com.jaspersoft.jasperserver.ws.scheduling.Job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Debug
 */
public class ManterPagamento {
    
    private int idPagamento;
    private double valorPago;
    private String competencia;
    private double valorPendente;
    private int codMedico;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    DefaultTableModel tabelaModel;

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public double getValorPendente() {
        return valorPendente;
    }

    public void setValorPendente(double valorPendente) {
        this.valorPendente = valorPendente;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }
    
    public void preencheCombo(JComboBox combo){
        String sql = "SELECT * FROM tipopagamento";
        conn = new ConexaoBanco().getConnection();
        
//        Job[] jobs = new Job[] { new Job("Developer") };
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
//            rs.first();
//            rs.last();
            while(rs.next()){
                String nome = rs.getString("nomePagamento");
                combo.addItem(nome);
            }
        
//            combo.setSize(170, 20);//(w, h)
            
            rs.close();
            stmt.close();
            
        }catch(Exception ex){
            System.out.println("Houve um erro na execução. " + ex.getMessage());
        }
    }
    
    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idPagamento) AS idPagamento FROM pagamento";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idPagamento") + 1;
            
            this.setIdPagamento(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void inserePagamento(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_PAGAMENTO(" + this.idPagamento + ", " + this.valorPago + ", '" + this.competencia + "', " + this.valorPendente + ", " + this.codMedico + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void alteraPagamento(){
        try{
            String sql = "SELECT ALTERA_PAGAMENTO(" + this.idPagamento + ", " + this.valorPago + ", '" + this.competencia + "', " + this.valorPendente + ", " + this.codMedico + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    
    public void excluiEndereco(){
        try{
            String sql = "SELECT EXCLUI_PAGAMENTO(" + this.idPagamento + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
}
