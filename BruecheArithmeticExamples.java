/*
 * @author: Tizian Seehaus 
 */

import Bruch.*;
import Bruch.Representator.*;
import Bruch.Representator.Abstract.BruchRepresentator;
import Exceptions.BruecheRechnerException;
import MVC.Controller.BruecheRechnerController;
import MVC.Controller.CmdBruecheRechnerController;
import MVC.Controller.GUIBruecheRechnerController;
import MVC.Model.BruecheRechnerModel;
import MVC.View.BruecheRechnerView;
import MVC.View.CmdBruecheRechnerView;
import MVC.View.GUIBruecheRechnerView;
import Operations.ArithmeticOperationsEnum;
import Utils.Utils;

public class BruecheArithmeticExamples {

  public static void main(String[] args) {
    try {
      demo();
    } catch (BruecheRechnerException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void demo() throws BruecheRechnerException {
    BruecheRechnerModel model = new BruecheRechnerModel(3);

    System.out.println("Bruch 1:");
    Bruch b1 = BruchFactory.bruch_factory_cmd();

    System.out.println("\nBruch2:");
    Bruch b2 = BruchFactory.bruch_factory_cmd();

    System.out.println("\nBruch3:");
    Bruch b3 = BruchFactory.bruch_factory_cmd();

    model.set_input_brüche(new Bruch[] { b1, b2, b3 });
    BruchRepresentator single_line_representator = new SingleLineBruchRepresentator();
    BruchRepresentator multi_line_representator = new MultiLineBruchRepresentator();
    BruchRepresentator html_multi_line_representator = new HTMLMultiLineBruchRepresentator();
    CmdBruecheRechnerView cmd_view = new CmdBruecheRechnerView(multi_line_representator, model);
    GUIBruecheRechnerView gui_view = new GUIBruecheRechnerView(single_line_representator, model);
    BruecheRechnerController cmd_controller = new CmdBruecheRechnerController(model, cmd_view);
    BruecheRechnerController gui_controller = new GUIBruecheRechnerController(model, gui_view);

    cmd_view.register_controller(cmd_controller);
    gui_view.register_controller(gui_controller);
    model.register_view(gui_view);
    model.register_view(cmd_view);

    gui_view.init();

    /**
     * NOTE: You can specify own Representator if you want (see ´BruchRepresentator´
     * Class). A ´Representator´ is only responsible to representate
     * the fraction(or if possible the whole number) and not to strip it!
     * So if you want to strip the raw fractions, call .strip()
     * before or build your own ´Representator´ that does that by hooking
     * ´representate_bruch´ function but it is not recommended cause it will
     * interfere with the Single-Responsibility-Principle. Also main operators as
     * add, sub, div, mult, etc. have a builtin strip call at the end of its
     * operations.
     * 
     * The GUI is implemented with the MVC Design-Pattern. 
     * A View is responsible for showing the model to the user.
     * A View can have one ´Bruch Representator´ that controls how the Bruch is representated.
     * This representator can be changed also during runtime.
     * A Controller is reponsible for handle user inputted actions such as when a user clicks the calculate button.
     * It needs access to the model to update the values in there. 
     * A View generally can have many controllers that subscribe to their views with the Observer-Pattern, but
     * in this case one View (e.g GUIView) has only one corresponding Controller (e.g. GUIController).
     * A Model is the data storage of our programm and implements the main logic with these
     * low level datas (e.g. calculating). 
     * A Model can also have many views which can subscribe to their models with the Observer-Pattern.
     * Its not unusual that a model have more than one view depending how the data have to be 
     * presented (e.g. GUI, CMD, ..)
     * Whenever data in the model changes it notifies all its registered views for that data change leading the
     * views in updating itself and presentate the new model data. 
     * 
     **/



    // NORMAL BRUCH OPERATIONS TO SIMPLY PRINT OUT

    // SingleLineRepresentator Demo
    System.out.println(single_line_representator.representate_bruch(b1, "Bruch 1: "));
    System.out.println(single_line_representator.representate_bruch(b2, "Bruch 2: "));
    System.out.println(single_line_representator.representate_bruch(b3, "Bruch 3: "));

    // MultiLineRepresentator Demo
    System.out.println(multi_line_representator.representate_bruch(b1, "Bruch 1: "));
    System.out.println(multi_line_representator.representate_bruch(b2, "Bruch 2: "));
    System.out.println(multi_line_representator.representate_bruch(b3, "Bruch 3: "));

    // Swap
    System.out.println(multi_line_representator.representate_bruch(b1.swap(), "Swapped Bruch 1: "));
    System.out.println(multi_line_representator.representate_bruch(b2.swap(), "Swapped Bruch 2: "));
    System.out.println(multi_line_representator.representate_bruch(b3.swap(), "Swapped Bruch 3: "));

    // Negotiate
    System.out.println(multi_line_representator.representate_bruch(b1.negotiate(), "Negotiate Bruch 1: "));
    System.out.println(multi_line_representator.representate_bruch(b2.negotiate(), "Negotiate Bruch 2: "));
    System.out.println(multi_line_representator.representate_bruch(b3.negotiate(), "Negotiate Bruch 3: "));

    // Extend
    System.out.println(multi_line_representator.representate_bruch(b1.extend(3), "Extended with 3 Bruch 1: "));
    System.out.println(multi_line_representator.representate_bruch(b2.extend(3), "Extended with 3 Bruch 2: "));
    System.out.println(multi_line_representator.representate_bruch(b3.extend(3), "Extended with 3 Bruch 3: "));

    // Pow
    System.out.println(multi_line_representator.representate_bruch(b1.pow(0), "Powed with 0 Bruch 1: "));
    System.out.println(multi_line_representator.representate_bruch(b2.pow(1), "Powed with 1 Bruch 2: "));
    System.out.println(multi_line_representator.representate_bruch(b3.pow(2), "Powed with 2 Bruch 3: "));

    // Plus
    System.out.println(multi_line_representator.representate_bruch(b1.add(b2), "Bruch1 + Bruch2: "));
    System.out.println(multi_line_representator.representate_bruch(b1.add(b2).add(b3), "Bruch1 + Bruch2 + Bruch3: "));

    // Minus
    System.out.println(multi_line_representator.representate_bruch(b1.sub(b2), "Bruch1 - Bruch2: "));
    System.out.println(multi_line_representator.representate_bruch(b1.sub(b2).sub(b3), "Bruch1 - Bruch2 - Bruch3: "));

    // Mal
    System.out.println(multi_line_representator.representate_bruch(b1.mult(b2), "Bruch1 * Bruch2: "));
    System.out.println(multi_line_representator.representate_bruch(b1.mult(b2).mult(b3), "Bruch1 * Bruch2 * Bruch3: "));

    // Geteilt
    System.out.println(multi_line_representator.representate_bruch(b1.div(b2), "Bruch1 / Bruch2: "));
    System.out.println(multi_line_representator.representate_bruch(b1.div(b2).div(b3), "Bruch1 / Bruch2 / Bruch3: "));

    // Fun :-) (= ((b1 * b2)/b3)-(b1+b2)+(b3-b1) )
    System.out.println(multi_line_representator.representate_bruch(b1.mult(b2).div(b3).sub(b1.add(b2)).add(b3.sub(b1)), "Fun: "));







    // NOW LETS USE OUR CALCULATOR
    // NOTE: when changing fractions in GUI the fractions are also updated in the
    // model, resulting in different
    // calculations as with the beginning supplied fractions
    // NOTE: See how the cmd_view also updates automaticly when changing the gui_view? This is the power of MVC ;-)

    calculator_demo(model, gui_view, html_multi_line_representator);

  }

  public static void calculator_demo(BruecheRechnerModel model, BruecheRechnerView view, BruchRepresentator new_representator) throws BruecheRechnerException {
    //Plus
    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.ADD, ArithmeticOperationsEnum.ADD});
    model.calculate();
    Utils.input("ENTER...");

    //Minus
    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.SUB, ArithmeticOperationsEnum.SUB});
    model.calculate();
    Utils.input("ENTER...");

    //we can also change representation of fractions
    view.set_representator(new_representator);

    //Mal
    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.MULT, ArithmeticOperationsEnum.MULT});
    model.calculate();
    Utils.input("ENTER...");

    //Div
    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.DIV, ArithmeticOperationsEnum.DIV});
    model.calculate();
    Utils.input("ENTER...");

    //Some Random Fun :-)
    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.ADD, ArithmeticOperationsEnum.SUB});
    model.calculate();
    Utils.input("ENTER...");

    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.ADD, ArithmeticOperationsEnum.MULT});
    model.calculate();
    Utils.input("ENTER...");

    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.MULT, ArithmeticOperationsEnum.DIV});
    model.calculate();
    Utils.input("ENTER...");

    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.ADD, ArithmeticOperationsEnum.DIV});
    model.calculate();
    Utils.input("ENTER...");

    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.SUB, ArithmeticOperationsEnum.MULT});
    model.calculate();
    Utils.input("ENTER...");

    model.set_input_operations(new ArithmeticOperationsEnum[] {ArithmeticOperationsEnum.DIV, ArithmeticOperationsEnum.SUB});
    model.calculate();
    Utils.input("ENTER...");
    
  }

  
}