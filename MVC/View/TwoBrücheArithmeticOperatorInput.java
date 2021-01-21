package MVC.View;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Operations.ArithmeticOperationsEnum;


//Move to Controller
public class TwoBrücheArithmeticOperatorInput extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private ButtonGroup operator_group;
    private JRadioButton add_radiobutton;
    private JRadioButton sub_radiobutton;
    private JRadioButton mult_radiobutton;
    private JRadioButton div_radiobutton;

    public TwoBrücheArithmeticOperatorInput(){
        super();
        this.init();
    }

    private void init(){
        this.initialize_components();
        this.configure_components();
        this.build_components();
    }

    private void initialize_components(){
        this.operator_group = new ButtonGroup();
        this.add_radiobutton = new JRadioButton(ArithmeticOperationsEnum.ADD.get_sign(), true);
        this.sub_radiobutton = new JRadioButton(ArithmeticOperationsEnum.SUB.get_sign());
        this.mult_radiobutton = new JRadioButton(ArithmeticOperationsEnum.MULT.get_sign());
        this.div_radiobutton = new JRadioButton(ArithmeticOperationsEnum.DIV.get_sign());
    }

    private void configure_components(){
        this.operator_group.add(this.add_radiobutton);
        this.operator_group.add(this.sub_radiobutton);
        this.operator_group.add(this.mult_radiobutton);
        this.operator_group.add(this.div_radiobutton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void build_components(){
        this.add(this.add_radiobutton);
        this.add(this.sub_radiobutton);
        this.add(this.mult_radiobutton);
        this.add(this.div_radiobutton);
    }

    public String get_selected_button_text(){    
        for (Enumeration<AbstractButton> buttons = this.operator_group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public void update_from_enum(ArithmeticOperationsEnum operation){
        for (Enumeration<AbstractButton> buttons = this.operator_group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.getText() == operation.get_sign()) {
                button.setSelected(true);
                return;
            }
        }
    }
}

