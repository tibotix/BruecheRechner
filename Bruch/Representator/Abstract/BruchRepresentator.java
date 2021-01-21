package Bruch.Representator.Abstract;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;


public abstract class BruchRepresentator {

    protected BruchRepresentator(){;}



    protected abstract String representate_negative_bruch(Bruch b1, String message);
    protected abstract String representate_positive_bruch(Bruch b1, String message);
    protected abstract String representate_whole_number(int number, String message);


    public String representate_bruch(Bruch b1, String message) throws ConvertBruchException {
        try{
            int whole_number = b1.as_whole_number();
            return this.representate_whole_number(whole_number, message);
        } catch(ConvertBruchException e){
            if(b1.is_negative()) {
                return this.representate_negative_bruch(b1, message);
            }else{        
                return this.representate_positive_bruch(b1, message);
            }
        }
    }
}
