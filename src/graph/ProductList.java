/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Clase asociada a una lista simple compuesta por nodos Product
 * @author Liliana Nóbrega y Ana Tovar
 */
public class ProductList extends List<Product> {
    
    
    /**
     * Método para añadir al inicio de la lista
     * @param n, nodo que se desea añadir
     */
    @Override
    public void addFirst(Product n){
        
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
    @Override
    public void addLast(Product n){
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
    @Override
    public Product getNode(int position){
        if (position>=0 && position<size){
            Product aux = head;
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
    @Override
    public void insert(Product n, int position){
        Product aux = this.getNode(position);
        if (aux != null){ //si getNode devuelve null, el índice position es inválido
            if (position == 0){
                this.addFirst(n);
            }else{
                Product prev = this.getNode(position-1);
                prev.setNext(n);
                n.setNext(aux);
                size++;
            }
        }
    }
    
    
    /**
     * Método void para eliminar el elemento que se encuentra en la primera posición de la lista
     */
    @Override
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
    @Override
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
    @Override
    public void delete(int i){
        if (!isEmpty()){
            if (i >= 0 && i< size){
            
                if(i == 0){
                    this.deleteFirst();
            
                } else if(i == (size- 1)){
                    this.deleteLast();
                
                } else{
                    Product prev = this.getNode(i-1);
                    if (prev != null){
                        prev.setNext(prev.getNext().getNext());
                        size--;
                    }
                }
            
            }
        }
    }
    
}
