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
public class ManterEndereco {
    private int idEndereco;
    private String nomeEndereco;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private int codMedico;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNomeEndereco() {
        return nomeEndereco;
    }

    public void setNomeEndereco(String nomeEndereco) {
        this.nomeEndereco = nomeEndereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }
    
    public void preencheEndereco(){
        String sql = "SELECT * FROM endereco WHERE codMedico = " + this.codMedico;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            if(rs.getRow() > 0){
                this.nomeEndereco = rs.getString("nomeEndereco");
                this.numero = rs.getInt("numero");
                this.complemento = rs.getString("complemento");
                this.bairro = rs.getString("bairro");
                this.cidade = rs.getString("cidade");
                this.estado = rs.getString("estado");
            }

            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public boolean verificaEnderecoExclusao(){
        String sql = "SELECT idEndereco FROM endereco WHERE codMedico = " + this.codMedico;
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
    
    public void verificaExisteEndereco(){
        String sql = "SELECT * FROM endereco WHERE codMedico = " + this.codMedico;
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            if(rs.getRow() > 0){
                this.idEndereco = rs.getInt("idEndereco");
                alteraEndereco();
            }else{
                insereEndereco();
            }
            
            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idEndereco) AS idEndereco FROM endereco";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idEndereco") + 1;
            
            this.setIdEndereco(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void insereEndereco(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_ENDERECO(" + this.idEndereco + ", '" + this.nomeEndereco + "', " + this.numero + ", '" + this.complemento + "', '" + this.bairro + "', '" + this.cidade + "', '" + this.estado + "', " + this.codMedico + ")";
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void alteraEndereco(){
        try{
            String sql = "SELECT ALTERA_ENDERECO(" + this.idEndereco + ", '" + this.nomeEndereco + "', " + this.numero + ", '" + this.complemento + "', '" + this.bairro + "', '" + this.cidade + "', '" + this.estado + "', " + this.codMedico + ")";
//            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean retornaEndereco(){
        try{
            String sql = "SELECT COUNT(idEndereco) AS idEndereco FROM endereco WHERE codMedico = " + this.codMedico;
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            System.out.println("Passou o Statement.");
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();

            if(rs.getRow() == 0){
                return true;//Informa que está vazio;
            }
            
            stmt.close();
            
        }catch(SQLException e){
            System.out.println("Erro no método retornaEndereco(). " + e.getMessage());
        }
        return false;
        
    }
    
    public void excluiEndereco(){
        
        if(!retornaEndereco()){
            try{
                String sql = "SELECT EXCLUI_ENDERECO(" + this.codMedico + ")";
                stmt = conn.prepareStatement(sql);
                stmt.executeQuery();
                stmt.close();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    
}
