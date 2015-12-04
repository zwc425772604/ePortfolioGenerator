package ssm;

/**
 * This class provides setup constants for initializing the application
 * that are NOT language dependent.
 * 
 * @author McKilla Gorilla & _____________
 * @co-author Weichao Zhao
 */
public class StartupConstants {
    public static String ENGLISH_LANG = "English";
    public static String CHINESE_LANG = "Chinese";
    public static String WINDOW_ICON = "happy5.png";
    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS

    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String UI_PROPERTIES_FILE_NAME_English = "properties_EN.xml";
    public static String UI_PROPERTIES_FILE_NAME_Finnish = "properties_CHINESE.xml";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";
    public static String PATH_DATA = "./data/";
    public static String PATH_SLIDE_SHOWS = PATH_DATA + "slide_shows/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_ICONS = PATH_IMAGES + "icons/";
    public static String PATH_SLIDE_SHOW_IMAGES = PATH_IMAGES + "slide_show_images/";
    public static String PATH_CSS = "/ssm/style/";
    public static String STYLE_SHEET_UI = PATH_CSS + "SlideShowMakerStyle.css";
    public static String LANGUAGE_SELECTION_STYLE_SHEET = PATH_CSS + "LanguageSelectionStyle.css";
    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS
    public static String ICON_WINDOW_LOGO = "SSM_Logo.png";
    public static String ICON_NEW_SLIDE_SHOW = "New.png";
    public static String ICON_LOAD_SLIDE_SHOW = "Load.png";
    public static String ICON_SAVE_SLIDE_SHOW = "Save.png";
    public static String ICON_VIEW_SLIDE_SHOW = "View.png";
    public static String ICON_EXIT = "Exit.png";
    public static String ICON_ADD_SLIDE = "Add.png";
    public static String ICON_REMOVE_SLIDE = "Remove.png";
    public static String ICON_MOVE_UP = "MoveUp.png";
    public static String ICON_MOVE_DOWN = "MoveDown.png";
    public static String ICON_PREVIOUS = "Previous.png";
    public static String ICON_NEXT = "Next.png";

    // UI SETTINGS
    public static String    DEFAULT_SLIDE_IMAGE = "DefaultStartSlide.png";
    public static int	    DEFAULT_THUMBNAIL_WIDTH = 200;
    public static int	    DEFAULT_SLIDE_SHOW_HEIGHT = 500;
    
    // CSS STYLE SHEET CLASSES
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON = "vertical_toolbar_button";
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON = "horizontal_toolbar_button";
    public static String    CSS_CLASS_SLIDE_SHOW_EDIT_VBOX = "slide_show_edit_vbox";
    public static String    CSS_CLASS_SLIDE_EDIT_VIEW = "slide_edit_view";
    public static String    CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW = "selected_slide_edit_view";
    public static String    CSS_CLASS_TEXTFIELD_STYLE = "textField_style";
    // UI LABELS
    public static String    LABEL_SLIDE_SHOW_TITLE = "slide_show_title";
    public static String    LABEL_LANGUAGE_SELECTION_PROMPT = "Select a Language:";
    public static String    OK_BUTTON_TEXT = "OK";
    public static String    CSS_CLASS_LANGUAGE_OPTION_VBOX = "language_option_vbox";
   // public static String    CSS_CLASS_LANGUAGE_BOX = "language_box";
    public static String    CSS_CLASS_OK_BUTTON = "OK_BUTTON";
    public static String    CSS_CLASS_LANGUAGE_SELECTION_SCREEN = "language_selection_screen";
    public static String    CSS_CLASS_LANG_COMBOBOX = "language_ComboBox";
}
