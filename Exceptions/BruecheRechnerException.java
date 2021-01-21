package Exceptions;

public class BruecheRechnerException extends Exception{
    private static final long serialVersionUID = 1L;

    public BruecheRechnerException(String err_msg) {
      super(err_msg);
    }
}
