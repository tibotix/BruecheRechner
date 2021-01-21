package MVC.View;

import java.util.Vector;

import Bruch.Representator.Abstract.BruchRepresentator;
import Exceptions.ConvertBruchException;
import MVC.Model.BruecheRechnerModel;
import MVC.Controller.*;

public abstract class BruecheRechnerView {

    protected BruecheRechnerModel model;
    protected BruchRepresentator representator;
    protected Vector<BruecheRechnerController> registered_controllers = new Vector<BruecheRechnerController>();

    protected BruecheRechnerView(BruchRepresentator representator, BruecheRechnerModel model){
        this.set_representator(representator);
        this.set_model(model);
    }

    public void register_controller(BruecheRechnerController controller){
        this.registered_controllers.add(controller);
    }

    public void set_representator(BruchRepresentator representator){
        this.representator = representator;
    }

    public void set_model(BruecheRechnerModel model){
        this.model = model;
    }

    public abstract void init();

    public abstract void update() throws ConvertBruchException;

    public abstract void error(String message);


}
