/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import epg.view.ePortfolioMakerView;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author weichaozhao
 */
public class FileSaverDialog extends Stage {
    
    
    
    private ePortfolioMakerView ui;
    public FileSaverDialog(ePortfolioMakerView initUI){
           ui = initUI;
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("File Name");
    dialog.setHeaderText("Enter the file name");
    Optional<String> result = dialog.showAndWait();
    String entered = null;
    if (result.isPresent()){
        entered = result.get();
    }
    ui.getPortfolio().setFileName(entered);
        
    }
}
