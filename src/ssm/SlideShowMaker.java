package ssm;


import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import static ssm.LanguagePropertyType.TITLE_WINDOW;
import static ssm.StartupConstants.CHINESE_LANG;
import static ssm.StartupConstants.ICON_WINDOW_LOGO;
import static ssm.StartupConstants.PATH_DATA;
import static ssm.StartupConstants.PATH_ICONS;
import static ssm.StartupConstants.PATH_IMAGES;
import static ssm.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static ssm.StartupConstants.WINDOW_ICON;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import ssm.view.LanguageSelectionDialog;
import ssm.view.SlideShowMakerView;

/**
 * SlideShowMaker is a program for making custom image slideshows. It will allow
 * the user to name their slideshow, select images to use, select captions for
 * the images, and the order of appearance for slides.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowMaker extends Application {
    // THIS WILL PERFORM SLIDESHOW READING AND WRITING
    SlideShowFileManager fileManager = new SlideShowFileManager();

    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    SlideShowMakerView ui = new SlideShowMakerView(fileManager);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // GET THE USER SELECTED LANGUAGE
	LanguageSelectionDialog langDialog = new LanguageSelectionDialog();
	langDialog.showAndWait();
	
	String language = langDialog.getSelectedLanguage();
	String languageCode = "EN";
	if (language.equals(CHINESE_LANG)) {
	    languageCode = "CHINESE";
	}
	
	// SET THE WINDOW ICON
	String imagePath = PATH_IMAGES + ICON_WINDOW_LOGO;
	File file = new File(imagePath);
	
	// GET AND SET THE IMAGE
	URL fileURL = file.toURI().toURL();
	Image windowIcon = new Image(fileURL.toExternalForm());
	primaryStage.getIcons().add(windowIcon);
	
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
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
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
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
     * This is where the application starts execution. We'll load the
     * application properties and then use them to build our user interface and
     * start the window in event handling mode. Once in that mode, all code
     * execution will happen in response to user requests.
     *
     * @param args This application does not use any command line arguments.
     */
    public static void main(String[] args) {
	launch(args);
    }
}
