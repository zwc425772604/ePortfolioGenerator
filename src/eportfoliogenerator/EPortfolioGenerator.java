/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliogenerator;

import epg.file.ePortfolioFileManager;
import epg.view.ePortfolioMakerView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

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
            String appTitle = "ePortfolio";
        
       ui.startUI(primaryStage, appTitle);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
