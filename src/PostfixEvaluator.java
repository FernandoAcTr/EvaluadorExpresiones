
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fernando
 */
public class PostfixEvaluator {

    Stack<String> E = new Stack<>(); //Pila entrada
    Stack<String> P = new Stack<>(); //Pila de operandos

    public String evaluate(String expr) {
        E.clear();
        P.clear();

        String[] post = expr.split(" ");

        //Añadir post (array) a la Pila de entrada (E)
        for (int i = post.length - 1; i >= 0; i--) {
            E.push(post[i]);
        }

        //Algoritmo de Evaluación Postfija
        String operadores = "+-*/%^";
        while (!E.isEmpty()) {
            if (operadores.contains("" + E.peek())) {
                P.push(operar(E.pop(), P.pop(), P.pop()) + "");
            } else {
                P.push(E.pop());
            }
        }

        return P.pop();

    }

    private double operar(String op, String n2, String n1) {
        double num1 = Double.parseDouble(n1);
        double num2 = Double.parseDouble(n2);
        if (op.equals("+")) {
            return (num1 + num2);
        } else if (op.equals("-")) {
            return (num1 - num2);
        } else if (op.equals("*")) {
            return (num1 * num2);
        } else if (op.equals("/")) {
            return (num1 / num2);
        } else if (op.equals("%")) {
            return (num1 % num2);
        } else if (op.equals("^")) {
            return (Math.pow(num1, num2));
        }

        return 0;
    }

}
