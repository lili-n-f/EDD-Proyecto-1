/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Clase relacionada a archivos de texto
 * @author Liliana Nóbrega
 */
public class txtFile {
    
    /**
     * Función para guardar los datos del grafo en un archivo txt de nombre amazon.txt
     * @param g grafo del programa
     */
    public static void WriteTxt(Graph g){
        try{    
            String text = "Almacenes;\n";
            Warehouse w = g.warehouses.getFirst();
            for (int i = 0; i < g.warehouses.getSize(); i++) {
                text += "Almacen "+w.getName()+":\n";
                Product p = w.getStock().getFirst();
                for (int j = 0; j < w.getStock().getSize(); j++) {
                    text += p.getName()+","+p.getAmmount()+"\n";
                    p = p.getNext();
                }
                w = w.getNext();
            }
            text += "Rutas;\n";
            for (int i = 0; i < g.warehousesInGraph; i++) {
                for (int j = 0; j < g.warehousesInGraph; j++) {
                    if (g.adjMatrix[i][j] != 0){
                        Warehouse w1 = g.warehouses.getWarehouseWithID(i);
                        Warehouse w2 = g.warehouses.getWarehouseWithID(j);
                        text += w1.getName()+","+w2.getName()+","+g.adjMatrix[i][j]+"\n";
                    }
                }
            }
            PrintWriter pw = new PrintWriter("test\\amazon.txt");
            pw.print(text);
            pw.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar en archivo de texto.");
        }
    }
    /*No dio tiempo jaja
    public static Graph ReadTxt(){
        File file = new FIle("test\\amazon.txt");
        String line;
        String text = "";
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String[] info = br.toString().split("Rutas;");
            String [] warehouses = info[0].split(";");
            String [] routes = info[1].split("\n");
            
            String [] wh_inf;
            for(String wh : warehouses){
                if(!wh.equals("Almacenes")){
                    wh_inf = wh.split("\n");
                    for (String w : wh_inf){
                        if (w.contains(":")){
                            String name = w.split(" ")[1];
                            
                        }
                    }
                }
            }
            
        }catch{
        }
    
    }
    */
}       
