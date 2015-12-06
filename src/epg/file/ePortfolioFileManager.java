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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public static String JSON_PAGE_HEADER = "page_header";
    public static String JSON_PAGE_FOOTER = "page_footer";
    public static String JSON_PAGE_LIST = "page_list";
    public static String JSON_PAGE_PARAGRAPH = "page_paragraph";
    public static String JSON_PAGE_VIDEO_PATH = "page_video";
    public static String JSON_VIDEO_FILE_NAME = "video_file_name";
     
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
    public void loadPortfolio(PortfolioModel portfolioToLoad, String jsonFilePath) throws IOException {
	// LOAD THE JSON FILE WITH ALL THE DATA/	
        JsonObject json = loadJSONFile(jsonFilePath);

	// NOW LOAD THE COURSE
	portfolioToLoad.reset();
	portfolioToLoad.setTitle(json.getString(JSON_STUDENT_NAME));
	JsonArray jsonPagesArray = json.getJsonArray(JSON_PAGES);
	for (int i = 0; i < jsonPagesArray.size(); i++) {
	    JsonObject slideJso = jsonPagesArray.getJsonObject(i);
	    portfolioToLoad.addPage(slideJso.getString(JSON_PAGE_TITLE),
		    slideJso.getString(JSON_STUDENT_NAME));
          //  if (!slideJso.getString(JSON_PAGE_HEADER).isEmpty()){
//            if (!slideJso.getString(JSON_PAGE_HEADER).equals("[]")){
//                 portfolioToLoad.getPages().get(i).addHeader(slideJso.getString(JSON_PAGE_HEADER)
//                    .replace("[","").replace("]",""));
//                 }
            JsonArray Headerarray = slideJso.getJsonArray(JSON_PAGE_HEADER);
            for (int h = 0; h<Headerarray.size();h++){
                portfolioToLoad.getPages().get(i).addHeader(Headerarray.getString(h));
            }
            if (!slideJso.getString(JSON_PAGE_FOOTER).equals("[]")){
                portfolioToLoad.getPages().get(i).addFooter(slideJso.getString(JSON_PAGE_FOOTER)
                .replace("[", "").replace("]",""));
            }
            
            JsonArray paragraphArray = slideJso.getJsonArray(JSON_PAGE_PARAGRAPH);
            for (int p1 = 0; p1<paragraphArray.size();p1++){
                portfolioToLoad.getPages().get(i).addParagraph(paragraphArray.getString(p1));
            }
            
//            if (!slideJso.getString(JSON_PAGE_PARAGRAPH).equals("[]")){
//                portfolioToLoad.getPages().get(i).addParagraph(slideJso.getString(JSON_PAGE_PARAGRAPH)
//                .replace("]", ""));
//            }
//            
	
    }}

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
                .add(JSON_PAGE_HEADER, makeArray(page.getHeader()))
                .add(JSON_PAGE_FOOTER, page.getFooter())
                .add(JSON_PAGE_PARAGRAPH, makeArray(page.getParagraph()))
                .add(JSON_PAGE_LIST, makeArray(page.getListElement()))
                .add(JSON_IMAGE_PATH, makeArray(page.getImageList()))
                .add(JSON_IMAGE_FILE_NAME, makeArray(page.getImgFileNameList()))
                .add(JSON_PAGE_VIDEO_PATH, makeArray(page.getVideoPath()))
                .add(JSON_VIDEO_FILE_NAME, makeArray(page.getVidFileNameList()))
                .build();
	return jso;
    }
    private JsonArray makeArray(ArrayList<String> elements){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
	for (String x : elements) {

            jsb.add(x);
	   
	}
	JsonArray jA = jsb.build();
	return jA;
    }

    private JsonObject makeJSobject(String y){
        JsonObject jso = Json.createObjectBuilder()
                .add("",y)
                .build();
        return jso;
    }
    
   
}


