/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Clase asociada a los productos que se venden en cada almacén.
 * @author Liliana Nóbrega
 */
public class Product {
	private String name;
	private int ammount;
        private Product next;
	
    /**
     * Constructor de la clase Product
     * @param name es el nombre del producto
     * @param ammount es la cantidad disponible que se tiene de ese producto
     */
    public Product(String name, int ammount){
		this.name = name;
		this.ammount = ammount;
	}

    /**
     * Getter del atributo name: con él obtienes el nombre del producto.
     * @return el nombre del producto
     */
    public String getName(){
	return this.name;
	}

    /**
     * Setter del atributo name: con él puedes darle un nuevo nombre al producto.
     * @param newName nuevo nombre del producto
     */
    public void setName(String newName){
	this.name = newName;
	}

    /**
     * Getter del atributo ammount: con él obtienes la cantidad disponible del producto.
     * @return la cantidad disponible del producto
     */
    public int getAmmount(){
		return this.ammount;
	}

    /**
     * Setter del atributo ammount: con él puedes darle una nueva cantidad disponible al producto.
     * @param newAmmount
     */
    public void setAmmount(int newAmmount){
		this.ammount = newAmmount;
	}
    
    /**
     * Getter del atributo next: retorna el nodo al que apunta la instancia de Product
     * @return nodo Product al que apunta la instancia de Product
     */
    public Product getNext() {
        return next;
    }

    /**
     * Setter del atributo next: con él se puede cambiar el nodo al que apunta la instancia de Product
     * @param next nuevo nodo Product al que apunta la instancia de la clase Product 
     */
    public void setNext(Product next) {
        this.next = next;
    }

    /**
     * Método usado para sustraer de la cantidad disponible del producto la cantidad que fue vendida en el almacén.
     * @param ammountSold cantidad de unidades que se vendieron
     */
    public void sellProduct(int ammountSold){
		this.ammount -= ammountSold;
	}

    /**
     * Método usado para sumar a la cantidad disponible del producto la cantidad que fue añadida de dicho producto en el almacén al que pertenece.
     * @param ammountBought cantidad de unidades que se añadieron del producto en el almacén
     */
    public void buyProduct(int ammountBought){
		this.ammount += ammountBought; 
	}
    
    /**
     * Método que retorna la información del nodo producto (su nombre y cantidad)
     * @return string con el nombre y cantidad del producto
     */
    public String showInfo(){
        return this.name + " x" + this.ammount + "\n";
    }

}

