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
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author weichaozhao
 */
public class paragraphTextDialog extends Stage{
        private ePortfolioMakerView ui;

    public paragraphTextDialog(ePortfolioMakerView initUI){
        ui = initUI;
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Paragraph Component");
        dialog.setHeaderText("Enter an paragraph of text and select a font for the paragraph");
        dialog.setResizable(true);
        
        Label label1 = new Label("Contents:");
        TextField text1 = new TextField();
        Label label2 = new Label("width");
        TextField text2 = new TextField();
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2,2);
        dialog.getDialogPane().setContent(grid);
        
        ButtonType buttonTypeOK = new ButtonType("Okay", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOK);
        
        Platform.runLater(() -> text1.requestFocus());
        dialog.setResultConverter(dialogButton ->{
            if (dialogButton == buttonTypeOK){
                return text1.getText() + " " + text2.getText();
            }
            return null;
        });
        
        Optional<String> result = dialog.showAndWait();
//        dialog.getDialogPane().lookupButton(buttonTypeOK).setOnInputMethodTextChanged(e ->{
//            System.out.println(result.get());
//        });
//        dialog.showAndWait().ifPresent(response ->{
//            if(response == ButtonType.OK){
//                
//            }
//        });
        String content = null;
        if( result.isPresent()){
         System.out.println(text1.getText() + "" + text2.getText());
         content = text1.getText();
    };
    
         
         PortfolioModel model = ui.getPortfolio(); //get all the page associate with the portfolio
         Page p = model.getSelectedPage();  //return the selected page
         p.addParagraph(content);        //add the text for paragraph to the selected page
         pageEditView pev = p.getPageEditView();   //load the corresponding pageEditView
         pev.reloadPageEditView(p);
         pev.addParagraphToVBox(content);      //add the text to the pageEditView
    }
}
