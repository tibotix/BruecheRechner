package MVC.Model;

import java.util.Vector;

import Bruch.Bruch;
import Exceptions.BruecheRechnerException;
import Exceptions.ConvertBruchException;
import MVC.View.BruecheRechnerView;
import Operations.ArithmeticOperationsEnum;

//Main Logic
public class BruecheRechnerModel {

    private Vector<BruecheRechnerView> registered_views = new Vector<BruecheRechnerView>();
    public ArithmeticOperationsEnum[] input_operations;
    public Bruch[] input_brüche;
    public Bruch result;

    public BruecheRechnerModel(int brüche_input_count) {
        this.input_brüche = new Bruch[brüche_input_count];
        this.input_operations = new ArithmeticOperationsEnum[brüche_input_count - 1];
        this.initialize_input_brüche();
        this.initialize_input_operations();
    }

    private void initialize_input_brüche()  {
        for(int i=0; i<this.input_brüche.length; i++){
            try {
                this.input_brüche[i] = new Bruch(0, 1);
            } catch (BruecheRechnerException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize_input_operations(){
        for(int i=0; i<this.input_operations.length; i++){
            this.input_operations[i] = ArithmeticOperationsEnum.ADD;
        }
    }

    public void register_view(BruecheRechnerView view) {
        this.registered_views.add(view);
    }

    public void notify_views() throws ConvertBruchException {
        for (BruecheRechnerView view : this.registered_views) {
            view.update();
        }
    }

    private void check_array_length(Object[] array1, Object[] array2) throws BruecheRechnerException {
        if(array1.length != array2.length){
            throw new BruecheRechnerException("Cannot set array to model: Length Mismatch");
        }
    }

    private void check_out_of_bounds(Object[] array, int index) throws BruecheRechnerException {
        if(index > array.length){
            throw new BruecheRechnerException("Cannot access array at index " + index + ": OutOfBounds");
        }
    }

    public void set_input_bruch(Bruch b1, int index) throws BruecheRechnerException {
        this.check_out_of_bounds(this.input_brüche, index);
        this.input_brüche[index] = b1;
        this.notify_views();
    }

    public void set_input_operation(ArithmeticOperationsEnum operation, int index) throws BruecheRechnerException {
        this.check_out_of_bounds(this.input_operations, index);
        this.input_operations[index] = operation;
        this.notify_views();
    }

    public void set_input_brüche(Bruch[] input) throws BruecheRechnerException {
        this.check_array_length(this.input_brüche, input);
        this.input_brüche = input;
        this.notify_views();
    }

    public void set_input_operations(ArithmeticOperationsEnum[] input) throws BruecheRechnerException {
        this.check_array_length(this.input_operations, input);
        this.input_operations = input;
        this.notify_views();
    }

    public void set_result_bruch(Bruch b) throws ConvertBruchException {
        this.result = b;
        this.notify_views();
    }

    public void calculate() throws ConvertBruchException {
        if(this.input_brüche.length<2){
            throw new ConvertBruchException("You have to work with at least two fractions!");
        }
        Bruch b1 = this.input_brüche[0];
        for(int i=1; i<this.input_brüche.length; i++){
            Bruch b2 = this.input_brüche[i];
            b1 = this.input_operations[i-1].make_operation(b1, b2);
        }
        this.set_result_bruch(b1);
    }

}
