package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchNegotiateOperation extends SingleBruchOperation {

  public BruchNegotiateOperation(Bruch b1) {
    super(b1);
  }

  public Bruch handle_internal_operation(Object... x) throws ConvertBruchException {
    return new Bruch(-b1.zaehler, b1.nenner);
  }
  
  
  
}