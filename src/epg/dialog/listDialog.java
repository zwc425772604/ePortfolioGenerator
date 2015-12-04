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
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class listDialog extends Stage {
    VBox vBox;
    Label contentsLabel;
    Button okButton;
    Button cancelButton;
    
    Button addMoreListButton;
    Button removeButton;
    
    Label fontLabel;
    TextField contentsTextField;
    
    TextField fontTextField;
    ObservableList<TextField> inputTextField;
    ObservableList<String> inputList;
    //ArrayList<String>inputList;
    TextField selectedTextField;
    
    ePortfolioMakerView ui;
    public listDialog(ePortfolioMakerView initUI){
        ui = initUI;
//        contentsLabel = new Label("Contents:");
//        contentsLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
//        contentsTextField = new TextField("Entering The Contents");
//        contentsTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        inputList = FXCollections.observableArrayList();
       // inputList = new ArrayList<String>();
        inputTextField = FXCollections.observableArrayList();
        vBox = new VBox();
        okButton = new Button("OK");
        okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        okButton.setPrefSize(250, 30);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_CANCEL_BUTTON);
        cancelButton.setPrefSize(250,30);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
         addMoreListButton = new Button("Add more list element");
        removeButton = new Button("remove list element");
        vBox.getChildren().add(addMoreListButton);
        vBox.getChildren().add(removeButton);
        
        
        
        initTextField(vBox);
        
       
        
        cancelButton.setOnAction(e -> {
            this.hide();
        });

        addMoreListButton.setOnAction( e -> {       
            addMoreTextField(vBox);
            
        });
        
        removeButton.setOnAction(e ->{
            vBox.getChildren().remove(selectedTextField);
            inputTextField.remove(selectedTextField);
        });
         okButton.setOnAction((ActionEvent e) -> {
             for (int i = 0; i<inputTextField.size();i++){
                 inputList.add(inputTextField.get(i).getText());
             } 
             
         PortfolioModel model = ui.getPortfolio(); //get all the page associate with the portfolio
         Page p = model.getSelectedPage();  //return the selected page
         p.addElementToTheList(inputList);
         
         pageEditView pev = p.getPageEditView();   //load the corresponding pageEditView
         pev.reloadPageEditView(p);
         pev.addListToVBox(inputList);
         
             this.hide();
        });
        
         Scene scene = new Scene(vBox, 400,400);
        scene.getStylesheets().add(DIALOG_STYLE_SHEET);
	setScene(scene);
    }
    
    
    public ObservableList<String> getInputList(){
        return inputList;
    }
    
    private void initTextField(Pane pane){
        contentsLabel = new Label("Contents:");
        contentsLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        contentsTextField = new TextField("Entering The Contents");
        contentsTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        contentsTextField.setOnMouseClicked(e ->{
            selectedTextField = this.contentsTextField;
        
        });
        inputTextField.add(contentsTextField);
        pane.getChildren().add(contentsLabel);
        pane.getChildren().add(contentsTextField);
    }
    
    private void addMoreTextField(Pane pane){
        
        TextField tf = new TextField("Entering The Contents");
        tf.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        tf.setOnMouseClicked(e ->{
            selectedTextField = tf;
        
        });
        inputTextField.add(tf);
        pane.getChildren().add(tf);
    }
    
    
}
