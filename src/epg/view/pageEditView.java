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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


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
    
    Media m;
    MediaPlayer mp;
    MediaView mv;
    
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
    MediaView selectedMediaView;
    int selectedFontSize;
    String selectedFontStyle;
    ListView selectedListView;
    ObservableList<String> selectedListElement;
    String selectedTextFromTextArea;
    
    TextArea footerTA;
    TextArea studentNameTA;
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
        selectedComponent = null;
        selectedMediaView = null;
	headerComp = FXCollections.observableArrayList();
        paragraphComp = FXCollections.observableArrayList();
        imageComp = FXCollections.observableArrayList();
        addStudentNameToVBox();
        addFooterToVBox();
        //footerVBox.toBack();
        updateHeader();
        updateParagraph();
        if(!page.getBannerPath().equals("")){
            addBannerImageToVBox(page.getBannerPath()+page.getBannerName(),
                    page.getBannerWidth(),page.getBannerHeight());
        }
        if(!page.getVideoPath().isEmpty()){
           for( int i = 0; i<page.getVideoPath().size();i++){
               addVideoToVBox(page.getVideoPath().get(i)+page.getVidFileNameList().get(i),
               page.getVideoWidth().get(i),page.getVideoHeight().get(i));
           }
        }
//        if(!page.getImagePath().isEmpty()){
//        for( int j = 0; j<page.getImagePath().size();j++){
//               addImageToVBox(page.getImagePath().get(j)+page.getImageFileName().get(j),
//               page.getImageHeight().get(j),page.getImageWidth().get(j),
//               page.getImageLayout().get(j),page.getImageCaption().get(j));
//    }
//        }
//        if(!page.getImageList().isEmpty()){
//            addImageToVBox(page.get)
//        }
       
       
        
        
    }
    public void updateParagraph(){
        ArrayList<String> paragraph = page.getParagraphContent();
        for (String text : paragraph){
          addHeaderToVBox(text);
        }
        reloadPageEditView(page);
        
//        ui.reloadPortfolioPane();
    }

     
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
	
	
    
    
    public VBox getWorkspace(){
        return this;
    }
    
    //can remove a single vbox from the pane
   
    

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
        });
        
        h1.getChildren().add(ta);
        h1.setPrefHeight(30);
        headerComp.add(h1);
        getChildren().remove(footerVBox);
        getChildren().add(h1);
        getChildren().add(footerVBox);
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
        
        listView.setOnMouseClicked(e ->{
           selectedTypeComponent = "list";
           setSelectedComponent(null);
           selectedComponent = l1;
           selectedListView = listView;
           selectedListElement = listView.getItems();
           selectedTextFromTextArea = listView.getSelectionModel().getSelectedItem();
            System.out.println(selectedTextFromTextArea);
          
           });
        getChildren().remove(footerVBox);
        getChildren().add(l1);
        getChildren().add(footerVBox);
    }
    public void updateListComponent(ObservableList<String> listElement){
        ListView<String> lv = getSelectedListView();
        //lv.setPrefHeight(listElement.size() * 500);
        //lv.setMinHeight(listElement.size() * 20);
        lv.setMinHeight(30* listElement.size());
        final ScrollBar scrollBarH = (ScrollBar) lv.lookup(".scroll-bar:vertical");
        scrollBarH.setVisible(false);
        lv.setItems(listElement);
        setListView(null);
        setListElement(null);
        reloadPageEditView(page);
        
    }
    
    public ListView getSelectedListView(){
        return selectedListView;
    }
    public void setListView(ListView<String> lv){
        selectedListView = lv;
    }
    
    public ObservableList getListElement(){
        return selectedListElement;
    }
    public void setListElement(ObservableList<String> LE){
        selectedListElement = LE;
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
             getChildren().remove(footerVBox);
             i1.setOnMouseClicked(e ->{
                 System.out.println("XXx");
             });
             i1.getChildren().add(imageSelectionView);
             i1.getChildren().add(captionTF);
             getChildren().add(i1);
             getChildren().remove(footerVBox);
	} catch (Exception e) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	} 
         imageSelectionView.setOnMouseClicked(e ->{
                 System.out.println("xx");
             });

        
    }
    
    
    public void addVideoToVBox(String path, int height, int width){
         VBox v1 = new VBox();
         
         File f = new File(path);
         try{
            m = new Media(f.toURI().toString());
             mp = new MediaPlayer(m);
             mp.setAutoPlay(true);
            mv = new MediaView(mp);
            
            mv.setFitHeight(height);
            mv.setFitWidth(width);
            //v1.setAlignment(Pos.CENTER); CSS 
            getChildren().remove(footerVBox);
           v1.getChildren().add(mv);
            getChildren().add(v1);
            getChildren().add(footerVBox);
         }
         catch (Exception e) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	}
          mv.setOnMousePressed(e ->{
              System.out.println("MV");
              selectedComponent = v1;
              selectedMediaView = mv;
          });
         
        
         
    }
    public MediaView getSelectedMediaView(){
        return selectedMediaView;
    }
    public void setSelectedMediaView(MediaView MV){
        selectedMediaView = mv;
    }
    
    public void addStudentNameToVBox(){
        studentNameVBox = new VBox();
        studentNameVBox.setPrefSize(100,50);
        Label name = new Label("Student Name");
        name.getStyleClass().add(CSS_CLASS_PAGE_LABEL);
        TextArea ta = new TextArea(page.getStudentName());
        studentNameTA = ta;

        ta.setEditable(false);
        studentNameVBox.getChildren().addAll(name,ta);
        getChildren().add(studentNameVBox);
        

    }
    public TextArea getStudentNameTextArea(){
        return studentNameTA;
    }
    public void setStudentNameTextArea(TextArea s){
        studentNameTA = s;
    }
    public void updateStudentNameVBox(String name){
        getStudentNameTextArea().setText(name);
    }
    
    private void addFooterToVBox(){
        footerVBox = new VBox();
        footerVBox.setPrefSize(100, 100);
        Label footer = new Label("Footer");
        footer.getStyleClass().add(CSS_CLASS_PAGE_LABEL);
        TextArea ta = new TextArea(page.getFooter());
        footerTA = ta;
        ta.setEditable(false);
        ta.setOnMouseClicked(e ->{
            selectedTextFromTextArea = ta.getSelectedText();
            System.out.println(ta.getSelectedText());
        });
        
        footerVBox.getChildren().addAll(footer,ta);
        getChildren().add(footerVBox);
        
//        setBottom(footerVBox); //might be a good choice
        
    }
    
    public TextArea getFooterTextArea(){
        return footerTA;
    }
    public void setFooterTextArea(TextArea t){
        footerTA = t;
    }
    public void updateFooterVBox(String x){
        getFooterTextArea().setText(x);
    }
    public void setSelectedComponent(VBox x){
        selectedComponent = x;
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
             setSelectedComponent(null);
            selectedComponent = h1;
            selectedFontSize =  fontSize;
            selectedFontStyle = family;
            ta.getStyleClass().add(CSS_CLASS_SELECTED_COMPONENT);
            System.out.println("paragraph component has been clicked");
            selectedTextFromTextArea = ta.getSelectedText();
            System.out.println(selectedTextFromTextArea);
        });
       
        getChildren().remove(footerVBox);
//        Hyperlink l = new Hyperlink("google");
//        l.setOnAction(e ->{
//            final Stage stage = new Stage();
//            final WebView webView = new WebView();
//            final WebEngine engine = webView.getEngine();
//            final String url = "https://www.google.com";
//            engine.load(url);
//            stage.setScene(new Scene(webView));
//            stage.show();
//        });
//        h1.getChildren().add(l);
        h1.getChildren().add(ta);
        h1.setVisible(true);
        paragraphComp.add(h1);
        getChildren().add(h1);
         getChildren().add(footerVBox);
    }
    
    public String getSelectedTextFromTA(){
        return selectedTextFromTextArea;
    }
    public void setSelectedTextFromTA(String x) {
        selectedTextFromTextArea = x;
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
        ta.setOnMouseClicked(e ->{
            selectedTextFromTextArea = ta.getSelectedText();
            System.out.println(ta.getSelectedText());
        });
        setSelectedComponentContent(null);
        setSelectedTypeComponent(null);
        setSelectedFontSize(0);
        setSelectedTextArea(null);
        setFontStyle(null);
         reloadPageEditView(page);
    }
    public void addHyperlink(String url){
        HTMLEditor htmlEditor = new HTMLEditor();
        Hyperlink link = new Hyperlink();
        link.setText(getSelectedTextFromTA());
        String x= getSelectedTextFromTA();
        
//        getSelectedComponent().getChildren().add(link);
        //getSelectedTextArea().setText(link.getText());
        link.setOnAction(e ->{
            final Stage stage = new Stage();
            final WebView webView = new WebView();
            final WebEngine engine = webView.getEngine();
            final String URL = "https://" + url;
            engine.load(URL);
            stage.setScene(new Scene(webView));
            stage.show();
        });
        //getSelectedTextArea().appendText(link.getText());
        reloadPageEditView(page);
    }
    
    public String updateTextInTextArea(String x, String y){
        String para= getSelectedTextFromTA();
        String output = para.replace(x, y);
        return output;      
    }
    
    public VBox getSelectedComponent(){
        return selectedComponent;
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
//         getChildren().remove(footerVBox);
         page = testPage;
         
         //footerVBox.toBack();
         
         
         
        
	
	
	
        
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

    public void addBannerImageToVBox(String path, int width,int height) {
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
            
             //getChildren().remove(footerVBox);
             i1.setOnMouseClicked(e ->{
                 System.out.println("XXx");
             });
             i1.getChildren().add(imageSelectionView);
            
            // getChildren().add(i1); good
             getChildren().add(1, i1);
             
            // getChildren().remove(footerVBox);
          }
          catch (Exception e) {
	    ErrorHandler eH = new ErrorHandler(null);
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	}
          reloadPageEditView(page);
    }
   
     public void removeSelectingComponent(){
        this.getChildren().remove(getSelectedComponent());
        this.getChildren().remove(getSelectedMediaView());
       //System.out.println("removed");
        }
   
   
   
}
