package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bpmNoovaPartita;

    @FXML
    private TextField txtTentativi;
    
    @FXML
    private ProgressBar btnProgressBar;

    @FXML
    private HBox layoutTentativo;
    
    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btmProva;
    
    @FXML
    private Button btnAbbandonaPartita;

    @FXML
    private TextArea txtRisultato;
    

    @FXML
    void doAbbandonaPartita(ActionEvent event) {
    	
    	this.txtRisultato.setText("");
    	this.btnProgressBar.setProgress(0);
    	this.txtTentativoUtente.setText("");
    	this.txtTentativi.setText("");
    	this.layoutTentativo.setDisable(true);
    }

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	
    	this.model.nuovaPartita();
    	
    	this.btnProgressBar.setProgress(0);
    	this.txtRisultato.clear();
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()));
    	this.layoutTentativo.setDisable(false);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	String ts = txtTentativoUtente.getText();
    	
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		this.txtTentativoUtente.clear();
    		return;
    	}
    	
    	this.txtTentativoUtente.setText("");
    	
    	
    	
    	double progress =  (double) this.model.getTentativiFatti()/8.0;
    	this.btnProgressBar.setProgress(progress);
    	
    	
    	int result;
    	
    	try {
    		result = this.model.tentativo(tentativo);
    	}catch(IllegalStateException se) {
    		this.txtRisultato.setText(se.getMessage());
    		this.layoutTentativo.setDisable(true);
    		return;
    	}catch(InvalidParameterException pe) {
    		this.txtRisultato.setText(pe.getMessage());
    		return;
    	}
    	
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    
    	if(result == 0) {
    		txtRisultato.setText("HAI VINTO con "+ this.model.getTentativiFatti() + " tentativi!");
    		this.layoutTentativo.setDisable(true);
    		return;
    	}else if(result < 0) {
    		txtRisultato.setText("Tentativo troppo basso!");
    	}else {
    		txtRisultato.setText("Tentativo troppo alto!");
    	}
    	
    	
    }

    @FXML
    void initialize() {
        assert bpmNoovaPartita != null : "fx:id=\"bpmNoovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmProva != null : "fx:id=\"btmProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProgressBar != null : "fx:id=\"btnProgressBar\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAbbandonaPartita != null : "fx:id=\"btnAbbandonaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
