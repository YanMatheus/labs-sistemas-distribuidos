package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;


public class TelaPrincipal {
  private JFrame janelaPrincipal;
  private ClientSocketController clientController;

  /**
   * Iniciar a aplicação.
   */
  public  static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.janelaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }


  public TelaPrincipal() {
    dialogRequestCredentials();
    initialize();
  }

  private void initialize() {
    janelaPrincipal = new JFrame();
    Container panel = janelaPrincipal.getContentPane();

    final JTextField num1 = new JTextField(10);
    final JTextField num2 = new JTextField(10);
    final JTextArea areaResposta = new JTextArea(0, 28);
    JButton btnSomar = new JButton("Somar");

		janelaPrincipal.setTitle("Example Remote Procedure Call");
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setBounds(100, 100, 350, 192);
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.setLocationRelativeTo(null);
    janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    areaResposta.setEditable(false);

    panel.setLayout(new FlowLayout());
    panel.add(num1);
    panel.add(num2);
    panel.add(btnSomar);
    panel.add(areaResposta);

    btnSomar.addActionListener((ActionEvent ae) -> {
      String strNum1 = num1.getText().trim();
      String strNum2 = num2.getText().trim();

      try {

        int num3 = Integer.parseInt(strNum1);
        int num4 = Integer.parseInt(strNum2);
        int soma = clientController.callRPCSomar(num3, num4);

        // Inserir resposta na primeira linha
        try {
          areaResposta.getDocument().insertString(0,
            String.format("> %s + %s = %d\n", strNum1, strNum2, soma), null);
        } catch (BadLocationException e) {
          e.printStackTrace();
        }

      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null,
          "Os números devem ser menores que "+ Integer.MAX_VALUE,
          "Formato Inválido", JOptionPane.ERROR_MESSAGE);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  /**
   * Solicita os parâmetros para a conexão com servidor
   * e, em caso de sucesso, inicia a conexão.
   */
  private void dialogRequestCredentials() {
    JTextField serverHostField = new JTextField(8);
    JPanel panel = new JPanel( new GridLayout(2, 2) );
    NumberFormatter nfUS = new NumberFormatter( NumberFormat.getInstance(Locale.US) );
    JFormattedTextField serverPortField = new JFormattedTextField(nfUS);

    nfUS.setValueClass(Integer.class);
    nfUS.setMinimum(0);
    nfUS.setMaximum(65535);
    nfUS.setAllowsInvalid(false);

    serverHostField.setText("localhost");
    serverPortField.setText("4444");

    panel.add( new JLabel("Host:") );
    panel.add(serverHostField);
    panel.add( new JLabel("Porta:") );
    panel.add(serverPortField);

    do {
      int selectOpt = JOptionPane.showConfirmDialog(null, panel,
        "Informações para a Conexão", JOptionPane.OK_CANCEL_OPTION);

      if (selectOpt != JOptionPane.OK_OPTION) System.exit(1);

      String serverHost = serverHostField.getText();
      int serverPort = Integer.parseInt(
        serverPortField.getText().replace(",", ""));

      try {
        this.clientController = new ClientSocketController(serverHost, serverPort);
        return;
      } catch (IOException ex) {
          JOptionPane.showMessageDialog(null,
            "Não foi possível conectar",
            "Erro ao Conectar", JOptionPane.ERROR_MESSAGE);
      }
    } while (true);
  }
}
