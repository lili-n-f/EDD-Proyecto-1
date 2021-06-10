/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import javax.swing.JOptionPane;

/**
 * Clase asociada a una lista simple compuesta por nodos Warehouse
 * @author Ana Tovar
 */
public class WarehouseList{
    
    private Warehouse head;
    private Warehouse tail;
    
    /**
     * Cosntructor
     */
    public WarehouseList(){
    
        this.head = null;
        this.tail = null;
    }
    
    /**
     * Constructor con un nodo dado
     * @param n, nodo Warehouse con el que se desea instanciar la lista
     */
    public WarehouseList(Warehouse n){
        this.head = n;
        this.tail = n;
    }
    
    /**
     * Método para verificar si la lista está vacía o no
     * @return booleano, false si no está vacñio, true si lo está
     */
    public boolean isEmpty(){
        return this.head == null;
    
    }
    
    /**
     * Método para recorrer la lista y obtener el tamaño de esta
     * @return número entero correspondiente al tamaño de la lista
     */
    public int size(){
        int i = 0;
                
        if (isEmpty()){
            return 0;
        }
        
        Warehouse aux = this.head;
            while(aux != null){
               aux = aux.getNext();
               i++;   
            }
        return i;
        
    }
    
    /**
     * Método para añadir al inicio de la lista
     * @param n, nodo que se desea añadir
     */
    public void addFirst(Warehouse n){
        if (isEmpty()) {
            this.head = n;
            this.tail = n;
            this.head.setNext(this.tail);
            this.tail.setNext(null);
        } else {
            n.setNext(this.head);
            this.head = n;
        }      
    }
    
    /**
     * Método para añadir al final de la lista
     * @param n, nodo que se desea añadir
     */
    public void addLast(Warehouse n){
        if (isEmpty()) {
            this.head = n;
            this.tail = n;
            this.head.setNext(this.tail);
            this.tail.setNext(null);
        } else {
            this.tail.setNext(n);
            this.tail = n; 
        }
    }
    
    /**
     * Método void para eliminar el elemento que se encuentra en la primera posición de la lista
     */
    public void deleteFirst(){
        if (isEmpty()){
        } else {
        Warehouse temp = this.head;
        this.head = this.head.getNext();
        temp.setNext(null);
        }
    }
    
    /**
     * Método void para eliminar el elemento que se encuentra en la última posición de la lista
     */
    public void deleteLast(){
        if (isEmpty()){
        } else {
        Warehouse aux = this.head;
        
        while(aux.getNext().getNext() != null){
            
            aux = aux.getNext();
            
        }
        
        Warehouse temp = aux.getNext();
        aux.setNext(null);
        this.tail = aux;
        temp.setNext(null);
        }
    }
    
    /**
     * Método para eliminar un nodo en una posición dada
     * @param i, posición d ela lista en la que se desea eliminar el nodo
     */
    public void delete(int i){
        if (isEmpty()){
        } else if(i == 0){
            this.deleteFirst();
        } else if(i == (size() - 1)){
            this.deleteLast();
        } else if(i < 0){
            this.delete(size() + i);
        } else if(i > size()){
            JOptionPane.showMessageDialog(null,"Error. Índice inválido.");
        } else{
            Warehouse aux = this.head;
            int counter = 0;
            while(counter < i - 1){
                aux = aux.getNext();
                counter++;
            }
            Warehouse temp = aux.getNext();
            aux.setNext(temp.getNext());
            temp.setNext(null);
        }
    }
    
    /**
     * Método para obtener el último elemento de la lista
     * @return la cola de la lista, el último elemento
     */
    public Warehouse getLast(){
        if(isEmpty()){
            return null;
        }
        return this.tail;
    }
    
    /**
     * Método para obtener el primer elemento de la lista
     * @return la cabeza de la lista
     */
    public Warehouse getFirst(){
        if(isEmpty()){
            return null;
        }
        return this.head;
    }
}
