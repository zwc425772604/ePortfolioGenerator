/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.file.ePortfolioFileManager;
import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class headerTextDialog extends Stage {
    
    private PortfolioModel portfolio;
    private ePortfolioMakerView ui;
    public headerTextDialog(ePortfolioMakerView initUI){
        ui = initUI;
        
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Header");
    dialog.setHeaderText("Enter some contents for the header");
    Optional<String> result = dialog.showAndWait();
    String entered = null;
    if (result.isPresent()){
        entered = result.get();
    }

    PortfolioModel model = ui.getPortfolio();
    Page p = model.getSelectedPage();
//    model.addHeader(entered);

    p.addHeader(entered);
   pageEditView pev = p.getPageEditView();
//    pageEditView pev = new pageEditView(p,ui);
   // pev.addHeader();
   
    pev.reloadPageEditView(p);
    pev.addHeaderToVBox(entered);
    //pev.updateHeader();
    
   // ui.reloadPortfolioPane();
    System.out.println(p.getHeader().get(0));
    
    }
}
