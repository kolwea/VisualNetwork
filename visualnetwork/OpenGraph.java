/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class OpenGraph {
        
    private Stage stage;
    
    public OpenGraph(){
        
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public Graph open(){
        System.out.println("Starting Open!");
        File openFile = handleFile();
        System.out.println("File HANDLED!");
        Graph done = null;
        try {
            FileInputStream fI = new FileInputStream(openFile);
            ObjectInputStream objIn = new ObjectInputStream(fI);
            
            done = (Graph)objIn.readObject();
            objIn.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenGraph.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpenGraph.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OpenGraph.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            if(done != null)
                return done;
            
            System.out.println("File not opened bro...");    
            return null;
        }
    }
    
    private File handleFile(){
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Open Graph");
        fileChoose.setInitialDirectory(new File("src/visualnetwork/GraphLibs"));
        return fileChoose.showOpenDialog(stage);
        
    }
}
