/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.controller.ImageSelectionController;
import epg.controller.videoSelectionController;
import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_OK_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import static eportfoliogenerator.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class addVideoComponentDialog extends Stage{
    
    VBox vBox;
    MediaView mediaView;
    MediaPlayer mediaPlayer;
    Media media;
    Label videoLabel;
    videoSelectionController videoController;
    // CONTROLS FOR EDITING THE CAPTION
    Label captionLabel;
    TextField captionTextField;
    
    ImageSelectionController imageController;
    Button openButton;
    Button okButton;
    Button cancelButton;
    
    Label videoHeightLabel;
    Label videoWidthLabel;
    TextField videoHeightTextField;
    TextField videoWidthTextField;
    
    ePortfolioMakerView ui;
    public addVideoComponentDialog(ePortfolioMakerView initUI){
        ui = initUI;
        videoLabel = new Label("Select a video");
        openButton = new Button("Open File");
        videoLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        vBox = new VBox();
        openButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        videoController = new videoSelectionController();
        openButton.setOnAction(e ->{
          videoController.processSelectVideo(vBox);
        });
        
        captionLabel = new Label("Caption:");
        captionLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        captionTextField = new TextField();
        captionTextField.setMinWidth(50);
        captionTextField.setPrefWidth(50);
        captionTextField.setMaxWidth(250);
        captionTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        
        videoWidthLabel = new Label("Image Width:");
        videoWidthLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        videoWidthTextField = new TextField();
        videoWidthTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        videoWidthTextField.setMinWidth(50);
        videoWidthTextField.setPrefWidth(50);
        videoWidthTextField.setMaxWidth(250);
        
        //HEIGHT
        videoHeightLabel = new Label("Image Height:");
        videoHeightLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        videoHeightTextField = new TextField();
        videoHeightTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        videoHeightTextField.setMinWidth(50);
        videoHeightTextField.setPrefWidth(50);
        videoHeightTextField.setMaxWidth(250);
        okButton = new Button("OK");
        okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        okButton.setOnAction(e ->{
            int wid = Integer.parseInt(videoWidthTextField.getText());
            int hei = Integer.parseInt(videoHeightTextField.getText());
            
            PortfolioModel model = ui.getPortfolio(); //get all the page associate with the portfolio
         Page p = model.getSelectedPage();  //return the selected page
                //add the text for paragraph to the selected page
         pageEditView pev = p.getPageEditView();   //load the corresponding pageEditView
         pev.reloadPageEditView(p);
         String videoPath = videoController.getVideoPath();
         System.out.println(videoPath);
         pev.addVideoToVBox(videoPath,hei, wid);
            this.hide();
        });
        
        vBox = new VBox();
        vBox.getChildren().add(videoLabel);
        vBox.getChildren().add(openButton);
        vBox.getChildren().add(captionLabel);
        vBox.getChildren().add(captionTextField);
        vBox.getChildren().add(videoWidthLabel);
        vBox.getChildren().add(videoWidthTextField);
        vBox.getChildren().add(videoHeightLabel);
        vBox.getChildren().add(videoHeightTextField);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        
        
        
        Scene scene = new Scene(vBox, 600,600);
        scene.getStylesheets().add(DIALOG_STYLE_SHEET);
	setScene(scene);
    }
    
}
