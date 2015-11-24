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

/**
 *
 * @author weichaozhao
 */


public class pageEditorController {
    private ePortfolioMakerView ui;
    
    public pageEditorController(ePortfolioMakerView initUI){
        ui = initUI;
    }

    public void processAddTextComponent() {
        addTextComponentDialog textDialog = new addTextComponentDialog();
	textDialog.showAndWait();
    }

    public void processAddImageComponent() {
        addImageComponentDialog imageDialog = new addImageComponentDialog();
	imageDialog.showAndWait();
    }

    public void processAddVideoComponent() {
        addVideoComponentDialog videoDialog = new addVideoComponentDialog();
        videoDialog.showAndWait();
    }
    
    
}
