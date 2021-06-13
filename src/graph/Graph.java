/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import javax.swing.JOptionPane;
/**
 * Clase asociada al grafo de almacenes.
 * @author Liliana Nóbrega
 */
public class Graph {

    WarehouseList warehouses;
    int [][] adjMatrix;
    int vertexNumber; 
    int warehousesInGraph; //esto es la cantidad de nodos ya en la matriz de adyacencia
	
    /**
     * Constructor de la clase Graph. Se inicializa el grafo sin almacenes en él, con una matriz de adyacencia vacía y una lista de almacenes también vacía.
     * @param vertexNumber cantidad total de vértices que tendrá el grafo (es decir, la cantidad de filas y columnas de la matriz de adyacencia).
     */
    public Graph(int vertexNumber){
		this.vertexNumber = vertexNumber;
		this.warehouses = new WarehouseList(); 
		this.adjMatrix = new int[vertexNumber][vertexNumber];
		this.warehousesInGraph = 0;
		for (int i=0; i<vertexNumber; i++){
			for (int j=0; j<vertexNumber; j++){
				adjMatrix[i][j] = 0; //inicializa a la matriz con ceros (aún no hay arcos entre los vértices)
			}
		}
		
	}

    
    
    /**
     * Método que permite añadir un nuevo vértice/almacén al grafo.
     * @param newWarehouse nuevo almacén que se pretende agregar al grafo.
     */
    public void addVertex(Warehouse newWarehouse){
                this.warehousesInGraph++;
                if (this.warehousesInGraph > vertexNumber){
                        this.addExtraWarehouse();
                }
                newWarehouse.setID(this.warehousesInGraph-1); //se resta 1 al número de almacenes ya presentes en el grafo para darle al nuevo almacén su identificación ya que la misma representa el índice de la fila y columna asociado a dicho almacén, el cual debe tener un valor entre 0 y la cantidad total de almacenes en el grafo -1.

                this.warehouses.addLast(newWarehouse);
    }
    
    
    
    /**
     * Método que permite añadir un arco entre dos vértices del grafo: el vértice "A" (vértice de origen) y el "B" (vértice de destino)
     * @param nameA nombre del vértice A (de donde sale el arco que se añadirá)
     * @param nameB nombre del vértice B (a donde entra el arco que se añadirá)
     * @param distance distancia entre el vértice A y B
     */
    public void addArch(String nameA, String nameB, int distance){
        if (!nameA.equals(nameB)){
            Warehouse a = warehouses.getWarehouse(nameA);
            Warehouse b = warehouses.getWarehouse(nameB);

            if (distance > 0){
                if (a != null && b != null){
                    
                    adjMatrix[a.getID()][b.getID()] = distance;
               
                }else{
                        JOptionPane.showMessageDialog(null, "Vértice(s) inválido(s).");
                }
            }else{
                    JOptionPane.showMessageDialog(null, "Distancia inválida.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No puede conectarse un vértice consigo mismo.");
        }
    }

    /**
     * Método que determina si dos vértices están conectados en el grafo.
     * @param nameA nombre del vértice A (de donde sale el arco que se añadirá)
     * @param nameB nombre del vértice B (a donde entra el arco que se añadirá)
     * @return true si los vértices están conectados (la distancia representada en la matriz de adyacencia es válida), false si no lo están (la distancia representada en la matriz de adyacencia es 0, o alguno de los vértices ingresados no está en el grafo).
     */
    public boolean areConnected(String nameA, String nameB){
        Warehouse a = warehouses.getWarehouse(nameA);
        Warehouse b = warehouses.getWarehouse(nameB);
        if (a != null && b != null){
            return (adjMatrix[a.getID()][b.getID()] != 0);
        }else{
            return false;
        }
    }
    
    
    /**
     * Método que evalúa si en el grafo no hay almacenes aislados (es decir, que algún almacén no tenga entradas o salidas) en la totalidad del grafo. 
     * @return true si no hay almacenes aislados, false si hay almacenes aislados.
     */
    public boolean noWarehousesIsolated(){
        int lineSum, columnSum;
        for (int i = 0; i<this.warehousesInGraph; i++){
            lineSum = 0;
            columnSum = 0;
            for (int j = 0; j<this.warehousesInGraph; j++){
                    lineSum += adjMatrix[i][j];
                    columnSum += adjMatrix[j][i];
            }
            if (lineSum == 0 || columnSum == 0){ //lo 1ro significa que algún vértice no tiene salidas; lo 2do significa que algún vértice no tiene entradas.
                return false;
            }
        }
        return true; 
    }


    
    /**
     * Método para expandir el número máximo de vértices que tiene el grafo en 1 unidad. Se crea una nueva matriz de adyacencia (con una fila y una columna más).
     */
    private void addExtraWarehouse(){
        this.vertexNumber++;
        int[][] newAdjMatrix = new int[vertexNumber][vertexNumber];
        for (int i = 0; i<vertexNumber; i++){
                for (int j = 0; j<vertexNumber; j++){
                        newAdjMatrix[i][j] = (i<this.adjMatrix.length && j<this.adjMatrix[i].length)? this.adjMatrix[i][j] : 0;
                }
        }
        this.adjMatrix = newAdjMatrix;
	}
    
    
    
    /**
     * Método que permite eliminar un almacén del grafo, si es que dicha eliminación es válida (el almacén está en el grafo y su supresión no deja vértices aislados).
     * @param name nombre del almacén que se desea borrar del grafo
     */
    public void deleteWarehouse(String name){ 
		
                Warehouse w = warehouses.getWarehouse(name);
                
                if (w!=null){
                    int vertexIndex = w.getID();
                    if (this.isEliminationValid(vertexIndex)){
                        this.warehouses.delete(vertexIndex);
                        Warehouse aux = this.warehouses.getWarehouseWithID(vertexIndex+1); 
                        if (aux != null){
                            for (int i = vertexIndex; i<this.warehouses.getSize(); i++){ //se actualizan los IDs para coincidir con sus índices en la matriz de adyacencia luego de borrar el nodo con índice vertexIndex
                                aux.setID(i);
                                aux = aux.getNext();
                            }
                        }
                        
                        while (vertexIndex < this.warehousesInGraph){
                            for (int i = 0; i<this.warehousesInGraph; i++){
                                this.adjMatrix[vertexIndex][i] = (vertexIndex==warehousesInGraph-1)? 0: this.adjMatrix[vertexIndex+1][i]; //esto "elimina" una fila (desplaza los elementos de las filas una posición hacia arriba)
                                this.adjMatrix[i][vertexIndex] = (vertexIndex==warehousesInGraph-1)? 0: this.adjMatrix[i][vertexIndex+1]; //esto "elimina" una columna (desplaza los elementos de las columnas una posición hacia la izquierda)
                            }
                            vertexIndex++;
                        }
                        this.warehousesInGraph--;
                        JOptionPane.showMessageDialog(null, "Almacén eliminado exitosamente.");

                    }else{
                            JOptionPane.showMessageDialog(null, "El almacén que se desea borrar no puede ser eliminado: deja al menos un almacén aislado.");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Nombre de almacén inválido.");
                }
		
	} 

    
    
    /**
     * Método que permite saber si la eliminación de un almacén específico del grafo dejaría a al menos un otro almacén aislado (sin entradas o salidas)
     * @param vertexIndex índice del almacén de cuya eliminación se quiere determinar la validez
     * @return true si la eliminación es válida (no deja a otros vértices aislados), false si es inválida
     */
    private boolean isEliminationValid(int vertexIndex){ 
        int lineSum, columnSum;
        for (int i = 0; i<this.warehousesInGraph; i++){
            lineSum = 0;
            columnSum = 0;
            if (i != vertexIndex){
                for (int j = 0; j<this.warehousesInGraph; j++){
                    if (j != vertexIndex){	
                        lineSum += adjMatrix[i][j];
                        columnSum += adjMatrix[j][i];
                    }
                }
                if (lineSum == 0 || columnSum == 0){ //lo 1ro significa que algún vértice se quedaría sin salidas al eliminarse ese vértice; lo 2do significa que algún vértice se quedaría sin entradas al eliminarse ese vértice.
                    return false;
                }
            }
        }
        return true; //si ningún vértice se quedaría aislado al eliminar el vértice, entonces se retorna true
}

    
    
    /**
     * Método para mostrar la disponibilidad total mediante BFS (Breadth First Search)
     * @return un string con la información deseada que aparecerá en la interfaz
     * @author Ana Tovar
     */
    public String bfs(){ // Ya que el ID es el número de vértice, no paso ningún parámetro porque siempre se iniciará desde el primero

        String toPrint = "";
        boolean visited[] = new boolean[warehousesInGraph]; // Crea un array de booleanos del tamaño de la cantidad de almacenes en el grafo, default en false
        Queue queue = new Queue();
        Warehouse aux = warehouses.getFirst();

        toPrint += "Búsqueda BFS\nDisponibilidad de productos por almacén\n\n";

        queue.inqueue(aux.getName(), aux.getStock(), aux.getID()); // Los nodos visitados entrarán aquí;
        visited[0] = true; // Ok entonces aquí la idea es utilizar el índice a cada warehouse, de lo contrario no podremos tener una lista booleana de visitados y eso causaría un loop infinito.
        
        while (!queue.isEmpty()){

            aux = queue.getHead();
            queue.dequeue(); // Se hace dequeue porque igualmente se anexarán otros nodos más adelante por lo que no sale del while, el que usamos para entrar no nos interesa porque ya quedó registrado
            
            toPrint += aux.showStock();
            
            for(int i=0; i < warehousesInGraph; i++){

                if(adjMatrix[aux.getID()][i] != 0 && visited[i] == false){
                    Warehouse newNode = warehouses.getNode(i);
                    queue.inqueue(newNode.getName(), newNode.getStock(), newNode.getID()); // Me añade al queue
                    visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.
                }

            }  

        }

        return toPrint; //String de info de warehouses en el orden que se encontraron en el BFS
    }       

    
    
    /**
     * Método el cual busca un (1) producto en específico mediante BFS
     * @param name, nombre del producto que se desea encontrar
     * @return otro string el cual contiene los almacenes con dicho producto y la cantidad de éste, se mostrará en la interfaz
     * @author Ana Tovar
     */
    public String bfsProduct(String name){

        String toPrint = "";
        boolean visited[] = new boolean[warehousesInGraph]; // Crea un array de booleanos del tamaño la cantidad de almacenes en el grafo, default en false
        Queue queue = new Queue();
        Warehouse aux = this.warehouses.getFirst();
        
        toPrint += "Búsqueda BFS\nDisponibilidad de " + name + " por almacén.\n\n";
        
        queue.inqueue(aux.getName(), aux.getStock(), aux.getID()); // Se  supone que empieza desde el menor ID, es decir, 0
        visited[0] = true;
        
        while (!queue.isEmpty()){

            aux = queue.getHead();
            queue.dequeue();
            
            ProductList products = aux.getStock();
            Product product = products.getProductWithName(name);
            if (product != null){ //esto significa que product es un producto con el nombre buscado
                toPrint += "Almacén "+ aux.getName() + "\n\t" + product.getName() + " x" + product.getAmmount() + "\n"; //Aquí solo añade a los almacenes que tengan ese producto, si todos lo tienen, a pos , igual solo se appendea la cantidad de ESE producto en específico, no más
            }
            
            for(int i=1; i < warehousesInGraph; i++){ // En este caso i empieza desde 1 ya que comencé desde 0

                if(adjMatrix[aux.getID()][i] != 0 && visited[i] == false){

                    queue.inqueue(warehouses.getNode(i).getName(), warehouses.getNode(i).getStock(), warehouses.getNode(i).getID()); // Me añade al queue
                    visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.
                }
            }  

        }

        return toPrint;
    }

    
    
    /**
     * Método para mostrar la disponibilidad total mediante DFS (Depth First Search)
     * @return un string con la información deseada que aparecerá en la interfaz
     * @author Ana Tovar
     */
    public String dfs(){ 
        if (!this.warehouses.isEmpty()){
            String toPrint = "";
            boolean visited[] = new boolean[warehousesInGraph];
            Stack stack = new Stack();
            Warehouse aux = warehouses.getNode(0);

            toPrint += "Búsqueda DFS\nDisponibilidad de productos por almacén\n\n";

            stack.push(aux.getName(), aux.getStock(), aux.getID()); // Los nodos visitados entrarán aquí; ahora usamos el stack
            visited[0] = true;

            toPrint += aux.showStock(); // Este print es "único" en el sentido que solo se hace para el vértice de menor índice, porque los otros se harán dentro del for loop siguiente


            while (!stack.isEmpty()){

                aux = stack.getTop();
                stack.pop();

                for(int i=0; i < warehousesInGraph; i++){

                    if(adjMatrix[aux.getID()][i] != 0 && visited[i] == false){

                        stack.push(aux.getName(), aux.getStock(), aux.getID()); // Me añade al stack, recordar que aquí es LIFO (Last in, first out)
                        visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.

                        aux = warehouses.getNode(i); // "Salta" al siguiente nodo inmediatamente

                        toPrint += aux.showStock(); // Nueva adición al toPrint, ya que si se incluye en el while, habrían duplicados.

                        i = -1; // "Se reinicia el contador", -1 ya que gracias al i++ se convertirá en 0
                    }
                }  
            }
            return toPrint; //String de info de warehouses en el orden que se encontraron en el DFS instead
        }else{
            return "Grafo vacío.";
        }
    }


    
    /**
     * Método en el cual, dado un (1) producto, lo busque mediante DFS en todos los almacenes del grafo
     * @param name, nombre del producto que se desea encontrar
     * @return string de almacenes con dicho producto y sus respectivas cantidades
     * @author Ana Tovar
     */
    public String dfsProduct(String name){ 
        if (!this.warehouses.isEmpty()){
            String toPrint = "";
            boolean visited[] = new boolean[warehousesInGraph];
            Stack stack = new Stack();
            Warehouse aux = warehouses.getFirst();

            toPrint += "Búsqueda DFS\nDisponibilidad de " + name + " por almacén.\n\n";

            stack.push(aux.getName(), aux.getStock(), aux.getID()); 
            visited[0] = true;

            ProductList products = aux.getStock(); // Inicio desde cero, el primer nodo
            Product productAux = products.getProductWithName(name);
            if (productAux != null){ //esto significa que product es un producto con el nombre buscado
                toPrint += "Almacén "+ aux.getName() + "\n\t" + productAux.getName() + " x" + productAux.getAmmount() + "\n"; //Aquí solo añade a los almacenes que tengan ese producto, si todos lo tienen, a pos , igual solo se appendea la cantidad de ESE producto en específico, no más
            }
            // Este print parece redundante pero es necesario para que aparezca el primer nodo y no se repita en las demás iteraciones. Es único

            while (!stack.isEmpty()){

                aux = stack.getTop();
                stack.pop();

                for(int i=0; i < warehousesInGraph; i++){

                    if(adjMatrix[aux.getID()][i] != 0 && visited[i] == false){

                        stack.push(aux.getName(), aux.getStock(), aux.getID()); // Me añade al stack, recordar que aquí es LIFO (Last in, first out)
                        visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.

                        aux = warehouses.getNode(i); // "Salta" al siguiente nodo inmediatamente
                        products = aux.getStock(); // Cambia el array de productos por almacén
                        productAux = products.getProductWithName(name);
                        if (productAux != null){ //esto significa que product es un producto con el nombre buscado
                            toPrint += "Almacén "+ aux.getName() + "\n\t" + productAux.getName() + " x" + productAux.getAmmount() + "\n"; //Aquí solo añade a los almacenes que tengan ese producto, si todos lo tienen, a pos , igual solo se appendea la cantidad de ESE producto en específico, no más
                        }
                        i = -1; // "Se reinicia el contador", -1 ya que gracias al i++ se convertirá en 0
                        
                    }  
                }
            }
            return toPrint;
        }else{
            return "Grafo vacío.";
        }
    }

    
    
    /**
     * Método para mostrar los almacenes candidatos a pedir más stock
     * @param request es un ProductList el cual se solicita a otros almacenes
     * @param shop es el nodo Warehouse desde donde se está realizando la compra
     * @return una WarehouseList que son elegibles para ser el almacén desde el que se solicita el stock extra
     * @author Ana Tovar
     */     
    public WarehouseList availability(Warehouse shop, ProductList request){
        
        WarehouseList available = new WarehouseList();
        
        for(int index = 0; index < warehouses.getSize(); index++){
            if (index != shop.getID()){ //el almacén que complete la orden debe ser distinto al almacén original que no logró completar la orden en primer lugar
                ProductList products = warehouses.getNode(index).getStock();

                boolean isWarehouseValid = true;

                for(int j = 0; j < request.getSize(); j++){

                    boolean isproduct = products.isProductInList(request.getNode(j).getName());

                    if(!isproduct || products.getProductWithName(request.getNode(j).getName()).getAmmount() < request.getNode(j).getAmmount()){ // Se confirma que el almacén tenga no solo el producto sino la cantidad necesaria de este
                        isWarehouseValid = false;
                        break;
                    }
                }
                if(isWarehouseValid){
                  available.addLast(warehouses.getNode(index));
                }  
            }
        }
        if(available.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay ningún almacén que tenga más stock del producto."); // Esto lo que verifica es que si no hay ningún dato en la lista available, es porque ningún almacén cumple con los requisitos para que se solicite dicho producto
            return null;
        }
        return available;
    }

    
    
    /**
     * Dijkstra Short Path, algoritmo de camino más corto de Dijkstra
     * @param source Almacén desde donde se realiza la solicitud de stock 
     * @param target Almacén desde donde se realiza el pedido original
     * @return string final para imprimir en la interfaz
     * @author Ana Tovar
     */
    public String dijkSP(Warehouse source, Warehouse target){ 
        boolean[] visited = new boolean[warehousesInGraph]; 
        int[] distance =  new int[warehousesInGraph]; // Aquí se guardaran las distancias
        int INF = Integer.MAX_VALUE; // El mayor valor de los enteros
        int[] vertexPath = new int[warehousesInGraph];
        int lastDistance = 0;
        String toPrint;

        for(int i = 0; i < warehousesInGraph; i++){

            distance[i] = INF; // Se inicializan todas las distancias como "infinito", no están visitadas
        }

        distance[source.getID()] = 0; // La distancia desde donde se inicia es 0
        vertexPath[source.getID()] = -1; //El source no tiene un nodo precedente
        
        for(int i = 0; i < warehousesInGraph; i++){

            int aux = minimumDistance(distance, visited); // Se llama al método para saber la distancia mínima, siempre iniciará con 0 ya que ese es el nodo source;
            if (aux != -1){    
                visited[aux] = true; // Se marca como visitado
            }else{ //si se retorna -1, significa que no hay más caminos que recorrer, por lo que si no se había conseguido un camino válido hasta el target, significa que dicho camino no existe.
                break;
            }
            for(int j = 0; j < warehousesInGraph; j++){

                if(visited[j] == false && adjMatrix[aux][j] != 0 && distance[aux] != INF && (distance[aux] + adjMatrix[aux][j] < distance[j])){ // Se chequea, respectivamente, que ese índice no esté visitado, que los vértices estén conectados, que aux esté no sea un nodo desconectado, es decir, distinto de INF, y por último, que la distancia ya acumulada más la distancia en el arco sea menor al del nodo que se está iterando

                    distance[j] = distance[aux] + adjMatrix[aux][j]; // Se reemplaza valores en ese índice, el array va a ir cambiando
                    lastDistance = distance[j]; // Me guarda la última distancia que se agregó al array
                    vertexPath[j] = aux; // Me guarda los ÍNDICES de los nodos que se tomaron en cuenta como camino
                } 
            }
            if(aux == target.getID()){ // Me compara los int de los vértices, si aux es el vértice target, ya tendríamos la distancia mínima calculada del for loop previo
                toPrint = dijkPrint(source, target, vertexPath, lastDistance); // Se obtiene el return de esta función
                return toPrint; // si se ingresa en este if, entonces logramos nuestro objetivo y se retorna el string para imprimir
            }
        }

        return null; // En este caso no se obtiene nada, retornando null

    }
  
    
    
    /**
     * Método para obtener el índice vértice con distancia mínima hasta el momento
     * @param distance array con las respectivas distancias acumuladas entre nodos
     * @param visited array con los índices de los nodos ya visitados
     * @return índice del vértice con menor distancia
     * @author Ana Tovar
     */
    private int minimumDistance(int[] distance, boolean[] visited){
        int minimum = Integer.MAX_VALUE; // El mínimo se inicializa como MAX_VALUE para hacer la comparación más adelante
        int minIndexVertex = -1;  // Igualmente que el punto anterior;

        for(int i = 0; i < warehousesInGraph; i++){
            if(visited[i] == false && distance[i] < minimum){
                minimum = distance[i];
                minIndexVertex = i; // Aquí los valores se actualizan por el mínimo
            }
        }

        return minIndexVertex; // Se retorna el ínidice del vértice que tiene el camino/distancia más corto
    }

    
    
    /**
     * Método para generar el string a imprimir
     * @param source nodo a donde se realiza la solicitud de stock
     * @param target nodo desde el cual se solicita la compra original
     * @param vertexPath array de índices con el recorrido de los nodos que se ha realizado
     * @param lastDistance número entero con la distancia total
     * @return string con la información deseada
     * @author Ana Tovar
     */
    private String dijkPrint(Warehouse source, Warehouse target, int[]vertexPath, int lastDistance){
        String toPrintDik = "";
        toPrintDik += "Distancia total, " + lastDistance + "\n" + "El reccorido empieza en " + source.getName() + " Pasa por: " + "\n";
        int aux = target.getID();
        while (aux != -1){
            toPrintDik += warehouses.getNode(aux).getName();
            toPrintDik += (aux != source.getID())?  " <-- ": "\n";
            aux = vertexPath[aux];
        }
        return toPrintDik;
    }
    
    /**
    * Método para conseguir el string con la información del recorrido de Floyd Warshal
    * @param source nodo al que se le realiza la solicitud de stock
    * @param target nodo al cual se le hace la compra original
    * @return un string con la información deseada de esta función
    * @author Ana Tovar
    */
    public String FloydWarshall(Warehouse source, Warehouse target){
        int [][] distanceMatrix = new int[vertexNumber][vertexNumber];
        int [][] vertexMatrix = new int[vertexNumber][vertexNumber];
        int INF = 1000000;
        int start = source.getID();
        int finish = target.getID();
        String toPrintFW = "";
        
        for(int i = 0; i < vertexNumber; i++){
            for(int j = 0; j < vertexNumber; j++){

                if(adjMatrix[i][j] == 0){
                    distanceMatrix[i][j] = INF;
                }else{
                    distanceMatrix[i][j] = adjMatrix[i][j];
                }
                
                if(distanceMatrix[i][j] == INF){
                    
                    vertexMatrix[i][j] = -1;
                
                }else{
                    
                    vertexMatrix[i][j] = j;
                
                }
            }
        }
        
        for(int k = 0; k < vertexNumber; k++){ // El algoritmo Floyd Warshall usa un par de datos, por lo que se necesita este otro for loop con el contador k
            for(int i = 0; i < vertexNumber; i++){
                for(int j = 0; j < vertexNumber; j++){
                    if(distanceMatrix[i][k] + distanceMatrix[k][j] < distanceMatrix[i][j]){ // distanceMatrix[i][j] originalmente guarda el dato de la matriz original, este se va a ir actualizando a la distancia total más corta entre los nodos
                        
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                        vertexMatrix[i][j] = vertexMatrix[i][k];
                    
                    } 
                }
            }
        }
        
        if(vertexMatrix[start][finish] == -1){
            
            return null;
            
        }else{

            toPrintFW += "Distancia total, " + distanceMatrix[start][finish] + "\n" + "El recorrido empieza en " + source.getName() + " Pasa por: " + "\n"; // Me appendea al string la distancia total entre ambos nodos
            
            while(start != finish){
                
                start = vertexMatrix[start][finish];
                toPrintFW += warehouses.getNode(start).getName() + " --> ";
                
            }

        }
        
        
        toPrintFW += "Finaliza el recorrido."; // Añade al último almacén, que es el del pedido original
        
        return toPrintFW;
    }
  

     /**
     * Método para realizar pedido. Determina si se puede completar el pedido a partir del almacén original, o si existe otro almacén que pueda completar la orden. De lo contrario, no se realiza el pedido.
     * @param warehouseToBuy Almacén desde donde se hace el pedido original
     * @param order lista de productos ordenados del almacén
     * @param shortPath Booleano para saber si se buscaría el almacén más cercado que complete la orden por Dijkstra (si es true) o Floyd-Warshall (si es false), según el input del usuario
     * @author Liliana Nóbrega y Ana Tovar
     */
    public void buy(Warehouse warehouseToBuy, ProductList order, boolean shortPath){
        Product orderedP, inWarehouse;
        ProductList toOrderFromWarehouse, missingOrder, warehouseProds;
        warehouseProds = warehouseToBuy.getStock();
        missingOrder = new ProductList();
        toOrderFromWarehouse = new ProductList();
        
        for (int i=0; i<order.getSize(); i++){
            orderedP = order.getNode(i);
            inWarehouse = warehouseProds.getProductWithName(orderedP.getName());
            if (inWarehouse != null){ //entonces ese producto está en el almacén original
                    if (inWarehouse.getAmmount()<orderedP.getAmmount()){ //el almacén original no tiene suficiente para completar la orden
                            toOrderFromWarehouse.addLast(orderedP.getName(), inWarehouse.getAmmount()); //se añade a la lista de productos a ordenar del almacén original al producto ordenado con la cantidad que tiene el almacén.
                            missingOrder.addLast(orderedP.getName(), (orderedP.getAmmount()-inWarehouse.getAmmount())); //necesitas completar lo que no puede el almacén original
                    }else{ //si la cantidad en el almacén es mayor o igual a la ordenada, no se añade a missingOrder, sino todo a toOrderFromWarehouse
                            toOrderFromWarehouse.addLast(orderedP.getName(), orderedP.getAmmount());
                    }
            }else{
                //porque el almacén no tiene NADA de lo que se ordenó de este producto  
                    missingOrder.addLast(orderedP.getName(), orderedP.getAmmount());
            }
        }
        
        if (missingOrder.isEmpty()){ //significa que toda la orden se completa del almacén original y no hay que buscar cómo completarla con otro almacén
            JOptionPane.showMessageDialog(null, "La orden puede ser completada exitosamente a partir del almacén escogido. Gracias por su compra.");
            for (int i = 0; i<order.getSize(); i++){
                orderedP = order.getNode(i);
                warehouseProds.sellProduct(orderedP);
            }

        }else{ //AQUÍ ES DONDE SE BUSCAN EL ÚNICO ALMACÉN QUE COMPLETE TOOOOODOOOOOOO
            //TODO
        }
    
    }
}