/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.controller.ImageSelectionController;
import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_IMAGE_COMPONENT_OPTION_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_OK_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_COMBOBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_OPTION_VBOX;
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import java.io.File;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import ssm.LanguagePropertyType;
import static ssm.StartupConstants.DEFAULT_THUMBNAIL_WIDTH;
import ssm.error.ErrorHandler;

/**
 *
 * @author weichaozhao
 */
public class addBannerImageDialog extends Stage {
   
    VBox vBox;
    ImageView imageSelectionView;
    
    Label imgLabel;
    
    // CONTROLS FOR EDITING THE CAPTION
    
    Label captionLabel;
    TextField captionTextField;
    
    ImageSelectionController imageController;
    
    Button okButton;
    Button cancelButton;
    
    Label imgHeightLabel;
    Label imgWidthLabel;
    TextField imgHeightTextField;
    TextField imgWidthTextField;
    
    Label imgFloatLabel;
    ComboBox imgFloatComboBox;
    
    Page selectedPage;
    private ePortfolioMakerView ui;
   
    
    public addBannerImageDialog(ePortfolioMakerView initUI){
        ui = initUI;
        selectedPage = ui.getPortfolio().getSelectedPage();
        imgLabel = new Label("Select An Image:");
        imageSelectionView = new ImageView();
        String imagePath = "./images/slide_show_images/DefaultStartSlide.png";
	File file = new File(imagePath);
        
        try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
	    imageSelectionView.setImage(slideImage);
            imageSelectionView.setFitHeight(250);
            imageSelectionView.setFitWidth(250);
        }
         catch (Exception e) {
	    System.out.println("Invalid File");
	}
        
        imgLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        
        //FOR CAPTION
        captionLabel = new Label("Caption:");
        captionLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        captionTextField = new TextField();
        captionTextField.setMinWidth(50);
        captionTextField.setPrefWidth(50);
        captionTextField.setMaxWidth(250);
        captionTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        
        //WIDTH
        imgWidthLabel = new Label("Image Width:");
        imgWidthLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        imgWidthTextField = new TextField();
        imgWidthTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        imgWidthTextField.setMinWidth(50);
        imgWidthTextField.setPrefWidth(50);
        imgWidthTextField.setMaxWidth(250);
        
        //HEIGHT
        imgHeightLabel = new Label("Image Height:");
        imgHeightLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        imgHeightTextField = new TextField();
        imgHeightTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        imgHeightTextField.setMinWidth(50);
        imgHeightTextField.setPrefWidth(50);
        imgHeightTextField.setMaxWidth(250);
        
        // INIT THE TEXT COMPONENT CHOICE
        imgFloatLabel = new Label("Image Float Layout");
        imgFloatLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
	ObservableList<String> imgFloatChoices = FXCollections.observableArrayList();
	imgFloatChoices.add("LEFT");
	imgFloatChoices.add("RIGHT");
        imgFloatChoices.add("NEITHER");
	imgFloatComboBox = new ComboBox(imgFloatChoices);
	imgFloatComboBox.getSelectionModel().select("LEFT");
        imgFloatComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
        okButton = new Button("OK");
        okButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add(CSS_CLASS_OK_BUTTON);
        vBox = new VBox();
        
        vBox = new VBox();
        vBox.getStyleClass().add(CSS_CLASS_IMAGE_COMPONENT_OPTION_VBOX);
        vBox.getChildren().add(imgLabel);
        vBox.getChildren().add(imageSelectionView);
        vBox.getChildren().add(captionLabel);
        vBox.getChildren().add(captionTextField);
        vBox.getChildren().add(imgWidthLabel);
        vBox.getChildren().add(imgWidthTextField);
        vBox.getChildren().add(imgHeightLabel);
        vBox.getChildren().add(imgHeightTextField);
        vBox.getChildren().add(imgFloatLabel);
        vBox.getChildren().add(imgFloatComboBox);
        vBox.getChildren().add(okButton);
        vBox.getChildren().add(cancelButton);
        imageController = new ImageSelectionController();
	imageSelectionView.setOnMousePressed(e -> {
	    imageController.processSelectImage(selectedPage,selectedPage.getPageEditView());
            updateSelectionImage(imageController.getImagePath(),imageController.getImageFileName());
	});
        
        okButton.setOnAction(e -> {
//	   
         int wid = Integer.parseInt(imgWidthTextField.getText());
         int hei = Integer.parseInt(imgHeightTextField.getText());
         String selectLayout = (String) imgFloatComboBox.getSelectionModel().getSelectedItem();
         String caption = captionTextField.getText();
            
         PortfolioModel model = ui.getPortfolio(); //get all the page associate with the portfolio
         Page p = model.getSelectedPage();  //return the selected page
                //add the text for paragraph to the selected page
         pageEditView pev = p.getPageEditView();   //load the corresponding pageEditView
         pev.reloadPageEditView(p);
         
         String path = imageController.getImagePath() + imageController.getImageFileName();
         p.addImage(path);
         
         System.out.println(path);
         pev.addImageToVBox(path,hei, wid,selectLayout,caption);
         
         //pev.addImage();
         this.hide();
	});
        
        cancelButton.setOnAction(e ->{
            this.hide();
        });
        
        Scene scene = new Scene(vBox, 650,650);
        scene.getStylesheets().add(DIALOG_STYLE_SHEET);
	setScene(scene);
    }
    
    public void updateSelectionImage(String path, String fileName){
        String p = path + fileName;
        File file1 = new File(p);
	try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file1.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
	    imageSelectionView.setImage(slideImage);
	    
	    // AND RESIZE IT
	    double scaledWidth = DEFAULT_THUMBNAIL_WIDTH;
	    double perc = scaledWidth / slideImage.getWidth();
	    double scaledHeight = slideImage.getHeight() * perc;
	    imageSelectionView.setFitWidth(scaledWidth);
	    imageSelectionView.setFitHeight(scaledHeight);
	} catch (Exception i) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	}
    }
    
}
