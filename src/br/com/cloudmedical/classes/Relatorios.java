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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Debug
 */
public class Relatorios {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    
    
    public void geraRelatorioMedicos(){
        
        String sql = "SELECT DISTINCT idMedico, nomeMedico, crm, valor, nomePagamento, nomePerfil, sigla "
                   + "FROM medicos, tipopagamento, perfil "
                   + "GROUP BY crm";

        conn = new ConexaoBanco().getConnection();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            //Gera o relatório                
            String path = "relatorios//reportMedicos.jasper";
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório
            view.setTitle("Relatório de Médicos"); //Preenche o título do relatório
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    public void geraRelatorioTerceirizados(){
        
        String sql = "SELECT DISTINCT idTerceirizado, nomeTerceirizado, documento, servicos, contatos, valorPago, nomePagamento, nomePerfil, sigla "
                   + "FROM terceirizados, tipopagamento, perfil "
                   + "GROUP BY crm";

        conn = new ConexaoBanco().getConnection();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            //Gera o relatório                
            String path = "relatorios//reportMedicos.jasper";
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório
            view.setTitle("Relatório de Médicos"); //Preenche o título do relatório
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
    
}
