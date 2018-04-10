package lab1noswing;
import javax.swing.*;
import lab1noswing.ConnectionInputDialog;
/**
 *
 * @author Yan
 */
public class Lab1noSwing {
    static JFrame frame = new JFrame();
    static String userIP, userPort; 
    
   
    public static void main(String[] args) {
        
        
        ConnectionInputDialog dialog = new ConnectionInputDialog(frame, true);
        
        
        if(dialog.isReady == true){
            
                userIP = dialog.getIPInput();
                userPort = dialog.getPortInput();
                System.out.println("IP inserido: "+userIP+ "/ Porta inserida: "+userPort);
                //Começa conexão
        }
        dialog.setVisible(true);
    }
    
}
