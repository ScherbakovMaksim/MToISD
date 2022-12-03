package ru.rsatu.lr4.loader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ModuleLoader extends ClassLoader {  
 
  private String pathtobin; //Путь до директории с модулями
  
  public ModuleLoader(String pathtobin, ClassLoader parent) {
    super(parent);    
    this.pathtobin = pathtobin;    
  }

  @Override	 //переопределение метода findClass стандартного загрузчика через обработку исключений
  public Class<?> findClass(String className) throws ClassNotFoundException {
    try {
      //Получение байт-кода из файла и загрузка класса      
      byte b[] = fetchClassFromFS(pathtobin + className + ".class");
      return defineClass(className, b, 0, b.length);
    } catch (FileNotFoundException ex) {	//обработка исключений
      return super.findClass(className);
    } catch (IOException ex) {
      return super.findClass(className);
    }
    
  }
  
  private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
    InputStream is = new FileInputStream(new File(path));
    
    // Получение размера файла
    long length = new File(path).length();

    if (length > Integer.MAX_VALUE) {
        System.out.println("File is too large"); //файл слишком большой
      }
    
    // Сохранение полученных данных в массиве
    byte[] bytes = new byte[(int)length];
  
    // Чтение в байтах
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length
        && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
      offset += numRead;
    }
  
    // Проверка, что все байты прошли чтение
    if (offset < bytes.length) {
      throw new IOException("The file could not be read: "+path);
    }
  
    // Close the input stream and return bytes
    is.close();
    return bytes;

  }
}