/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.ImageSelectionController;
import epg.error.ErrorHandler;
import static epg.file.ePortfolioFileManager.SLASH;
import epg.model.Page;
import eportfoliogenerator.LanguagePropertyType;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_IMAGE_COMPONENT_OPTION_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_LIST_COMPONENT;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_LIST_COMPONENT_VBOX;
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
import static eportfoliogenerator.StartupConstants.CSS_CLASS_PAGE_LABEL;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_SELECTED_COMPONENT;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_COMBOBOX;
import static eportfoliogenerator.StartupConstants.DEFAULT_THUMBNAIL_WIDTH;
import static eportfoliogenerator.StartupConstants.STYLE_SHEET_UI;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


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
    
    VBox studentNameVBox;
    VBox footerVBox;
    
    VBox selectedComponent;
    String selectedTypeComponent;
    String selectedComponentContents;
    TextArea selectedTextArea;
    int selectedFontSize;
    String selectedFontStyle;
    
    ObservableList<VBox> headerComp;
    ObservableList<VBox> paragraphComp;
    ObservableList<VBox> imageComp;
    ObservableList<VBox> videoComp;
    ObservableList<VBox> listComp;
    /**
     * THis' constructor initializes the full UI for this component, using
     * the initSlide data for initializing values./
     * 
     * @param initSlide The slide to be edited by this component.
     */
    public pageEditView(Page initPage, ePortfolioMakerView initUI) {
	// FIRST SELECT THE CSS STYLE CLASS FOR THIS CONTAINER
	//this.getStyleClass().add(CSS_CLASS_PAGE_EDIT_VIEW);
        this.getStylesheets().add(STYLE_SHEET_UI);
	ui = initUI;
	// KEEP THE SLIDE FOR LATER
	page = initPage;
        this.setPrefHeight(1200);
        selectedTypeComponent = null;
	headerComp = FXCollections.observableArrayList();
        paragraphComp = FXCollections.observableArrayList();
        imageComp = FXCollections.observableArrayList();
        addStudentNameToVBox();
        addFooterToVBox();
        //footerVBox.toBack();
        updateHeader();
        
	

	// SETUP THE CAPTION CONTROLS
//	captionVBox = new VBox();
//        captionVBox.setPrefSize(300, 500);
//        PropertiesManager props = PropertiesManager.getPropertiesManager();
//	captionLabel = new Label(props.getProperty(LanguagePropertyType.LABEL_CAPTION));
//	captionTextField = new TextField();
//        captionTextField.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
//	captionTextField.setText("enter title");
//	captionVBox.getChildren().add(captionLabel);
//	captionVBox.getChildren().add(captionTextField);
//
//	// LAY EVERYTHING OUT INSIDE THIS COMPONENT
//	
//	getChildren().add(captionVBox);
//
//	
//	captionTextField.textProperty().addListener(e -> {
//	    String text = captionTextField.getText();
//	    page.setTitle(text);
//            
//	});	    

	
//   addHeader();
         //addImage("./images/slide_show_images/ArchesUtah.jpg");
//        String x = "./images/slide_show_images/ArchesUtah.jpg";
//        addImageToVBox(x,200,200);
	
	
    }
    
    public VBox getWorkspace(){
        return this;
    }
    
    //can remove a single vbox from the pane
    public void removeSelectingComponent(){
        this.getChildren().remove(selectedComponent);
        
    }
    

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
            selectedComponent = this;
             ta.getStyleClass().add(CSS_CLASS_SELECTED_COMPONENT);
            System.out.println("header component  has been clicked" + ta.getText());
            //for edit the component
             selectedTypeComponent = "header";
             selectedComponentContents = ta.getText();
             
             selectedTextArea = ta;
     });
        
        h1.getChildren().add(ta);
        h1.setPrefHeight(30);
        headerComp.add(h1);
        getChildren().add(h1);
        //h1.toFront();
    }
    //update the contents after the user edited the component
    public void updateHeaderToVBox(String x ){
       getSelectedTextArea().setText(x);
       setSelectedComponentContent(null);
       setSelectedTypeComponent(null);
       setSelectedTextArea(null);
       reloadPageEditView(page);
    }
    
    public void addListToVBox(ObservableList<String>listElement){
        VBox l1 = new VBox();
        //l1.getStyleClass().add(CSS_CLASS_LIST_COMPONENT);
        ListView<String> listView = new ListView<String>(listElement);
        listView.getStyleClass().add(CSS_CLASS_LIST_COMPONENT);
        listView.getStyleClass().add(CSS_CLASS_LIST_COMPONENT_VBOX);
        l1.setPrefHeight(listElement.size() * 25);
        l1.getChildren().add(listView);
        
        
        getChildren().add(l1);
    }
    
    public void addImageToVBox(String path, int height, int width,
            String layout,String caption){
       
//       
         VBox i1 = new VBox();
        //String imagePath = page.getImagePath() + SLASH + page.getImageFileName();
       
	File file = new File(path);
	try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
           
            imageSelectionView = new ImageView();
	    imageSelectionView.setImage(slideImage);
            imageSelectionView.setFitHeight(height);
            imageSelectionView.setFitWidth(width);
            if (layout.equals("LEFT")){
                i1.setAlignment(Pos.CENTER_LEFT);
            }
            else if(layout.equals("RIGHT")){
                i1.setAlignment(Pos.CENTER_RIGHT);
            }
            else{
            i1.setAlignment(Pos.CENTER);
            }
	    // AND RESIZE IT
//	    double scaledWidth = DEFAULT_THUMBNAIL_WIDTH;
//	    double perc = scaledWidth / slideImage.getWidth();
//	    double scaledHeight = slideImage.getHeight() * perc;
//	    imageSelectionView.setFitWidth(scaledWidth);
//	    imageSelectionView.setFitHeight(scaledHeight);
            TextField captionTF = new TextField(caption);
            
            if (layout.equals("LEFT")){
                captionTF.setAlignment(Pos.CENTER_LEFT);
            }
            else if(layout.equals("RIGHT")){
                captionTF.setAlignment(Pos.CENTER_RIGHT);
            }
            else{
                captionTF.setAlignment(Pos.CENTER);
            }
             i1.getChildren().add(imageSelectionView);
             i1.getChildren().add(captionTF);
             getChildren().add(i1);
	} catch (Exception e) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	}
//        i1.getChildren().add(imgView);
//        getChildren().add(i1);
        
    }
    
    public void addVideoToVBox(String path, int height, int width){
         VBox v1 = new VBox();
         
         File f = new File(path);
         try{
            Media m = new Media(f.toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            MediaView mv = new MediaView(mp);
            mv.setFitHeight(height);
            mv.setFitWidth(width);
            //v1.setAlignment(Pos.CENTER); CSS 
            v1.getChildren().add(mv);
            getChildren().add(v1);
         }
         catch (Exception e) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	}
        
         
    }
    
    public void addStudentNameToVBox(){
        studentNameVBox = new VBox();
        studentNameVBox.setPrefSize(100,50);
        Label name = new Label("Student Name");
        name.getStyleClass().add(CSS_CLASS_PAGE_LABEL);
        TextArea ta = new TextArea(page.getStudentName());
        ta.setEditable(false);
        studentNameVBox.getChildren().addAll(name,ta);
        getChildren().add(studentNameVBox);
        

    }
    
    public void addFooterToVBox(){
        footerVBox = new VBox();
        footerVBox.setPrefSize(100, 100);
        Label footer = new Label("Footer");
        footer.getStyleClass().add(CSS_CLASS_PAGE_LABEL);
        TextArea ta = new TextArea(page.getFooter());
        ta.setEditable(false);
        footerVBox.getChildren().addAll(footer,ta);
        getChildren().add(footerVBox);
        
//        setBottom(footerVBox); //might be a good choice
        
    }
    
    
   
    //Add the paragraph vbox to the pageEditView
    public void addParagraphToVBox(String contents,String family,int fontSize){
        VBox h1 = new VBox();
        h1.setPrefHeight(fontSize * 15);
//        Text text = new Text(contents);
//        text.setFont(Font.font(family, fontSize));
        TextArea ta = new TextArea(contents);
         ta.setStyle("-fx-font-size:" + fontSize);
        System.out.println(family);
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Sigmar+One");
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Indie+Flower");
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Shadows+Into+Light");
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Dancing+Script");
        if (family.equals("Sigmar One")){
        ta.setStyle("-fx-font-family:'Sigmar One'");
        }
        if (family.equals("Indie Flower")){
            ta.setStyle("-fx-font-family: 'Indie Flower'");
        }
        if (family.equals("Shadows Into Light")){
            ta.setStyle("-fx-font-family: 'Shadows Into Light'");
        }
        if (family.equals("Dancing Script")){
            ta.setStyle("-fx-font-family: 'Dancing Script'");
        }
        ta.setPrefSize(300, 300);
        ta.setWrapText(true);
        ta.setEditable(false);
        ta.setOnMouseClicked(e -> {
            selectedTypeComponent = "paragraph";
            selectedComponentContents = ta.getText();
            selectedTextArea = ta;
            selectedFontSize =  12;
            selectedFontStyle = family;
            ta.getStyleClass().add(CSS_CLASS_SELECTED_COMPONENT);
            System.out.println("paragraph component has been clicked");
        });
        
        h1.getChildren().add(ta);
        h1.setVisible(true);
        paragraphComp.add(h1);
        getChildren().add(h1);
    }
    
    public void updateParagraphComponent(String content,String family,int fontSize){
        TextArea ta = getSelectedTextArea();
        ta.setText(content);
        ta.setStyle("-fx-font-size:" + fontSize);
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Sigmar+One");
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Indie+Flower");
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Shadows+Into+Light");
        ta.getStylesheets().add("http://fonts.googleapis.com/css?family=Dancing+Script");
        if (family.equals("Sigmar One")){
        ta.setStyle("-fx-font-family:'Sigmar One'");
        }
        if (family.equals("Indie Flower")){
            ta.setStyle("-fx-font-family: 'Indie Flower'");
        }
        if (family.equals("Shadows Into Light")){
            ta.setStyle("-fx-font-family: 'Shadows Into Light'");
        }
        if (family.equals("Dancing Script")){
            ta.setStyle("-fx-font-family: 'Dancing Script'");
        }
        setSelectedComponentContent(null);
        setSelectedTypeComponent(null);
        setSelectedFontSize(0);
        setSelectedTextArea(null);
        setFontStyle(null);
         reloadPageEditView(page);
    }
    
    //GETTING THE INFO IN THE SELECTED COMPONENT
    public String getSelectedTypeComponent(){
        return selectedTypeComponent;
    }
    public void setSelectedTypeComponent(String x){
        selectedTypeComponent = x;
    }
    public String getSelectedComponentContent(){
        return selectedComponentContents;
    }
    public void setSelectedComponentContent(String x){
        selectedComponentContents = x;
    }
    
    public TextArea getSelectedTextArea(){
        return selectedTextArea;
    }
    public void setSelectedTextArea(TextArea t){
        selectedTextArea = t;
    }
    public int getFontSize(){
        return selectedFontSize;
    }
    public void setSelectedFontSize(int x){
        selectedFontSize = x;
    }
    public String getFontStyle(){
        return selectedFontStyle;
    }
    public void setFontStyle(String x){
        selectedFontStyle = x;
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
        //String imagePath = "./images/slide_show_images/DefaultStartSlide.png";
        String imagePath = "./images/slide_show_images/ArchesUtah.jpg";
       // String imagePath = x;
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
	   // imageController.processSelectImage(imageSelectionView);
            imageController.processSelectImage(page, this);
	});
        
        getChildren().add(vBox);
        
        
        
    }
    
    public void getPageInfo(){
        System.out.println(page.getStudentName());
        System.out.println(page.getTitle());
    }
    
    public void updatePageImage() {
	String imagePath = page.getImagePath() + SLASH + page.getImageFileName();
	File file = new File(imagePath);
	try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
	    imageSelectionView.setImage(slideImage);
	    
	    // AND RESIZE IT
	    double scaledWidth = DEFAULT_THUMBNAIL_WIDTH;
	    double perc = scaledWidth / slideImage.getWidth();
	    double scaledHeight = slideImage.getHeight() * perc;
	    imageSelectionView.setFitWidth(scaledWidth);
	    imageSelectionView.setFitHeight(scaledHeight);
            reloadPageEditView(page);
	} catch (Exception e) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	}
    }    
   
   
   
   
}
