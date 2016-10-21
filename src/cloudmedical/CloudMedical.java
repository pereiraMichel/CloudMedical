/*
 * App Clínica Médica
 */
package cloudmedical;

import br.com.cloudmedical.telas.TelaPrincipal;
import java.awt.Color;

/**
 *
 * @author Debug
 */
public class CloudMedical {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TelaPrincipal tela = new TelaPrincipal();
        
        tela.setTitle("Cloud Medical");
        tela.setExtendedState(tela.MAXIMIZED_BOTH);
        tela.setLocationRelativeTo(tela);
//        tela.getContentPane().setBackground(Color.WHITE);
        tela.setVisible(true);
        
    }
    
}
