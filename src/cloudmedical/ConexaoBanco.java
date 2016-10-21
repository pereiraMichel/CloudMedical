/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudmedical;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Debug
 */
public class ConexaoBanco {
    
    private String servidor;
    private String usuario;
    private String senha;
    private String database;
    private String path;
    
    
    public static Properties getProp(){
        
        try{
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("banco.properties");
            props.load(file);
            return props;
        }catch(FileNotFoundException e){
            e.getMessage();
        }catch(IOException ex){
            ex.getMessage();
        }
        return null;
    }
    
    public Connection getConnection(){
        
        Connection con = null;
        Properties prop = getProp();
        
        this.servidor = prop.getProperty("prop.server.servidor");
        this.usuario = prop.getProperty("prop.server.usuario");
        this.senha = prop.getProperty("prop.server.senha");
        this.database = prop.getProperty("prop.server.database");
        this.path = "jdbc:mysql://";

        try{
            
            con = DriverManager.getConnection(this.path + this.servidor + "/" + this.database, this.usuario, this.senha);
                        
            if(con == null){
                JOptionPane.showMessageDialog(null, "Houve um erro de conexão.");
            }else{
                return con;
            }
            
//            return con;
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Conexão: " + this.path + this.servidor + "/" + this.database + this.usuario + this.senha);

        }
        return null;
    }
}
