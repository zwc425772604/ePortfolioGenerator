/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
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
import javafx.scene.control.TextArea;
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
    
    TextArea contentsTextArea;
    ComboBox  fontFamilyComboBox;
    ComboBox  fontSizeComboBox;
    Integer selectedFont;
    
    ePortfolioMakerView ui;
    public paragraphDialog(ePortfolioMakerView initUI){
        ui = initUI;
         contentsLabel = new Label("Contents:");
        contentsLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
//        contentsTextField = new TextField("Entering The Contents");
//        contentsTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        contentsTextArea = new TextArea("Enter The Contents");
        contentsTextArea.setPrefSize(300,300);
       // contentsTextArea.setMaxWidth(100);
        contentsTextArea.setWrapText(true);
        contentsTextArea.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        
        fontFamilyLabel = new Label("Select a font family for the paragraph");
        fontFamilyLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        ObservableList<String> fontFamilyChoices = FXCollections.observableArrayList();
        fontFamilyChoices.add("Sigmar One");
        fontFamilyChoices.add("Indie Flower");
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
        okButton.setPrefSize(200, 50);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_CANCEL_BUTTON);
        cancelButton.setPrefSize(200,50);
        
        vBox = new VBox();
        vBox.getChildren().add(contentsLabel);
       
        vBox.getChildren().add(contentsTextArea);
        vBox.getChildren().add(fontFamilyLabel);
        vBox.getChildren().add(fontFamilyComboBox);
        vBox.getChildren().add(fontSizeLabel);
        vBox.getChildren().add(fontSizeComboBox);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        
        okButton.setOnAction(e -> {
         String content= contentsTextArea.getText();
         String family = fontFamilyComboBox.getSelectionModel().getSelectedItem().toString();
         int fontSize = Integer.parseInt(fontSizeComboBox.getSelectionModel().getSelectedItem().toString());
         PortfolioModel model = ui.getPortfolio(); //get all the page associate with the portfolio
         Page p = model.getSelectedPage();  //return the selected page
         p.addParagraph(content);        //add the text for paragraph to the selected page
         pageEditView pev = p.getPageEditView();   //load the corresponding pageEditView
         pev.reloadPageEditView(p);
         pev.addParagraphToVBox(content,family,fontSize); 
//           
            
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
