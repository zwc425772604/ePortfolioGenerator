/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliogenerator;

/**
 *
 * @author weichaozhao
 */
public class StartupConstants {
    
    public static String WINDOW_ICON = "happy5.png";
    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS

    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";
    public static String PATH_DATA = "./data/";
    public static String PATH_SLIDE_SHOWS = PATH_DATA + "slide_shows/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_ICONS = PATH_IMAGES + "icons/";
    
    public static String PATH_CSS = "/epg/style/";
    public static String STYLE_SHEET_UI = PATH_CSS + "ePortfolioMakerStyle.css";
   
    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS
    
    public static String ICON_NEW_PORTFOLIO = "New.png";
    public static String ICON_LOAD_PORTFOLIO = "Load.png";
    public static String ICON_SAVE_PORTFOLIO = "Save.png";
    public static String ICON_EXPORT_PORTFOLIO = "View.png";
    public static String ICON_EXIT = "Exit.png";
    public static String ICON_ADD_PAGE = "Add.png";
    public static String ICON_REMOVE_PAGE = "Remove.png";
    

    // UI SETTINGS
    
    public static int	    DEFAULT_THUMBNAIL_WIDTH = 200;
    public static int	    DEFAULT_SLIDE_SHOW_HEIGHT = 500;
    
    // CSS STYLE SHEET CLASSES
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON = "vertical_toolbar_button";
    public static String    CSS_CLASS_SITE_EDIT_VBOX = "site_edit_vbox";
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON = "horizontal_toolbar_button";
}

