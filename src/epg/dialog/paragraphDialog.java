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
    
    Label fontLabel;
    TextField contentsTextField;
    
    TextField fontTextField;
    ComboBox  fontComboBox;
    Integer selectedFont;
    
    public paragraphDialog(){
         contentsLabel = new Label("Contents:");
        contentsLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        contentsTextField = new TextField("Entering The Contents");
        contentsTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        
        fontLabel = new Label("Select a font for the paragraph:");
        fontLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        ObservableList<Integer> fontChoices = FXCollections.observableArrayList();
        for (int i = 8; i<40; i+= 2){
            fontChoices.add(i);
        }
        fontComboBox = new ComboBox(fontChoices);
	fontComboBox.getSelectionModel().select("8");
        fontComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
        okButton = new Button("OK");
        okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        okButton.setPrefSize(250, 30);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_CANCEL_BUTTON);
        cancelButton.setPrefSize(250,30);
        
        vBox = new VBox();
        vBox.getChildren().add(contentsLabel);
        vBox.getChildren().add(contentsTextField);
        vBox.getChildren().add(fontComboBox);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        
        okButton.setOnAction(e -> {
            
            selectedFont = ((Integer) fontComboBox.getSelectionModel().getSelectedItem()).intValue();
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
