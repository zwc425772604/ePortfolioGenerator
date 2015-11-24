/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import static eportfoliogenerator.StartupConstants.CSS_CLASS_CANCEL_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_OK_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_COMBOBOX;
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class paragraphDialog extends Stage {
    VBox vBox;
    Label contentsLabel;
    Button okButton;
    Button cancelButton;
    
    Label fontSizeLabel;
    TextField contentsTextField;
    Label fontFamilyLabel;
    
    ComboBox  fontFamilyComboBox;
    ComboBox  fontSizeComboBox;
    Integer selectedFont;
    
    public paragraphDialog(){
         contentsLabel = new Label("Contents:");
        contentsLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        contentsTextField = new TextField("Entering The Contents");
        contentsTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        
        fontFamilyLabel = new Label("Select a font family for the paragraph");
        fontFamilyLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        ObservableList<String> fontFamilyChoices = FXCollections.observableArrayList();
        fontFamilyChoices.add("Sigmar One");
        fontFamilyChoices.add("Covered+By+Your+Grace");
        fontFamilyChoices.add("Shadows Into Light");
        fontFamilyChoices.add("Dancing Script");
        
        fontFamilyLabel = new Label("Select a font family from the following:");
        fontFamilyLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        fontFamilyComboBox = new ComboBox(fontFamilyChoices);
	fontFamilyComboBox.getSelectionModel().select("Sigmar One");
        fontFamilyComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
        
        fontSizeLabel = new Label("Select a font size from the following");
        fontSizeLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        ObservableList<Integer> fontSizeChoices = FXCollections.observableArrayList();
        for (int i = 8; i<40; i+= 2){
            fontSizeChoices.add(i);
        }
        
        fontSizeComboBox = new ComboBox(fontSizeChoices);
	fontSizeComboBox.getSelectionModel().select("8");
        fontSizeComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
        okButton = new Button("OK");
        okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        okButton.setPrefSize(250, 30);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_CANCEL_BUTTON);
        cancelButton.setPrefSize(250,30);
        
        vBox = new VBox();
        vBox.getChildren().add(contentsLabel);
        vBox.getChildren().add(contentsTextField);
        vBox.getChildren().add(fontFamilyLabel);
        vBox.getChildren().add(fontFamilyComboBox);
        vBox.getChildren().add(fontSizeLabel);
        vBox.getChildren().add(fontSizeComboBox);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        
        okButton.setOnAction(e -> {
            
            selectedFont = ((Integer) fontSizeComboBox.getSelectionModel().getSelectedItem()).intValue();
            //TODO 
            
            this.hide();
        });
        
        cancelButton.setOnAction(e -> {
            this.hide();
        });
        
        Scene scene = new Scene(vBox, 400,400);
        scene.getStylesheets().add(DIALOG_STYLE_SHEET);
	setScene(scene);
    }
    
    public int getSelectedFont(){
        return selectedFont;
    }
}
