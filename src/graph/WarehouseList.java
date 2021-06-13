/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Clase asociada a una lista simple compuesta por nodos Warehouse
 * @author Ana Tovar
 */
public class WarehouseList{
    
    private Warehouse head;
    private Warehouse tail;
    private int size;
    
    /**
     * Constructor
     */
    public WarehouseList(){
    
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Destructor de la lista
     */
    public void destructor(){
        this.head = this.tail = null;
        this.size = 0;
    }
    
    //no sé si sea necesario!!!!!!!!!!!!!!!!!!!!!
    /**
     * Constructor con un nodo dado
     * @param n, nodo Warehouse con el que se desea instanciar la lista
     */
    /*
    public WarehouseList(Warehouse n){
        this.head = n;
        this.tail = n;
    }
    */
    
    
    
    /**
     * Método para verificar si la lista está vacía o no
     * @return booleano, false si no está vacñio, true si lo está
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    
    /**
     * Método para obtener el tamaño de la lista
     * @return número entero correspondiente al tamaño de la lista
     */
    public int getSize(){
        return this.size;
    }
    
    /**
     * Método para obtener el último nodo de la lista
     * @return la cola de la lista, el nodo elemento
     */
    public Warehouse getLast(){
        return this.tail;
    }
    
    /**
     * Método para cambiar el último nodo de la lista
     * @param n nuevo último nodo de la lista
     */
    public void setLast(Warehouse n){
        this.tail = n;
    }
    
    /**
     * Método para obtener el primer nodo de la lista
     * @return la cabeza de la lista
     */
    public Warehouse getFirst(){
        return this.head;
    }
    
    /**
     * Método para cambiar el primer nodo de la lista
     * @param n nuevo primer nodo de la lista
     */
    public void setFirst(Warehouse n){
        this.head = n;
    }
    
    /**
     * Método para añadir al inicio de la lista
     * @param n, nodo que se desea añadir
     */
    public void addFirst(Warehouse n){
        
        if (isEmpty()) {
            this.tail = this.head = n;
            this.head.setNext(null);
            this.tail.setNext(null);
        }else{
            n.setNext(this.head);
            this.head = n;      
        }
        this.size++;
    }
    
    /**
     * Método para añadir al final de la lista
     * @param n, nodo que se desea añadir
     */
    public void addLast(Warehouse n){
        if (isEmpty()) {
            this.head = this.tail = n;
            this.head.setNext(null);
            this.tail.setNext(null);
        } else {
            this.tail.setNext(n);
            this.tail = n; 
        }
        this.size++;
    }
    
    /**
     * Método que retorna el nodo en la posición de la lista pasada por parámetro.
     * @param position posición del nodo en la lista
     * @return el nodo en la posición pasada por parámetro de la lista
     * @author Liliana Nóbrega
     */
    public Warehouse getNode(int position){
        if (position>=0 && position<size){
            Warehouse aux = head;
            for (int i = 0; i < position; i++){
                aux = aux.getNext();
            }
            return aux;
        }
        return null;
    }
    
    /**
     *  Método para insertar un nodo en una posición pasada por parámetro en la lista
     * @param n nodo que se desea añadir a la lista
     * @param position posición en la que se quiere insertar el nodo en la lista
     * @author Liliana Nóbrega
     */
    public void insert(Warehouse n, int position){
        Warehouse aux = this.getNode(position);
        if (aux != null){ //si getNode devuelve null, el índice position es inválido
            if (position == 0){
                this.addFirst(n);
            }else{
                Warehouse prev = this.getNode(position-1);
                prev.setNext(n);
                n.setNext(aux);
                size++;
            }
        }
    }
    
    
    /**
     * Método void para eliminar el elemento que se encuentra en la primera posición de la lista
     */
    public void deleteFirst(){
        if (!isEmpty()){
            if(this.size>1){
                this.head = this.head.getNext();
                this.size--;
            }else{
                this.destructor();
            }
        }
    }
    
    
    /**
     * Método void para eliminar el elemento que se encuentra en la última posición de la lista
     */
    public void deleteLast(){
        if (!isEmpty()){
            if (this.size >1){
                this.tail = this.getNode(size-2); //size-2 es el índice de la penúltima posición de la lista
                this.tail.setNext(null);
                size--;
            }else{
                this.destructor();
            }   
        }    
    }
    
    
    /**
     * Método para eliminar un nodo en una posición dada
     * @param i, posición d ela lista en la que se desea eliminar el nodo
     */
    public void delete(int i){
        if (!isEmpty()){
            if (i >= 0 && i< size){
            
                if(i == 0){
                    this.deleteFirst();
            
                } else if(i == (size- 1)){
                    this.deleteLast();
                
                } else{
                    Warehouse prev = this.getNode(i-1);
                    if (prev != null){
                        prev.setNext(prev.getNext().getNext());
                        size--;
                    }
                }
            
            }
        }
    }
}