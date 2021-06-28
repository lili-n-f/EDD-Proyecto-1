/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Liliana Nóbrega
 * @param <T> tipo de nodo
 */
public abstract class List<T>{
    
    protected T head;
    protected T tail;
    protected int size;
    
    /**
     * Constructor
     */
    public List(){
    
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
    
    
    /**
     * Método para verificar si la lista está vacía o no
     * @return booleano, false si no está vacío, true si lo está
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
     * @return la cola de la lista, el último nodo
     */
    public T getLast(){
        return this.tail;
    }
    
    /**
     * Método para cambiar el último nodo de la lista
     * @param n nuevo último nodo de la lista
     */
    public void setLast(T n){
        this.tail = n;
    }
    
    /**
     * Método para obtener el primer nodo de la lista
     * @return la cabeza de la lista
     */
    public T getFirst(){
        return this.head;
    }
    
    /**
     * Método para cambiar el primer nodo de la lista
     * @param n nuevo primer nodo de la lista
     */
    public void setFirst(T n){
        this.head = n;
    }
    
    /**
     * Método abstracto implementada en cada subclase para añadir al inicio de la lista
     * @param n, nodo que se desea añadir
     */
    public abstract void addFirst(T n);
    
    /**
     * Método abstracto implementada en cada subclase para añadir al final de la lista
     * @param n, nodo que se desea añadir
     */
    public abstract void addLast(T n);
    
    /**
     * Método abstracto implementada en cada subclase que retorna el nodo en la posición de la lista pasada por parámetro.
     * @param position posición del nodo en la lista
     * @return el nodo en la posición pasada por parámetro de la lista
     * @author Liliana Nóbrega
     */
    public abstract T getNode(int position);
    
    /**
     * Método abstracto implementada en cada subclase para insertar un nodo en una posición pasada por parámetro en la lista
     * @param n nodo que se desea añadir a la lista
     * @param position posición en la que se quiere insertar el nodo en la lista
     * @author Liliana Nóbrega
     */
    public abstract void insert(T n, int position);
    
    
    /**
     * Método abstracto implementada en cada subclase para eliminar el elemento que se encuentra en la primera posición de la lista
     */
    public abstract void deleteFirst();
    
    
    /**
     * Método abstracto implementada en cada subclase para eliminar el elemento que se encuentra en la última posición de la lista
     */
    public abstract void deleteLast();
    
    
    /**
     * Método abstracto implementada en cada subclase para eliminar un nodo en una posición dada
     * @param i, posición d ela lista en la que se desea eliminar el nodo
     */
    public abstract void delete(int i);
}
