package Operations;

import Bruch.Bruch;                                  

public abstract class MultiBruchOperation extends BruchOperation{
  
  protected Bruch b1;
  protected Bruch b2;
  
  protected MultiBruchOperation(Bruch b1, Bruch b2){
    this.b1 = b1;
    this.b2 = b2;
  }
  
}