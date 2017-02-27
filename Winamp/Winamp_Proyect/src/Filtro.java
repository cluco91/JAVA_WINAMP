import java.io.File;
import java.io.FileFilter;
//Esta clase es para filtrar los archivos con extension mp3
public class Filtro implements FileFilter {
	private final String [] extensionok = new String [] {"mp3"}; 
	//Aqui podria agregar cualquier extension ya sea wav, png, jpg, etc
	public boolean accept (File file)
	{
		for (String extension : extensionok)
		{
			if (file.getName().toLowerCase().endsWith(extension))
			{
				return true;
			}
		}
		return false;
	}
}
