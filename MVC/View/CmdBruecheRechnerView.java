package MVC.View;

import java.util.ArrayList;
import java.util.Arrays;

import Bruch.Bruch;
import Bruch.Representator.Abstract.BruchRepresentator;
import Exceptions.ConvertBruchException;
import MVC.Controller.BruecheRechnerController;
import MVC.Controller.CmdBruecheRechnerController;
import MVC.Model.BruecheRechnerModel;
import Operations.ArithmeticOperationsEnum;
import Utils.Utils;

public class CmdBruecheRechnerView extends BruecheRechnerView {

    public CmdBruecheRechnerView(BruchRepresentator representator, BruecheRechnerModel model) {
        super(representator, model);
    }

    public void padded_print(String message) {
        System.out.println("\n".repeat(7));
        System.out.println(message);
    }

    public void representate_bruch(Bruch b1, String message) throws ConvertBruchException {
        if(b1 != null){
            System.out.println(this.representator.representate_bruch(b1, message));
        } else {
            System.out.println(message + " -");
        }
    }

    public void init(){
        //main loop
        while(true){
            try {
                this._main_menu();
            } catch (ConvertBruchException e) {
                this.error(e.getMessage());
            }
        }
    }


    private void _main_menu() throws ConvertBruchException {
        this.padded_print("");
        System.out.println("On Which Entity do you want to perform an action?");
        System.out.println("1. Brüche");
        System.out.println("2. Operator");
        System.out.println("3. Calculate");
        System.out.println("4. Exit BruecheRechner (or hit Ctrl+C)");

        int choice = Utils.get_number("> ", 1, 4);

        switch (choice) {
            case 1: this._handle_brüche_selection(); break;
            case 2: this._handle_operator_selection(); break;
            case 3: this._handle_calculate_request(); break;
            case 4: System.exit(0);
            default:
        }
    }

    private void _handle_brüche_selection() throws ConvertBruchException {
        this.padded_print("");
        System.out.println("Welchen Bruch möchten Sie bearbeiten?");

        for(int i=0; i<this.model.input_brüche.length; i++){
            this.representate_bruch(this.model.input_brüche[i], i+1 + ". : ");
        }

        int choice = Utils.get_number("> ", 1, this.model.input_brüche.length);
        this._handle_bruch_action_menu(choice-1);
    }

    private void _handle_operator_selection(){
        this.padded_print("");
        System.out.println("Welchen Operator möchten Sie berarbeiten?");

        for(int i=0; i<this.model.input_operations.length; i++){
            System.out.println(i+1 + ". : " + this.model.input_operations[i].get_sign());
        }

        int choice = Utils.get_number("> ", 1, this.model.input_operations.length);
        this._handle_operator_action_menu(choice-1);
    }

    private void _handle_calculate_request(){
        for(BruecheRechnerController controller : this.registered_controllers){
            ((CmdBruecheRechnerController) controller).handle_calculate_request();
        }
    }

    private void _handle_bruch_action_menu(int index) throws ConvertBruchException {
        this.padded_print("");

        Bruch b = this.model.input_brüche[index];
        this.representate_bruch(b, "Actions for Bruch No. " + (((int)index)+1) + ": ");

        System.out.println("1. Negotiate Bruch");
        System.out.println("2. Swap Bruch");
        System.out.println("3. Strip Bruch");
        System.out.println("4. Change Bruch");
        System.out.println("5. Back");

        int choice = Utils.get_number("> ", 1,5);

        switch(choice){
            case 1: this._handle_negotiate_bruch(index); break;
            case 2: this._handle_swap_bruch(index); break;
            case 3: this._handle_strip_bruch(index); break;
            case 4: this._handle_change_bruch(index); break;
            case 5: this._handle_brüche_selection(); break;
        }

    }

    private void _handle_operator_action_menu(int index){
        this.padded_print("");

        ArithmeticOperationsEnum operator = this.model.input_operations[index];
        // No NullPointerException Check cause operators are initialzed with default value... not best practise but however
        System.out.println("New Value for Operator No. " + (((int)index)+1) + ": " + operator.get_sign());

        System.out.println("\n New Operator:");
        String new_operator_sting = Utils.input("> ", new ArrayList<String>(Arrays.asList(ArithmeticOperationsEnum.get_all_signs())));

        for(BruecheRechnerController controller : this.registered_controllers){
            ((CmdBruecheRechnerController) controller).handle_new_operator_input(index, new_operator_sting);
        }
    }

    private void _handle_negotiate_bruch(int index){
        for(BruecheRechnerController controller : this.registered_controllers){
            ((CmdBruecheRechnerController) controller).handle_negotiate_bruch(index);
        }
    }

    private void _handle_swap_bruch(int index){
        for(BruecheRechnerController controller : this.registered_controllers){
            ((CmdBruecheRechnerController) controller).handle_swap_bruch(index);
        }
    }

    private void _handle_strip_bruch(int index){
        for(BruecheRechnerController controller : this.registered_controllers){
            ((CmdBruecheRechnerController) controller).handle_strip_bruch(index);
        }
    }

    private void _handle_change_bruch(int index) throws ConvertBruchException {
        for(BruecheRechnerController controller : this.registered_controllers){
            ((CmdBruecheRechnerController) controller).handle_change_bruch(index);
        }
    }

    public void update() throws ConvertBruchException {
        this.padded_print("Calculation:\n");
        if(this.model.input_brüche.length > 0){
            this.representate_bruch(this.model.input_brüche[0], "");
        }
        for(int i=1; i<this.model.input_brüche.length; i++){
            this._representate_operator(this.model.input_operations[i-1]);
            representate_bruch(this.model.input_brüche[i], "");
        }
        this._seperated_print("=");
        this._update_result_bruch();
        Utils.input("ENTER...");
    }

    private void _representate_operator(ArithmeticOperationsEnum operation){
        if(operation != null){
            this._seperated_print(operation.get_sign());
        } else {
            System.out.println(" -");
        }
    }

    private void _seperated_print(String message){
        System.out.println("--"+"-".repeat(message.length()) + "--");
        System.out.println("  " + message);
        System.out.println("--"+"-".repeat(message.length()) + "--");
    }

    private void _update_result_bruch() throws ConvertBruchException {
        if(this.model.result != null){
            this.representate_bruch(this.model.result, "");
        } else {
            System.out.println("-");
        }
    }

    public void error(String message){
        this.padded_print("ERROR:\n" + message);
        Utils.input("ENTER...");
    }
    
    
}
