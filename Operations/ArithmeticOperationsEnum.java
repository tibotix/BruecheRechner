package Operations;

import Bruch.Bruch;
import Exceptions.ConvertBruchException;
import Exceptions.ConvertOperationException;

public enum ArithmeticOperationsEnum {
    ADD("+"), SUB("-"), MULT("*"), DIV("/");

    private String sign;

    private ArithmeticOperationsEnum(String sign) {
        this.sign = sign;
    }

    public String get_sign() {
        return this.sign;
    }

    public static String[] get_all_signs() {
        String[] all_operators = new String[ArithmeticOperationsEnum.values().length];
        for (int i = 0; i < all_operators.length; i++) {
            all_operators[i] = ArithmeticOperationsEnum.values()[i].get_sign();
        }
        return all_operators;
    }

    public static ArithmeticOperationsEnum fromSign(String sign) throws ConvertOperationException {
        for (ArithmeticOperationsEnum enum_operator : ArithmeticOperationsEnum.values()) {
            if (enum_operator.sign.equals(sign)) {
                return enum_operator;
            }
        }
        throw new ConvertOperationException("Couldnt match " + sign + " to an aritmetic operation");
    }

    public Bruch make_operation(Bruch b1, Bruch b2) throws ConvertBruchException {
        switch(this){
          case ADD: return b1.add(b2);
          case SUB: return b1.sub(b2);
          case MULT: return b1.mult(b2);
          case DIV: return b1.div(b2);
          default: return b1;
        }
      }

}
