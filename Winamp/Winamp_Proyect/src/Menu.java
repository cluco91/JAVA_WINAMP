import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Menu {
	//Metodo del Menu
	public static void Iniciar_Menu() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader entrada = new BufferedReader(isr);
		//Atributos
		String path;
		String cancion;         
		String cancion_pr;     
		String ruta_total =""; //Inicio la ruta_total
		int index; //Para el indice
		int r;     //El indice para referenciar un path
		int op = 0; //opcion para Menu Principal
		int sub_op=0;  //opcion para entrar en modo Reproductor
		int sub_op2=0; //opcion para el Menu Reproductor
		int sub_op3=0; //opcion para definir que hacer con el audio seleccionado

		Lista l = new Lista("--",0); //Inicializo la Lista
		do{
			System.out.println();
			System.out.println("Winamp v0.1");
			System.out.println("Menu Principal");
			System.out.println();
			System.out.println("[1] Insertar");
			System.out.println("[2] Eliminar");
			System.out.println("[3] Reproductor");
			System.out.println("[4] Creditos");
			System.out.println("[5] Salir");
			System.out.println();
			System.out.print("Ingresa una opcion: ");
			op = Integer.parseInt(entrada.readLine());		
			switch (op) {
			case 1:
				//Inserto un path a la lista y lo asocio a un indice
				System.out.println();
				System.out.println("<Modo Insertar>");
				System.out.println();
				System.out.print("Ingrese el path: ");
				path = (entrada.readLine());
				System.out.print("Ingrese un indice para ese path: ");
				index = Integer.parseInt(entrada.readLine());
				l.insertar(path, index);
				break;
			case 2:
				//Elimino un path de la lista mediante el indice asociado
				System.out.println();
				System.out.println("<Modo Eliminar>");
				System.out.println();
				System.out.print("Ingrese el indice del path que desea borrar: ");
				index = Integer.parseInt(entrada.readLine());
				l.eliminar(index);
				break;
			case 3:				
				//Entro en modo Reproductor
				do {		
					System.out.println();
					System.out.println("¿Qué desea hacer?");
					System.out.println();
					System.out.println("[1] Mostrar paths ingresados."); 
					System.out.println("[2] Menu Reproductor.");
					System.out.println("[3] Atrás.");
					System.out.println();
					System.out.print("Ingresa una opcion: ");
					sub_op = Integer.parseInt(entrada.readLine());
					switch (sub_op){
					case 1:
						//Me muestra los paths que tiene la lista con su respectivo indice
						System.out.println();
						l.mostrar(l);
						System.out.println();
						break;
					case 2:
						//Ingreso el indice asociado al path con el que voy a trabajar
						System.out.println();
						System.out.print("Ingrese un indice asociado a un path: ");
						r = Integer.parseInt(entrada.readLine());
						String ruta = l.mostrarPath(r); //Le asigno a la ruta el path asociado al indice ingresado
						System.out.println("El path escogido es: "+ruta);
						do{
						System.out.println();
						System.out.println("Menu Reproductor");
						System.out.println();
						System.out.println("[1] Ver el contenido de ese path.");
						System.out.println("[2] Escoger un archivo .mp3 en ese path.");
						System.out.println("[3] Atrás.");
						System.out.println();
						System.out.print("Ingresa una opcion: ");
						sub_op2 = Integer.parseInt(entrada.readLine());
						switch (sub_op2){
						case 1:
							/*Me muestra solo los archivos con extension mp3 contenidos 
							 en la carpeta asociada al path ingresado en la lista*/
							System.out.println();
							File dir2 = new File (ruta);
							File [] files2 = dir2.listFiles(new Filtro());
							System.out.println();
							System.out.println("Los archivos con extension .mp3 en este directorio son:");
							System.out.println();
							for (File f : files2)
							{System.out.println(f.getName());}
							break;
						case 2:
							/*Aqui ingresamos el nombre del archivo con extension mp3, que queremos reproducir*/
							System.out.println();
							System.out.print("Ingrese el nombre del archivo: ");
							cancion = (entrada.readLine());
							cancion_pr = cancion+".mp3";//Aqui se le agrega la extension .mp3 al nombre de la cancion escogido
							ruta_total = ruta + "\\" + cancion_pr; //Esta es la ruta para la reproduccion de la cancion
							do{
								System.out.println();
								System.out.println("¿Qué desea hacer ahora?");
								System.out.println();
								System.out.println("[1] Reproducir");
								System.out.println("[2] Atrás");
								System.out.println();
								System.out.print("Ingrese una opción: ");
								sub_op3 = Integer.parseInt(entrada.readLine());
								switch(sub_op3){
								case 1:
								System.out.println();
								System.out.println("Ahora Suena ... "+cancion);
								musica(ruta_total);
								break;	
								}
							}while (sub_op3!=2);
							break;
						}
						}while (sub_op2!=3);
							break;
					}
				}while (sub_op!=3);
				break;
			case 4:
				//Creditos del Programa
				System.out.println();
				System.out.println("Creditos");
				System.out.println("Programado por Cristián Luco R.");
				break;
			}
		}while (op != 5);
	}
	//Metodo del audio
	public static void musica (String path){
		try {
            FileInputStream fis;
            Player player;
            fis = new FileInputStream(path);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis); 
            player.play();             
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
}
