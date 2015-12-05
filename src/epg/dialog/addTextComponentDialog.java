/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_CANCEL_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_OK_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_COMBOBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_OPTION_VBOX;
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
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
public class addTextComponentDialog extends Stage{
     VBox vBox;
    Label typeOfComponentLabel;
    ComboBox textCompComboBox;
    Button okButton;
    Button cancelButton;
    String selectedTypeComponent;
    Label contentLabel;
    TextField contentsTextField;
    Label fontLabel;
    TextField fontTextField;
   // private pageEditView ui;
     public static Stage primaryStage;
    
     private ePortfolioMakerView ui;
    public addTextComponentDialog(ePortfolioMakerView initUI) {
        ui = initUI;
        primaryStage = new Stage();
        typeOfComponentLabel = new Label("Type Of Text Component:");
        typeOfComponentLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
	// INIT THE TEXT COMPONENT CHOICE
	ObservableList<String> textCompChoices = FXCollections.observableArrayList();
	textCompChoices.add("paragraph");
	textCompChoices.add("header");
        textCompChoices.add("list");
        textCompComboBox = new ComboBox(textCompChoices);
	textCompComboBox.getSelectionModel().select("paragraph");
        textCompComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
        
        
        //CONTROLS
        okButton = new Button("OK");
        okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        okButton.setPrefSize(250, 30);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_CANCEL_BUTTON);
        cancelButton.setPrefSize(250,30);
        
	vBox = new VBox();
        vBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_OPTION_VBOX);
	vBox.getChildren().add(typeOfComponentLabel);
	vBox.getChildren().add(textCompComboBox);
	vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
//        vBox.getStyleClass().add(CSS_CLASS_LANGUAGE_OPTION_VBOX);
	okButton.setOnAction((ActionEvent e) -> {
            this.hide();
	    selectedTypeComponent = textCompComboBox.getSelectionModel().getSelectedItem().toString();
            if (selectedTypeComponent.equals("paragraph")){
                paragraphDialog dialog = new paragraphDialog(ui);
                dialog.showAndWait();
//                paragraphTextDialog dialog;
//                dialog = new paragraphTextDialog(ui);
//                dialog.show();
//                this.hide();
//                dialog.showAndWait();
//                dialog.show();
                //this.hide();
//                closeWindow();
            }
            if (selectedTypeComponent.equals("header")){
//                headerDialog dialog = new headerDialog();
//                dialog.show();
                //this.hide();
//                 dialog.showAndWait();
                
               
                
                headerTextDialog dialog;
                dialog = new headerTextDialog(ui);
                
            }
            if (selectedTypeComponent.equals("list")){
               listDialog dialog = new listDialog(ui);
//               this.hide();
//                dialog.showAndWait();
               dialog.show();
//               this.hide();
            }
            
            //TODO
            //execute the textfield
//	    this.hide();
	});
	
        cancelButton.setOnAction(e -> {
            this.hide();
        });
        
	// NOW SET THE SCENE IN THIS WINDOW
	Scene scene = new Scene(vBox, 400,400);
        scene.getStylesheets().add(DIALOG_STYLE_SHEET);
	setScene(scene);
    }
    
    public String getSelectedTextComponent() {
	return selectedTypeComponent;
    }
    
    
    public static void closeWindow(){
        primaryStage.close();
    }
}




