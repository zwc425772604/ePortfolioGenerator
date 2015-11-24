/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;


import epg.file.ePortfolioFileManager;
import epg.view.ePortfolioMakerView;
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
        
    }
    
    public void handleSwitchWorkspaceRequest(){
        
    }
  
}
  