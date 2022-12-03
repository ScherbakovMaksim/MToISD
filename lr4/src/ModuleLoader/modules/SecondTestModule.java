import ru.rsatu.lr4.minterface.ModuleInterface;
public class SecondTestModule implements ModuleInterface {

	  @Override
	  public void load() {
	    System.out.println("Module " + this.getClass() + " loading ...");
	  }

	  @Override
	  public int run() {
	    System.out.println("Module " + this.getClass() + " running ...");
	    return ModuleInterface.EXIT_SUCCESS;
	  }

	  @Override
	  public void unload() {
	    System.out.println("Module " + this.getClass() + " inloading ...");    
	  }
	}