/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author lilin
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //para probar
        Graph g = new Graph(3);
        Warehouse w1 = new Warehouse("a");
        Warehouse w2 = new Warehouse("b");
        g.addVertex(w1);
        g.addVertex(w2);
        g.addArch(w1.getName(), w2.getName(), 2);
        g.addArch(w2.getName(), w1.getName(), 2);
        addWarehouseWindow w = new addWarehouseWindow(g);
        
    }
    
}
