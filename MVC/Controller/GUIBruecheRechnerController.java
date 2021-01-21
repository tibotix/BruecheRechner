package MVC.Controller;

import Bruch.Bruch;
import Exceptions.BruecheRechnerException;
import MVC.Model.BruecheRechnerModel;
import MVC.View.BruchInput;
import MVC.View.BruecheRechnerView;
import MVC.View.TwoBrücheArithmeticOperatorInput;
import Operations.ArithmeticOperationsEnum;

//Handle User Input
public class GUIBruecheRechnerController extends BruecheRechnerController{

    public GUIBruecheRechnerController(BruecheRechnerModel model, BruecheRechnerView view) {
        super(model, view);
    }

    public void handle_negotiate_request(BruchInput[] brüche_inputs, TwoBrücheArithmeticOperatorInput[] operator_inputs, int index){
        try{
            ArithmeticOperationsEnum[] converted_operations = this.convert_input_operations(operator_inputs);
            Bruch[] converted_brüche = this.convert_input_brüche(brüche_inputs);

            converted_brüche[index] = converted_brüche[index].negotiate();

            this.set_new_inputs(converted_brüche, converted_operations);

            this.model.calculate();
        }catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }

    public void handle_swap_request(BruchInput[] brüche_inputs, TwoBrücheArithmeticOperatorInput[] operator_inputs, int index){
        try{
            ArithmeticOperationsEnum[] converted_operations = this.convert_input_operations(operator_inputs);
            Bruch[] converted_brüche = this.convert_input_brüche(brüche_inputs);
            
            converted_brüche[index] = converted_brüche[index].swap();

            this.set_new_inputs(converted_brüche, converted_operations);

            this.model.calculate();
        }catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }

    public void handle_strip_request(BruchInput[] brüche_inputs, TwoBrücheArithmeticOperatorInput[] operator_inputs, int index){
        try{
            ArithmeticOperationsEnum[] converted_operations = this.convert_input_operations(operator_inputs);
            Bruch[] converted_brüche = this.convert_input_brüche(brüche_inputs);
            
            converted_brüche[index] = converted_brüche[index].strip();

            this.set_new_inputs(converted_brüche, converted_operations);

            this.model.calculate();
        }catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }        

    public void handle_calculate_request(BruchInput[] brüche_inputs, TwoBrücheArithmeticOperatorInput[] operator_inputs) {
        try{
            Bruch[] converted_brüche = this.convert_input_brüche(brüche_inputs);
            ArithmeticOperationsEnum[] converted_operations = this.convert_input_operations(operator_inputs);
            
            this.set_new_inputs(converted_brüche, converted_operations);

            this.model.calculate();
        }catch(BruecheRechnerException e){
            this.view.error(e.getMessage());
        }
    }

    private ArithmeticOperationsEnum[] convert_input_operations(TwoBrücheArithmeticOperatorInput[] input) throws BruecheRechnerException {
        ArithmeticOperationsEnum[] converted_operations = new ArithmeticOperationsEnum[input.length];
        for(int i=0; i<input.length; i++){
            converted_operations[i] = ArithmeticOperationsEnum.fromSign(input[i].get_selected_button_text());
        }
        return converted_operations;
    }

    private Bruch[] convert_input_brüche(BruchInput[] input) throws BruecheRechnerException {
        Bruch[] converted_brüche = new Bruch[input.length];
        for (int i = 0; i < input.length; i++) {
            converted_brüche[i] = input[i].to_bruch();
        }
        return converted_brüche;
    }

    private void set_new_inputs(Bruch[] brüche, ArithmeticOperationsEnum[] operations) throws BruecheRechnerException {
        this.model.set_input_brüche(brüche);
        this.model.set_input_operations(operations);
    }

    
}
