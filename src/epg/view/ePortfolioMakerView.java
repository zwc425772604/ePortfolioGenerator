/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.FileController;
import epg.controller.ePortfolioEditController;
import epg.controller.pageEditorController;
import epg.error.ErrorHandler;
import epg.file.ePortfolioFileManager;
import epg.model.Page;
import epg.model.PortfolioModel;
import eportfoliogenerator.LanguagePropertyType;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_IMAGE_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_PAGE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_SLIDESHOW_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_TEXT_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_TEXT_HYPERLINK;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_VIDEO_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EDIT_IMAGE_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EDIT_SLIDE_SHOW_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EDIT_TEXT_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EXIT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_LOAD_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_NEW_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_REMOVE_PAGE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SAVE_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SAVE_AS_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EXPORT_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EDIT_THE_HYPERLINK;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EDIT_VIDEO_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_REMOVE_COMPONENT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SELECT_BANNER_IMAGE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SELECT_EDITOR_WORKSPACE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SELECT_VIEWER_WORKSPACE;

import static eportfoliogenerator.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_PAGE_EDITOR_WORKSPACE_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_PAGE_LABEL;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_SITE_EDIT_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_PAGE_EDITOR_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_PAGE_EDIT_VIEW;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_SELECTED_PAGE_EDIT_VIEW;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXT_COMPONENT_COMBOBOX;
import static eportfoliogenerator.StartupConstants.ICON_ADD_IMAGE_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_ADD_PAGE;
import static eportfoliogenerator.StartupConstants.ICON_ADD_SLIDESHOW_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_ADD_TEXT_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_ADD_TEXT_HYPERLINK;
import static eportfoliogenerator.StartupConstants.ICON_ADD_VIDEO_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_EDIT_IMAGE_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_EDIT_SLIDE_SHOW_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_EDIT_TEXT_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_EDIT_THE_HYPERLINK;
import static eportfoliogenerator.StartupConstants.ICON_EDIT_VIDEO_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_EXIT;
import static eportfoliogenerator.StartupConstants.ICON_LOAD_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_NEW_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_REMOVE_PAGE;
import static eportfoliogenerator.StartupConstants.ICON_SAVE_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_EXPORT_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_REMOVE_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_SAVE_AS_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_SELECT_BANNER_IMAGE;
import static eportfoliogenerator.StartupConstants.ICON_SELECT_EDITOR_COMPONENT;
import static eportfoliogenerator.StartupConstants.ICON_SELECT_VIEWER_COMPONENT;
import static eportfoliogenerator.StartupConstants.LABEL_PAGE_TITLE;
import static eportfoliogenerator.StartupConstants.PATH_ICONS;
import static eportfoliogenerator.StartupConstants.STYLE_SHEET_UI;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author weichaozhao
 */
public class ePortfolioMakerView {
    
    Stage primaryStage;
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane epgPane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newPortfolioButton;
    Button loadPortfolioButton;
    Button savePortfolioButton;
    Button savePortfolioAsButton;
    Button exportPortfolioButton;
    Button exitButton;
    
    Button page1Button;
    
    //WORKSPACE
    HBox workspace;
    
    //WORKSPACE MODE TOOLBAR
    FlowPane workspaceModeToolbar;
    Button selectPageEditorWorkspaceButton;
    Button selectSiteViewerWorkspaceButton;
    
    //THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    VBox siteEditToolbar;
    Button addPageButton;
    Button removePageButton;
    Button selectPageButton;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane sitesEditorScrollPane;
    VBox sitesEditorPane;
    
    //THIS WILL GO IN THE RIGHT OF THE SCREEN
    VBox pageEditorToolbar;
    ScrollPane pagesEditorToolbarScrollPane;
    Button selectBannerImageButton; //pop up dialog
    
    //FOR ENTERING TEXT 
    VBox pageTitleVBox;
    VBox studentNameVBox;
    VBox footerVBox;
    
    //FOR TEMPLATE
    VBox layoutTemplate;
    VBox colorTemplate;
    VBox fontComponent;
    
    Button addTextComponentButton; // dialog
    Button addImageComponentButton;//dialog
    Button addSlideshowComponentButton;//dialog
    Button addVideoComponentButton;//dialog
    Button removeComponentButton;
    Button editTextComponentButton;//dialog
    Button editImageComponentButton;//dialog
    Button editSlideshowComponentButton;//dialog
    Button editVideoComponentButton;//dialog
    Button addTextHyperlinkButton;//dialog
    Button editTextHyperlinkButton;
    Button selectBannerImage;
    
    
    // THIS IS THE E-PORTFOLIO WE'RE WORKING WITH
    PortfolioModel portfolio;
    
    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
    ePortfolioFileManager fileManager;
    
    //THIS IS A LIST OF PAGE BUTTONS
    ObservableList<Button> pageButton;
    
    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
    private FileController fileController;
    
    // THIS CLASS WILL HANDLE ALL ERRORS FOR THIS PROGRAM
    private ErrorHandler errorHandler;
    
     // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
    private ePortfolioEditController editController;
    private pageEditorController pageController;

    public ePortfolioMakerView(ePortfolioFileManager initFileManager) {
        fileManager = initFileManager;
        portfolio = new PortfolioModel(this);
        pageButton = FXCollections.observableArrayList();
    }
    
    public PortfolioModel getPortfolio(){
        return portfolio;
    }
    
    public Stage getWindow(){
        return primaryStage;
    }

    public void startUI(Stage initPrimaryStage, String windowTitle) {
        // THE TOOLBAR ALONG THE NORTH
	initFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
	// TO THE WINDOW YET
	initWorkspace();

	
        
         //INIT THE RIGHT WORKSPACE CONTROLS
        initPageEditorWorkspaceButton();
        
        //INIT THE WORKSPACE MODE TOOLBAR CONTROLS
        initWorkspaceModeToolbar();
        
        // NOW SETUP THE EVENT HANDLERS
	initEventHandlers();
        
        //INIT THE RIGHT WORKSPACE CONTROLS
        

	// AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
	// KEEP THE WINDOW FOR LATER
	primaryStage = initPrimaryStage;
	initWindow(windowTitle);
    }
    
    public void initWorkspaceModeToolbar(){
        workspaceModeToolbar = new FlowPane();
        
        selectPageEditorWorkspaceButton = this.initChildButton(workspaceModeToolbar,ICON_SELECT_EDITOR_COMPONENT,
                TOOLTIP_SELECT_EDITOR_WORKSPACE,  CSS_CLASS_PAGE_EDITOR_BUTTON, false);
        selectSiteViewerWorkspaceButton = this.initChildButton(workspaceModeToolbar, ICON_SELECT_VIEWER_COMPONENT,
                TOOLTIP_SELECT_VIEWER_WORKSPACE, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
    }
    
    
    public ErrorHandler getErrorHandler() {
	return errorHandler;
    }
    
    public VBox getLeftSiteToolbar(){
        return siteEditToolbar;
    
    
}
    
    public void initPageEditorWorkspaceButton(){
        pageEditorToolbar = new VBox();
        pageEditorToolbar.getStyleClass().add(CSS_CLASS_PAGE_EDITOR_WORKSPACE_VBOX);
//      
	
//        pagesEditorToolbarScrollPane = new ScrollPane(pageEditorToolbar);
        pagesEditorToolbarScrollPane = new ScrollPane();
        pagesEditorToolbarScrollPane.setContent(pageEditorToolbar);
        
        pageTitleVBox = this.initPageTextfieldControl(pageEditorToolbar,"Title:","ENTER THE TITLE");
        studentNameVBox = this.initPageTextfieldControl(pageEditorToolbar,"Student Name:", "ENTER STUDENT NAME");
        footerVBox = this.initPageTextfieldControl(pageEditorToolbar, "Footer:", "ENTER THE FOOTER");
        ArrayList<String> layoutChoices = new ArrayList();
        layoutChoices.add("topLeftNavBar");layoutChoices.add("dropdownNavBar");layoutChoices.add("center");
        layoutChoices.add("circularNavBar"); layoutChoices.add("dotNavBar");
        layoutTemplate = this.initTemplate(pageEditorToolbar, "Select Layout Template:", layoutChoices);
        ArrayList<String> colorChoices = new ArrayList<String>(
        Arrays.asList("Green","Red","Blue","Pink","Purple","Gray"));
        colorTemplate = this.initTemplate(pageEditorToolbar, "Select Color Template:", colorChoices);
        
        fontComponent = this.initFontTemplate(pageEditorToolbar);
        
//	initPageTitleControl(pageEditorWorkspace);
//    Button addTextComponentButton; // dialog
//    Button addImageComponentButton;//dialog
  //  Button addSlideshowComponenetButton;//dialog
//    Button addVideoComponentButton;//dialog
//    Button removeComponentButton;
//    Button editTextComponentButton;//dialog
//    Button editImageComponentButton;//dialog
//    Button editSlideshowComponentButton;//dialog
//    Button editVideoComponentButton;//dialog
//    Button addTextHyperlinkButton;//dialog
	
//    Button editTextComponentButton;//dialog
//    Button editImageComponentButton;//dialog
//    Button editSlideshowComponentButton;//dialog
//    Button editVideoComponentButton;//dialog
//    Button addTextHyperlinkButton;//dialog
        
        //PAGE EDITOR CONTROLL
     selectBannerImage = this.initChildButton(pageEditorToolbar, ICON_SELECT_BANNER_IMAGE, 
                TOOLTIP_SELECT_BANNER_IMAGE,CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     addTextComponentButton = this.initChildButton(pageEditorToolbar, ICON_ADD_TEXT_COMPONENT,
             TOOLTIP_ADD_TEXT_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     addSlideshowComponentButton = this.initChildButton(pageEditorToolbar,ICON_ADD_SLIDESHOW_COMPONENT, 
             TOOLTIP_ADD_SLIDESHOW_COMPONENT,CSS_CLASS_PAGE_EDITOR_BUTTON , false);
     addImageComponentButton = this.initChildButton(pageEditorToolbar, ICON_ADD_IMAGE_COMPONENT,
             TOOLTIP_ADD_IMAGE_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     addVideoComponentButton = this.initChildButton(pageEditorToolbar, ICON_ADD_VIDEO_COMPONENT,
             TOOLTIP_ADD_VIDEO_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     addTextHyperlinkButton = this.initChildButton(pageEditorToolbar, ICON_ADD_TEXT_HYPERLINK,
             TOOLTIP_ADD_TEXT_HYPERLINK, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     removeComponentButton = this.initChildButton(pageEditorToolbar, ICON_REMOVE_COMPONENT ,
             TOOLTIP_REMOVE_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     editVideoComponentButton = this.initChildButton(pageEditorToolbar, ICON_EDIT_VIDEO_COMPONENT,
             TOOLTIP_EDIT_VIDEO_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     editSlideshowComponentButton = this.initChildButton(pageEditorToolbar, ICON_EDIT_SLIDE_SHOW_COMPONENT,
             TOOLTIP_EDIT_SLIDE_SHOW_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     editTextComponentButton = this.initChildButton(pageEditorToolbar, ICON_EDIT_TEXT_COMPONENT,
             TOOLTIP_EDIT_TEXT_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     editImageComponentButton = this.initChildButton(pageEditorToolbar, ICON_EDIT_IMAGE_COMPONENT,
             TOOLTIP_EDIT_IMAGE_COMPONENT, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
     editTextHyperlinkButton = this.initChildButton(pageEditorToolbar, ICON_EDIT_THE_HYPERLINK,
             TOOLTIP_EDIT_THE_HYPERLINK, CSS_CLASS_PAGE_EDITOR_BUTTON, false);
    }
    private VBox initFontTemplate(Pane pane){
         VBox vb = new VBox();
      Label fontFamilyLabel = new Label("Google Font Family");
      fontFamilyLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
     ArrayList<String> fontFamilyChoices = new ArrayList<String>(
        Arrays.asList("Sigmar One","Covered+By+Your+Grace","Shadows Into Light",
                "Dancing Script","Purple","Indie Flower","Poiret One","Indie Flower",
                "Titillium Web","Lobster","Dosis","Raleway"));
      
     ObservableList<String> famChoices = FXCollections.observableArrayList();
	for(String st : fontFamilyChoices){
            famChoices.add(st);
        }
       ComboBox familyComboBox = new ComboBox(famChoices);
       familyComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
       
       //font style
       Label styleLabel = new Label("Font Style");
       styleLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
       ArrayList<String> fontStyleChoices = new ArrayList<String>(
        Arrays.asList("normal","italic","oblique","initial","inherit"));
       ObservableList<String> styleChoices = FXCollections.observableArrayList();
	for(String st : fontStyleChoices){
            styleChoices.add(st);
        }
        ComboBox styleComboBox = new ComboBox(styleChoices);
        styleComboBox.getSelectionModel().select("normal");
        styleComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
        Label fontSizeLabel = new Label("Font Size:");
        fontSizeLabel.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        ObservableList<Integer> fontSizeChoices = FXCollections.observableArrayList();
        for (int i = 8; i<40; i+= 2){
            fontSizeChoices.add(i);
        }
        
        ComboBox fontSizeComboBox = new ComboBox(fontSizeChoices);
	fontSizeComboBox.getSelectionModel().select("8");
        fontSizeComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
        
       
       vb.getChildren().add(fontFamilyLabel);
       vb.getChildren().add(familyComboBox);
       vb.getChildren().add(styleLabel);
       vb.getChildren().add(styleComboBox);
       vb.getChildren().add(fontSizeLabel);
       vb.getChildren().add(fontSizeComboBox);
       
       familyComboBox.getSelectionModel().select(0);
       Button okButton = new Button("OK");
       okButton.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
       okButton.setOnAction(e -> {
           
       });
       vb.getChildren().add(okButton);
       familyComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
       pane.getChildren().add(vb);
       
       return vb;
        
    }
    
   private VBox initTemplate(Pane pane, String str, ArrayList<String> input){
       VBox vb = new VBox();
      Label template = new Label(str);
      template.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
     
      ObservableList<String> choices = FXCollections.observableArrayList();
	for(String st : input){
            choices.add(st);
        }
       ComboBox choicesComboBox = new ComboBox(choices);
       vb.getChildren().add(template);
       vb.getChildren().add(choicesComboBox);
       
       choicesComboBox.getSelectionModel().select(0);
       Button okButton = new Button("OK");
       okButton.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
       okButton.setOnAction(e -> {
           
       });
       vb.getChildren().add(okButton);
       choicesComboBox.getStyleClass().add(CSS_CLASS_TEXT_COMPONENT_COMBOBOX);
       pane.getChildren().add(vb);
       
       return vb;
   }
    //UI SETUP HELPER METHODS
    private void initWorkspace(){
    // FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
	workspace = new HBox(); 
    
    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
      siteEditToolbar = new VBox();
      siteEditToolbar.setPrefWidth(148);
      siteEditToolbar.getStyleClass().add(CSS_CLASS_SITE_EDIT_VBOX);
      addPageButton = this.initChildButton(siteEditToolbar,ICON_ADD_PAGE, 
            TOOLTIP_ADD_PAGE,  CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  false);
      removePageButton = this.initChildButton(siteEditToolbar, ICON_REMOVE_PAGE,
            TOOLTIP_REMOVE_PAGE,  CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);//supposed to be true
      
    //buttons for selecting page
//      page1Button = this.initPageButton(siteEditToolbar, "ABOUT ME",
//              CSS_CLASS_VERTICAL_TOOLBAR_BUTTON , false);

     
   
        // AND THIS WILL GO IN THE CENTER
//        sitesEditorPane = new VBox();
//        sitesEditorScrollPane = new ScrollPane(sitesEditorPane);
////        workspace.getChildren().add(siteEditToolbar);
//        workspace.getChildren().add(sitesEditorScrollPane);
    }
    
    public void initPageButton(Pane toolbar,
            String title,
            String cssClass,
            boolean disable){
        
        Button button = new Button();
	button.getStyleClass().add(cssClass);
        button.setText(title);
	button.setDisable(disable);
	pageButton.add(button);
	toolbar.getChildren().add(button);
        
//	return button;
        
    }

    private void initFileToolbar() {
      fileToolbarPane = new FlowPane();
      fileToolbarPane.getStyleClass().add(CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON);
       // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	newPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_PORTFOLIO,	
                TOOLTIP_NEW_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
	loadPortfolioButton = initChildButton(fileToolbarPane, ICON_LOAD_PORTFOLIO,	
                TOOLTIP_LOAD_PORTFOLIO,    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
	savePortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_PORTFOLIO,	
                TOOLTIP_SAVE_PORTFOLIO,    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        savePortfolioAsButton = initChildButton(fileToolbarPane, ICON_SAVE_AS_PORTFOLIO,	
                TOOLTIP_SAVE_AS_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
	exportPortfolioButton = initChildButton(fileToolbarPane, ICON_EXPORT_PORTFOLIO,
                TOOLTIP_EXPORT_PORTFOLIO,    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
	exitButton = initChildButton(fileToolbarPane, ICON_EXIT,
                TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
    }
    

    private void initEventHandlers() {
       fileController = new FileController(this, fileManager);
        newPortfolioButton.setOnAction(e -> {
         fileController.handleNewPortfolioRequest();
        });
        loadPortfolioButton.setOnAction(e -> {
	    fileController.handleLoadPortfolioRequest();
	});
	savePortfolioButton.setOnAction(e -> {
	    fileController.handleSavePortfolioRequest();
	});
        savePortfolioAsButton.setOnAction(e -> {
            fileController.handleSaveAsPortfolioRequest();
        });
	exportPortfolioButton.setOnAction(e -> {
	    fileController.handleExportPortfolioRequest();
	});
	exitButton.setOnAction(e -> {
	    fileController.handleExitRequest();
	});
        
        //THEN THE PORTFOLIO EDIT CONTROLS
        editController = new ePortfolioEditController(this);
        addPageButton.setOnAction(e -> {
	    editController.processAddPageRequest();
	});
        
	removePageButton.setOnAction(e -> {
	    editController.processRemovePageRequest();
            
	});
        
        pageController = new pageEditorController(this);
        addTextComponentButton.setOnAction(e -> {
            pageController.processAddTextComponent();
        });
        addImageComponentButton.setOnAction(e ->{
            pageController.processAddImageComponent();
        });
        addVideoComponentButton.setOnAction(e ->{
            pageController.processAddVideoComponent();
        });
        selectSiteViewerWorkspaceButton.setOnAction(e -> {
            pageController.processSiteViewer();
        });
        
     
	
    }
    
       public VBox initPageTextfieldControl(Pane pane, String nameLabel, String namePrompt){
           
            pageTitleVBox = new VBox();
        Label lbl = new Label(nameLabel);
        lbl.getStyleClass().add(CSS_CLASS_PAGE_LABEL);
        TextField tf = new TextField();
        tf.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
        pageTitleVBox.getChildren().add(lbl);
        pageTitleVBox.getChildren().add(tf);
        String titlePrompt = namePrompt;
        tf.setText(titlePrompt);
        tf.textProperty().addListener(e -> {
	    portfolio.getPages().get(0).setTitle(tf.getText());
//            System.out.println(tf.getText());
            System.out.println(portfolio.getPages().get(0).getTitle());
            updatePageTitle();
                    
	});
        pane.getChildren().add(pageTitleVBox);
        return pageTitleVBox;
       }
//        PropertiesManager props = PropertiesManager.getPropertiesManager();
//	String labelPrompt = props.getProperty(LABEL_PAGE_TITLE);
//	pageTitlePane = new FlowPane();
//	pageTitleLabel = new Label(labelPrompt);
//	pageTitleTextfield = new TextField();
//        pageTitleTextfield.getStyleClass().add(CSS_CLASS_TEXTFIELD_STYLE);
//	pageTitlePane.getChildren().add(pageTitleLabel);
//	pageTitlePane.getChildren().add(pageTitleTextfield);
//	
//	String titlePrompt = props.getProperty(LanguagePropertyType.LABEL_PAGE_TITLE);
//	pageTitleTextfield.setText(titlePrompt);
//	
//	pageTitleTextfield.textProperty().addListener(e -> {
//	    portfolio.setTitle(pageTitleTextfield.getText());
//	});
//        pageEditorWorkspace.getChildren().add(pageTitlePane);
       
       
//        addToolbarToPageEditWorkspace(pageTitlePane);
    
    
       public void addToolbarToPageEditWorkspace(Pane pane){
           pageEditorToolbar.getChildren().add(pane);
       }

    private void initWindow(String windowTitle) {
        //SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);
        
        //GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        //AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
	primaryStage.setWidth(bounds.getWidth());
	primaryStage.setHeight(bounds.getHeight());
        
        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
	epgPane = new BorderPane();
	epgPane.setTop(fileToolbarPane);
        epgPane.setBottom(workspaceModeToolbar);
        epgPane.setLeft(siteEditToolbar);  //testedddd
        
        sitesEditorPane = new VBox();
        sitesEditorPane.setPrefSize(900, 700);
        sitesEditorScrollPane = new ScrollPane(sitesEditorPane);
        epgPane.setCenter(sitesEditorScrollPane);
        //epgPane.setRight(pageEditorToolbar);
        epgPane.setRight(pagesEditorToolbarScrollPane);
	primaryScene = new Scene(epgPane);
        
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
	// WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
	primaryScene.getStylesheets().add(STYLE_SHEET_UI);
	primaryStage.setScene(primaryScene);
	primaryStage.show();
    }

    public Button initChildButton(
            Pane toolbar,
            String iconFileName,
            LanguagePropertyType tooltip,
            String cssClass,
             boolean disable) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disable);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }

    public void updatePageTitle(){
        for (int i = 0; i<pageButton.size();i++){
            pageButton.get(i).setText(portfolio.getPages().get(i).getTitle());
        }
    }
    
   /**
     * Uses the slide show data to reload all the components for
     * slide editing.
     * 
     * @param slideShowToLoad SLide show being reloaded.
     */
    public void reloadSlideShowPane() {
	sitesEditorPane.getChildren().clear();
//	reloadTitleControls();
	for (Page page : portfolio.getPages()) {
	    pageEditView pageEditor = new pageEditView(page);
	    if (portfolio.isSelectedPage(page))
		pageEditor.getStyleClass().add(CSS_CLASS_PAGE_EDIT_VIEW);
	    else
	        pageEditor.getStyleClass().add(CSS_CLASS_SELECTED_PAGE_EDIT_VIEW);
	    sitesEditorPane.getChildren().add(pageEditor);
	    pageEditor.setOnMousePressed(e -> {
		portfolio.setSelectedPage(page);
		this.reloadSlideShowPane();
	    });
	}
//	updateSlideshowEditToolbarControls();
    }

   
    
   
}
    

   

    
    
    
    
    
   
    
    

