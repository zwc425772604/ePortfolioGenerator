/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.view.ePortfolioMakerView;
import eportfoliogenerator.LanguagePropertyType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;

/**
 *
 * @author weichaozhao
 */
public class PortfolioModel {
    ePortfolioMakerView ui;
    ObservableList<Page> pages;
    Page selectedPage;
    String title;
    
   public PortfolioModel(ePortfolioMakerView initUI){
       ui = initUI;
       pages = FXCollections.observableArrayList();
       reset();
       
   } 
   
   //ACCESSOR METHODS
   public boolean isPageSelected(){
       return selectedPage != null;
   }
   
   public boolean isSelectedPage(Page testPage) {
       return selectedPage == testPage;
   }
   
   public ObservableList<Page> getPages(){
       return pages;
   }
   
   public Page getSelectedPage() {
       return selectedPage;
   }
   
   public String getTitle(){
       return title;
   }
   
   //MUTATOR METHODS
   public void setSelectedPage(Page initSelectedPage){
       selectedPage = initSelectedPage;
   }
   
    public void setTitle(String initTitle) { 
	title = initTitle; 
    }
    
    public void addPage(Page p) {
	Page pageToAdd = p;
	pages.add(pageToAdd);
        
	ui.reloadPortfolioPane();
    }
    
    public void removeSelectedPage() {
	if (isPageSelected()) {
	    pages.remove(selectedPage);
	    selectedPage = null;
	    ui.reloadPortfolioPane();
	}
    }
    
    public void addHeader(String text){
        this.getSelectedPage().addHeader(text);
        ui.reloadPortfolioPane();
    }
    
    
    // SERVICE METHODS
    public void reset() {
	pages.clear();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
//	title = props.getProperty(LanguagePropertyType.DEFAULT_SLIDE_SHOW_TITLE);
	selectedPage = null;
    }

    
  
}
