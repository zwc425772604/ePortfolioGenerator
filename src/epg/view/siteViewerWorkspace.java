/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import java.io.File;
import java.net.URL;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class siteViewerWorkspace extends Stage {
    
    
    public siteViewerWorkspace(){
         Stage primaryStage = new Stage();
        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);
        Scene scene = new Scene(new Group(),950,950);
    
	
	

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
}
