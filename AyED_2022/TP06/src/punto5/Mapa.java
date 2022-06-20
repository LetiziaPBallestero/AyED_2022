package punto5;

import ejercicio3.Arista;
import ejercicio3.Grafo;
import ejercicio3.Vertice;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class Mapa {
	private Grafo<String> mapaCiudades;
	
	public Mapa (Grafo <String> g) {
		this.mapaCiudades = g;
	}
	/**
	 * Retorna la lista de ciudades que se deben atravesar
	 * para ir de ciudad1 a ciudad2 en caso que se pueda llegar, si no retorna la
	 * lista vacía. (Sin tener en cuenta el combustible).
	 */
	
	public ListaGenerica<String> devolverCamino (String ciudad1, String ciudad2) {
		// dfs
		// primero tengo que encontrar la primer ciudad
		// despues calcular el camino entre c1 y c2 
		
		boolean [] marca = new boolean [mapaCiudades.listaDeVertices().tamanio()+1];
		
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		ListaEnlazadaGenerica<String> posiblesCaminos = new ListaEnlazadaGenerica<String>();
		ListaGenerica<Vertice<String>> ciudades = this.mapaCiudades.listaDeVertices();
		
		boolean encontre = false; 
		int indiceCiudad1 = -1; 
		Vertice<String> ciudad; 
		
		ciudades.comenzar();
		
		while (!ciudades.fin()&& !encontre) {
			ciudad = ciudades.proximo();
			if (ciudad.dato() == ciudad1) {
				encontre = true; 
				indiceCiudad1 = ciudad.getPosicion();
				// si no hago esto en la lista de posibles caminos
				// despues no me guarda el dato, creo 
				// ya lo probé en la lista final de camino
				posiblesCaminos.agregarFinal(ciudad.dato());
			}
		}
		if (indiceCiudad1 != -1 ) {
			dfs(indiceCiudad1,camino,marca,ciudad2, posiblesCaminos);
		}
		return camino;
	}
	
	private void dfs(int indice, ListaEnlazadaGenerica<String> camino , boolean[]marca , String ciudad2, ListaEnlazadaGenerica<String> posiblesCaminos) {
		// marco la primer ciudad (U) como visitado: 
		marca[indice] = true;
		// busco a la siguiente ciudad: 
		Vertice<String> v = this.mapaCiudades.listaDeVertices().elemento(indice);
		
		if (v.dato() == ciudad2) {
			posiblesCaminos.comenzar();
			while (!posiblesCaminos.fin()) 
				camino.agregarFinal(posiblesCaminos.proximo());
		} else {
			// para todo adyacente de esta ciudad
			ListaGenerica<Arista<String>> ciudadesAdyacentes = this.mapaCiudades.listaDeAdyacentes(v);
			ciudadesAdyacentes.comenzar();
			// hasta que no visito todas las ciudades
			while (!ciudadesAdyacentes.fin() && camino.esVacia()) {
				Vertice<String> proximaCiudad = ciudadesAdyacentes.proximo().verticeDestino();
				int j = proximaCiudad.getPosicion(); // no puedo usar ciudadAdyacente porque movi el puntero creo
				if (!marca[j]) {
					posiblesCaminos.agregarFinal(proximaCiudad.dato());
					this.dfs(indice, camino, marca, ciudad2, posiblesCaminos);
					posiblesCaminos.eliminarEn(posiblesCaminos.tamanio());
				}
			}
		}
		
	}
	
	/**
	 * Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2, 
	 * sin pasar por las ciudades que están contenidas en la lista 
	 * ciudades pasada por parámetro, si no existe camino retorna la lista vacía. 
	 * (Sin tener en cuenta el combustible).
	 */
	
	public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1, String ciudad2,
			 ListaGenerica<String> ciudades) {
		
	}
	
	/**
	 * Retorna la lista de ciudades que forman el camino más corto 
	 * para llegar de ciudad1 a ciudad2, si no existe camino retorna la lista vacía. 
	 * (Las rutas poseen la distancia). (Sin tener en cuenta el combustible).
	 */
	
	public ListaGenerica<String> caminoMasCorto (String ciudad1, String ciudad2) {
		
	}
	/**
	 * Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2. 
	 * El auto no debe quedarse sin combustible y no puede cargar. 
	 * Si no existe camino retorna la lista vacía.
	 */
	
	public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, 
			int tanqueAuto) {
		
	}
	/**
	 * Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2 
	 * teniendo en cuenta que el auto debe cargar la menor cantidad de veces. 
	 * El auto no se debe quedar sin combustible en medio de una ruta, 
	 * además puede completar su tanque al llegar a cualquier ciudad. 
	 * Si no existe camino retorna la lista vacía.
	 */
	public ListaGenerica<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, 
			int tanqueAuto) {
		
	}
}
