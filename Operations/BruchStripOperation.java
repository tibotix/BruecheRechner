package Operations;

import java.math.*;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;

public class BruchStripOperation extends SingleBruchOperation {

  public BruchStripOperation(Bruch b1) {
    super(b1);
  }

  private void check_both_negative() {
    if (b1.zaehler < 0 && b1.nenner < 0) {
      b1.zaehler = -b1.zaehler;
      b1.nenner = -b1.nenner;
    }
  }

  private int gcd(int a, int b) {
    BigInteger big_a = BigInteger.valueOf(a);
    BigInteger big_b = BigInteger.valueOf(b);
    return big_a.gcd(big_b).intValue();
  }

  public Bruch handle_internal_operation(Object... x) throws ConvertBruchException {
    check_both_negative();
    int gcd_num = (int) this.gcd(b1.zaehler, b1.nenner);
    return new Bruch(b1.zaehler/gcd_num, b1.nenner/gcd_num); 
  }
  
}