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
public class ManterTelefone {
    
    private int idTelefone;
    private String telefone;
    private int codTipoTelefone;
    private int codProprietario;
    private int codPerfil;
    private boolean conteudo;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public boolean isConteudo() {
        return conteudo;
    }

    public void setConteudo(boolean conteudo) {
        this.conteudo = conteudo;
    }

    public int getCodPerfil(){
        return codPerfil;
    }
    
    public void setCodPerfil(int codPerfil){
        this.codPerfil = codPerfil;
    }
    
    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCodTipoTelefone() {
        return codTipoTelefone;
    }

    public void setCodTipoTelefone(int codTipoTelefone) {
        this.codTipoTelefone = codTipoTelefone;
    }

    public int getCodProprietario() {
        return codProprietario;
    }

    public void setCodProprietario(int codMedico) {
        this.codProprietario = codMedico;
    }
    
    public void preencheComboTipoTelefone(JComboBox combo){
        DefaultComboBoxModel tabela = (DefaultComboBoxModel) combo.getModel();
        try{
            String sql = "SELECT * FROM tipotelefone";
            conn = new ConexaoBanco().getConnection();
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
//            rs.first();
//            if(rs.getRow() >= 0){
                while(rs.next()){
                    tabela.addElement(rs.getString("tipo"));
                    combo.setModel(tabela);
                }
//            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void preencheTabelaTelefone(JTable tabelaTelefone){
        String sql = "SELECT t.idTelefone, t.telefone, p.nomePerfil, tp.tipo " +
                      "FROM telefone t " +
                      "INNER JOIN tipotelefone tp ON t.codTipoTelefone = tp.idTipoTelefone " +
                      "INNER JOIN perfil p ON p.idPerfil="+this.codPerfil+ " " +
                      "WHERE t.codProprietario = "+this.codProprietario;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            DefaultTableModel tabela = new DefaultTableModel();
            tabelaTelefone.setModel(tabela);
            
            tabela.addColumn("Código");
            tabela.addColumn("Telefone");
            tabela.addColumn("Tipo");
            
            tabelaTelefone.getColumnModel().getColumn(0).setPreferredWidth(30);//coluna códigos
            tabelaTelefone.getColumnModel().getColumn(1).setPreferredWidth(60);//coluna telefones
            tabelaTelefone.getColumnModel().getColumn(2).setPreferredWidth(80);//coluna telefones
            
            while(rs.next()){
                
                if(rs.getRow() > 0){
                    int campoId = rs.getInt("idTelefone");
                    String telefone = rs.getString("telefone");
                    String tipo = rs.getString("tipo");

                    tabela.addRow(new Object[] { campoId, telefone, tipo });
                }else{
                    tabela.addRow(new Object[] { "Vazio", "Vazio", "Vazio" });
                }
                
            }
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void verificaExisteTelefone(){
        String sql = "SELECT * FROM telefone WHERE telefone = '" + this.telefone + "'";
        conn = new ConexaoBanco().getConnection();
//        System.out.println(sql);
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            if(rs.getRow() > 0){
                alteraTelefone();
            }else{
                insereTelefone();
            }
            
            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public int retornaIdTipoTelefone(String telefone){
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT idTipoTelefone, tipo FROM tipotelefone WHERE tipo = '" + telefone + "'";
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            return rs.getInt("idTipoTelefone");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idTelefone) AS idTelefone FROM telefone";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idTelefone") + 1;
            
            this.setIdTelefone(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void insereTelefone(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_TELEFONE(" + this.idTelefone + ", '" + this.telefone + "', " + this.codProprietario + ", " + this.codTipoTelefone + ", " + this.codPerfil + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void alteraTelefone(){
        try{
            String sql = "SELECT ALTERA_TELEFONE(" + this.idTelefone + ", '" + this.telefone + "', " + this.codProprietario + ", " + this.codTipoTelefone + ", " + this.codPerfil + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    
    public void excluiTelefone(){
        try{
            String sql = "SELECT EXCLUI_TELEFONE(" + this.idTelefone + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
}
