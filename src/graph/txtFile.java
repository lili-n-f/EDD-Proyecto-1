/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

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
}
