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
import static eportfoliogenerator.StartupConstants.CSS_CLASS_SITE_EDIT_VBOX;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static eportfoliogenerator.StartupConstants.ICON_ADD_PAGE;
import static eportfoliogenerator.StartupConstants.ICON_EXIT;
import static eportfoliogenerator.StartupConstants.ICON_REMOVE_PAGE;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    Button viewPortfolioButton;
    Button exitButton;
    
    //WORKSPACE
    HBox workspace;
    
    //THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    VBox siteToolbarPane;
    Button addPageButton;
    Button removePageButton;
    Button selectPageButton;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane pagesEditorScrollPane;
    HBox pagesEditorPane;
    
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
      siteToolbarPane = new VBox();
      siteToolbarPane.getStyleClass().add(CSS_CLASS_SITE_EDIT_VBOX);
      addPageButton = this.initChildButton(siteToolbarPane,ICON_ADD_PAGE,
              CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
      removePageButton = this.initChildButton(siteToolbarPane, ICON_REMOVE_PAGE,
              CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
      selectPageButton = this.initChildButton(siteToolbarPane, ICON_EXIT,
              CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
      
      // AND THIS WILL GO IN THE CENTER
	pagesEditorPane = new HBox();
	pagesEditorScrollPane = new ScrollPane(pagesEditorPane);
//	initTitleControls();
        workspace.getChildren().add(siteToolbarPane);
        workspace.getChildren().add(pagesEditorScrollPane);
    }

    private void initFileToolbar() {
      fileController = new FileController(this, fileManager);
    }

    private void initEventHandlers() {

    }

    private void initWindow(String windowTitle) {

    }

    private Button initChildButton(VBox siteToolbarPane, String ICON_ADD_PAGE, String CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

   

    
    
    
    
    
   
    
    
}
