/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudmedical.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Debug
 */
public class Configuracao {
    private String macFisico;
    
    
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
//           System.out.println(macAddress.substring(0, macAddress.length()-1));
        } catch (UnknownHostException e) {  
           e.printStackTrace();
        } catch (SocketException e) {  
           e.printStackTrace();  
        }        
    }
    
    

    public void criaXML(String emailUser, String mac, String nomeRegistro, String dataHoje){
        
        Element dados = new Element("dados");
        Document data = new Document(dados);
        
        Element primeiroUsuario = new Element("primeiro");
        primeiroUsuario.setAttribute("id", "123");
        
        Element nome = new Element("registro");
        nome.setText(nomeRegistro);
        
        Element email = new Element("email");
        email.setText(emailUser);
        
        Element macPc = new Element("mac");
        macPc.setText(mac);
        
        Element dataRegistro = new Element("dataregistro");
        dataRegistro.setText(dataHoje);
        
        Element autoriza = new Element("autorizacao");
        autoriza.setText("autorizado");

        primeiroUsuario.addContent(nome);
        primeiroUsuario.addContent(email);
        primeiroUsuario.addContent(macPc);
        primeiroUsuario.addContent(dataRegistro);
        primeiroUsuario.addContent(autoriza);
        
        dados.addContent(primeiroUsuario);
        
        XMLOutputter xout = new XMLOutputter();
        
        org.jdom2.output.Format formato = org.jdom2.output.Format.getPrettyFormat();
        
        xout.setFormat(formato);
     
        try{
            String nomeArquivo = "config.xml";
            File path = new File("C:\\CloudMedical\\Config\\");
            if(!path.exists()){
                File criaPastaPrincipal = new File("C:\\CloudMedical");
                if(!criaPastaPrincipal.exists()){
                    criaPastaPrincipal.mkdir();
                }else{
                    System.out.println("Não foi possível criar a pasta principal.");
                }
                
                File criaPastaConfig = new File("C:\\CloudMedical\\Config\\");
                if(!criaPastaConfig.exists()){
                    criaPastaConfig.mkdir();
                }else{
                    System.out.println("Não foi possível criar a pasta config.");
                }
            }else{
                System.out.println("Não existe.");
                
            }
            
            String pastaExistente = "C:\\CloudMedical\\Config\\"+nomeArquivo;
            
            FileWriter arquivo = new FileWriter(new File(pastaExistente));
            xout.output(data, arquivo);
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void leituraXML(){
        File documento = new File("C:\\CloudMedical\\config\\config.xml");
        
        if(documento.exists()){
            
        }else{
            
        }
    }
    
    public boolean retornaArquivo(){
        File path = new File("C:\\CloudMedical\\config\\config.xml");
        
        if(path.exists()){
            return true;
        }
        
        return false;
    }
    
    public void geraTxtError(String problema){
        String pathPrincipal = "C:\\CloudMedical";
        String pathLog = "C:\\CloudMedical\\Log\\";
        String pathErro = "C:\\CloudMedical\\Log\\erro.txt";
        
        File principal = new File(pathPrincipal);
        File log = new File(pathLog);
        File erro = new File(pathErro);
        
        if(!erro.exists()){
            if(!principal.exists()){
                principal.mkdir();
            }

            if(!log.exists()){
                log.mkdir();
            }
        }
        
        criaArquivoTxt(erro, problema);

    }
    
    public void criaArquivoTxt(File path, String problema){
        try{
            Date data = new Date();
            FileWriter arquivo = new FileWriter(path, true);
            PrintWriter grava = new PrintWriter(arquivo);
            
            grava.append("===================================");
            grava.println("\n");
            grava.append("Registro de Inconsistência.");
            grava.println("\n");
            grava.append(data.toString());
            grava.println("\n");
            grava.append(problema);
            grava.println("\n");
            grava.append("===================================");
            grava.println("\n");
            arquivo.close();
            
        }catch(IOException ex){
            System.out.println("Erro ao criar o arquivo: " + ex.getMessage());
        }
        
    }
    
}
