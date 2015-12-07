/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;


import epg.dialog.FileNameDialog;
import epg.dialog.FileSaverDialog;
import epg.error.ErrorHandler;
import epg.file.ePortfolioFileManager;
import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import eportfoliogenerator.LanguagePropertyType;
import static eportfoliogenerator.StartupConstants.PATH_PORTFOLIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ssm.model.Slide;
import ssm.model.SlideShowModel;

/**
 * This class serves as the controller for all file toolbar operations,
 * driving the loading and saving of slide shows, among other things.
 * 
 * @author Weichao Zhao
 */
public class FileController {

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    // THE APP UI
    private ePortfolioMakerView ui;
    
    // THIS GUY KNOWS HOW TO READ AND WRITE SLIDE SHOW DATA
    private ePortfolioFileManager portfolioIO;
    private PortfolioModel model;
    /**
     * This default constructor starts the program without a slide show file being
     * edited.
     *
     * @param initSlideShowIO The object that will be reading and writing slide show
     * data.
     */
    public FileController(ePortfolioMakerView initUI, ePortfolioFileManager initPortfolioIO) {
        // NOTHING YET
        saved = true;
	ui = initUI;
        portfolioIO = initPortfolioIO;
    }
    
    public void markAsEdited() {
        saved = false;
       ui.updateToolbarControls(saved);
    }
    
    public void handleNewPortfolioRequest(){
        boolean continueToMakeNew = true;
       
        if (continueToMakeNew) {
            // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
            PortfolioModel portfolio = ui.getPortfolio();
            portfolio.reset();
            ui.reset();
            saved = false;
            
            // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateToolbarControls(saved);
            
            // MAKE SURE THE TITLE CONTROLS ARE ENABLED
            
        }
    }
    
    public void handleLoadPortfolioRequest(){
         try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING);
        }
         ui.setSaveAsButtonAble();
        
    }
    
    public boolean handleSavePortfolioRequest(){
         try {
	    // GET THE SLIDE SHOW TO SAVE
             
	    PortfolioModel portfolioToSave = ui.getPortfolio();
	    
            FileNameDialog dialog = new FileNameDialog(ui);
            // SAVE IT TO A FILE
            portfolioIO.savePortfolio(portfolioToSave);

            // MARK IT AS SAVED
            saved = true;

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateToolbarControls(saved);
            ui.setSaveAsButtonAble();
            ui.reloadPortfolioPane();
	    return true;
        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	    return false;
        }
    }
    
    public boolean handleSaveAsPortfolioRequest(){ 
        try{
        PortfolioModel portfolioToSave = ui.getPortfolio();
        FileSaverDialog dialog = new FileSaverDialog(ui);
        portfolioIO.savePortfolio(portfolioToSave);

            // MARK IT AS SAVED
            saved = true;

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            
            ui.updateToolbarControls(saved);
            ui.setSaveAsButtonAble();
            ui.reloadPortfolioPane();
	    return true;
        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	    return false;
        }
        
    }
    
    public void handleExportPortfolioRequest(){
        PortfolioModel model = ui.getPortfolio();
	    
            //Save the slide show every times the view button has been clicked
        try{
            portfolioIO.savePortfolio(model);
        }
        catch (IOException ioe) {
           
    }   
//        initDirectory();
        Stage primaryStage = new Stage();
        primaryStage.setWidth(1500);
        primaryStage.setHeight(1500);
        Scene scene = new Scene(new Group(),1350,1350);
       

         final WebView browser = new WebView();
       final WebEngine webEngine = browser.getEngine();
        FlowPane flowpane = new FlowPane();
     
      flowpane.getChildren().add(browser);
       flowpane.setAlignment(Pos.CENTER);

     //load a local file
        String path = ("public_html/" + "1stPage.html");
        File file = new File(path);
	try{
	  URL fileURL = file.toURI().toURL();
          //System.out.println(fileURL.toExternalForm());
	    webEngine.load(fileURL.toExternalForm());
        }
        catch (Exception e){
        
        }
        

       
        
        
       scene.setRoot(flowpane);
    
    primaryStage.setScene(scene);
    primaryStage.show();
        
    }
    
    public void handleExitRequest(){
         try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave();
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
        }
       
    }
    
    //still working
//    private boolean promptToSave() throws IOException {
//        // PROMPT THE USER TO SAVE UNSAVED WORK
//        boolean saveWork = true; 
//
//        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
//        if (saveWork) {
//            PortfolioModel portfolio = ui.getPortfolio();
//            portfolioIO.savePortfolio(portfolio);
//            saved = true;
//        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
//        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
//        else if (!true) {
//            return false;
//        }
//
//        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
//        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
//        // HAD IN MIND IN THE FIRST PLACE
//        return true;
//    }
    
    public void handleSwitchWorkspaceRequest(){
//         try {
//            // WE MAY HAVE TO SAVE CURRENT WORK
//            boolean continueToSwitch = true;
//            if (!saved) {
//                // THE USER CAN OPT OUT HERE WITH A CANCEL
//                continueToSwitch = promptToSave();
//            }
//
//            // IF THE USER REALLY WANTS TO OPEN A POSE
//            if (continueToSwitch) {
//                // GO AHEAD AND PROCEED MAKING A NEW POSE
//               
//            }
//        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING);
//        }
//         ui.setSaveAsButtonAble();
//        
           PortfolioModel model = ui.getPortfolio();
	   Page p = model.getSelectedPage();
           model.reset();
           model.addPage(p.getTitle(),p.getStudentName());
            //Save the slide show every times the view button has been clicked
        try{
            portfolioIO.savePortfolio(model);
        }
        catch (IOException ioe) {
           
    }   
        initDirectory();
        Stage primaryStage = new Stage();
        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);
        Scene scene = new Scene(new Group(),950,950);
        final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     FlowPane flowpane = new FlowPane();
     
     flowpane.getChildren().add(browser);
     flowpane.setAlignment(Pos.CENTER);
     String path = ("data/index.html");
        File file = new File(path);
	try{
	  URL fileURL = file.toURI().toURL();
          //System.out.println(fileURL.toExternalForm());
	    webEngine.load(fileURL.toExternalForm());
        }
        catch (Exception e){
        
        }
        
       scene.setRoot(flowpane);
        primaryStage.setScene(scene);
    primaryStage.show();
    }
    public void initDirectory(){
        PortfolioModel portfolio = ui.getPortfolio();
        String title = ui.getPortfolio().getFileName();
        try{
            
            for(int i = 0; i<portfolio.getPages().size();i++){
            Page p = portfolio.getPages().get(i);
               
               // String imgPath = p.getImagePath() + "/" + p.getImageFileName();
            for(int j = 0; j<p.getImageList().size(); j++){
            String imgPath = p.getImageList().get(j);
            System.out.println(imgPath);
            FileInputStream from = new FileInputStream(imgPath);
            FileOutputStream to = new FileOutputStream("data/"+ p.getImageFileName());
            byte[] buffer = new byte[4096];
            int byteRead;
            while( (byteRead = from.read(buffer))!=-1){
                to.write(buffer, 0, byteRead);
            }
            from.close();
            to.close();
            System.out.println("IMG file copied successfully");
        }}}
        catch(Exception ee){
            System.err.println("Fail to copy the image");
        }
        try{
            FileInputStream from = new FileInputStream("data/portfolio/" + title +".json");
            FileOutputStream to = new FileOutputStream("data/" + "index.json");
            byte[] buffer = new byte[4096];
            int byteRead;
            while( (byteRead = from.read(buffer))!=-1){
                to.write(buffer, 0, byteRead);
            }
            from.close();
            to.close();
            System.out.println("JSON file copied successfully");
        }
        catch(Exception ee){
            System.err.println("Fail to copy the json");
        }
    }
    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser slideShowFileChooser = new FileChooser();
        slideShowFileChooser.setInitialDirectory(new File(PATH_PORTFOLIO));
        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
		PortfolioModel portfolioToLoad = ui.getPortfolio();
                
                portfolioIO.loadPortfolio(portfolioToLoad, selectedFile.getAbsolutePath());
//                ui.reloadPortfolioPane();
              //  portfolioToLoad.setSelectedPage(null);
                saved = true;
                ui.updateToolbarControls(saved);
                ui.reloadPortfolioPane();
            } catch (Exception e) {
//                ErrorHandler eH = ui.getErrorHandler();
//		eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
                System.out.println("Invalid json ");
            }
        }
    }

     /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * pose, or opening another pose, or exiting. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is retuned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        boolean saveWork = true; 

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            PortfolioModel model = ui.getPortfolio();
            portfolioIO.savePortfolio(model);
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
            return false;
        }
    return true;
    }

    public void handleSaveAs() {
        try{
      PortfolioModel portfolioToSave = ui.getPortfolio();
        FileSaverDialog dialog = new FileSaverDialog(ui);
        portfolioIO.savePortfolio(portfolioToSave);
        ui.updateToolbarControls(saved);
            ui.setSaveAsButtonAble();
            ui.reloadPortfolioPane();
        }
        catch
            (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
	    System.out.println("Cannot save the file");
        }
    }
}
  