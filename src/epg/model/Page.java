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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
     
    
    ArrayList<String>imagePath;
    ArrayList<String>imageFileName;
    ArrayList<Integer>imageWidth;
    ArrayList<Integer>imageHeight;
    ArrayList<String>imageLayout;
    ArrayList<String>imageCaption;
   
    ArrayList<String> list;
    
    ArrayList<String> video;
    ArrayList<String> videoFileNameList;
    ArrayList<Integer> videoWidth;
    ArrayList<Integer> videoHeight;
     String bannerImagePath;
    String bannerFileName;
    int bannerWidth;
    int bannerHeight;
    
    String imgP;
    String imgF;
    
    
    public Page(String defaultTitle, String defaultStuName){
        title = defaultTitle;
        studentName = defaultStuName;
        imgP = "";
        imgF = "";
        footer = "";
        bannerImagePath = "";
        bannerFileName = "";
        header = new ArrayList<String>();
        paragraph = new ArrayList<String>();
        imagePath = new ArrayList<String>();
        imageFileName = new ArrayList<String>();
        imageWidth = new ArrayList<Integer>();
        imageHeight = new ArrayList<Integer>();
        imageLayout= new ArrayList<String>();
        imageCaption = new ArrayList<String>();
        
        list = new ArrayList<String>();
        video = new ArrayList<String>();
       
        videoFileNameList = new ArrayList<String>();
        videoWidth = new ArrayList<Integer>();
        videoHeight = new ArrayList<Integer>();
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
    
   
    public void addHeader(String text){
        header.add(text);
    }
    public ArrayList getHeader(){
        return header;
    }
    public void addBannerImage(String path, String fileName
                     ,int width, int height ){
         bannerImagePath = path;
         bannerFileName = fileName;
         bannerWidth = width;
         bannerHeight = height;
       // this.getPageEditView().addBannerImageToVBox(path+fileName, width, height);
    }
    public String getBannerPath(){
        return bannerImagePath;
        
    }
    public String getBannerName(){
        return bannerFileName;
    }
    public int getBannerWidth(){
        return bannerWidth;
    }
    public int getBannerHeight(){
        return bannerHeight;
    }
   
    
    
    public void addParagraph(String text){
        paragraph.add(text);
        this.getPageEditView().reloadPageEditView(this);
    }
    
    public ArrayList getParagraphContent(){
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
    
    public void addImage(String path,String fileName, int width,
                         int height,String layout,String caption){
        imagePath.add(path);
        imageFileName.add(fileName);
        imageWidth.add(width);
        imageHeight.add(height);
       
    }
    public ArrayList<String> getImagePath(){
        return imagePath;
    }
    public void adddImagePath(String x){
        imagePath.add(x);
    }
    public ArrayList<String> getImageFileName(){
        return imageFileName;
    }
    public ArrayList<Integer> getImageWidth(){
        return imageWidth;
    }
    public ArrayList<Integer> getImageHeight(){
        return imageHeight;
    }
    public ArrayList<String> getImageLayout(){
    return imageLayout;
    }
    public ArrayList<String> getImageCaption(){
        return imageCaption;
    }
   
    
    
    
    
    public void addVideoFileName(String name){
        videoFileNameList.add(name);
    }
    public ArrayList<String> getVidFileNameList(){ //the file name
        return videoFileNameList;
    }
    
    public void addVideo(String path,String fileName, int width, int height){ //the path
        video.add(path);
        videoFileNameList.add(fileName);
        videoWidth.add(width);
        videoHeight.add(height);
    }
    public ArrayList<String> getVideoPath(){
        return video;
    }
    
    public void addVideoWidth(int width){
        videoWidth.add(width);
    }
    public ArrayList<Integer> getVideoWidth(){
        return videoWidth;
    }
    public void addVideoHeight(int height){
        videoHeight.add(height);
    }
    public ArrayList<Integer> getVideoHeight(){
        return videoHeight;
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

    public void setImage(String imagePath, String imageFileName) {
         imgP =imagePath;
         imgF = imageFileName;
    }
    public String getImgP(){
        return imgP;
    }
    public String getImgF(){
        return imgF;
    }

    

    
}
