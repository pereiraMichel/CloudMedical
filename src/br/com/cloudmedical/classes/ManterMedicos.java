/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.classes;

import cloudmedical.ConexaoBanco;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Debug
 */
public class ManterMedicos {
    
    private int idMedico;
    private String nomeMedico;
    private String crm;
    private int tipoPagamento;
    private double valor;
    private int codUsuario;
    private int codPerfil;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    ManterEndereco endereco;
    ManterInformacoes informacoes;
    
    
    //Para retorno de pesquisa
    private String nomePagamento;
    private String nomeUsuario;

    public int getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(int codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getNomePagamento() {
        return nomePagamento;
    }

    public void setNomePagamento(String nomePagamento) {
        this.nomePagamento = nomePagamento;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public void setIdMedico(int idMedico){
        this.idMedico = idMedico;
    }
    public int getIdMedico(){
        return this.idMedico;
    }
    public void setNomeMedico(String nomeMedico){
        this.nomeMedico = nomeMedico;
    }
    public String getNomeMedico(){
        return this.nomeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public void verificaExisteMedico(){
        String sql = "SELECT * FROM medicos WHERE idMedico = " + this.idMedico;
//        String sql = "SELECT * FROM medicos WHERE nomeMedico = '" + this.nomeMedico + "' AND crm = '" + this.crm + "'";
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            if(rs.getRow() > 0){
                alteraMedico();
            }else{
                insereMedico();
            }
            
            stmt.close();
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    public void verificaUltimoId() throws SQLException{
        conn = new ConexaoBanco().getConnection();
        String sql = "SELECT MAX(idMedico) AS idMedico FROM medicos";
        int dados = 0;
        try{
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            rs.first();
            dados = rs.getInt("idMedico") + 1;
            
            this.setIdMedico(dados);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean cellEditable(int row, int column){
        return false;
    }
    
    public void preencheTabelaMedicos(JTable medicos){
        String sql = "SELECT idMedico, nomeMedico, crm FROM medicos";
        conn = new ConexaoBanco().getConnection();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
//            rs.first();
//            rs.next();
//            rs.last();
//            
            DefaultTableModel tabela = new DefaultTableModel();
            medicos.setModel(tabela);
            tabela.addColumn("Código");
            tabela.addColumn("Nome do Médico");
            tabela.addColumn("CRM");
            
            medicos.getColumnModel().getColumn(0).setPreferredWidth(30);//tamanho da tabela
            medicos.getColumnModel().getColumn(1).setPreferredWidth(100);//tamanho da tabela
            medicos.getColumnModel().getColumn(2).setPreferredWidth(50);//tamanho da tabela
            
            while(rs.next()){
                int campoIdMedico = rs.getInt("idMedico");
                String campoNome = rs.getString("nomeMedico");
                String campoCRM = rs.getString("crm");
                tabela.addRow(new Object[]{ campoIdMedico, campoNome, campoCRM });
//                this.cellEditable(tabela.getRowCount(), tabela.getColumnCount());
            }
            
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void preencheCamposMedicos(){
        String sql = "SELECT m.idMedico, m.nomeMedico, m.crm, m.valor, tp.nomePagamento, u.idUsuario, u.nomeUsuario "
                + "   FROM medicos m"
                + "   INNER JOIN tipopagamento tp ON m.codtipoPagamento = tp.idTipoPagamento"
                + "   INNER JOIN tblusuarios u ON m.codUsuario = u.idUsuario"
                + "   WHERE m.idMedico = " + this.idMedico;
        conn = new ConexaoBanco().getConnection();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
            this.nomeMedico = rs.getString("nomeMedico");
            this.crm = rs.getString("crm");
            this.valor = rs.getDouble("valor");
            this.nomePagamento = rs.getString("nomePagamento");
            this.nomeUsuario = rs.getString("nomeUsuario");
            this.codUsuario = rs.getInt("idUsuario");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
    public void pesquisaNomeMedico(JTable tabelaMedico, String campo){
        String sql = "SELECT m.idMedico, m.nomeMedico, m.crm, m.valor, tp.nomePagamento, u.idUsuario, u.nomeUsuario "
                + "   FROM medicos m"
                + "   INNER JOIN tipopagamento tp ON m.codtipoPagamento = tp.idTipoPagamento"
                + "   INNER JOIN tblusuarios u ON m.codUsuario = u.idUsuario"
                + "   WHERE m.nomeMedico LIKE '%" + campo + "%' OR m.crm LIKE '%" + campo + "%'";
        conn = new ConexaoBanco().getConnection();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            rs.first();
            rs.next();
            rs.last();
            
//            System.out.println("Nome do médico: " + rs.getString("nomeMedico"));
            
                DefaultTableModel tabela = new DefaultTableModel();
                tabelaMedico.setModel(tabela);
                tabela.addColumn("Código");
                tabela.addColumn("Nome do Médico");
                tabela.addColumn("CRM");

                tabelaMedico.getColumnModel().getColumn(0).setPreferredWidth(30);//tamanho da tabela
                tabelaMedico.getColumnModel().getColumn(1).setPreferredWidth(100);//tamanho da tabela
                tabelaMedico.getColumnModel().getColumn(2).setPreferredWidth(50);//tamanho da tabela

            if(rs.getRow() > 0){
                int campoIdMedico = rs.getInt("idMedico");
                String campoNome = rs.getString("nomeMedico");
                String campoCRM = rs.getString("crm");
                tabela.addRow(new Object[]{ campoIdMedico, campoNome, campoCRM });
            }else{
                tabela.addRow(new Object[]{ "Não localizado", "Não localizado", "Não localizado" });
                
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void insereMedico(){
        try{
            verificaUltimoId();
            String sql = "SELECT INSERE_MEDICO(" + this.idMedico + ", '" + this.nomeMedico + "', '" + this.crm + "', " + this.valor + ", " + this.getCodUsuario() + ", " + this.tipoPagamento + ")";
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void alteraMedico(){
        try{
            String sql = "SELECT ALTERA_MEDICO(" + this.idMedico + ", '" + this.nomeMedico + "', '" + this.crm + "', " + this.tipoPagamento + ", " + this.valor + ", " + this.codUsuario + ")";
//            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void excluiMedico(){
        endereco = new ManterEndereco();
        informacoes = new ManterInformacoes();
        try{
            String sql = "SELECT EXCLUI_MEDICO(" + this.idMedico + ")";
//            endereco.setCodMedico(this.idMedico);
//            informacoes.setCodMedico(this.idMedico);
//            
//            endereco.setCodMedico(this.idMedico);
//            informacoes.setCodMedico(this.idMedico);
//            
//            if(endereco.verificaEnderecoExclusao()){//se tiver conteúdo
//                endereco.excluiEndereco();
//            }
//            
//            if(informacoes.verificaInformacaoExclusao()){
//                informacoes.excluiInformacao();
//            }
            
            
//            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
}
