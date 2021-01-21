package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchSubOperation extends MultiBruchOperation {

  public BruchSubOperation(Bruch b1, Bruch b2) {
    super(b1, b2);
  }

  public Bruch handle_internal_operation(Object... x) throws ConvertBruchException {
    Bruch erweitert1 = b1.extend(b2.nenner);
    Bruch erweitert2 = b2.extend(b1.nenner);
    return new Bruch(erweitert1.zaehler-erweitert2.zaehler, erweitert1.nenner);
  }
  
  
  
}