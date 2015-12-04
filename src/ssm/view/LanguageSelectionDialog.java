/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static ssm.StartupConstants.ENGLISH_LANG;
import static ssm.StartupConstants.CHINESE_LANG;
import static ssm.StartupConstants.CSS_CLASS_LANGUAGE_OPTION_VBOX;
import static ssm.StartupConstants.CSS_CLASS_LANGUAGE_SELECTION_SCREEN;
import static ssm.StartupConstants.CSS_CLASS_LANG_COMBOBOX;

import static ssm.StartupConstants.CSS_CLASS_OK_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_SHOW_EDIT_VBOX;
import static ssm.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static ssm.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
//import static ssm.StartupConstants.CSS_CLASS_LANGUAGE_BOX;
import static ssm.StartupConstants.LABEL_LANGUAGE_SELECTION_PROMPT;
import static ssm.StartupConstants.LANGUAGE_SELECTION_STYLE_SHEET;
import static ssm.StartupConstants.OK_BUTTON_TEXT;


/**
 *
 * @author McKillaGorilla
 */
public class LanguageSelectionDialog extends Stage {
    VBox vBox;
    Label languagePromptLabel;
    ComboBox languageComboBox;
    Button okButton;
    String selectedLanguage = ENGLISH_LANG;
    
    public LanguageSelectionDialog() {
	languagePromptLabel = new Label(LABEL_LANGUAGE_SELECTION_PROMPT);
	languagePromptLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
	// INIT THE LANGUAGE CHOICES
	ObservableList<String> languageChoices = FXCollections.observableArrayList();
	languageChoices.add(ENGLISH_LANG);
	languageChoices.add(CHINESE_LANG);
	languageComboBox = new ComboBox(languageChoices);
	languageComboBox.getSelectionModel().select(ENGLISH_LANG);
        languageComboBox.getStyleClass().add(CSS_CLASS_LANG_COMBOBOX);
        okButton = new Button(OK_BUTTON_TEXT);
	okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
	vBox = new VBox();
        vBox.getStyleClass().add(CSS_CLASS_LANGUAGE_OPTION_VBOX);
	vBox.getChildren().add(languagePromptLabel);
	vBox.getChildren().add(languageComboBox);
	vBox.getChildren().add(okButton);
        
//        vBox.getStyleClass().add(CSS_CLASS_LANGUAGE_OPTION_VBOX);
	okButton.setOnAction(e -> {
	    selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem().toString();
	    this.hide();
	});
	
	// NOW SET THE SCENE IN THIS WINDOW
	Scene scene = new Scene(vBox, 300,300);
        scene.getStylesheets().add(LANGUAGE_SELECTION_STYLE_SHEET);
	setScene(scene);
    }
    
    public String getSelectedLanguage() {
	return selectedLanguage;
    }
}


