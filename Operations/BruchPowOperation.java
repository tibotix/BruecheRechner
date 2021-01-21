package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchPowOperation extends SingleBruchOperation {

    public BruchPowOperation(Bruch b1) {
        super(b1);
    }

    protected Bruch handle_internal_operation(Object... args) throws ConvertBruchException {
        int num = (int) args[0];
        return new Bruch((int) Math.pow(b1.zaehler, num), (int) Math.pow(b1.nenner, num));
    }
    
}
