/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.view.pageEditView;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

/**
 *
 * @author weichaozhao
 */
public class Page {
    String title;
    String studentName;
    String footer;
    Component selectedComponent;
    ArrayList<String>header;
    ArrayList<String>paragraph;
    pageEditView p;
     String imageFileName;
    String imagePath;
    ArrayList<String>image;//whole image path
    ArrayList<String>imageFileNameList;
    ArrayList<String> list;
    ArrayList<String> video;
    ArrayList<String> videoFileNameList;
    
    public Page(String defaultTitle, String defaultStuName){
        title = defaultTitle;
        studentName = defaultStuName;
        
        footer = "";
        header = new ArrayList<String>();
        paragraph = new ArrayList<String>();
        image = new ArrayList<String>();
        imageFileNameList = new ArrayList<String>();
        list = new ArrayList<String>();
        video = new ArrayList<String>();
        videoFileNameList = new ArrayList<String>();
    }
    
    public String getTitle(){
        return title;
    }
    public pageEditView getPageEditView(){
        return p;
    }
    public void setPageEditView(pageEditView testPEV){
        p = testPEV;
    }
    
    public String getStudentName(){
        return studentName;
}
    public void setTitle(String initTitle){
        title = initTitle;
    }   
    
    public void setStudentName(String initName){
        studentName = initName;
    }
    
    public void addTextComponent(String st){
        
    }
    public void addHeader(String text){
        header.add(text);
    }
    public ArrayList getHeader(){
        return header;
    }
   
    
    public void addParagraph(String text){
        paragraph.add(text);
        
    }
    
    public ArrayList getParagraph(){
        return paragraph;
    }
    
    public void addElementToTheList(ObservableList<String> input){
        for (String x : input){
            list.add(x);
        }
    }
    public void updateListElement(ObservableList<String> input){
        list.clear();
        for (String x : input){
            list.add(x);
        }
    }
    public ArrayList<String> getListElement(){
        return list;
    }
    
    public void addImage(String path){
        image.add(path);
    }
    public ArrayList getImageList(){
        return image;
    }
    public void addImageFileName(String name){
        imageFileNameList.add(name);
    }
    public ArrayList getImgFileNameList(){
        return imageFileNameList;
    }
    public void addVideoFileName(String name){
        videoFileNameList.add(name);
    }
    public ArrayList getVidFileNameList(){
        return videoFileNameList;
    }
    
    public void addVideo(String path){
        video.add(path);
    }
    public ArrayList getVideoPath(){
        return video;
    }
    
    public void setImage(String initPath, String initFileName) {
	imagePath = initPath;
	imageFileName = initFileName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageFileName() {
        return imageFileName;
    }
    
    public void addFooter(String x){
        footer = x;
    }
    
    public String getFooter(){
        return footer;
    }
    public void setFooter(String x){
        footer = x;
    }
}
