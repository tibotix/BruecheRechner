package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchSwapOperation extends SingleBruchOperation {

  public BruchSwapOperation(Bruch b1) {
    super(b1);
  }

  public Bruch handle_internal_operation(Object... x) throws ConvertBruchException {
    return new Bruch(b1.nenner, b1.zaehler); 
  }
  
}