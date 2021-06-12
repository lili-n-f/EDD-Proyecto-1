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
    private Product[] stock;
    private int ID;
    private Warehouse next;

    /**
     * Constructor de la clase Warehouse
     * @param name nombre del almacén (por ejemplo, A, B, o C)
     * @param stock array de productos (objetos de la clase Product) que se pueden vender desde el almacén
     */
    public Warehouse(String name, Product[] stock){
            this.name = name;
            this.stock = stock;
            this.ID = -1;
    }
    
    public Warehouse(String name){
            this.name = name;
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
     * Getter del atributo stock: con él obtienes el array de productos que se pueden vender desde el almacén.
     * @return array de productos que se pueden vender desde el almacén
     */
    public Product[] getStock(){
            return this.stock;
    }

    /**
     * Setter del atributo stock: con él puedes darle un nuevo array de productos que se pueden vender al almacén.
     * @param newStock nuevo array de productos que se pueden vender al almacén
     */
    public void setStock(Product[] newStock){
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
     * Método que permite añadir un nuevo producto al array de productos que se pueden vender en el almacén.
     * @param productName nombre del producto a añadir
     * @param productAmmount cantidad disponible del producto a añadir
     */
    public void addProductToStock(String productName, int productAmmount){ //esto es para la parte de gestión de stock!!
            boolean isNewProduct = true;
            for (Product product : this.stock){
                    if (product.getName().equals(productName)){
                            JOptionPane.showMessageDialog(null, "El producto que se intenta añadir ya se encuentra en el almacén.");
                            isNewProduct = false;
                            break;
                    }
            }
            if (isNewProduct){
                    Product newProduct = new Product(productName, productAmmount);
                    Product[] newStock = new Product[this.stock.length+1];
                    int i = 0;
                    for (; i < this.stock.length; i++){
                            newStock[i] = this.stock[i];
                    }
                    newStock[i] = newProduct;
                    this.setStock(newStock);
                    JOptionPane.showMessageDialog(null, "El producto fue añadido con éxito.");
            }
    }
    
    
    /**
     * Método para obtener el siguiente nodo al que apunta
     * @return
     */

    public Warehouse getNext() {
        return next;
    }

    /**
     * Método para seleccionar el siguiente nodo al que apunta
     * @param next
     */
    public void setNext(Warehouse next) {
        this.next = next;
    }
    
}

