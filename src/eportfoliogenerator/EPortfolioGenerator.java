/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliogenerator;

import epg.error.ErrorHandler;
import epg.file.ePortfolioFileManager;
import epg.view.ePortfolioMakerView;
import static eportfoliogenerator.LanguagePropertyType.TITLE_WINDOW;
import static eportfoliogenerator.StartupConstants.PATH_DATA;
import static eportfoliogenerator.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author weichaozhao
 */
public class EPortfolioGenerator extends Application {
    
    ePortfolioFileManager fileManager = new ePortfolioFileManager();
    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
   ePortfolioMakerView ui = new ePortfolioMakerView(fileManager);
    
    
    @Override
    public void start(Stage primaryStage) {
//       PropertiesManager props = PropertiesManager.getPropertiesManager();
//            String appTitle = "ePortfolio";
//         ui.startUI(primaryStage, appTitle);
        String languageCode = "EN";
         boolean success = loadProperties(languageCode);
        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(TITLE_WINDOW);

	    // NOW START THE UI IN EVENT HANDLING MODE
	    ui.startUI(primaryStage, appTitle);
	} // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
	else {
	    // LET THE ERROR HANDLER PROVIDE THE RESPONSE
	    ErrorHandler errorHandler = ui.getErrorHandler();
	    errorHandler.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING);
	    System.exit(0);
	}
    }
    public boolean loadProperties(String languageCode) {
        try {
	    // FIGURE OUT THE PROPER FILE NAME
	    String propertiesFileName = "properties_" + languageCode + ".xml";

	    // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
	    props.loadProperties(propertiesFileName, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_PROPERTIES_FILE_LOADING);
            return false;
        }        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
