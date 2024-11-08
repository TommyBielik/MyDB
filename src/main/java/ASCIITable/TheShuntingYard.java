package ASCIITable;

import Database.Row;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class TheShuntingYard {

    private final String inputExpression;
    private final HashMap<String,Integer> precedence;

    public TheShuntingYard(String inputExpression) {
        this.inputExpression = inputExpression;

        this.precedence = new HashMap<>();
        this.precedence.put("or", 1);
        this.precedence.put("and", 2);
    }

    /* Helper functions to check precedence of token and
     * if given token is an operator
     * */
    public int getPrecedence(String word) {
        return precedence.get(word);
    }

    public boolean isOperator(String word) {
        return word.equals("and") || word.equals("or");
    }

    // Final evaluation
    public boolean evaluateWhereClause(Row currentRow) {
        if(this.inputExpression.isBlank()) return true;
        ArrayList<String> output = createOutput(currentRow);
        return solveExpression(output);
    }

    /* This will create an output we can use for final solving stack
     * from evaluated conditions and operators(and/or) from given where clause
     * */
    public ArrayList<String> createOutput(Row currentRow) {
        boolean[] booleanArray = getBooleanArray(currentRow);
        ArrayList<String> operatorsArray = getOperators();

        Stack<String> operatorsStack = new Stack<>();
        ArrayList<String> output = new ArrayList<>();

        int index = 0;
        while(index < booleanArray.length) {
            output.add(String.valueOf(booleanArray[index]));
            if(index < operatorsArray.size()) {
                String currentOperator = operatorsArray.get(index);

                while (!operatorsStack.isEmpty() && getPrecedence(currentOperator) <= getPrecedence(operatorsStack.peek())) {
                    output.add(operatorsStack.pop());
                }
                operatorsStack.push(currentOperator);
            }
            index++;
        }

        // ADD REMAINING OPERATORS TO OUTPUT
        while(!operatorsStack.isEmpty()) {
            output.add(operatorsStack.pop());
        }
        return output;
    }

    /* Solves the where clause and returns the result */
    public boolean solveExpression(ArrayList<String> output) {
        Stack<String> solvingStack = new Stack<>();
        boolean result = false;

        for(String token : output) {
            if(!isOperator(token)) {
                solvingStack.push(token);
            } else {
                boolean right = Boolean.parseBoolean(solvingStack.pop());
                boolean left = Boolean.parseBoolean(solvingStack.pop());

                if(token.equals("and")) {
                    result = left && right;
                }
                else if(token.equals("or")) {
                    result = left || right;
                }
                solvingStack.push(String.valueOf(result));
            }
        }
        result = Boolean.parseBoolean(solvingStack.pop());
        return result;
    }

    /* Evaluates each condition in given where clause
     * and returns boolean array from evaluated values
    */
    public boolean[] getBooleanArray(Row currentRow) {
        String[] conditions = this.inputExpression.split(" (?i)and | (?i)or ");
        boolean[] evaluatedConditions = new boolean[conditions.length];

        for(int i = 0; i < conditions.length; i++) {
            evaluatedConditions[i] = evaluateCondition(conditions[i], currentRow);
        }
        return evaluatedConditions;
    }

    // Function to get ands and ors from where clause
    public ArrayList<String> getOperators() {
        Pattern pattern = Pattern.compile("(?i)\\b(and|or)\\b");
        Matcher matcher = pattern.matcher(this.inputExpression);

        ArrayList<String> operators = new ArrayList<>();

        while(matcher.find()) {
            operators.add(matcher.group());
        }
        return operators;
    }


    // This function evaluates boolean value of given condition
    public boolean evaluateCondition(String condition, Row currentRow) {
        try {
            if (condition.contains("=")) {
                String[] parts = condition.split("=");
                String key = parts[0].trim();
                String value = parts[1].trim();

                return currentRow.getRow().get(key).equals(value);
            }

            if (condition.contains(">")) {
                String[] parts = condition.split(">");
                String key = parts[0].trim();

                int keyValue = (int) currentRow.getRow().get(key);
                int value = Integer.parseInt(parts[1].trim());

                return keyValue > value;
            }

            if (condition.contains("<")) {
                String[] parts = condition.split("<");
                String key = parts[0].trim();

                double keyValue = (double) currentRow.getRow().get(key);
                double value = Double.parseDouble(parts[1].trim());

                return keyValue < value;
            }
        } catch (Exception e) {
            System.out.println("Error evaluating condition: " + condition);
        }
        return false;
    }
}
