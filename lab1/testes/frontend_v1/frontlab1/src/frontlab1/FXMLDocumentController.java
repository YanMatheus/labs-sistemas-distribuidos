/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontlab1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.io.File;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
/**
 *
 * @author Yan
 */
public class FXMLDocumentController implements Initializable {
    //Desvio podrão
    @FXML
    private Button calculatebutton;
    @FXML
    private Button cleartextbutton;
    @FXML
    private TextField input_textfield;
    @FXML
    private TextArea output_textarea;
    
    //Copia de diretorios
    
    @FXML
    private TextField input_localdir;
    @FXML
    private TextField input_remotedir;
    @FXML
    private Button select_localdir;
    @FXML
    private Button initcopy;
    @FXML
    private ProgressBar progressbar;
    
    
    //Start methods
    
    
    @FXML 
    private void handleButtonAction(ActionEvent event) {
        
        String output;
        
        output = "Desvio Padrão para " + input_textfield.getText() + "\n";
        output_textarea.setText(output_textarea.getText()+ output);
          
        clearInputText(event);
    }
    
    @FXML //Limpa o texto de input
    private void clearInputText(ActionEvent event){
        
        input_textfield.setText("");
        
    }
    
    @FXML //Calcula o desvio padrão
    private void calculateDV(ActionEvent event){
    
        clearInputText(event);

        
    }
    
    
     @FXML //Limpa o texto de input do diretorio local
    private void clearInputLocalDir(ActionEvent event){
        
        input_localdir.setText("");
        
    }
     @FXML //Limpa o texto de input do diretorio remoto
    private void clearInputRemoteDir(ActionEvent event){
        
        input_remotedir.setText("");
        
    }
    
    @FXML //Limpa o texto de input
    private void initCopy(ActionEvent event){
        
        System.out.println("Iniciando transferencia de diretorio");    
        
    }
    
    @FXML
    public void selectlocaldir(ActionEvent event){
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Selecione um diretórios");
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        
        if(selectedDirectory == null){
            input_localdir.setPromptText("Nada selecionado, digite ou escolha um diretório local");
        }else{
            input_localdir.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    
    
    
    public double desvioPadrao(ArrayList<Float> numbers) {
		if (numbers.size() == 1) {
			return 0.0;
		} else {
			double mediaAritimetica = mediaAritimetica(numbers);
			double somatorio = 0l;
			for (int i = 0; i < numbers.size(); i++) {
				double result = numbers.get(i) - mediaAritimetica;
				somatorio = somatorio + result * result;
			}
			return Math.sqrt(((double) 1 /( numbers.size()-1))
					* somatorio);
		}
	}
    
    
	public double mediaAritimetica(ArrayList<Float> numeros) {
		double somatorio = 0l;
		for (Float d : numeros) {
			somatorio += d;
		}
		return somatorio / numeros.size();
	}
        
   

   

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
