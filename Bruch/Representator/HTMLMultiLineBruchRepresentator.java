package Bruch.Representator;

import Bruch.Bruch;
import Bruch.Representator.Abstract.BruchRepresentator;

public class HTMLMultiLineBruchRepresentator extends BruchRepresentator {
  
    public HTMLMultiLineBruchRepresentator(){
      ;
    }
  
  
    protected String representate_negative_bruch(Bruch b1, String message){
      String res = "<html><body>";
      res += "&nbsp;".repeat(message.length()) + "&nbsp;&nbsp;" + Math.abs(b1.zaehler) + "<br>";
      res += message + "-&nbsp;-" + "<br>";
      res += "&nbsp;".repeat(message.length()) + "&nbsp;&nbsp;" + Math.abs(b1.nenner);
      res += "</body></html>";
      return res;
    }
  
    protected String representate_positive_bruch(Bruch b1, String message){
      String res = "<html><body>";
      res += "&nbsp;".repeat(message.length()) + "&nbsp;&nbsp;&nbsp;" + b1.zaehler + "<br>";
      res += message + "+&nbsp;-" + "<br>";
      res += "&nbsp;".repeat(message.length()) + "&nbsp;&nbsp;&nbsp;" + b1.nenner;
      res += "</body></html>";
      return res;
    }
  
    protected String representate_whole_number(int number, String message){
      return "<html><body>" + message + String.valueOf(number) + "</body></html>";
    }
  }