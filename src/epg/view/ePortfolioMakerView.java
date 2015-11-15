/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.FileController;
import epg.controller.ePortfolioEditController;
import epg.file.ePortfolioFileManager;
import epg.model.PortfolioModel;
import eportfoliogenerator.LanguagePropertyType;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_ADD_PAGE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EXIT;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_LOAD_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_NEW_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_REMOVE_PAGE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SAVE_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SAVE_AS_PORTFOLIO;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_SELECT_PAGE;
import static eportfoliogenerator.LanguagePropertyType.TOOLTIP_EXPORT_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_SITE_EDIT_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static eportfoliogenerator.StartupConstants.ICON_ADD_PAGE;
import static eportfoliogenerator.StartupConstants.ICON_EXIT;
import static eportfoliogenerator.StartupConstants.ICON_LOAD_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_NEW_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_REMOVE_PAGE;
import static eportfoliogenerator.StartupConstants.ICON_SAVE_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.ICON_EXPORT_PORTFOLIO;
import static eportfoliogenerator.StartupConstants.PATH_ICONS;
import static eportfoliogenerator.StartupConstants.STYLE_SHEET_UI;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    
    //WORKSPACE
    HBox workspace;
    
    //THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    VBox siteEditToolbar;
    Button addPageButton;
    Button removePageButton;
    Button selectPageButton;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane sitesEditorScrollPane;
    VBox sitesEditorPane;
    
    // THIS IS THE E-PORTFOLIO WE'RE WORKING WITH
    PortfolioModel portfolio;
    
    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
    ePortfolioFileManager fileManager;
    
    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
    private FileController fileController;
    
     // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
    private ePortfolioEditController editController;

    public ePortfolioMakerView(ePortfolioFileManager initFileManager) {
        fileManager = initFileManager;
        portfolio = new PortfolioModel(this);
        
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

	// NOW SETUP THE EVENT HANDLERS
	initEventHandlers();

	// AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
	// KEEP THE WINDOW FOR LATER
	primaryStage = initPrimaryStage;
	initWindow(windowTitle);
    }
    
    
    //UI SETUP HELPER METHODS
    private void initWorkspace(){
    // FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
	workspace = new HBox(); 
    
    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
      siteEditToolbar = new VBox();
      siteEditToolbar.getStyleClass().add(CSS_CLASS_SITE_EDIT_VBOX);
      addPageButton = this.initChildButton(siteEditToolbar,ICON_ADD_PAGE, 
            TOOLTIP_ADD_PAGE,  CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  false);
      removePageButton = this.initChildButton(siteEditToolbar, ICON_REMOVE_PAGE,
            TOOLTIP_REMOVE_PAGE,  CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
      selectPageButton = this.initChildButton(siteEditToolbar, ICON_EXIT,
            TOOLTIP_SELECT_PAGE,  CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
      
      // AND THIS WILL GO IN THE CENTER
//	pagesEditorPane = new HBox();
//	pagesEditorScrollPane = new ScrollPane(pagesEditorPane);
       
//	initTitleControls();
      
        sitesEditorPane = new VBox();
        sitesEditorScrollPane = new ScrollPane(sitesEditorPane);
        workspace.getChildren().add(siteEditToolbar);
        workspace.getChildren().add(sitesEditorScrollPane);
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
                TOOLTIP_SAVE_PORTFOLIO,    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        savePortfolioAsButton = initChildButton(fileToolbarPane, ICON_SAVE_PORTFOLIO,	
                TOOLTIP_SAVE_AS_PORTFOLIO,    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
	exportPortfolioButton = initChildButton(fileToolbarPane, ICON_EXPORT_PORTFOLIO,
                TOOLTIP_EXPORT_PORTFOLIO,    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
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
        selectPageButton.setOnAction(e -> {
	    editController.processSelectPageRequest();
	});
	
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
//        epgPane.setLeft(siteEditToolbar);//tested
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
}
    

   

    
    
    
    
    
   
    
    

