/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import javax.swing.JOptionPane;
/**
 * Clase asociada a los almacenes, los cuales son los nodos del grafo.
 * @author Liliana Nóbrega
 */
public class Warehouse {

    private String name;
    private ProductList stock;
    private int ID;
    private Warehouse next;

    /**
     * Constructor de la clase Warehouse
     * @param name nombre del almacén (por ejemplo, A, B, C...)
     * @param stock lista de productos (objetos de la clase Product) que se pueden vender desde el almacén
     */
    public Warehouse(String name, ProductList stock){
            this.name = name;
            this.stock = stock;
            this.ID = -1;
            this.next = null;
    }
    
    
    
    /**
     * Constructor de la clase Warehouse
     * @param name nombre del almacén
     */
    public Warehouse(String name){
            this.name = name;
            this.stock = new ProductList();
            this.ID = -1;
    }

    /**
     * Getter del atributo name: con él obtienes el nombre del almacén.
     * @return nombre del almacén
     */
    public String getName(){
            return this.name;
    }

    /**
     * Setter del atributo name: con él puedes darle un nuevo nombre al almacén.
     * @param newName nuevo nombre que se le da al almacén
     */
    public void setName(String newName){
            this.name = newName;
    }

      /**
     * Método para obtener el siguiente nodo al que apunta
     * @return el siguiente nodo al que apunta
     */
    public Warehouse getNext() {
        return next;
    }

    /**
     * Método para cambiar el siguiente nodo al que apunta
     * @param next nuevo nodo al que apuntará el almacén
     */
    public void setNext(Warehouse next) {
        this.next = next;
    }
    
    
    /**
     * Getter del atributo stock: con él obtienes la lista de productos que se pueden vender desde el almacén.
     * @return lista de productos que se pueden vender desde el almacén
     */
    public ProductList getStock(){
            return this.stock;
    }

    /**
     * Setter del atributo stock: con él puedes darle una nueva lista de productos que se pueden vender al almacén.
     * @param newStock nueva lista de productos que se pueden vender al almacén
     */
    public void setStock(ProductList newStock){
            this.stock = newStock;
    }

    /**
     * Getter del atributo ID: con él obtienes la identificación del almacén, es decir, el índice del almacén en la matriz de adyacencia del grafo.
     * @return identificación del almacén
     */
    public int getID(){
            return this.ID;
    }

    /**
     * Setter del atributo ID: con él puedes darle una nueva identificación al almacén.
     * @param newID nueva identificación que tendrá el almacén
     */
    public void setID(int newID){
            this.ID = newID;
    }
    
   
    /**
     * Método que retorna un string con el nombre y cantidad de cada producto en el stock del almacén (si es que tiene stock).
     * @return un string con nombre y cantidad de cada producto del stock o un string vacío si el almacén no tiene stock (lista de productos está vacía).
     */
    public String showStock(){
        String warehouseStock = "";
        if (!this.stock.isEmpty()){
            warehouseStock += "Almacén " + this.name + "\n";
            warehouseStock += this.stock.showProducts();
        }
        return warehouseStock;
    }
    
    
    
  
    
}

