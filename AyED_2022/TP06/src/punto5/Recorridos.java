package punto5;

import ejercicio3.Arista;
import ejercicio3.Grafo;
import ejercicio3.Vertice;
import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

// ejercicio 4, recorridos DFS y BFS
public class Recorridos<T> {
	/**
	 * Retorna una lista de vértices con el recorrido en profundidad del grafo
	 * recibido como parámetro.
	 **/
	public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo) {
		// Marcar todos los vértices como no visitados: (se inicializa por default en false)
		boolean [] marca = new boolean [grafo.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		for (int i=1 ; i <= grafo.listaDeVertices().tamanio(); i++) {
			if (!marca[i])
				// Elegir vértice u (no visitado) como punto de partida (el i=1 con el que arranca): 
				this.dfsAux(i, marca , grafo , lista);
		}
		return lista;
	}
	
	private void dfsAux(int i , boolean[] marca , Grafo<T> grafo , ListaEnlazadaGenerica<Vertice<T>> lista){
		// Marco U como  visitado: 
		marca[i] = true;
		Vertice<T> v = grafo.listaDeVertices().elemento(i);
		// agrego el vertice 
		lista.agregarFinal(v);
		// para todo adyacente a U ... 
		ListaGenerica<Arista<T>> adyacente = grafo.listaDeAdyacentes(v);
		adyacente.comenzar();
		// (hasta que no visito todos los adyacentes)
		while(!adyacente.fin()){
			// me meto por este mandandole la nueva variable j: 
			int j = adyacente.proximo().verticeDestino().getPosicion();
			// si no lo visite repito este metodo (3 y 4): 
			if(!marca[j]){
				this.dfsAux(j, marca , grafo , lista);
			}
		}
	}
	
	/**
	 * 1. Marcar todos los vértices como no visitados.
	 * 2. Elegir vértice u (no visitado) como punto de partida.
	 * 3. Marcar u como visitado.
	 * 4. Para todo v adyacente a u, (u,v) Є A, 
	 * si v no ha sido visitado, repetir recursivamente (3) y (4) para v.
	 */
	
	// --------------------------------------------------------------------------

	/**
	 * Retorna una lista de vértices con el recorrido en amplitud del grafo recibido
	 * como parámetro.
	 **/

	public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo) {
		// creo el vector de booleanos para ver qué vértice visité :
		boolean [] marca = new boolean [grafo.listaDeVertices().tamanio()+1];
		// creo qune lista con los vértices: 
		ListaEnlazadaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		for (int i = 1 ; i <= marca.length ; i++) {
			// hasta que no recorrí todos 
			if (!marca[i]) // si no lo visite ... 
				this.bfsAux (i,grafo,marca, lista); // las listas empiezan en la pos 1 
		}
		return lista;
	}
	
	private void bfsAux (int i , Grafo <T> grafo, boolean[]marca , ListaGenerica<Vertice<T>> lista) {
		ListaGenerica<Arista<T>> adyacente = null; 
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		// encolo el vértice de origen: 
		cola.encolar(grafo.listaDeVertices().elemento(i));
		// marco ese vértice como visitado: 
		marca[i] = true; 
		// proceso la cola hasta que se vacie:
		while (!cola.esVacia()) {
			// desencolo el vértice y lo agrego al final como visitado:
			Vertice<T> vertice = cola.desencolar();
			lista.agregarFinal(vertice);
			// a la lista de adyacentes le agrego el adyacente de u:
			adyacente = grafo.listaDeAdyacentes(vertice);
			adyacente.comenzar();
			// hastas que no visite todos los adyacentes:
			while (!adyacente.fin()) {
				// me preparo para analizar el vértice 
				Arista<T> arista = adyacente.proximo();
				int j = arista.verticeDestino().getPosicion();
				// si no lo visite a v 
				if (!marca[j]) {
					// lo encolo y lo visito: 
					Vertice<T> w = arista.verticeDestino();
					cola.encolar(w);
					marca[j] = true; 
				}
			}
		}
	}
	/**
	 * 1. Encolar el vértice origen u. 
	 * 2. Marcar el vértice u como visitado. 
	 * 3. Procesar la cola. 
	 * 4. 	Desencolar u de la cola 
	 * 5. 		adyacente a u,(u,v) Є E, 
	 * 6. 			si v no ha sido visitado 
	 * 7. 				encolar y visitar v
	 */

}