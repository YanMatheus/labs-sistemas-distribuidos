package lab1noswing;
import javax.swing.*;
import lab1noswing.MainWindow;
import lab1noswing.ConnectionDialog;
/**
 *
 * @author Yan
 */
public class Lab1noSwing {
    static JPanel frame = new JPanel();
    static JFrame jframe = new JFrame();
    static JDialog connectionInput = new ConnectionDialog(jframe,true);
    public String userIP, userPort; 
    
   
    
    public void setConnections(String IP, String Port){//método usado em ConnectionDialog para pegar os dados de entrada e passar para cá como string;
        userIP = IP;
        userPort = Port;
    }
    
    public String getUserIP(){
        return userIP;
    }
     public String getUserPort(){
        return userPort;
    }
    
    public static void main(String[] args) {
      
        MainWindow mainWindow = new MainWindow();
        connectionInput.setLocationRelativeTo(null);
        
        
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
      
    }
    
}
