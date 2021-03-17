package it.polito.tdp.IndovinaNumero;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	

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
    	
    	this.txtTentativi.setText("");
    	this.btnProgressBar.setProgress(0);
    	this.segreto =(int) ( Math.random() * NMAX ) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	this.txtTentativi.setText(Integer.toString(TMAX));
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
    		return;
    	}
    	
    	this.txtTentativoUtente.setText("");
    	
    	this.tentativiFatti ++;
    	
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiFatti));
    	
    	double progress =  (double) this.tentativiFatti/8.0;
    	
    	this.btnProgressBar.setProgress(progress);
    	
    	if(tentativo == this.segreto) {
    		txtRisultato.setText("Hai vinto con "+ this.tentativiFatti + " tentativi!");
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		
    		txtRisultato.setText("Hai perso! Il numero segreto era: "+ this.segreto);
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    		
    	}
    	
    	if(tentativo < this.segreto) {
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
}
