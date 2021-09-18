
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author fernando
 */
public class InfixToPostfixConverter {

    //Declaración de las pilas
    Stack<String> E = new Stack<>(); //Pila entrada
    Stack<String> T = new Stack<>(); //Pila temporal para operadores
    Stack<String> S = new Stack<>(); //Pila salida

    public InfixToPostfixConverter() {

    }

    private String depurar(String s) {
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()^";
        String str = "";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            } else {
                str += s.charAt(i);
            }

        }
        return str.replaceAll("\\s+", " ").trim();
    }

    //Jerarquia de los operadores en la expresion
    private int prefInfix(String op) {
        Map<String, Integer> prioridades = new HashMap<>();
        prioridades.put("^", 4);
        prioridades.put("*", 2);
        prioridades.put("/", 2);
        prioridades.put("+", 1);
        prioridades.put("-", 1);
        prioridades.put("(", 5);
        prioridades.put(")", -1);

        return prioridades.get(op);
    }

    //Jerarquia de los operadores en la pila
    private int prefPila(String op) {
        Map<String, Integer> prioridades = new HashMap<>();
        prioridades.put("^", 3);
        prioridades.put("*", 2);
        prioridades.put("/", 2);
        prioridades.put("+", 1);
        prioridades.put("-", 1);
        prioridades.put("(", 0);
        prioridades.put(")", -1);

        return prioridades.get(op);
    }

    public String convert(String expression) throws Exception {
        E.clear();
        T.clear();
        S.clear();

        //Depurar la expresion algebraica
        String expr = depurar(expression);
        String[] arrayInfix = expr.split(" ");

        //Añadir el array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }

        try {
            //Algoritmo Infijo a Postfijo
            while (!E.isEmpty()) {

                String op = E.pop();

                boolean isNumber = isInteger(op) || isReal(op);

                if (isNumber) {
                    S.push(op);
                } else if (!op.equals(")")) {
                    if (T.isEmpty()) {
                        T.push(op);
                    } else if (prefInfix(op) > prefPila(T.peek())) {
                        T.push(op);
                    } else if (prefInfix(op) <= prefPila(T.peek())) {
                        S.push(T.pop());
                        T.push(op);
                    }
                } else {
                    while (!T.peek().equals("(")) {
                        S.push(T.pop());
                    }
                    T.pop();
                }

            }

            while (!T.isEmpty()) {
                S.push(T.pop());
            }

            //Eliminacion de `impurezas´ en la expresiones algebraicas
            String infix = expr.replace(" ", "");
            String postfix = S.toString().replaceAll("[\\]\\[,]", "");

            return postfix;
        } catch (Exception ex) {
            throw new Exception("Error en la expresión algebraica");
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean isReal(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
