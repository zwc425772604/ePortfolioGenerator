/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import static eportfoliogenerator.LanguagePropertyType.DEFAULT_PAGE_TITLE;
import static eportfoliogenerator.LanguagePropertyType.DEFAULT_STUDENT_NAME;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import properties_manager.PropertiesManager;

/**
 * This controller provides responses for the site edit toolbar,
 * which allows the user to add, remove, and select page.
 * 
 * @author weichaozhao
 */
public class ePortfolioEditController {
    
    // APP UI
    private ePortfolioMakerView ui;
    
    /**
     * This constructor keeps the UI for later.
     */
    public ePortfolioEditController(ePortfolioMakerView initUI) {
	ui = initUI;
    }
    
    public void processAddPageRequest(){
        
        PortfolioModel portfolio = ui.getPortfolio();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
        Page p = new Page("TITlE","STUDENT NAME");
	portfolio.addPage(p);
        ui.initPageButton(ui.getLeftSiteToolbar(),p.getTitle(),CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        ui.updatePageEditorControls();
        pageEditView pev = new pageEditView(p,ui);
        pev.addImage();
        
        System.out.println("create a new page");
    }
    
    public void processRemovePageRequest(){
        
	PortfolioModel portfolio = ui.getPortfolio();
        ui.removePageButton(portfolio.getSelectedPage().getTitle());
	portfolio.removeSelectedPage();
        
        ui.updatePageTitle();
	ui.reloadPortfolioPane();
    
    }
    
    public void processSelectPageRequest(){
        
}
}