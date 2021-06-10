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


	Warehouse[] warehouses;
	int [][] adjMatrix;
	int vertexNumber; 
	int warehousesInGraph; //esto es la cantidad de nodos ya en la matriz de adyacencia
	
    /**
     * Constructor de la clase Graph. Se inicializa el grafo sin almacenes en él, con una matriz de adyacencia vacía y un array de almacenes también vacío.
     * @param vertexNumber cantidad total de vértices que tendrá el grafo (es decir, la cantidad de filas y columnas de la matriz de adyacencia y la cantidad de elementos del array de almacenes).
     */
    public Graph(int vertexNumber){
		this.vertexNumber = vertexNumber;
		this.warehouses = new Warehouse[vertexNumber]; 
		this.adjMatrix = new int[vertexNumber][vertexNumber];
		this.warehousesInGraph = 0;
		for (int i=0; i<vertexNumber; i++){
			for (int j=0; j<vertexNumber; j++){
				adjMatrix[i][j] = 0; //inicializa a la matriz con ceros (aún no hay arcos entre los vértices)
			}
		}
		
	}

	/*
	public boolean isVertexInGraph(Warehouse vertex){
		for (Warehouse warehouse : this.warehouses){
			if (warehouse.getName() == vertex.getName()){
				return true;
			}
		}
		return false;
	}/*
	public boolean isVertexInGraph(Warehouse vertex){
		for (Warehouse warehouse : this.warehouses){
			if (warehouse.getName() == vertex.getName()){
				return true;
			}
		}
		return false;
	}
	*/ //No sé si este sea necesario lol con ver que el índice sea distinto a -1 basta

    /**
     * Método que devuelve el índice (la identificación) de un almacén cuyo nombre se pasa por parámetro en el array de almacenes y la matriz de adyacencia.
     * @param name nombre del almacén del cual se busca el índice correspondiente a dicho vértice en la matriz de adyacencia y el array de almacenes.
     * @return el índice del almacén si el mismo se encuentra en el array de almacenes en el grafo, o -1 si el almacén buscado no existe en el grafo.
     */
	
	public int getVertexIndex(String name){ //OJO! los nombres deben ser únicos
		for (Warehouse warehouse : this.warehouses){
                    if (warehouse.getName().equals(name)){
                        return warehouse.getID();
                    }
		}
		return -1;
	}

    /**
     * Método que permite saber si un índice (para el array de almacenes o la matriz de adyacencia) es válido (está entre 0, incluido, y la cantidad de almacenes en el grafo, sin incluir)
     * @param index índice del cual se quiere conocer su validez
     * @return true si el índice es válido, false si no lo es
     */
    private boolean isValidIndex(int index){
		return (index>=0 && index < this.warehousesInGraph);
	}

    /**
     * Método que permite añadir un nuevo vértice/almacén al grafo.
     * @param newWarehouse nuevo almacén que se pretende agregar al grafo.
     */
    public void addVertex(Warehouse newWarehouse){
        if (this.getVertexIndex(newWarehouse.getName()) != -1){ //los nombres de los almacenes deben ser únicos, por lo que si se consigue un índice válido (distinto a -1) para algún almacén ya en el grafo, quiere decir que ya existe un almacén con dicho nombre.
                JOptionPane.showMessageDialog(null, "El almacén ya se encuentra en el grafo.");
        }else{
                this.warehousesInGraph++;
                if (this.warehousesInGraph > vertexNumber){
                        this.addExtraWarehouse();
                }
                newWarehouse.setID(this.warehousesInGraph-1); //se resta 1 al número de almacenes ya presentes en el grafo para darle al nuevo almacén su identificación ya que la misma representa el índice de la fila y columna asociado a dicho almacén (así como de su posición en el array de almacenes), el cual debe tener un valor entre 0 y la cantidad total de almacenes en el grafo -1.

                this.warehouses[warehousesInGraph-1] = newWarehouse;
        }
	}

    /**
     * Método que permite añadir un arco entre dos vértices del grafo: el vértice "A" (vértice de origen) y el "B" (vértice de destino)
     * @param nameA nombre del vértice A (de donde sale el arco que se añadirá)
     * @param nameB nombre del vértice B (a donde entra el arco que se añadirá)
     * @param distance distancia entre el vértice A y B
     */
    public void addArch(String nameA, String nameB, int distance){
        if (!nameA.equals(nameB)){

            int vertexIndexA = this.getVertexIndex(nameA);
            int vertexIndexB = this.getVertexIndex(nameB);

            if (distance > 0){
                if (this.isValidIndex(vertexIndexA) && this.isValidIndex(vertexIndexB)){
                        adjMatrix[vertexIndexA][vertexIndexB] = distance;
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
     * Método para expandir el número máximo de vértices que tiene el grafo en 1 unidad. Se crean una nueva matriz de adyacencia (con una fila y una columna más) y un nuevo array de almacenes.
     */
    private void addExtraWarehouse(){
        this.vertexNumber++;
        int[][] newAdjMatrix = new int[vertexNumber][vertexNumber];
        Warehouse[] newWarehouses = new Warehouse [vertexNumber];
        for (int i = 0; i<vertexNumber; i++){
                newWarehouses[i] = (i<this.warehouses.length)? this.warehouses[i] : null;
                for (int j = 0; j<vertexNumber; j++){
                        newAdjMatrix[i][j] = (i<this.adjMatrix.length && j<this.adjMatrix[i].length)? this.adjMatrix[i][j] : 0;
                }
        }
        this.adjMatrix = newAdjMatrix;
        this.warehouses = newWarehouses;
	}

    /**
     * Método que permite eliminar un almacén del grafo, si es que dicha eliminación es válida (el almacén está en el grafo y su supresión no deja vértices aislados).
     * @param name nombre del almacén que se desea borrar del grafo
     */
    public void deleteWarehouse(String name){ 
		int vertexIndex = this.getVertexIndex(name);
		
                if (this.isValidIndex(vertexIndex)){
                    if (this.isEliminationValid(vertexIndex)){
                        /*
                        for (int [] line : this.adjMatrix){
                                line[vertexIndex-1] = 0;
                        }
                        for (int i = 0; i < this.warehousesInGraph; i++){
                                this.adjMatrix[vertexIndex-1][i] = 0;
                        }
                        this.warehousesInGraph--;
                        */
                        for (int i = vertexIndex; i<this.warehousesInGraph; i++){
                            this.warehouses[i] = (i==warehousesInGraph-1)? null: this.warehouses[i+1];
                            this.warehouses[i].setID(i);
                        }
                        while (vertexIndex < this.warehousesInGraph){
                            for (int i = 0; i<this.warehousesInGraph; i++){
                                this.adjMatrix[vertexIndex][i] = (vertexIndex==warehousesInGraph-1)? 0: this.adjMatrix[vertexIndex+1][i]; //esto "elimina" una fila (desplaza los elementos de las filas una posición hacia arriba)
                                this.adjMatrix[i][vertexIndex] = (vertexIndex==warehousesInGraph-1)? 0: this.adjMatrix[i][vertexIndex+1]; //esto "elimina" una columna (desplaza los elementos de las columnas una posición hacia la izquierda)
                            }
                            vertexIndex++;
                        }
                        this.warehousesInGraph--;

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
    private boolean isEliminationValid(int vertexIndex){ //vertexIndex es el índice del elemento que quiere ser eliminado
        int lineSum, columnSum;
        for (int i = 0; i<this.warehousesInGraph; i++){
            lineSum = 0;
            columnSum = 0;
            if (i != vertexIndex){
                for (int j = 0; i<this.warehousesInGraph; j++){
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
     * Método para retornar un nodo dado su índice
     * @param i, siendo este el índice del nodo que se desea
     * @return el nodo correspondiente a ese índice
     */
    public Warehouse getVertex(int i){
        return this.warehouses[i];
    }

    /**
     * Método para mostrar la disponibilidad total mediante BFS (Breadth First Search)
     * @return un string con la información deseada que aparecerá en la interfaz
     */
    public String bfs(){ // Ya que el ID = número de vértice, no paso ningún parámetro porque siempre se iniciará desde el primero

        String toPrint = "";
        boolean visited[] = new boolean[vertexNumber]; // Crea un array de booleanos del tamaño de los nodos, default en false
        Queue queue = new Queue();
        Warehouse aux;

        toPrint += "Búsqueda BFS\n";

        queue.inqueue(getVertex(0)); // Los nodos visitados entrarán aquí;
        visited[0] = true; // Ok entonces aquí la idea es utilizar el índice a cada warehouse, de lo contrario no podremos tener una lista booleana de visitados y eso causaría un loop infinito.
        while (!queue.isEmpty()){

            aux = queue.getHead();
            toPrint += aux.getName() + "\n";
            Product[] products = aux.getStock();
            for (Product product : products) {

                toPrint += product.getName() + " x" + product.getAmmount() + "\n";

            }

            queue.dequeue(); // Se hace dequeue porque igualmente se anexarán otros nodos más adelantes por lo que no sale del while, el que usamos para entrar no nos interesa porque ya quedó registrado
            for(int i=0; i < vertexNumber; i++){

                if(adjMatrix[aux.getID()][i] != 0 && visited[i] == false){

                    queue.inqueue(getVertex(i)); // Me añade al queue
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
     */
    public String bfsProduct(String name){

        String toPrint = "";
        boolean visited[] = new boolean[vertexNumber]; // Crea un array de booleanos del tamaño de los nodos, default en false
        Queue queue = new Queue();

        toPrint += "Búsqueda BFS\n";

        Warehouse aux;
        queue.inqueue(getVertex(0)); // Se  supone que empieza desde el menor ID, es decir, 0

        visited[0] = true;
        while (!queue.isEmpty()){

            aux = queue.getHead();

            Product[] products = aux.getStock();
            for (Product product : products) {

                if (product.getName().equals(name)) {

                    toPrint += aux.getName() + "\n" + product.getName() + " x" + product.getAmmount() + "\n"; //Aquí solo añade a los almacenes que tengan ese producto, si todos lo tienen, a pos , igual solo se appendea la cantidad de ESE producto en específico, no más
                }
            }

            queue.dequeue();

            for(int i=1; i < vertexNumber; i++){ // En este caso i empieza desde 1 ya que comencé desde 0

                if(adjMatrix[getVertexIndex(aux.getName())][i] != 0 && visited[i] == false){ // getVetexIndex -1???  o i-1??? ya que los indices comienzan en 0(?)

                    queue.inqueue(getVertex(i)); // Me añade al queue
                    visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.
                }
            }  

        }

        return toPrint;
    }

    /**
     * Método para mostrar la disponibilidad total mediante DFS (Depth First Search)
     * @return un string con la información deseada que aparecerá en la interfaz
     */
    public String dfs(){ 

        String toPrint = "";
        boolean visited[] = new boolean[vertexNumber];
        Stack stack = new Stack();
        Warehouse aux;

        toPrint += "Búsqueda DFS\n";

        stack.push(getVertex(0)); // Los nodos visitados entrarán aquí; ahora usamos el stack
        visited[getVertexIndex(getVertex(0).getName())] = true;
        toPrint += "Disponibilidad de productos por almacén" + "\n" + "Almacén " + getVertex(0).getName() + "\n";
        Product[] products = getVertex(0).getStock();

            for (Product product : products) {

                toPrint += product.getName() + " x" + Integer.toString(product.getAmmount()) + "\n"; // Este print es "único" en el sentido que solo se hace para el source, porque los otros se harán dentro del for loop siguiente
            }

        while (!stack.isEmpty()){

            aux = stack.getTop();
            int aux_num = getVertexIndex(aux.getName()); // Aquí necesito una variable entera para mejor manejo


            stack.pop();

            for(int i=0; i < vertexNumber; i++){

                if(adjMatrix[aux_num][i] != 0 && visited[i] == false){

                    Warehouse nextVertex = getVertex(i); // A diferencia del BFS, aquí necesito guardar ese vértice en una variable para su mejor manejo
                    products = nextVertex.getStock(); // Cambia el array de productos por almacén

                    toPrint += "Almacén " + nextVertex.getName() + "\n"; // Nueva adición al toPrint, ya que si se incluye en el while, habrían duplicados.                        
                    for (Product product : products) {
                        toPrint += product.getName() + " x" + product.getAmmount() + "\n";
                        }

                    stack.push(getVertex(aux_num)); // Me añade al stack, recordar que aquí es LIFO (Last in, first out)
                    visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.
                    aux_num = i; // "Salta" al siguiente nodo inmediatamente
                    i = -1; // "Se reinicia el contador", -1 ya que gracias al i++ se convertirá en 0

                }
            }  

        }

        return toPrint; //String de info de warehouses en el orden que se encontraron en el DFS instead
    }



    /**
     * Método en el cual, dado un (1) producto, lo busque mediante DFS
     * @param name, nombre del producto que se desea encontrar
     * @return string de almacenes con dicho producto y sus respectivas cantidades
     */
    public String dfsProduct(String name){ 

        String toPrint = "";
        boolean visited[] = new boolean[vertexNumber];

        Stack stack = new Stack();
        Warehouse aux;
        Product[] products = getVertex(0).getStock(); // Inicio desde cero, el primer nodo

        stack.push(getVertex(0)); 
        visited[0] = true;

        for (Product product : products) {
                if (product.getName().equals(name)) {
                    toPrint += "Almacén " + getVertex(0).getName() + "\n" + product.getName() + " x" + product.getAmmount() + "\n";
                }
        } // Este print parece redundante pero es necesario para que aparezca el primer nodo y no se repita en las demás iteraciones. Es único

        while (!stack.isEmpty()){

            aux = stack.getTop();
            int aux_num = aux.getID(); // Aquí necesito una variable entera para mejor manejo

            stack.pop();

            for(int i=0; i < vertexNumber; i++){

                if(adjMatrix[aux_num][i] != 0 && visited[i] == false){

                    Warehouse nextVertex = getVertex(i); // A diferencia del BFS, aquí necesito guardar ese vértice en una variable para su mejor manejo
                    products = nextVertex.getStock();

                    for (Product product : products) {
                        if (product.getName().equals(name)){
                            toPrint += "Almacén " + nextVertex.getName() + "\n" + product.getName() + " x" + product.getAmmount() + "\n";
                        }

                    stack.push(getVertex(aux_num)); // Me añade al stack, recordar que aquí es LIFO (Last in, first out)
                    visited[i] = true; //Esto settea los vértices ya visitados como true, ya que esa es la condición que vamos a revisar.
                    aux_num = i; // "Salta" al siguiente nodo inmediatamente
                    i = -1; // "Se reinicia el contador", -1 ya que gracias al i++ se convertirá en 0

                    }
                }  

            }

        }
        return toPrint;
    }

    /**
     * Método para mostrar los almacenes candidatos a pedir más stick
     * @param request es un nodo Product el cual se solicita a otros almacenes
     * @return una lista de nodos Warehouse que son elegibles para ser el almacén desde el que se solicita el stock extra
     */     
    public WarehouseList availability(Product request){
        WarehouseList available = new WarehouseList(); // se crea una array nuevo, con valores null inicialmente
        for(Warehouse warehouse: warehouses){
            Product [] products = warehouse.getStock();
            for(Product product: products){
                if(product.getName().equals(request.getName()) && product.getAmmount() >= request.getAmmount()){ // Se confirma que el almacén tenga no solo el producto sino la cantidad necesaria de este
                    available.addLast(warehouse);
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
     * @param source Almacén desde donde se realiza el pedido original
     * @param target Almacén desde donde se realiza la solicitud de stock
     * @return string final para imprimir en la interfaz
     */
    public String dijkSP(Warehouse source, Warehouse target){ 
        boolean[] visited = new boolean[vertexNumber]; 
        int[] distance =  new int[vertexNumber]; // Aquí se guardaran las distancias
        int INF = Integer.MAX_VALUE; // El mayor valor de los enteros
        int[] vertexPath = new int[vertexNumber];
        int lastDistance;
        String toPrint;

        for(int i = 0; i < vertexNumber; i++){

            distance[i] = INF; // Se inicializan todas las distancias como "infinito", no están visitadas
        }

        distance[getVertexIndex(source.getName())] = 0; // El nodo almacén desde donde se inicia es 0, ya que se inicia desde allí

        for(int i = 0; i < vertexNumber; i++){

            int aux = minimumDistance(distance, visited); // Se llama al método para saber la distancia mínima, siempre iniciará con 0 ya que ese es el nodo source;
            visited[aux] = true; // Se marca como visitado

            for(int j = 0; j < vertexNumber; j++){

                if(visited[j] == false && adjMatrix[aux][j] != 0 && distance[aux] != INF && (distance[aux] + adjMatrix[aux][j] < distance[j])){

                    distance[j] = distance[aux] + adjMatrix[aux][j]; // Se reemplaza valores en ese índice, el array va a ir cambiando
                    lastDistance = distance[j]; // Me guarda la última distancia del array
                    vertexPath[j] = aux; // Me guarda los ÍNDICES de los nodos que se tomaron en cuenta como camino


            if(aux == getVertexIndex(target.getName())){ // Me compara los int de los vértices, si aux es el vértice target, me ya tendríamos la distancia mínima calculada del for loop previo
                toPrint = dijkPrint(source, vertexPath, lastDistance); // Se obtiene el return de esta función
                return toPrint; // si se ingresa en este if, entonces logramos nuestro objetivo y se retorna el string para imprimir
            }
                } // Se chequea, respectivamente, que ese índice no esté visitado, que los vértices estén conectados, que aux esté no sea un nodo desconectado, es decir, distinto de INF, y por último, que la distancia ya acumulada más la distancia en el arco sea menor al del nodo que se está iterando
            }
        }

        return null; // En este caso no se obtiene nada, retornando null

    }
        
    /**
     * Método para obtener el índice vértice con distancia mínima hasta el momento
     * @param distance array con las respectivas distancias acumuladas entre nodos
     * @param visited array con los índices de los nodos ya visitados
     * @return índice del vértice con menor distancia
     */
    public int minimumDistance(int[] distance, boolean[] visited){
        int minimum = Integer.MAX_VALUE; // El mínimo se inicializa como MAX_VALUE para hacer la comparación más adelante
        int minIndexVertex = -1;  // Igualmente que el punto anterior;

        for(int i = 0; i < vertexNumber; i++){
            if(visited[i] == false && distance[i] < minimum){
                minimum = distance[i];
                minIndexVertex = i; // Aquí los valores se actualizan por el mínimo
            }
        }

        return minIndexVertex; // Se retorna el ínidice del vértice que tiene el camino/distancia más corto
    }

    /**
     * Método para generar el string a imprimir
     * @param source nodo desde el cual se solicita la compra original
     * @param vertexPath array con el recorrido de los nodos que se ha realizado
     * @param lastDistance número entero con la distancia total
     * @return string con la información deseada
     */
    public String dijkPrint(Warehouse source, int[]vertexPath, int lastDistance){
        String toPrintDik = "";
        toPrintDik += "Distancia total, " + lastDistance + "\n" + "El reccorido empieza en " + source.getName() + " Pasa por: " + "\n";
        for(int vertexindex: vertexPath){
            toPrintDik += (getVertex(vertexindex)).getName() + " -> ";
        }
        toPrintDik += "//"; // Final del recorrido

        return toPrintDik;
    }
    
}