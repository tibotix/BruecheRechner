package MVC.Controller;

import Bruch.Bruch;
import Bruch.BruchFactory;
import Exceptions.BruecheRechnerException;
import Exceptions.ConvertBruchException;
import MVC.Model.BruecheRechnerModel;
import MVC.View.BruecheRechnerView;
import MVC.View.CmdBruecheRechnerView;
import Operations.ArithmeticOperationsEnum;

public class CmdBruecheRechnerController extends BruecheRechnerController {

    public CmdBruecheRechnerController(BruecheRechnerModel model, BruecheRechnerView view) {
        super(model, view);
    }

    public void handle_calculate_request() {
        try {
            this.model.calculate();
        } catch (ConvertBruchException e) {
            this.view.error(e.getMessage());
        }
    }

    public void handle_new_operator_input(int index, String new_operator_string){
        try{
            ArithmeticOperationsEnum new_operator = ArithmeticOperationsEnum.fromSign(new_operator_string);
            this.model.set_input_operation(new_operator, index);
            this.model.calculate();
        }catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }

    public void handle_swap_bruch(int index){
        try{
            this._check_initialized(this.model.input_brüche, index);
            Bruch b = this.model.input_brüche[index].swap();
            this.model.set_input_bruch(b, index);
            this.model.calculate();
        } catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }
    
    public void handle_negotiate_bruch(int index){
        try{
            this._check_initialized(this.model.input_brüche, index);
            Bruch b = this.model.input_brüche[index].negotiate();
            this.model.set_input_bruch(b, index);
            this.model.calculate();
        } catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }

    public void handle_strip_bruch(int index){
        try{
            this._check_initialized(this.model.input_brüche, index);
            Bruch b = this.model.input_brüche[index].strip();
            this.model.set_input_bruch(b, index);
            this.model.calculate();
        } catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }

    public void handle_change_bruch(int index) throws ConvertBruchException {
        ((CmdBruecheRechnerView) this.view).padded_print("");
        ((CmdBruecheRechnerView) this.view).representate_bruch(this.model.input_brüche[index],
                "Change Bruch No. " + (((Integer)index)+1) + ": ");

        try {
            Bruch b = BruchFactory.bruch_factory_cmd();
            this.model.set_input_bruch(b, index);
            this.model.calculate();
        } catch (BruecheRechnerException e) {
            this.view.error(e.getMessage());
        }
    }

    private void _check_initialized(Object[] array, int index) throws BruecheRechnerException {
        if(array[index] == null){
            throw new BruecheRechnerException("Cannot Access Bruch No. " + (((int)index)+1) + ": It is not intitialized yet!");
        }
    }
    
}
