package parcial;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos == null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos == null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}

	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}

	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo))
				hijos.eliminar(hijo);
		}
	}

	public ListaGenerica<T> numerosImparesMayoresQuePreOrden(Integer n) {
		ListaGenerica<T> l = new ListaEnlazadaGenerica<T>();
		if (this.getDato().getClass().getSimpleName().equals("Integer")) {
			numerosImparesMayoresQuePreOrden(n, l);
		}
		return l;
	}
	// me fijo que sea de un integer antes (el dato, mi dato)

	private void numerosImparesMayoresQuePreOrden(Integer n, ListaGenerica<T> lista) {
		int num = (int) this.getDato();
		// lo des-wrappeo (?)
		if (((num % 2) == 1) && (num > n))
			lista.agregarFinal(this.getDato());
		// si es impar y mayor a n, lo agrego a mi listaa
		if (this.tieneHijos()) {
			// si el nodo tiene hijos, obtengo todos y lo recorro
			ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
			listaHijos.comenzar();
			// limpio la lista de hijos y, hasta que no termine
			// recorro
			while (!listaHijos.fin())
				listaHijos.proximo().numerosImparesMayoresQuePreOrden(n, lista);
		}
	}

	public ListaGenerica<T> numerosImparesMayoresQueInOrden(Integer n) {
		ListaGenerica<T> l = new ListaEnlazadaGenerica<T>();
		if (this.getDato().getClass().getSimpleName().equals("Integer")) {
			numerosImparesMayoresQueInOrden(n, l);
		}
		return l;
	}

	private void numerosImparesMayoresQueInOrden(Integer n, ListaGenerica<T> lista) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
			listaHijos.comenzar();
			listaHijos.proximo().numerosImparesMayoresQueInOrden(n, lista);
		}
		int num = (int) this.getDato();
		if (((num % 2) == 1) && (num > n))
			lista.agregarFinal(this.getDato());
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
			while (!lhijos.fin())
				lhijos.proximo().numerosImparesMayoresQuePreOrden(n, lista);
		}
	}

	public ListaGenerica<T> numerosImparesMayoresQuePostOrden(Integer n) {
		ListaGenerica<T> l = new ListaEnlazadaGenerica<T>();
		if (this.getDato().getClass().getSimpleName().equals("Integer")) // if (object instanceof Integer)
		{
			numerosImparesMayoresQuePostOrden(n, l);
		}
		return l;
	}

	private void numerosImparesMayoresQuePostOrden(Integer n, ListaGenerica<T> lista) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
			listaHijos.comenzar();
			while (!listaHijos.fin())
				listaHijos.proximo().numerosImparesMayoresQuePostOrden(n, lista);
		}
		int num = (int) this.getDato();
		if (((num % 2) == 1) && (num > n))
			lista.agregarFinal(this.getDato());

	}

	public ListaGenerica<T> numerosImparesMayoresQuePorNiveles(Integer n) {
		ListaGenerica<T> l = new ListaEnlazadaGenerica<T>();
		if (this.getDato().getClass().getSimpleName().equals("Integer")) {
			int num = (int) this.getDato();
			ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
			ArbolGeneral<T> aux;

			cola.encolar(this);
			cola.encolar(null);

			while (!cola.esVacia()) {
				aux = cola.desencolar();
				if (aux != null) {
					num = (int) aux.getDato();
					if (((num % 2) == 1) && (num > n))
						l.agregarFinal(aux.getDato());
					if (aux.tieneHijos()) {
						ListaGenerica<ArbolGeneral<T>> lhijos = aux.getHijos();
						lhijos.comenzar();
						while (!lhijos.fin()) {
							cola.encolar(lhijos.proximo());
						}

					}
				} else if (!cola.esVacia())
					cola.encolar(null);
			}
		}
		return l;
	}

	public Integer altura() {
		int altura = -1;
		// inicializo en un valor imposible

		if (this.esHoja())
			// altura = 0; -> mal
			return 0;
		else {
			if (this.tieneHijos()) {
				ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
				listaHijos.comenzar();
				// limpio la lista
				while (!listaHijos.fin()) {
					// mientras no termine la lista
					altura = Math.max(altura, listaHijos.proximo().altura());
					// me quedo con el maximo entre la altura guardada y
					// la altura que voy obteniendo de recorrer la lista.
				}
			}
		}
		return altura + 1;
	}

	public Integer ancho() {
		// Tengo que hacer un recorrido por niveles
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T> aux;

		cola.encolar(this);
		cola.encolar(null);
		// encolo el arbol y luego un null, que va a ser mi marca

		int cantidadNodos = 0;
		int maximo = -1;
		int nivel = 0;

		while (!cola.esVacia()) {
			// mientras no se vacie, desencolo un elemento del arbol
			aux = cola.desencolar();
			if (aux != null) {
				// si ese elemento no es null...
				cantidadNodos = cantidadNodos + 1;
				// sumo la cantidad de nodos
				if (aux.tieneHijos()) {
					// si tiene hijos, los guardo en la lista de hijos ...
					ListaGenerica<ArbolGeneral<T>> listaHijos = aux.getHijos();
					listaHijos.comenzar();
					// ... y limpio esa lista
					while (!listaHijos.fin()) {
						cola.encolar(listaHijos.proximo());
						// encolo los hijos
					}
				}
			} else
			// en caso de que sea null ( es decir llegué a una marca de nivel)
			if (!cola.esVacia()) {
				// mientras no este vacia
				cola.encolar(null);
				// vuelvo a marcaar que es null
				nivel++;
				if (cantidadNodos > maximo)
					// si la cantidad de nodos que conte es mayor al maximo
					// guardado, actualizo:
					maximo = cantidadNodos;
				cantidadNodos = 0;
				// y reinicio mi contador de nodos
			}
			// salgo cuando la cola esta vacia
		}
		return maximo;
	}

	public Integer nivel(T dato) {
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T> aux;

		cola.encolar(this);
		cola.encolar(null);

		int nivel = 0;

		while (!cola.esVacia()) {
			aux = cola.desencolar();
			if (aux != null) {
				if (aux.getDato() == dato)
					return nivel;
				if (aux.tieneHijos()) {
					ListaGenerica<ArbolGeneral<T>> lhijos = aux.getHijos();
					lhijos.comenzar();
					while (!lhijos.fin()) {
						cola.encolar(lhijos.proximo());
					}
				}
			} else if (!cola.esVacia()) {
				cola.encolar(null);
				nivel++;
			}
		}
		return -1;
	}

	public boolean esAncestro(T a, T b) {
		ListaGenerica<T> lista = new ListaEnlazadaGenerica<T>();
		ListaGenerica<T> camino = new ListaEnlazadaGenerica<T>();
		lista.agregarInicio(this.getDato());
		esAncestro(a, b, lista, camino);
		if ((camino.incluye(a)) && (camino.incluye(b))) {
			return true;
		}
		return false;
	}

	private void clonar(ListaGenerica<T> lista, ListaGenerica<T> camino) {
		lista.comenzar();
		while (!lista.fin()) {
			camino.agregarFinal(lista.proximo());
		}
	}

	private void esAncestro(T a, T b, ListaGenerica<T> lista, ListaGenerica<T> camino) {
		if (this.getDato() == b)
			clonar(lista, camino);
		if (camino.esVacia()) {
			ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
			lhijos.comenzar();
			while ((!lhijos.fin()) && (camino.esVacia())) {
				ArbolGeneral<T> aux = lhijos.proximo();
				lista.agregarFinal(aux.getDato());
				aux.esAncestro(a, b, lista, camino);
				lista.eliminarEn(lista.tamanio());
			}
		}
	}

}