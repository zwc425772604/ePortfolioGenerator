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
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class addTextHyperLinkDialog extends Stage {
    
    VBox vBox;
    Label contentsLabel;
    TextArea text;
    Button okButton;
    Button cancelButton;
    
    TextField textF;
    
    ePortfolioMakerView ui;
    public addTextHyperLinkDialog(ePortfolioMakerView initUI){
        ui = initUI;
        
        String textForHyperLink = ui.getPortfolio().getSelectedPage().getPageEditView().getSelectedTextFromTA();
        text = new TextArea(textForHyperLink);
        text.setPrefHeight(80);
        contentsLabel = new Label("Enter an URL for the element");
        contentsLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        textF = new TextField();
        textF.setPromptText("Enter An URL");
        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        vBox = new VBox();
        vBox.getChildren().addAll(text,contentsLabel,textF,okButton,cancelButton);
        
        okButton.setOnAction(e ->{
           // Hyperlink link = new Hyperlink(text.getText());
         PortfolioModel model = ui.getPortfolio(); //get all the page associate with the portfolio
         Page p = model.getSelectedPage();  //return the selected page
        
        // p.addHyperLinkToText(textF.getText());
           pageEditView pev = p.getPageEditView(); 
         pev.reloadPageEditView(p);
         //pev.addListToVBox(inputList);
         pev.addHyperlink(textF.getText());
             this.hide();
//            link.setOnAction(new EventHandler<ActionEvent>(){
//                @Override
//                public void handle(ActionEvent event) {
//                    getHostServices().showDocument(textF.getText());
//                }
//            });
            this.hide();
        });
        cancelButton.setOnAction(e ->{
            this.hide();
        });
        
         Scene scene = new Scene(vBox, 400,400);
        scene.getStylesheets().add(DIALOG_STYLE_SHEET);
	setScene(scene);
        
    }
}
