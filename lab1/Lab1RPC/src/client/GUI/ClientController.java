package client.GUI;

import client.connection.SocketController;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Inicializa a janela principal e
 * cordena a conexão do cliente com o servidor.
 *
 * @author Yan
 * @author micael
 */
public class ClientController {

    private SocketController cs = null;

    public void setClientSocket(SocketController cs) {
        this.cs = cs;
    }

    public void closeClientSocket() {
        this.cs.closeSocket();
    }

    /**
     *
     * @param valoresStr
     * @return
     */
    public Double calcularDesvioPadrao(String valoresStr) {
        if (this.cs == null) return null;

        try {

            List<Double> valores = Arrays.stream( valoresStr.split(" ") )
                                         .map(Double::parseDouble)
                                         .collect(Collectors.toList());

            return this.cs.callRPDesvioPadrao(valores);

        } catch (NumberFormatException | IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param dirRemoto
     * @param dirDestino
     * @return
     */
    public Boolean baixarDiretorio(String dirRemoto, String dirDestino) {
        if (this.cs == null) return false;

        try {

            return this.cs.callRPBaixarDiretorio(dirRemoto, dirDestino);

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }


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
