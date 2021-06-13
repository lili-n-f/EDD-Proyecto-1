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
public class WarehouseList extends List<Warehouse>{
    
    
    /**
     * Método para añadir al inicio de la lista
     * @param n, nodo que se desea añadir
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
                    Warehouse prev = this.getNode(i-1);
                    if (prev != null){
                        prev.setNext(prev.getNext().getNext());
                        size--;
                    }
                }
            
            }
        }
    }
    
    /**
     * Método que devuelve el almacén con el nombre pasado por parámetro
     * @param name nombre del almacén que se busca
     * @return el nodo, si encuentra al almacén, o null si el almacén con el nombre name no existe en la lista.
     */
    public Warehouse getWarehouse(String name){
        Warehouse aux = this.head;
        while (aux != null){ //si la lista estuviera vacía, head == null, por tanto aux == null y no se entraría en el ciclo. si la lista no está vacía, si aux llega a ser null significa que ya recorrió toda la lista y NO consiguió a un nodo con el name pasado por parámetro.
            if (aux.getName().equals(name)){
                return aux;
            }
            aux = aux.getNext();
        }
        return null;
    
    }
    
    /**
     * Método que devuelve el almacén con el ID igual al índice i pasado por parámetro
     * @param i ID del almacén que se busca
     * @return el nodo, si encuentra al almacén, o null si el almacén con el ID i no existe en la lista.
     */
    public Warehouse getWarehouseWithID(int i){
        Warehouse aux = this.head;
        while (aux != null){ //si la lista estuviera vacía, head == null, por tanto aux == null y no se entraría en el ciclo. si la lista no está vacía, si aux llega a ser null significa que ya recorrió toda la lista y NO consiguió a un nodo con el name pasado por parámetro.
            if (aux.getID() == i){
                return aux;
            }
            aux = aux.getNext();
        }
        return null;
    }
    
    /**
     * Método que determina si un almacén, a partir de su nombre, existe en la lista
     * @param name nombre de un posible almacén de la lista, del cual se busca saber si en realidad está en la lista
     * @return false si el mismo NO se encuentra en la lista de almacenes, o true si el nombre del almacén se encuentra en la lista. (ya que getWarehouse retorna null cuando no consigue el almacén con ese nombre y retorna el nodo con ese nombre cuando sí lo consigue)
     */
    public boolean isWarehouseInList(String name){
        return getWarehouse(name) != null; 
    }
    
    /**
     * Método que devuelve los nombres de cada almacén en la lista.
     * @return cadena con los nombres de cada almacén en la lista
     */
    public String getWarehouseNames(){
        String warehouseNames = "";
        Warehouse aux = head;
        while (aux != null){
            warehouseNames += aux.getName() + "\n";
            aux = aux.getNext();
        }
        return warehouseNames;
    }
    
}