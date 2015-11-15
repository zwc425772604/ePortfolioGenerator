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
 * @author McKilla Gorilla & _____________
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
}
    /**
     * This method starts the process of editing a new slide show. If a pose is
     * already being edited, it will prompt the user to save it first.
     */
//    public void handleNewSlideShowRequest() {
//        try {
//            // WE MAY HAVE TO SAVE CURRENT WORK
//            
//            boolean continueToMakeNew = true;
//            if (!saved) {
//                // THE USER CAN OPT OUT HERE WITH A CANCEL
//                continueToMakeNew = promptToSave();
//            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
//            if (continueToMakeNew) {
//                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
//                SlideShowModel slideShow = ui.getSlideShow();
//		slideShow.reset();
//                saved = false;
//
//                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
//                // THE APPROPRIATE CONTROLS
//                ui.updateToolbarControls(saved);
//
//		// MAKE SURE THE TITLE CONTROLS ARE ENABLED
//		ui.reloadTitleControls();		
//            }
//        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
//    }
//    }
//    /**
//     * This method lets the user open a slideshow saved to a file. It will also
//     * make sure data for the current slideshow is not lost.
//     */
//    public void handleLoadSlideShowRequest() {
//        try {
//            // WE MAY HAVE TO SAVE CURRENT WORK
//            boolean continueToOpen = true;
//            if (!saved) {
//                // THE USER CAN OPT OUT HERE WITH A CANCEL
//                continueToOpen = promptToSave();
//            }
//
//            // IF THE USER REALLY WANTS TO OPEN A POSE
//            if (continueToOpen) {
//                // GO AHEAD AND PROCEED MAKING A NEW POSE
//                promptToOpen();
//            }
//        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING);
//        }
//    }
//
//    /**
//     * This method will save the current slideshow to a file. Note that we already
//     * know the name of the file, so we won't need to prompt the user.
//     */
//    public boolean handleSaveSlideShowRequest() {
//        try {
//	    // GET THE SLIDE SHOW TO SAVE
//	    SlideShowModel slideShowToSave = ui.getSlideShow();
//	    
//            // SAVE IT TO A FILE
//            slideShowIO.saveSlideShow(slideShowToSave);
//
//            // MARK IT AS SAVED
//            saved = true;
//
//            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
//            // THE APPROPRIATE CONTROLS
//            ui.updateToolbarControls(saved);
//	    return true;
//        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
//	    return false;
//        }
//    }
//
//    /**
//     * This method shows the current slide show in a separate window.
//     */
//    public void handleViewSlideShowRequest() {
//	SlideShowViewer viewer = new SlideShowViewer(ui);
//	//viewer.startSlideShow();
//        SlideShowModel slideShow = ui.getSlideShow();
//	    
//            //Save the slide show every times the view button has been clicked
//        try{
//            slideShowIO.saveSlideShow(slideShow);
//        }
//        catch (IOException ioe) {
//           
//    }   
//        initDirectory();
//        Stage primaryStage = new Stage();
//        primaryStage.setWidth(1000);
//        primaryStage.setHeight(1000);
//        Scene scene = new Scene(new Group(),950,950);
//    
//	
//	
//
//    final WebView browser = new WebView();
//    final WebEngine webEngine = browser.getEngine();
//     FlowPane flowpane = new FlowPane();
//     
//     flowpane.getChildren().add(browser);
//     flowpane.setAlignment(Pos.CENTER);
//
//     
//        String title = slideShow.getTitle();
//
//        //load a local file
//        String path = ("sites/" + title + "/" + "index.html");
//        File file = new File(path);
//	try{
//	  URL fileURL = file.toURI().toURL();
//          //System.out.println(fileURL.toExternalForm());
//	    webEngine.load(fileURL.toExternalForm());
//        }
//        catch (Exception e){
//        
//        }
//        
//       scene.setRoot(flowpane);
//    
//    primaryStage.setScene(scene);
//    primaryStage.show();
//        viewer.startSlideShow();
//    }
//    
//    //This method create a new directory
//    public void initDirectory(){
//        //the slideshow
//        SlideShowModel slideShow = ui.getSlideShow();
//        String title = slideShow.getTitle();
//        
//        //create the slideshow directory of the name of the slideshow
//        File slideDir = new File("sites/" + title);
//        if (!slideDir.exists()){
//            slideDir.mkdirs();
//            System.out.println("slide show directory created successfully");
//            System.out.println(slideDir.getAbsolutePath());
//        }
//        else{
//            System.err.println("Slide Show Directory already existed");
//        }
//        
//        //create the css directory inside the slideshow directory
//        File cssDir = new File("sites/" + title + "/" + "css");
//            if (!cssDir.exists()){
//            cssDir.mkdirs();
//            System.out.println("CSS directory created successfully");
//            System.out.println(cssDir.getAbsolutePath());
//        }
//        else{
//            System.err.println("CSS Directory already existed");
//        }
//        
//        //create the javascript directory inside the slideshow directory    
//        File jsDir = new File("sites/" + title + "/" + "js");
//        if (!jsDir.exists()){
//            jsDir.mkdirs();
//            System.out.println("Javascript directory created successfully");
//        }
//        else{
//            System.err.println("JavaScript Directory already existed");
//        }
//        
//         //create the image directory inside the slideshow directory  
//        File imgDir = new File("sites/" + title + "/" + "img");
//        if (!imgDir.exists()){
//            imgDir.mkdirs();
//            System.out.println("Image directory created successfully");
//        }
//        else{
//            System.err.println("Image Directory already existed");
//        }
//        
//        //copy all the image in the slide show to the Image Directory
//       try{
//            for (int i = 0; i <slideShow.getSlides().size();i++){
//                Slide s = slideShow.getSlides().get(i);
//                String imgPath = s.getImagePath() + "/" + s.getImageFileName();
//            FileInputStream from = new FileInputStream(imgPath);
//            FileOutputStream to = new FileOutputStream(imgDir.getPath() + "/" + s.getImageFileName());
//            byte[] buffer = new byte[4096];
//            int byteRead;
//            while( (byteRead = from.read(buffer))!=-1){
//                to.write(buffer, 0, byteRead);
//            }
//            from.close();
//            to.close();
//            System.out.println("IMG file copied successfully");
//        }}
//        catch(Exception ee){
//            System.err.println("Fail to copy the image");
//        }
//       
//        //copy the HTML file to the site directory
//        try{
//            FileInputStream from = new FileInputStream("images/index.html");
//            FileOutputStream to = new FileOutputStream(slideDir.getPath() + "/" + "index.html");
//            byte[] buffer = new byte[4096];
//            int byteRead;
//            while( (byteRead = from.read(buffer))!=-1){
//                to.write(buffer, 0, byteRead);
//            }
//            from.close();
//            to.close();
//            System.out.println("HTML file copied successfully");
//        }
//        catch(Exception ee){
//            System.err.println("fail to copy the HTML file");
//        }
//     
//        //copy the Javascript File to the slideshow directory
//        try{
//            FileInputStream from = new FileInputStream("images/Slideshow.js");
//            FileOutputStream to = new FileOutputStream(jsDir.getPath() + "/" + "Slideshow.js");
//            byte[] buffer = new byte[4096];
//            int byteRead;
//            while( (byteRead = from.read(buffer))!=-1){
//                to.write(buffer, 0, byteRead);
//            }
//            from.close();
//            to.close();
//            System.out.println("Javascript file copied successfully");
//        }
//        catch(Exception ee){
//            System.err.println("fail to copy the Javascript file");
//        }
//        
//        //copy the CSS style to the slideshow Directory
//        try{
//            FileInputStream from = new FileInputStream("images/slideshow_style.css");
//            FileOutputStream to = new FileOutputStream(cssDir.getPath() + "/" + "slideshow_style.css");
//            byte[] buffer = new byte[4096];
//            int byteRead;
//            while( (byteRead = from.read(buffer))!=-1){
//                to.write(buffer, 0, byteRead);
//            }
//            from.close();
//            to.close();
//            System.out.println("CSS file copied successfully");
//        }
//        catch(Exception ee){
//            System.out.println("fail to copy the CSS file");
//        }
//        
//        //copy the JSON file to the site directory, change the json file name, so the JS can read all json
//        try{
//            FileInputStream from = new FileInputStream("data/slide_shows/" + title +".json");
//            FileOutputStream to = new FileOutputStream(slideDir.getPath() + "/" + "slideData.json");
//            byte[] buffer = new byte[4096];
//            int byteRead;
//            while( (byteRead = from.read(buffer))!=-1){
//                to.write(buffer, 0, byteRead);
//            }
//            from.close();
//            to.close();
//            System.out.println("JSON file copied successfully");
//        }
//        catch(Exception ee){
//            
//        }
//        
//        
//    
//    }
//    
//    
//     
//    
//
//     /**
//     * This method will exit the application, making sure the user doesn't lose
//     * any data first.
//     */
//    public void handleExitRequest() {
//        try {
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
//    }
//
//    /**
//     * This helper method verifies that the user really wants to save their
//     * unsaved work, which they might not want to do. Note that it could be used
//     * in multiple contexts before doing other actions, like creating a new
//     * pose, or opening another pose, or exiting. Note that the user will be
//     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
//     * to save their work and continue the other action (we return true to
//     * denote this), NO means don't save the work but continue with the other
//     * action (true is returned), CANCEL means don't save the work and don't
//     * continue with the other action (false is retuned).
//     *
//     * @return true if the user presses the YES option to save, true if the user
//     * presses the NO option to not save, false if the user presses the CANCEL
//     * option to not continue.
//     */
//    private boolean promptToSave() throws IOException {
//        // PROMPT THE USER TO SAVE UNSAVED WORK
//        boolean saveWork = true; 
//
//        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
//        if (saveWork) {
//            SlideShowModel slideShow = ui.getSlideShow();
//            slideShowIO.saveSlideShow(slideShow);
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
//
//    /**
//     * This helper method asks the user for a file to open. The user-selected
//     * file is then loaded and the GUI updated. Note that if the user cancels
//     * the open process, nothing is done. If an error occurs loading the file, a
//     * message is displayed, but nothing changes.
//     */
//    private void promptToOpen() {
//        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
//        FileChooser slideShowFileChooser = new FileChooser();
//        slideShowFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOWS));
//        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());
//
//        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
//        if (selectedFile != null) {
//            try {
//		SlideShowModel slideShowToLoad = ui.getSlideShow();
//                slideShowIO.loadSlideShow(slideShowToLoad, selectedFile.getAbsolutePath());
//                ui.reloadSlideShowPane();
//                saved = true;
//                ui.updateToolbarControls(saved);
//            } catch (Exception e) {
//                ErrorHandler eH = ui.getErrorHandler();
//		eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
//            }
//        }
//    }
//
//    /**
//     * This mutator method marks the file as not saved, which means that when
//     * the user wants to do a file-type operation, we should prompt the user to
//     * save current work first. Note that this method should be called any time
//     * the pose is changed in some way.
//     */
//    public void markFileAsNotSaved() {
//        saved = false;
//    }
//
//    /**
//     * Accessor method for checking to see if the current pose has been saved
//     * since it was last editing. If the current file matches the pose data,
//     * we'll return true, otherwise false.
//     *
//     * @return true if the current pose is saved to the file, false otherwise.
//     */
//    public boolean isSaved() {
//        return saved;
//    }
//    
//    
//}
//
//
