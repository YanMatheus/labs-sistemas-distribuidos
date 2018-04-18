package client.GUI;

/**
 * Inicializa a janela principal e
 * cordena a conexão do cliente com o servidor.
 *
 * @author Yan
 * @author Micael
 */
public class ClientController {

    public static void main(String[] args) {

        // estilo
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        ClientController cc = new ClientController();
        MainWindow mainWindow = new MainWindow(cc);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);

    }

}
