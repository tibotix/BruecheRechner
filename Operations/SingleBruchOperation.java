package Operations;

import Bruch.Bruch;

public abstract class SingleBruchOperation extends BruchOperation{
  
  protected Bruch b1;
  
  protected SingleBruchOperation(Bruch b1){
    this.b1 = b1;
  }
  
}