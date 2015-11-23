/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.dialog.addTextComponentDialog;
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
    
    
}
