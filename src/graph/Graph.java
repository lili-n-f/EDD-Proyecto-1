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

}

