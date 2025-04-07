import jdk.internal.icu.text.UCharacterIterator;

import java.text.NumberFormat;

public class CalculatorModel {  //  4/3/2025
    private static UCharacterIterator numberFormat;
    private static final NumberFormat formatter = numberFormat.getInstance(); //i dont think she went over how this is broke I imported what she did but its giving me error??
    private double number1;
    private char op=' ';

    public String store (String display){
        String result =" ";
        try {
            number1 = Double.parseDouble(display);
            result = formatter.format(number1);
        } catch (NumberFormatException e)  {
            result = "Err";
        }
        return result;
    }

    public void setOp (char op) {
        this.op = op;
    }
    public String compute (String display) {
        String result=" ";
        try {
            double number2 = Double.parseDouble(display);
            switch (op) {
            case '+': number1 += number2;
                case '-': number1 -= number2;
                case '*': number1 *= number2;
                case '/': number1 /= number2;
                default: number1 = number2;

            }
        } catch (Exception e){
            result = "Err";
            number1 = 0;
        }
        return  result;
    }
    
}
