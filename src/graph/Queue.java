/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Clase asociada a la estructura de datos de colas
 * @author Ana Tovar
 */
public class Queue {
    
    private Warehouse head;
    private Warehouse tail;
    private int size;
    
    /**
     * Constructor
     */
    public Queue(){
    
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Destructor
     */
    public void empty(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Método para revisar si está vacío
     * @return booleano, false si no está vacío, true si lo está
     */
    public boolean isEmpty(){
        return this.head == null;
    
    }
    
    /**
     * Método para encolar
     * @param n, nodo que se desea encolar
     */
    public void inqueue(Warehouse n){
        if (isEmpty()){
            this.head = n;
            this.tail = n;
        } else {
            this.tail.setNext(n);
            this.tail = n;
        }
        size++;
    }
    
    /**
     * Método para desencolar
     */
    public void dequeue(){
        if (isEmpty()){
        } else if (size == 1) {
            this.empty();
        } else {     
            this.head = this.head.getNext();
            size--;
        }
    }  
    
    /**
     * Método para obtener el primer nodo en la cola
     * @return el nodo en la cabeza de la cola
     */
    public Warehouse getHead(){
        return head;
    }
    
}