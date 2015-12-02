/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.ImageSelectionController;
import epg.model.Page;
import eportfoliogenerator.LanguagePropertyType;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_IMAGE_COMPONENT_OPTION_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_OK_BUTTON;
import java.io.File;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_PAGE_EDIT_VIEW;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_COMBOBOX;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;


/**
 *
 * @author weichaozhao
 */
public class pageEditView extends VBox {
    Page page;
    
   
    
    // CONTROLS FOR EDITING THE CAPTION
    ImageView imageSelectionView1;
    
    // CONTROLS FOR EDITING THE CAPTION
    VBox captionVBox;
    Label captionLabel1;
    TextField captionTextField1;
    
    // PROVIDES RESPONSES FOR IMAGE SELECTION
    ImageSelectionController imageController1;

    
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
    private ePortfolioMakerView ui;
    
    private int index = 0;
    
    ObservableList<VBox> headerComp;
    ObservableList<VBox> paragraphComp;
    /**
     * THis' constructor initializes the full UI for this component, using
     * the initSlide data for initializing values./
     * 
     * @param initSlide The slide to be edited by this component.
     */
    public pageEditView(Page initPage, ePortfolioMakerView initUI) {
	// FIRST SELECT THE CSS STYLE CLASS FOR THIS CONTAINER
	this.getStyleClass().add(CSS_CLASS_PAGE_EDIT_VIEW);
	ui = initUI;
	// KEEP THE SLIDE FOR LATER
	page = initPage;
	headerComp = FXCollections.observableArrayList();
        paragraphComp = FXCollections.observableArrayList();
        updateHeader();
	

	// SETUP THE CAPTION CONTROLS
	captionVBox = new VBox();
        captionVBox.setPrefSize(300, 500);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
	captionLabel = new Label(props.getProperty(LanguagePropertyType.LABEL_CAPTION));
	captionTextField = new TextField();
        captionTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
	captionTextField.setText("enter title");
	captionVBox.getChildren().add(captionLabel);
	captionVBox.getChildren().add(captionTextField);

	// LAY EVERYTHING OUT INSIDE THIS COMPONENT
	
	getChildren().add(captionVBox);

	
	captionTextField.textProperty().addListener(e -> {
	    String text = captionTextField.getText();
	    page.setTitle(text);
            
	});	    

	
//   addHeader();
//       addImage();
//	
	
	
    }
    
    public VBox getWorkspace(){
        return this;
    }
    
//    public void addHeader(String h1){
//        TextArea ta = new TextArea(h1);
//        getChildren().add(ta);
//    }
    public void addHeader(){
        String input = "Hello World";
        TextArea ta = new TextArea(input);
        ta.setEditable(false);
        getChildren().add(ta);
        
    }
    
    public void updateHeader(){
        ArrayList<String> header = page.getHeader();
        for (String text : header){
          addHeaderToVBox(text);
        }
        reloadPageEditView(page);
        
//        ui.reloadPortfolioPane();
    }
    
    //add the header vbox to the pageEditView
    public void addHeaderToVBox(String x){
        VBox h1 = new VBox();
        TextArea ta = new TextArea(x);
        ta.setEditable(false);
        ta.setOnMouseClicked(e -> {
            System.out.println("this has been clicked");
        });
        h1.getChildren().add(ta);
        headerComp.add(h1);
        getChildren().add(h1);
    }
    
    //Add the paragraph vbox to the pageEditView
    public void addParagraphToVBox(String x){
        VBox h1 = new VBox();
        TextArea ta = new TextArea(x);
        ta.setEditable(false);
        ta.setOnMouseClicked(e -> {
            System.out.println("this has been clicked");
        });
        h1.getChildren().add(ta);
        paragraphComp.add(h1);
        getChildren().add(h1);
    }
    
     //reload the page
     public void reloadPageEditView(Page testPage){
//         getChildren().clear();
         page = testPage;
         
         
         
        
	
	
	
        
//         if(headerComp.size()> 0){
////             for(int i = 0; i<headerComp.size();i++){
//         getChildren().add(headerComp.get(0));
//         
//         }
        
         
         
	 
        
	

           
//        addHeader();

    }
    

    
    public void addImage(){
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
	    imageController.processSelectImage(imageSelectionView);
	});
        
        getChildren().add(vBox);
        
        
        
    }
    
    public void getPageInfo(){
        System.out.println(page.getStudentName());
        System.out.println(page.getTitle());
    }
    
   
   
   
   
}
