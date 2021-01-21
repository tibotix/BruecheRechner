package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchMultOperation extends MultiBruchOperation {

  public BruchMultOperation(Bruch b1, Bruch b2) {
    super(b1, b2);
  }

  public Bruch handle_internal_operation(Object... x) throws ConvertBruchException {
    return new Bruch(b1.zaehler*b2.zaehler, b1.nenner*b2.nenner); 
  }
  
  
  
}