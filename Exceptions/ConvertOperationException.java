package Exceptions;

public class ConvertOperationException extends BruecheRechnerException{
  
    private static final long serialVersionUID = 1L;
  
    public ConvertOperationException(String err_msg) {
      super(err_msg);
    }
    
  }