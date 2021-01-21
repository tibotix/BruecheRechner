package Bruch.Representator;

import Bruch.Bruch;
import Bruch.Representator.Abstract.BruchRepresentator;


public class SingleLineBruchRepresentator extends BruchRepresentator{
  
  public SingleLineBruchRepresentator(){
    ;
  }


  protected String representate_negative_bruch(Bruch b1, String message) {
     return message + "- " + Math.abs(b1.zaehler) + "/" + Math.abs(b1.nenner);
  }

  protected String representate_positive_bruch(Bruch b1, String message) {
    return message + b1.zaehler + "/" + b1.nenner;
  }

  protected String representate_whole_number(int number, String message){
    return message + String.valueOf(number);
  }
  
}