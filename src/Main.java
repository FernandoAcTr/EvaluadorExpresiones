
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fernando
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Escribe una expresión algebraica: ");
        Scanner leer = new Scanner(System.in);

        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        PostfixEvaluator evaluator = new PostfixEvaluator();

        String infix = leer.nextLine();
        String postfix = converter.convert(infix);

        String eval = evaluator.evaluate(postfix);

        //Mostrar resultados:
        System.out.println("Expresion Infija: " + infix);
        System.out.println("Expresion Postfija: " + postfix);
        System.out.println("Resultado: " + eval);

    }
}
