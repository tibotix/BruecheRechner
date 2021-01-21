package Bruch;

import Operations.*;
import Exceptions.*;

public class Bruch{
  
  public int zaehler;
  public int nenner;
  private BruchToWholeNumberConverter whole_number_converter;

  private void check_division_by_zero() throws ConvertBruchException{
    if(this.nenner==0){
      throw new ConvertBruchException("Nenner cannot have a value of 0");
    }
  }
  
  
  public Bruch(int zaehler, int nenner) throws ConvertBruchException{
    this.zaehler = zaehler;
    this.nenner = nenner;
    check_division_by_zero();
    this.whole_number_converter = new BruchToWholeNumberConverter();
  }
  
  public Bruch extend(int x) throws ConvertBruchException {
    return new BruchExtendOperation(this).handle_operation(x);  
  }
  
  public Bruch mult(Bruch b) throws ConvertBruchException {
    return new BruchMultOperation(this, b).handle_operation().strip(); 
  }
  
  public Bruch add(Bruch b) throws ConvertBruchException {
    return new BruchAddOperation(this, b).handle_operation().strip(); 
  }
  
  public Bruch sub(Bruch b) throws ConvertBruchException {
    return new BruchSubOperation(this, b).handle_operation().strip(); 
  }
  
  public Bruch div(Bruch b) throws ConvertBruchException {
    return new BruchMultOperation(this, b.swap()).handle_operation().strip(); 
  }

  public Bruch pow(int x) throws ConvertBruchException {
    return new BruchPowOperation(this).handle_operation(x).strip();
  }
  
  public Bruch swap() throws ConvertBruchException {
    return new BruchSwapOperation(this).handle_operation().strip();
  }
  
  public Bruch strip() throws ConvertBruchException {
    return new BruchStripOperation(this).handle_operation(); 
  }
  
  public Bruch negotiate() throws ConvertBruchException {
    return new BruchNegotiateOperation(this).handle_operation().strip();
  }
  
  public int as_whole_number() throws ConvertBruchException{
    return this.whole_number_converter.try_convert_to_whole_number(this);
  }

  public boolean is_negative() throws ConvertBruchException {
    Bruch b2 = this.strip();
    return (b2.zaehler<0 || b2.nenner<0);
  }
  
}