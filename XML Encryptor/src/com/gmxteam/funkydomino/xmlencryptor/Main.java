/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.xmlencryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;

/**
 *
 * @author guillaume
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        
        jfc.showOpenDialog(null);
        for(File f : jfc.getSelectedFiles()) {
        
            FileReader fr = new FileReader(f);
            
        
        }
        
    }
}
