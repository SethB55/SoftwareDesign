public class CalculatorModel {
    private static final NumberFormat formatter = numberFormat.getInstance();
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

}
