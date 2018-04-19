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
            java.util.logging.Logger.getLogger(MyMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        ClientController cc = new ClientController();
        MyMain myMain = new MyMain(cc);
        myMain.setLocationRelativeTo(null);
        myMain.setVisible(true);
        myMain.setResizable(false);

    }

}
