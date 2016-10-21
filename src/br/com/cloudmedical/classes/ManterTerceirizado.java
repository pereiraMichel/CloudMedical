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
import javax.swing.JOptionPane;

/**
 *
 * @author Debug
 */
public class ManterTerceirizado {
    
    private int idTerceirizado;
    private String nomeTerceirizado;
    private String documento;
    private String servicos;
    private String contatos;
    private double valorPagto;
    private int tipoPagamento;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public int getIdTerceirizado() {
        return idTerceirizado;
    }

    public void setIdTerceirizado(int idTerceirizado) {
        this.idTerceirizado = idTerceirizado;
    }

    public String getNomeTerceirizado() {
        return nomeTerceirizado;
    }

    public void setNomeTerceirizado(String nomeTerceirizado) {
        this.nomeTerceirizado = nomeTerceirizado;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getServicos() {
        return servicos;
    }

    public void setServicos(String servicos) {
        this.servicos = servicos;
    }

    public String getContatos() {
        return contatos;
    }

    public void setContatos(String contatos) {
        this.contatos = contatos;
    }

    public double getValorPagto() {
        return valorPagto;
    }

    public void setValorPagto(double valorPagto) {
        this.valorPagto = valorPagto;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    
    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idTerceirizado) AS idTerceirizado FROM terceirizados";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idTerceirizado") + 1;
            
            this.setIdTerceirizado(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void insereTerceirizado(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_TERCEIRIZADO(" + this.idTerceirizado + ", '" + this.nomeTerceirizado + "', '" + this.documento + "', '" + this.servicos + "', '" + this.contatos + "', " + this.valorPagto + ", " + this.tipoPagamento + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void alteraTerceirizado(){
        try{
            String sql = "SELECT ALTERA_TERCEIRIZADO(" + this.idTerceirizado + ", '" + this.nomeTerceirizado + "', '" + this.documento + "', '" + this.servicos + "', '" + this.contatos + "', " + this.valorPagto + ", " + this.tipoPagamento + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    
    public void excluiTerceirizado(){
        try{
            String sql = "SELECT EXCLUI_TERCEIRIZADO(" + this.idTerceirizado + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
}
