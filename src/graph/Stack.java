/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Clase asociada a la estructura de datos de pilas
 * @author Ana Tovar
 */
public class Stack {
    
    private Warehouse top;
    private Warehouse base;
    private int size;
    
    /**
     * Constructor
     */
    public Stack(){
    
        this.top = null;
        this.base = null;
        this.size = 0;
    }
    
    /**
     * Destructor
     */
    public void empty(){
        this.top = null;
        this.base = null;
        this.size = 0;
    }
    
    /**
     * Método para revisar si está vacío
     * @return booleano, false si no está vacío, true si está vacío
     */
    public boolean isEmpty(){
        return this.top == null;
    
    }
    
    /**
     * Método para apilar
     * @param name nombre del almacén que se apilará
     * @param stock lista de productos (objetos de la clase Product) que se pueden vender desde el almacén
     * @param ID identificación del nodo almacén (representa su posición en la matriz de adyacencia del grafo)
     */
    public void push(String name, ProductList stock, int ID){
        Warehouse n = new Warehouse(name, stock, ID);
        if (isEmpty()){
            this.top = n;
            this.base = n;
        } else {
            n.setNext(top);
            this.top = n;
        }
        size++;
    }
    
    /**
     * Método para desapilar
     */
    public void pop(){
        if (isEmpty()){
        } else if (size == 1) {
            this.empty();
        } else {
            top = top.getNext();
            size--;
        }
    }   
    
    /**
     * Método para obtener el primer nodo en la pila
     * @return
     */
    public Warehouse getTop(){
        return top;
    }
    
}
