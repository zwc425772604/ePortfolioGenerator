/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static eportfoliogenerator.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import java.io.File;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author weichaozhao
 */
public class videoSelectionController {
    
    private String videoPath;
    public videoSelectionController(){
        
    }
    
    public void processSelectVideo(Pane pane){
          FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOW_IMAGES));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("MPEG-4 files (*.mp4)", "*.m4p");
	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("FLV files (*.rmvb)", "*.flv");
	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.m4p)", "*.gif");
        FileChooser.ExtensionFilter vidFilter = new FileChooser.ExtensionFilter("Video Files", "*.mp4","*.wav");
	imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter,vidFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    String fileName = file.getName();
             videoPath = path + fileName;
//            Media media = new Media(videoPath);
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//	    MediaView mediaView = new MediaView(mediaPlayer);
//	    pane.getChildren().add(mediaView);
            File f = new File(videoPath);
            Media m = new Media(f.toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            MediaView mv = new MediaView(mp);
            pane.getChildren().add(mv);
            
	}
        
        }
    
    public String getVideoPath(){
        return videoPath;
    }
   }
    

