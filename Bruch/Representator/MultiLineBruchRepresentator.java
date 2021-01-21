package Bruch.Representator;

import Bruch.Bruch;
import Bruch.Representator.Abstract.BruchRepresentator;



public class MultiLineBruchRepresentator extends BruchRepresentator{
  
  public MultiLineBruchRepresentator(){
    ;
  }


  protected String representate_negative_bruch(Bruch b1, String message){
    String res = "";
    res += " ".repeat(message.length()) + "  " + Math.abs(b1.zaehler) + "\n";
    res += message + "- -" + "\n";
    res += " ".repeat(message.length()) + "  " + Math.abs(b1.nenner) + "\n";
    return res;
  }

  protected String representate_positive_bruch(Bruch b1, String message){
    String res = "";
    res += " ".repeat(message.length()) + "  " + b1.zaehler + "\n";
    res += message + "+ -" + "\n";
    res += " ".repeat(message.length()) + "  " + b1.nenner + "\n";
    return res;
  }

  protected String representate_whole_number(int number, String message){
    return message + String.valueOf(number);
  }
}