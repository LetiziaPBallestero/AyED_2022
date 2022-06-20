package parcial;


public class testParcial {
	public static void main(String[] args) {

	ArbolGeneral<Character> A = new ArbolGeneral<Character>('a');
	ArbolGeneral<Character> B = new ArbolGeneral<Character>('b');
	ArbolGeneral<Character> C = new ArbolGeneral<Character>('c');
	ArbolGeneral<Character> D = new ArbolGeneral<Character>('d');
	ArbolGeneral<Character> E = new ArbolGeneral<Character>('e');
	ArbolGeneral<Character> F = new ArbolGeneral<Character>('f');
	ArbolGeneral<Character> G = new ArbolGeneral<Character>('g');
	ArbolGeneral<Character> H = new ArbolGeneral<Character>('h');
	ArbolGeneral<Character> I = new ArbolGeneral<Character>('i');
	
	ArbolGeneral<Character> AUX = new ArbolGeneral<Character>('a');
	
	/* 				A
	 * 	B			C			D
	 * E		 F    G	
	 * 			H I	
	 */
	
	A.agregarHijo(B);
	A.agregarHijo(C);
	A.agregarHijo(D);
	
	B.agregarHijo(E);
	
	C.agregarHijo(F);
	C.agregarHijo(G);
	
	F.agregarHijo(H);
	F.agregarHijo(I);
	
	parcialTema2Fecha2 test = new parcialTema2Fecha2(); 
	ListaGenerica<ListaEnlazadaGenerica<Character>> listaDeListas = test.resolver(A,4);
	
	listaDeListas.comenzar();
	
	while (!listaDeListas.fin()) {
		ListaGenerica<Character> l = listaDeListas.proximo();
			l.comenzar();
			while (!l.fin()) {
				System.out.print(l.proximo() + " - ");
			}
			System.out.println(" ");
			
	}
	
	parcialTema2Fecha2 test2 = new parcialTema2Fecha2();
	ListaGenerica<ListaEnlazadaGenerica<Character>> listaUno = test.resolver (AUX , 1 );
	
	listaUno.comenzar(); 
	
	while (!listaUno.fin()) {
		ListaGenerica<Character> lAux = listaUno.proximo();
		lAux.comenzar();
		while (!lAux.fin()) {
			System.out.print(lAux.proximo());
		}
	}
	
	
	}

}
