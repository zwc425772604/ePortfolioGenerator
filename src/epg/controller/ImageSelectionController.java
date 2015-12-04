package epg.controller;

import epg.model.Page;
import epg.view.pageEditView;
import java.io.File;
import javafx.stage.FileChooser;
import static eportfoliogenerator.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author McKilla Gorilla & _____________
 */
public class ImageSelectionController {
    
    private String imagePath;
    private String imageFileName;
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public ImageSelectionController() {    }
    
    /**
     * This function provides the response to the user's request to
     * select an image.
     * 
     * 
     */
    public void processSelectImage(Page pageToEdit, pageEditView view) {
//	FileChooser imageFileChooser = new FileChooser();
//	
//	// SET THE STARTING DIRECTORY
//	imageFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOW_IMAGES));
//	
//	// LET'S ONLY SEE IMAGE FILES
//	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
//	imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);
//	
	// LET'S OPEN THE FILE CHOOSER
//	File file = imageFileChooser.showOpenDialog(null);
//        
//	if (file != null) {
//	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
//	    String fileName = file.getName();
//            Image img = new Image(path);
//            pageToEdit.setImage(path,fileName);
//          //  view.updatePageImage();
//	   // view.setImage(img);
//	    
//	}
        System.out.println("file choose method is called");
        try{
            FileChooser imageFileChooser = new FileChooser();
            imageFileChooser.setTitle("choose a  file");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
            imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter,gifFilter);
            File file = imageFileChooser.showOpenDialog(null);
            if (file != null){
               // System.out.println(file);
//                String img = file.toURI().toURL().toString();
//                System.out.println(img);
                
                //this print out the imageName.jpg
                //imagePath = file.getPath().substring(file.getPath().indexOf(file.getName())); 
               // System.out.println(imagePath);
                
            imagePath = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    imageFileName = file.getName();
//            System.out.println("path" + path);
//            System.out.println("fileName" + fileName);
            pageToEdit.setImage(imagePath, imageFileName);
            
//                String path = file.getPath();
//                System.out.println(path);
                
                
            }
            
        }
        catch (Exception e){
            System.out.println("wrong path ");
        }
        
        
       
    }
    
    public String getImagePath(){
            return imagePath;
        }
    public String getImageFileName(){
        return imageFileName;
    }
   
}
