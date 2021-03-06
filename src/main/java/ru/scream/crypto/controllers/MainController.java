package ru.scream.crypto.controllers;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Paint;
import ru.scream.crypto.crypto.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Verhoturov Denis - Leo.Scream.
 */
public class MainController implements Initializable
{
    @FXML
    private JFXComboBox<Actions> action;
    @FXML
    private JFXComboBox<Languages> language;
    @FXML
    private JFXComboBox<Ciphers> algorithm;
    @FXML
    private JFXTextField cipherKey;
    @FXML
    private Tooltip errorMessage;
    @FXML
    private JFXTextArea inputText;
    @FXML
    private JFXTextArea outputText;
    @FXML
    private JFXButton doMagic;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
	    this.action.getItems().setAll(Actions.values());
	    this.action.getSelectionModel().select(Actions.ENCRYPT);

	    this.algorithm.getItems().setAll(Ciphers.values());
	    this.algorithm.getSelectionModel().select(Ciphers.CAESAR);
	    this.algorithm.setOnAction((event) ->
			    cipherKey.setText(algorithm.getValue().getInstance().getKey()));

	    this.language.getItems().setAll(Languages.values());
	    this.language.getSelectionModel().select(Languages.EN);
	    this.language.setOnAction((event) ->
			    algorithm.getValue().getInstance().alphabet = language.getValue().alphabet());

	    this.cipherKey.focusedProperty().addListener((observable, oldValue, newValue) ->
	    {
		    if(!newValue) {
			    try {
				    algorithm.getValue().getInstance().setKey(cipherKey.getText());
				    cipherKey.setFocusColor(Paint.valueOf("#4059A9"));
				    doMagic.setDisable(false);
			    } catch (Exception e) {
				    doMagic.setDisable(true);
				    cipherKey.setFocusColor(Paint.valueOf("#dd1515"));
				    errorMessage.setText(e.getMessage());
			    }
		    }
	    });
    }

    public void action(ActionEvent actionEvent)
    {
        switch (this.action.getSelectionModel().getSelectedItem())
        {
            case ENCRYPT:
	            String encrypted = algorithm.getValue().getInstance().encrypt(this.inputText.getText());
                this.outputText.setText(encrypted);
                break;
            case DECRYPT:
	            String decrypted = algorithm.getValue().getInstance().decrypt(this.inputText.getText());
                this.outputText.setText(decrypted);
                break;
        }
    }
}
