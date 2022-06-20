package parcial;

/** Enunciado: 
 * Implemente en la clase Parcial el m�todo RESOLVER que recibe un �rbol general de caracteres. 
 * Este m�todo retorna todos los caminos que hay desde la ra�z a una hoja d�nde 
 * hay una cantidad X de nodos, siendo X un par�metro del m�todo 
 */
public class parcialTema2Fecha2 {
	
	public ListaGenerica<ListaEnlazadaGenerica<Character>> resolver (ArbolGeneral<Character> AG , Integer X) {
		ListaGenerica<Character> caminoActual = new ListaEnlazadaGenerica<Character>();
		ListaGenerica<ListaEnlazadaGenerica<Character>> caminos = new ListaEnlazadaGenerica<ListaEnlazadaGenerica<Character>>();
		
		if (AG.esVacio()) {
			return caminos;
		} else {
			resolverAuxiliar (AG , X , caminoActual, caminos );
			return caminos;
		}
	}
	
	private void resolverAuxiliar (ArbolGeneral<Character> AG , Integer X , 
			ListaGenerica<Character> caminoActual , 
			ListaGenerica<ListaEnlazadaGenerica<Character>> caminos ) {
		
		caminoActual.agregarFinal(AG.getDato());
		
		if (AG.esHoja()) {
			if (caminoActual.tamanio()  == X) {
				caminos.agregarFinal((ListaEnlazadaGenerica<Character>) caminoActual.clonar());
			}
		}
		if (AG.tieneHijos()) { 
			ListaGenerica<ArbolGeneral<Character>> hijos = AG.getHijos();
			hijos.comenzar();
			while (!hijos.fin()) {
				ArbolGeneral<Character> aux = hijos.proximo(); 
				resolverAuxiliar(aux , X , caminoActual , caminos);
				caminoActual.eliminarEn(caminoActual.tamanio());
			}
		}
			
			
	}
	
	
}
