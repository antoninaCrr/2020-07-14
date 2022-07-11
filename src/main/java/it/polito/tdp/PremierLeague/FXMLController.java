/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Avversario;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnClassifica"
    private Button btnClassifica; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSquadra"
    private ComboBox<Team> cmbSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doClassifica(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Team t = this.cmbSquadra.getValue();
    	if(t == null) {
    		this.txtResult.appendText("Selezionare un team di interesse\n");
    		return;
    	}
    	
    	List<Avversario> worst = model.getSquadrePeggiori(t);
    	this.txtResult.appendText("SQUADRE PEGGIORI: "+"\n");
    	for(Avversario ai : worst) {
    		this.txtResult.appendText(ai.toString()+"\n");
    	}
    	this.txtResult.appendText("\n\n");
    	List<Avversario> best = model.getSquadreMigliori(t);
    	this.txtResult.appendText("SQUADRE MIGLIORI: "+"\n");
    	for(Avversario ai : best) {
    		this.txtResult.appendText(ai.toString()+"\n");
    	}
    	
    	

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	model.creaGrafo();
    	this.txtResult.appendText("GRAFO CREATO\n");
    	this.txtResult.appendText("#VERTICI: "+this.model.nVertici()+"\n");
    	this.txtResult.appendText("#ARCHI: "+this.model.nArchi()+"\n");
    	
    	this.btnClassifica.setDisable(false);
    	this.cmbSquadra.setDisable(false);
    	
    	this.btnSimula.setDisable(false);
    	this.txtN.setDisable(false);
    	this.txtX.setDisable(false);
    	
    	this.cmbSquadra.getItems().clear();
    	this.cmbSquadra.getItems().addAll(model.getAllVertices());

    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClassifica != null : "fx:id=\"btnClassifica\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSquadra != null : "fx:id=\"cmbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.btnClassifica.setDisable(true);
    	this.cmbSquadra.setDisable(true);
    	
    	this.btnSimula.setDisable(true);
    	this.txtN.setDisable(true);
    	this.txtX.setDisable(true);
    }
}
