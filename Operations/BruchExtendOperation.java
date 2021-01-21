package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchExtendOperation extends SingleBruchOperation {

  public BruchExtendOperation(Bruch b1) {
    super(b1);
  }

  protected Bruch handle_internal_operation(Object... args) throws ConvertBruchException {
    int num = (int) args[0];
    return new Bruch(b1.zaehler*num, b1.nenner*num); 
  }

}