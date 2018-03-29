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

/**
 *
 * @author Yan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button calculatebutton;
    @FXML
    private Button cleartextbutton;
    @FXML
    private TextField input_textfield;
    @FXML
    private TextArea output_textarea;
    
    @FXML //
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
