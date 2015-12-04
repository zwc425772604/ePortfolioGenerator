/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class SlideShowWebViewer extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         primaryStage.setWidth(600);
     primaryStage.setHeight(600);
    Scene scene = new Scene(new Group());

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(browser);
//    browser.getEngine().setOnAlert((WebEvent<String> wEvent) -> {
//      System.out.println("Alert Event  -  Message:  " + wEvent.getData());
//    });

   //webEngine.load("http:/www.baidu.com");
    webEngine.load("file:///Users/weichaozhao/Downloads/SlideshowMaker/images/index.html");
   // webEngine.load("/Users/weichaozhao/Downloads/SlideshowMaker/images/test.html");
    scene.setRoot(scrollPane);

    primaryStage.setScene(scene);
    primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
