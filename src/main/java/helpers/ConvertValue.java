package helpers;

public class ConvertValue {

    public static Object convertValue(String input) {
        if(input.equals("true") || input.equals("false")) {
            try {
                return Boolean.parseBoolean(input);
            } catch(NumberFormatException a) {
                return input;
            }
        }

        if(input.contains(".")) {
            try {
                return Double.parseDouble(input);
            } catch(NumberFormatException b) {
                return input;
            }
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException c) {
            return input;
        }
    }
}
