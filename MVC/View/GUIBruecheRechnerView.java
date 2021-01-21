package MVC.View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.*;

import Bruch.Bruch;
import Bruch.Representator.Abstract.BruchRepresentator;
import Exceptions.ConvertBruchException;
import MVC.Controller.BruecheRechnerController;
import MVC.Controller.GUIBruecheRechnerController;
import MVC.Model.BruecheRechnerModel;
import Operations.ArithmeticOperationsEnum;

public class GUIBruecheRechnerView extends BruecheRechnerView implements ActionListener {

    private JFrame frame;
    private BruchInput[] brüche_inputs;
    private JPanel result_panel;
    private JLabel result_bruch_label;
    private JButton result_button;
    private TwoBrücheArithmeticOperatorInput[] operator_inputs;

    public GUIBruecheRechnerView(BruchRepresentator representator, BruecheRechnerModel model) {
        super(representator, model);
        this.brüche_inputs = new BruchInput[model.input_brüche.length];
        this.operator_inputs = new TwoBrücheArithmeticOperatorInput[model.input_operations.length];
    }

    public void init() {
        this.initialize_components();
        this.configure_components();
        this.build_components();

        this.frame.setVisible(true);
    }

    private void initialize_components() {
        this.frame = new JFrame();

        for (int i = 0; i < this.brüche_inputs.length; i++) {
            this.brüche_inputs[i] = new BruchInput(this, i);
        }

        for (int i = 0; i < this.operator_inputs.length; i++) {
            this.operator_inputs[i] = new TwoBrücheArithmeticOperatorInput();
        }

        this.result_button = new JButton("=");
        this.result_panel = new JPanel();
        this.result_bruch_label = new JLabel("", JLabel.CENTER);
    }

    private void configure_components() {
        this.result_bruch_label.setVerticalAlignment(JLabel.CENTER);
        this.result_bruch_label.setFont(new Font(this.result_bruch_label.getFont().getName(), Font.PLAIN, 35));
        this.result_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.result_panel.setLayout(new BoxLayout(this.result_panel, BoxLayout.Y_AXIS));
        this.result_panel.add(this.result_bruch_label);

        this.result_button.addActionListener(this);
        this.result_button.setActionCommand("calculate");

        this.frame.setTitle("GUI Brüche Rechner");
        this.frame.setSize(600, 250);
        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.X_AXIS));

        this.frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    private void build_components() {
        if (this.brüche_inputs.length > 0) {
            this.frame.add(this.brüche_inputs[0]);
        }
        for (int i = 1; i < this.brüche_inputs.length; i++) {
            this.frame.add(this.operator_inputs[i - 1]);
            this.frame.add(this.brüche_inputs[i]);
        }

        this.frame.add(this.result_button);
        this.frame.add(this.result_panel);
    }

    // simply delegate request to all registered controllers
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand() == "calculate") {
            for (BruecheRechnerController controller : this.registered_controllers) {
                ((GUIBruecheRechnerController) controller).handle_calculate_request(this.brüche_inputs,
                        this.operator_inputs);
            }
        } else if (ae.getActionCommand() == "negotiate") {
            int index = (Integer) ((JButton) ae.getSource()).getClientProperty("index");
            for (BruecheRechnerController controller : this.registered_controllers) {
                ((GUIBruecheRechnerController) controller).handle_negotiate_request(this.brüche_inputs,
                        this.operator_inputs, index);
            }
        } else if (ae.getActionCommand() == "swap") {
            int index = (Integer) ((JButton) ae.getSource()).getClientProperty("index");
            for (BruecheRechnerController controller : this.registered_controllers) {
                ((GUIBruecheRechnerController) controller).handle_swap_request(this.brüche_inputs, this.operator_inputs,
                        index);
            }
        } else if (ae.getActionCommand() == "strip") {
            int index = (Integer) ((JButton) ae.getSource()).getClientProperty("index");
            for (BruecheRechnerController controller : this.registered_controllers) {
                ((GUIBruecheRechnerController) controller).handle_strip_request(this.brüche_inputs, this.operator_inputs,
                        index);
            }
        }
    }

    public void error(String message) {
        JDialog dialog = new JDialog(this.frame);
        dialog.setModal(true);
        dialog.setTitle("Error");
        JLabel error_label = new JLabel(message, JLabel.CENTER);
        dialog.add(error_label);
        dialog.setSize(error_label.getPreferredSize().width + 50, error_label.getPreferredSize().height + 100);
        dialog.setVisible(true);
    }

    public void update() throws ConvertBruchException {
        this._update_result_bruch();
        this._update_brüche();
        this._update_operators();
    }

    private void _update_result_bruch() throws ConvertBruchException {
        if(this.model.result != null && this.result_bruch_label != null){
            this.result_bruch_label.setText(this.representator.representate_bruch(this.model.result, ""));
        }
    }

    private void _update_brüche(){
        for(int i=0; i<this.brüche_inputs.length; i++){
            Bruch b = this.model.input_brüche[i];
            if(b != null && this.brüche_inputs[i] != null){
                this.brüche_inputs[i].update_from_bruch(b);
            }
        }
    }

    private void _update_operators(){
        for(int i=0; i<this.operator_inputs.length; i++){
            ArithmeticOperationsEnum operation = this.model.input_operations[i];
            if(operation != null && this.operator_inputs[i] != null){
                this.operator_inputs[i].update_from_enum(operation);
            }
        }
    }
    
}
