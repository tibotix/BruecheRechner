package MVC.View;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Bruch.Bruch;
import Exceptions.BruecheRechnerException;
import Exceptions.ConvertBruchException;

public class BruchInput extends JPanel {
    private static final long serialVersionUID = 1L;

    private JButton bruch_strip;
    private JButton bruch_swap;
    private JButton bruch_negotiate;
    private JTextField bruch_zaehler_input;
    private JSeparator bruch_separator;
    private JTextField bruch_nenner_input;
    private ActionListener listener;
    private int index;

    public BruchInput(ActionListener listener, int index) {
        super();
        this.index = index;
        this.listener = listener;
        this.init();
    }

    private void init() {
        this.initialize_components();
        this.configure_components();
        this.build_components();
    }

    private void initialize_components() {
        this.bruch_strip = new JButton("Strip");
        this.bruch_negotiate = new JButton("Negotiate");
        this.bruch_swap = new JButton("Swap");
        this.bruch_zaehler_input = new JTextField(3);
        this.bruch_separator = new JSeparator(JSeparator.HORIZONTAL);
        this.bruch_nenner_input = new JTextField(3);
    }

    private void configure_components() {
        //We set index property to know in controller which BruchInput requested negotiate/swap
        this.bruch_strip.putClientProperty("index", index);
        this.bruch_strip.addActionListener(this.listener);
        this.bruch_strip.setActionCommand("strip");
        this.bruch_negotiate.putClientProperty("index", index);
        this.bruch_negotiate.addActionListener(this.listener);
        this.bruch_negotiate.setActionCommand("negotiate");
        this.bruch_swap.putClientProperty("index", index);
        this.bruch_swap.setActionCommand("swap");
        this.bruch_swap.addActionListener(this.listener);
        this.bruch_zaehler_input.setHorizontalAlignment(JTextField.CENTER);
        this.bruch_zaehler_input.setFont(new Font(this.bruch_zaehler_input.getFont().getName(), Font.PLAIN, 35));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.bruch_nenner_input.setHorizontalAlignment(JTextField.CENTER);
        this.bruch_nenner_input.setFont(new Font(this.bruch_nenner_input.getFont().getName(), Font.PLAIN, 35));
    }

    private void build_components(){
        this.add(this.bruch_strip);
        this.add(this.bruch_negotiate);
        this.add(this.bruch_swap);
        this.add(this.bruch_zaehler_input);
        this.add(this.bruch_separator);
        this.add(this.bruch_nenner_input);
    }

    public Bruch to_bruch() throws ConvertBruchException{
        try{
            return new Bruch(Integer.valueOf(this.bruch_zaehler_input.getText()), Integer.valueOf(this.bruch_nenner_input.getText()));
        } catch(BruecheRechnerException | NumberFormatException e){
            throw new ConvertBruchException("Couldnt convert input to a valid Bruch instance!: \n"+e.getMessage());
        }
    }

    public void update_from_bruch(Bruch b1){
        this.bruch_zaehler_input.setText(String.valueOf(b1.zaehler));
        this.bruch_nenner_input.setText(String.valueOf(b1.nenner));
    }
}