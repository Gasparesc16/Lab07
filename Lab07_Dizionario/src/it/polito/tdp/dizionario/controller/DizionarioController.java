package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	private Model model;

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	@FXML // fx:id="btnTuttiVicini"
    private Button btnTuttiVicini; // Value injected by FXMLLoader

	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		
		// Estraggo la lunghezza di input
		String numeroLettere = inputNumeroLettere.getText();
		
		int lun;

		try {
			
			lun = Integer.parseInt(numeroLettere);
			
			if(lun == 0){
				
				txtResult.setText("Controllare il numero di lettere inserite!!!");
				return;
			}
			
			List<String> parole = new ArrayList<String>(model.createGraph(lun));
			
			//txtResult.appendText(""+parole);
			
			txtResult.appendText("Parole caricate: " + parole.size() + "\n");
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		
		txtResult.appendText("Grado massimo:\n");
		txtResult.appendText(model.findMaxDegree() + "\n");
		
		/*
		try {
			//txtResult.setText("Controller -- TODO!");

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
		
		*/
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		
		// Estraggo la stringa
		String s = inputParola.getText();
		
		// Controllo la lunghezza della stringa
		
		if(s.length() != model.getLunghezza()){
			
			txtResult.appendText("Parola inserita non valida !!");
			return;
		}
		
		// Prendo dal model la lista di vicini
		
		List<String> vicini = model.displayNeighbours(s);
		
		if(vicini == null){
			
			txtResult.appendText("Parola inesistente!!\n");
			return;
		}
		
		// Stampo i vicini
		
		txtResult.appendText("---- " + s + " ---- " + "( " + vicini.size() + " parole )" + "\n");
		
		for(String stemp: vicini)
			txtResult.appendText(stemp + "\n");
		}
		
		@FXML
	    void doTrovaTuttiVicini(ActionEvent event){
			
			// Estraggo la stringa
			String s = inputParola.getText();
			
			// Controllo la lunghezza della stringa
			
			if(s.length() != model.getLunghezza()){
				
				txtResult.appendText("Parola inserita non valida !!");
				return;
			}
			
			// Prendo dal model la lista di vicini
			
			List<String> tutti = model.getTutti(s);
			
			if(tutti == null){
				
				txtResult.appendText("Parola inesistente!!\n");
				return;
			}
			
			// Stampo i vicini
			
			txtResult.appendText("---- " + s + " ---- " + "( " + tutti.size() + " parole )" + "\n");
			
			for(String stemp: tutti)
				txtResult.appendText(stemp + "\n");

	    }
	
	

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTuttiVicini != null : "fx:id=\"btnTuttiVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}

	public void setModel(Model model) {
		
		this.model = model;
		
		
	}
}