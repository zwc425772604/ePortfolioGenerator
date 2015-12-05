/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.model.Page;
import epg.model.PortfolioModel;
import epg.view.ePortfolioMakerView;
import epg.view.pageEditView;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_CANCEL_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_OK_BUTTON;
import static eportfoliogenerator.StartupConstants.CSS_CLASS_TEXTFIELD_STYLE;
import static eportfoliogenerator.StartupConstants.DIALOG_STYLE_SHEET;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class editHeaderDialog extends Stage{
    
      VBox vBox;
    Label contentsLabel;
    Button okButton;
    Button cancelButton;
    
    TextField contentsTextField;
    Label fontLabel;
    TextField fontTextField;
    
    private ePortfolioMakerView ui;
    public editHeaderDialog(ePortfolioMakerView initUI){
       ui = initUI;
       String x = ui.getPortfolio().getSelectedPage().getPageEditView().getSelectedComponentContent();
       TextInputDialog dialog = new TextInputDialog(x);
       dialog.setTitle("Header");
       //String x = ui.getPortfolio().getSelectedPage().getPageEditView().getSelectedComponentContent();
       dialog.setHeaderText("Change the contents for the header");
       Optional<String> result = dialog.showAndWait();
       String entered = null;
        
       if (result.isPresent()){
        entered = result.get();
    }
    
    PortfolioModel model = ui.getPortfolio();
    Page p = model.getSelectedPage();
    ArrayList<String> h = p.getHeader();
    System.out.println(h.get(0));
    //replace the old content with the new content
    int index = h.indexOf(x);
    h.set(index, entered);
    System.out.println(h.get(0));

    
   pageEditView pev = p.getPageEditView();
    
   
    pev.reloadPageEditView(p);
   // pev.addHeaderToVBox(entered);
    pev.updateHeaderToVBox(entered);
    
        
      
    }
}
