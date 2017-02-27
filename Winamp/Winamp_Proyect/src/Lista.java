public class Lista
{
	//Atributos
	String dato; 
	int indice; // indice posicion
	Lista sig;  
	static int numeroDeCasillas = 0; 
	//Constructor	
	Lista(String s, int ind)
	{
		dato = s;
		indice = ind;
		sig = null;
		numeroDeCasillas++;
	}
	//Metodo para Insertar paths con su respectivo indice
	public Lista insertar(String s, int ind)
	{
		Lista p = this;
		if (p == null) return new Lista (s, ind);
		if (ind == p.indice) return p; //Así no ingresará el mismo indice
		if (ind < p.indice){		
			Lista q = new Lista (s,ind);
			q.sig = p;
			return q;	
		}
		if (p.sig == null)
			p.sig = new Lista (s,ind);
		else
			p.sig =p.sig.insertar(s, ind);
		return p;
	}
	//Metodo para Mostrar paths con su respectivo indice
	public void mostrar(Lista l)
	{
		if (l!=null){
			System.out.println("Path: "+l.dato+ " ; " + "Indice: "+l.indice);
			mostrar(l.sig);
		}
	}
	//Metodo para Eliminar path asociado a un indice
	public Lista eliminar (int x){
		Lista p = this;
		if (p==null) return null;
		if (x==p.indice) return p.sig;
		if (p.sig != null)
			p.sig = p.sig.eliminar(x);
		return p;	
	}
	//Metodo para Mostrar el path asociado a un indice
	public String mostrarPath (int ind){
		String datos = ""; 
		Lista p = this;
		while (p!=null){
			if (p.indice == ind){
				datos = p.dato;
			}
			p=p.sig;
		}
		return datos;
	}
}
