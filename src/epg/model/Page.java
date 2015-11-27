/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.scene.control.TextArea;

/**
 *
 * @author weichaozhao
 */
public class Page {
    String title;
    String studentName;
    Component selectedComponent;
    
    public Page(String defaultTitle, String defaultStuName){
        title = defaultTitle;
        studentName = defaultStuName;
    }
    
    public String getTitle(){
        return title;
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
    
    public void addTextComponent(TextArea ta){
        
    }
    
}
