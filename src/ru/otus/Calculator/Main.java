package ru.otus.Calculator;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int num1, num2, num3, num4;
    static char operator;
    static int result;
    static int convert;

    public static String[] getOperands(String userInput) throws IOException {
        char[] myChar = new char[10];
        for (int i = 0; i < userInput.length(); i++) {
            myChar[i] = userInput.charAt(i);
            if (myChar[i] == '+') {
                operator = '+';
            } else if (myChar[i] == '-') {
                operator = '-';
            } else if (myChar[i] == '*') {
                operator = '*';
            } else if (myChar[i] == '/') {
                operator = '/';
            }
        }
        String[] numbers = userInput.split("[+-/*]");
        return numbers;
    }

    public static int calculation(int n1, int n2) {
        switch (operator) {
            case '+':
                result = n1 + n2;
                break;
            case '-':
                result = n1 - n2;
                break;
            case '*':
                result = n1 * n2;
                break;
            case '/':
                result = n1 / n2;
                break;
        }
        return result;
    }

    public static int romanToArabic(String number) throws IOException {
        switch (number) {
            case "I":
                convert = 1;
                break;
            case "II":
                convert = 2;
                break;
            case "III":
                convert = 3;
                break;
            case "IV":
                convert = 4;
                break;
            case "V":
                convert = 5;
                break;
            case "VI":
                convert = 6;
                break;
            case "VII":
                convert = 7;
                break;
            case "VIII":
                convert = 8;
                break;
            case "IX":
                convert = 9;
                break;
            case "X":
                convert = 10;
                break;
            default:
                throw new IOException();
        }
        return convert;
    }

    static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String str = roman[numArabian];
        return str;
    }

    public static boolean checkIfRoman(String a) {
        boolean b;
        if (a.contains("I") || a.contains("V") || a.contains("X")) {
            b = true;
        } else{
            b = false;
        }
        return b;
    }

    public static String calc (String userInput) throws IOException {
        String res;
        String[] numbers = getOperands(userInput);

        //trim operands
        String operand1 = numbers[0].trim();
        String operand2 = numbers[1].trim();

        //check if operands are roman numbs
        boolean ifRoman1 = checkIfRoman(operand1);
        boolean ifRoman2 = checkIfRoman(operand2);

        if (ifRoman1 && ifRoman2) {
            //roman numbs
            num1 = romanToArabic(operand1);
            num2 = romanToArabic(operand2);
            calculation(num1, num2);
            if (result <= 0) {
                throw new IOException();
            }
            res = convertNumToRoman(result);

        } else if (ifRoman2 && !ifRoman1 || !ifRoman2 && ifRoman1) {
            throw new IOException();
        } else {
            // arabic numbs
            num3 = Integer.valueOf(operand1);
            num4 = Integer.valueOf(operand2);
            if (num3 < 0 || num3 > 10 || num4 < 0 || num4 > 10) {
                throw new IOException();
            }
            calculation(num3, num4);
            res = Integer.toString(result);
        }
        return res;
    }

public static void main(String[]args) throws IOException {
    System.out.println("Введите выражение двух чисел от 1 до 10 арабскими или римскими цифрами I - X: [2+2, II+II], нажмите Enter");
    String userInput = scanner.nextLine();
    String calcResult=calc(userInput);
    System.out.println(calcResult);
  }
}
