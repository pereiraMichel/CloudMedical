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

/**
 *
 * @author Debug
 */
public class ManterInformacoes {
    
    private int idInformacao;
    private String texto;
    private int codMedico;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public int getIdInformacao() {
        return idInformacao;
    }

    public void setIdInformacao(int idInformacao) {
        this.idInformacao = idInformacao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    public boolean verificaInformacaoExclusao(){
        String sql = "SELECT idInformacao FROM informacoes WHERE codMedico = " + this.codMedico;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            if(rs.getRow() > 0){
                return true; //contém conteúdo
            }
            
        }catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        return false;// Não contém conteúdo
    }
    
    public void preencheInformacoes(){
        String sql = "SELECT * FROM informacoes WHERE codMedico = " + this.codMedico;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            if(rs.getRow() > 0){
                this.texto = rs.getString("texto");
            }

            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void verificaExisteInformacao(){
        String sql = "SELECT * FROM informacoes WHERE codMedico = " + this.codMedico;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            if(rs.getRow() > 0){
                this.idInformacao = rs.getInt("idInformacao");
                alteraInformacao();
            }else{
                insereInformacao();
            }
            
            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idInformacao) AS idInformacao FROM informacoes";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idInformacao") + 1;
            
            this.setIdInformacao(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void insereInformacao(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_INFORMACAO(" + this.idInformacao + ", '" + this.texto + "', " + this.codMedico + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void alteraInformacao(){
        try{
            String sql = "SELECT ALTERA_INFORMACAO(" + this.idInformacao + ", '" + this.texto + "', " + this.codMedico + ")";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean retornaInformacao(){
        try{
            String sql = "SELECT COUNT(idInformacao) AS idInformacao FROM informacoes WHERE codMedico = " + this.codMedico;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.first();
            rs.next();
            rs.last();

            if(rs.getRow() == 0){
                return true;//Informa que está vazio;
            }
            
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
        
    }
    
    public void excluiInformacao(){
//        if(!retornaInformacao()){
            try{
                String sql = "SELECT EXCLUI_INFORMACAO(" + this.codMedico + ")";
                stmt = conn.prepareStatement(sql);
                stmt.executeQuery();
                stmt.close();

            }catch(SQLException e){
                e.printStackTrace();
            }
//        }
        
    }
    
    
}
