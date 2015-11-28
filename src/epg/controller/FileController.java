/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;


import epg.error.ErrorHandler;
import epg.file.ePortfolioFileManager;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import eportfoliogenerator.LanguagePropertyType;
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
//        ui.updateToolbarControls(saved);
    }
    
    public void handleNewPortfolioRequest(){
        boolean continueToMakeNew = true;
        if (continueToMakeNew) {
            // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
            PortfolioModel portfolio = ui.getPortfolio();
            portfolio.reset();
            saved = false;
            
            // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateToolbarControls(saved);
            
            // MAKE SURE THE TITLE CONTROLS ARE ENABLED
            
        }
    }
    
    public void handleLoadPortfolioRequest(){
        
    }
    
    public void handleSavePortfolioRequest(){
        
    }
    
    public void handleSaveAsPortfolioRequest(){
        
    }
    
    public void handleExportPortfolioRequest(){
        
    }
    
    public void handleExitRequest(){
//         try {
//            // WE MAY HAVE TO SAVE CURRENT WORK
//            boolean continueToExit = true;
//            if (!saved) {
//                // THE USER CAN OPT OUT HERE
//                continueToExit = promptToSave();
//            }
//
//            // IF THE USER REALLY WANTS TO EXIT THE APP
//            if (continueToExit) {
//                // EXIT THE APPLICATION
//                System.exit(0);
//            }
//        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
//        }
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
        
    }
  
}
  