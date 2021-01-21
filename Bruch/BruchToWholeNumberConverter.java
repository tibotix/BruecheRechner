package Bruch;

import Exceptions.*;

public class BruchToWholeNumberConverter {
  
  public BruchToWholeNumberConverter(){
    ;
  }
  
  public int try_convert_to_whole_number(Bruch b1) throws ConvertBruchException{
    Bruch b2 = b1.strip();
    if(b2.nenner!=1 && b2.nenner!=-1){
      throw new ConvertBruchException("Cannot convert to whole number");
    }
    return b2.nenner*b2.zaehler;
  }
  
}