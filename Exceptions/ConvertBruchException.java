package Exceptions;



public class ConvertBruchException extends BruecheRechnerException{
  
  private static final long serialVersionUID = 1L;

  public ConvertBruchException(String err_msg) {
    super(err_msg);
  }
  
}