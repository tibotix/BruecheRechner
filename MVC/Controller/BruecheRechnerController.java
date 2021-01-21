package MVC.Controller;

import MVC.Model.BruecheRechnerModel;
import MVC.View.BruecheRechnerView;

//Handle User Input
public abstract class BruecheRechnerController {

    protected BruecheRechnerModel model;
    protected BruecheRechnerView view;

    public BruecheRechnerController(BruecheRechnerModel model, BruecheRechnerView view) {
        this.model = model;
        this.view = view;
    }
    
}
