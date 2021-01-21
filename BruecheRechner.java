/*
 * @author: Tizian Seehaus 
 */



import Bruch.Representator.HTMLMultiLineBruchRepresentator;
import Bruch.Representator.MultiLineBruchRepresentator;
import Bruch.Representator.SingleLineBruchRepresentator;
import Bruch.Representator.Abstract.BruchRepresentator;
import MVC.Controller.BruecheRechnerController;
import MVC.Controller.CmdBruecheRechnerController;
import MVC.Controller.GUIBruecheRechnerController;
import MVC.Model.BruecheRechnerModel;
import MVC.View.*;
import Utils.Utils;


public class BruecheRechner {

    public BruecheRechner(){
        ;
    }

    public static BruecheRechnerModel get_model(){
        System.out.println("\n\n\nMit wie vielen Brüchen wollen Sie rechnen?");

        int brüche_input_count = Utils.get_number("> ", 1, 20);

        return new BruecheRechnerModel(brüche_input_count);
    }

    public static BruecheRechnerView get_view(BruchRepresentator representator, BruecheRechnerModel model){
        System.out.println("\n\n\nBitte geben Sie den Ansichts Modus für den Brüche Rechner an.\n");
        System.out.println("1. Commandline");
        System.out.println("2. Graphical User Interface");
        
        int choice = Utils.get_number("> ", 1, 2);

        switch(choice){
            case 1: return new CmdBruecheRechnerView(representator, model);
            case 2: return new GUIBruecheRechnerView(representator, model);
            default: System.exit(0);
        }
        return null;
    }

    public static BruchRepresentator get_representator(){
        System.out.println("\n\n\nWie möchten Sie die Brüche darstellen lassen?");
        System.out.println("1. SingleLine");
        System.out.println("2. MultiLine");
        System.out.println("3. HTMLMultiLine (recommended for GUI)");

        int choice = Utils.get_number("> ", 1, 3);

        switch(choice){
            case 1: return new SingleLineBruchRepresentator();
            case 2: return new MultiLineBruchRepresentator();
            case 3: return new HTMLMultiLineBruchRepresentator();
        }
        return null;
    }

    public static BruecheRechnerController get_standard_controller(BruecheRechnerView view, BruecheRechnerModel model){
        if(view instanceof CmdBruecheRechnerView){
            return new CmdBruecheRechnerController(model, view);
        }
        if(view instanceof GUIBruecheRechnerView){
            return new GUIBruecheRechnerController(model, view);
        }
        return null;
    }

    public static void main(String[] args) {

        BruecheRechnerModel model = get_model();
        BruchRepresentator representator = get_representator();
        BruecheRechnerView view = get_view(representator, model);
        BruecheRechnerController controller = get_standard_controller(view, model);

        view.register_controller(controller);
        model.register_view(view);

        view.init();

        
        
    }
}
