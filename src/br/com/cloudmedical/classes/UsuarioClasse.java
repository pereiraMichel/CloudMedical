/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.classes;

import br.com.cloudmedical.telas.TelaPrincipal;
import cloudmedical.ConexaoBanco;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import sun.security.provider.MD5;

/**
 *
 * @author Debug
 */
public class UsuarioClasse {
    
    private int idUsuario;
    private String nomeUsuario;
    private String senha;
    private String email;
    private String dataCadastro;
    private String dataAlteracao;
    TelaPrincipal principal;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = MD5(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
    public boolean autenticaUsuario(){
        String sql = "SELECT * FROM tblusuarios WHERE nomeUsuario = '" + this.nomeUsuario + "' AND senha = '" + this.senha + "'";
        conn = new ConexaoBanco().getConnection();
        try{
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();//2ª vez que funciona informando o last()
            if(rs.getRow() == 0){
                return false;
            }
            this.idUsuario = rs.getInt("idUsuario");
//            stmt.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean verificaExisteUsuario(){
        String sql = "SELECT * FROM tblusuarios";
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
//            JOptionPane.showMessageDialog(null, rs.getRow());
            if(rs.getRow() == 0){
                return true;
            }
            stmt.close();
            conn.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public void ultimoIdUsuario(){
        String sql = "SELECT MAX(idUsuario) AS idUsuario FROM tblusuarios";
        conn = new ConexaoBanco().getConnection();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            int dados = rs.getInt("idUsuario") + 1;
            
            this.idUsuario = dados;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void insereUsuario(){
        try{
            ultimoIdUsuario();
            
            conn = new ConexaoBanco().getConnection();
            String sql = "INSERT INTO tblusuarios (idUsuario, nomeUsuario, senha, email, dataCadastro, dataAlteracao) VALUES (" + this.idUsuario + ", '" + this.nomeUsuario + "', '" + this.senha + "', '" + this.email + "', '" + this.dataCadastro + "', '" + this.dataAlteracao + "')";
            
//            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            
            stmt.execute();
            
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String MD5(String senha){
        try{
        String sen = "";
	MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes("UTF-8")));
		sen = hash.toString(16);			
		return sen;
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
}
