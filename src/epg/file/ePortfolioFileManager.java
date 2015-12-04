/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import epg.model.Page;
import epg.model.PortfolioModel;
import static eportfoliogenerator.StartupConstants.PATH_PORTFOLIO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author weichaozhao
 */
public class ePortfolioFileManager {
    
     public static String JSON_TITLE = "title";
    public static String JSON_SLIDES = "slides";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_CAPTION = "caption";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    
    public static String JSON_STUDENT_NAME = "student_name";
    public static String JSON_PAGES = "pages";
    public static String JSON_PAGE_TITLE = "page_title";
    
    public void savePortfolio(PortfolioModel portfolioToSave) throws IOException {
       
        
	StringWriter sw = new StringWriter();

	// BUILD THE PAGE ARRAY
	JsonArray pagesJsonArray = makeSlidesJsonArray(portfolioToSave.getPages());

	// NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
	JsonObject portfolioJsonObject = Json.createObjectBuilder()
		.add(JSON_STUDENT_NAME, portfolioToSave.getPages().get(0).getStudentName())
		.add(JSON_PAGES, pagesJsonArray)
		.build();

	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);

	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(portfolioJsonObject);
	jsonWriter.close();

        
	// INIT THE WRITER
	//String slideShowTitle = "" + portfolioToSave.getTitle();
        String fileName = portfolioToSave.getFileName();
	String jsonFilePath = PATH_PORTFOLIO + SLASH + fileName + JSON_EXT;
        OutputStream os = new FileOutputStream(jsonFilePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(portfolioJsonObject);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(jsonFilePath);
	pw.write(prettyPrinted);
	pw.close();
	System.out.println(prettyPrinted);
    }

    /**
     * This method loads the contents of a JSON file representing a slide show
     * into a SlideSShowModel objecct.
     *
     * @param slideShowToLoad The slide show to load
     * @param jsonFilePath The JSON file to load.
     * @throws IOException
     */
//    public void loadSlideShow(PortfolioModel slideShowToLoad, String jsonFilePath) throws IOException {
//	// LOAD THE JSON FILE WITH ALL THE DATA
//	JsonObject json = loadJSONFile(jsonFilePath);
//
//	// NOW LOAD THE COURSE
//	slideShowToLoad.reset();
//	slideShowToLoad.setTitle(json.getString(JSON_TITLE));
//	JsonArray jsonSlidesArray = json.getJsonArray(JSON_SLIDES);
//	for (int i = 0; i < jsonSlidesArray.size(); i++) {
//	    JsonObject slideJso = jsonSlidesArray.getJsonObject(i);
//	    slideShowToLoad.addSlide(slideJso.getString(JSON_IMAGE_FILE_NAME),
//		    slideJso.getString(JSON_IMAGE_PATH),
//		    slideJso.getString(JSON_CAPTION));
//	}
//    }

    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
	JsonObject json = loadJSONFile(jsonFilePath);
	ArrayList<String> items = new ArrayList();
	JsonArray jsonArray = json.getJsonArray(arrayName);
	for (JsonValue jsV : jsonArray) {
	    items.add(jsV.toString());
	}
	return items;
    }

    private JsonArray makeSlidesJsonArray(List<Page> pages) {
	JsonArrayBuilder jsb = Json.createArrayBuilder();
	for (Page page : pages) {
	    JsonObject jso = makeSlideJsonObject(page);
	    jsb.add(jso);
	}
	JsonArray jA = jsb.build();
	return jA;
    }

    private JsonObject makeSlideJsonObject(Page page) {
	JsonObject jso = Json.createObjectBuilder()
		.add(JSON_STUDENT_NAME, page.getStudentName())
		.add(JSON_PAGE_TITLE, page.getTitle())
		.build();
	return jso;
    }
}


