package ru.rsatu.lr4.main;

import java.io.File;
import ru.rsatu.lr4.minterface.ModuleInterface;
import ru.rsatu.lr4.loader.ModuleLoader;

public class Main {
  
  public static void main(String args[]) {
    String modulePath = "modules";
    
    //Экземпляр загрузчика модулей
    ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

    //Получение списка доступных модулей
    File dir = new File(modulePath);
    String[] modules = dir.list();
    
    //Загрузка и исполнение модуля
    for (String module: modules) {
      try {
        String moduleName = module.split(".class")[0];
		Class<?> clazz = loader.loadClass(moduleName); 
		ModuleInterface execute = (ModuleInterface) clazz.newInstance();

        execute.load();
		execute.run();
		execute.unload();
        
      } catch (ClassNotFoundException e) {
      e.printStackTrace();
   }    
   catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
     e.printStackTrace();
    }	
   }
    
    
  }

}