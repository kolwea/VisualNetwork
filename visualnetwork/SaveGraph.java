/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class SaveGraph {
    
    private Stage stage;
    
    public SaveGraph(){
        
    }
    
    public SaveGraph(Graph saveMe, String filename){
        
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void save(Graph saveMe, String filename){
        try {
            File saveFile = handleFile(filename);
            
            FileOutputStream fO = new FileOutputStream(saveFile);
            ObjectOutputStream fileOut = new ObjectOutputStream(fO);
            
            fileOut.writeObject(saveMe);
            fileOut.close();
            
            System.out.println("File saved.");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveGraph.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private File handleFile(String filename){
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Save Graph");
        fileChoose.setInitialFileName(filename);
        fileChoose.setInitialDirectory(new File("src/visualnetwork/GraphLibs"));

//        fileChoose.setSelectedExtensionFilter(
//                new ExtensionFilter("File","*.dile")
//        );
        return fileChoose.showSaveDialog(stage);
        
    }
    
}
