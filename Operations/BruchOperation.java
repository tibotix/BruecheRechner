package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public abstract class BruchOperation{
  
  protected BruchOperation(){
    ;  
  }
  
  protected abstract Bruch handle_internal_operation(Object... args) throws ConvertBruchException;
  
  public Bruch handle_operation(Object... args) throws ConvertBruchException{
    Bruch b = this.handle_internal_operation(args);
    return b;
  }
  
  
}