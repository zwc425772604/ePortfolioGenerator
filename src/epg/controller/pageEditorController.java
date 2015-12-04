/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.dialog.addImageComponentDialog;
import epg.dialog.addTextComponentDialog;
import epg.dialog.addVideoComponentDialog;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import epg.view.siteViewerWorkspace;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */


public class pageEditorController {
    private ePortfolioMakerView ui;
    siteViewerWorkspace siteViewer;
    //private pageEditView pageUI;
    public pageEditorController(ePortfolioMakerView initUI){
        ui = initUI;
//        pageUI = initPageUI;
    }
    
    

    public void processAddTextComponent() {
        addTextComponentDialog textDialog = new addTextComponentDialog(ui);
//	textDialog.showAndWait();
        textDialog.show();
    }

    public void processAddImageComponent() {
        addImageComponentDialog imageDialog = new addImageComponentDialog(ui);
	imageDialog.showAndWait();
    }

    public void processAddVideoComponent() {
        addVideoComponentDialog videoDialog = new addVideoComponentDialog(ui);
        videoDialog.showAndWait();
    }

    public void processSiteViewer() {
       siteViewer = new siteViewerWorkspace(ui);
//       
    }

    public void processPageEditor() {
      //  System.out.println("close");
       ui.setToolbarVisible();
       siteViewer.closeWindow();
//       siteViewer.getScene().getWindow().hide();
//        Stage stage = (Stage) siteViewer.getScene().getWindow();
//        stage.hide();
//       System.out.println("close");
//       ui.setToolbarVisible();
    }

    public void processRemoveComponent() {
        ui.getPortfolio().getSelectedPage().getPageEditView().removeSelectingComponent();
        ui.reloadPortfolioPane();
        ui.getPortfolio().getSelectedPage().getPageEditView().reloadPageEditView(ui.getPortfolio().getSelectedPage());
    }
    
    
}
