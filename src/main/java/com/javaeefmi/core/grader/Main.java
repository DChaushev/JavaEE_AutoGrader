package com.javaeefmi.core.grader;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitar
 */
public class Main {

//    public static void main(String[] args) {
//        String taskName = "rational";
//
//        //this two sould be received from the service
//        String inputsZip = "C:\\Users\\Dimitar\\Documents\\Tester\\hw01_" + taskName + "_tests\\input.zip";
//        String outputsZip = "C:\\Users\\Dimitar\\Documents\\Tester\\hw01_" + taskName + "_tests\\output.zip";
//
//        Grader g = new Grader();
//        System.out.println(g.test(source, inputsZip, outputsZip));
//    }

    private static final String source = "/* \n"
            + " * File:   main.cpp\n"
            + " * Author: Dimitar\n"
            + " *\n"
            + " * Created on March 27, 2015, 11:05 AM\n"
            + " */\n"
            + "\n"
            + "#include <iostream>\n"
            + "#include <stdlib.h>\n"
            + "#include <algorithm>\n"
            + "\n"
            + "using namespace std;\n"
            + "\n"
            + "class Rational {\n"
            + "private:\n"
            + "    int numerator;\n"
            + "    int denominator;\n"
            + "\n"
            + "    int gcd(int a, int b) {\n"
            + "        if (b == 0) return a;\n"
            + "        return gcd(b, a % b);\n"
            + "    }\n"
            + "\n"
            + "    void simplify() {\n"
            + "        if (denominator < 0) {\n"
            + "            denominator *= -1;\n"
            + "            numerator *= -1;\n"
            + "        }\n"
            + "        int d = gcd(abs(numerator), denominator);\n"
            + "        if (d > 1) {\n"
            + "            numerator = numerator / d;\n"
            + "            denominator = denominator / d;\n"
            + "        }\n"
            + "    }\n"
            + "\n"
            + "public:\n"
            + "\n"
            + "    Rational() : numerator(1), denominator(1) {\n"
            + "    }\n"
            + "\n"
            + "    Rational(int numerator, int denominator) :\n"
            + "    numerator(numerator), denominator(denominator) {\n"
            + "        simplify();\n"
            + "    }\n"
            + "\n"
            + "    friend ostream& operator<<(ostream& os, const Rational& d);\n"
            + "    friend istream& operator>>(istream& is, Rational& d);\n"
            + "\n"
            + "    Rational operator+(const Rational& other) const {\n"
            + "        int num = numerator * other.denominator + denominator * other.numerator;\n"
            + "        int denom = denominator * other.denominator;\n"
            + "        return Rational(num, denom);\n"
            + "    }\n"
            + "\n"
            + "    Rational operator-(const Rational& other) const {\n"
            + "        int num = numerator * other.denominator - denominator * other.numerator;\n"
            + "        int denom = denominator * other.denominator;\n"
            + "        return Rational(num, denom);\n"
            + "    }\n"
            + "\n"
            + "    Rational operator*(const Rational& other) const {\n"
            + "        return Rational(numerator * other.numerator, denominator * other.denominator);\n"
            + "    }\n"
            + "\n"
            + "    Rational operator/(const Rational& other) const {\n"
            + "        return Rational(numerator * other.denominator, denominator * other.numerator);\n"
            + "    }\n"
            + "\n"
            + "    bool operator==(const Rational& other) const {\n"
            + "        return numerator * other.denominator == other.numerator * denominator;\n"
            + "    }\n"
            + "\n"
            + "    bool operator<(const Rational& other) const {\n"
            + "        return numerator * other.denominator < denominator * other.numerator;\n"
            + "    }\n"
            + "\n"
            + "};\n"
            + "\n"
            + "ostream& operator<<(ostream& os, const Rational& r) {\n"
            + "    os << r.numerator;\n"
            + "    if (r.denominator != 1)\n"
            + "        os << \"/\" << r.denominator;\n"
            + "    return os;\n"
            + "}\n"
            + "\n"
            + "istream& operator>>(istream& is, Rational& d) {\n"
            + "    int num;\n"
            + "    int denom;\n"
            + "    char slash;\n"
            + "\n"
            + "    is >> num;\n"
            + "    is >> slash;\n"
            + "    if (slash == '/') {\n"
            + "        is >> denom;\n"
            + "    } else {\n"
            + "        denom = 1;\n"
            + "    }\n"
            + "\n"
            + "    d.numerator = num;\n"
            + "    d.denominator = denom;\n"
            + "    d.simplify();\n"
            + "\n"
            + "    return is;\n"
            + "}\n"
            + "\n"
            + "int n;\n"
            + "Rational a[50];\n"
            + "\n"
            + "int main(int argc, char** argv) {\n"
            + "\n"
            + "    cin >> n;\n"
            + "    for (int i = 0; i < n; i++) {\n"
            + "        cin >> a[i];\n"
            + "        if (i != 0) {\n"
            + "            a[i] = a[i] + a[i - 1];\n"
            + "        }\n"
            + "    }\n"
            + "    \n"
            + "    sort(a, a + n);\n"
            + "\n"
            + "    cout << a[0];\n"
            + "    for (int i = 1; i < n; i++) {\n"
            + "        cout << \" \" << a[i];\n"
            + "    }\n"
            + "\n"
            + "    cout << endl;\n"
            + "\n"
            + "    return 0;\n"
            + "}\n"
            + "";

//    public static void main(String[] args) {
//
//        String TASK_NAME = "rational";
//
//        String solutionsFolder = "C:\\Users\\Dimitar\\Documents\\Tester\\hw01_" + TASK_NAME;
//        String inputsFolder = "C:\\Users\\Dimitar\\Documents\\Tester\\hw01_" + TASK_NAME + "_tests\\input";
//        String outputsFolder = "C:\\Users\\Dimitar\\Documents\\Tester\\hw01_" + TASK_NAME + "_tests\\output";
//
//        Grader tester = new Grader();
//        FilesRetriever fr = new FilesRetriever();
//        FileReader reader = new FileReader();
//
//        List<String> solutions = fr.getFiles(solutionsFolder);
//        List<String> inputs = fr.getFiles(inputsFolder);
//        List<String> outputs = fr.getFiles(outputsFolder);
//
//        displayHeader(inputs);
//
//        solutions.stream().filter(solution -> solution.endsWith(".cpp")).forEach((solution) -> {
//            int points = 0;
//            String fileName = solution.substring(0, solution.length() - 4);
//            String exe = solutionsFolder + "\\" + fileName;
//            String cpp = exe + ".cpp";
//
//            String[] tokens = fileName.split("_");
//            System.out.print(String.format("%s:", (fileName.contains("_") ? tokens[tokens.length - 1] : fileName)));
//
//            if (tester.compile(cpp, exe)) {
//
//                for (int i = 0; i < inputs.size(); i++) {
//
//                    String input = reader.getContent(inputsFolder + "\\" + inputs.get(i));
//                    String output = reader.getContent(outputsFolder + "\\" + outputs.get(i));
//                    List<String> command = Arrays.asList(solutionsFolder + "\\" + fileName + ".exe");
//                    ErrorMessage outcome = tester.test(command, input, output);
//                    if (outcome == ErrorMessage.Ok) {
//                        System.out.print(String.format("\t%s", outcome));
//                        points++;
//                    } else if (outcome == ErrorMessage.No) {
//                        System.out.print(String.format("\t%s", outcome));
//                    } else {
//                        System.out.print(String.format("\t%s", outcome));
//                    }
//
//                }
//                System.out.println(String.format("\t%d points", points));
//            } else {
//                System.out.print(String.format("%s\t%d points\n", ErrorMessage.CompilationError, points));
//            }
//        });
//
//        System.out.println();
//
//    }
//
//    private static void displayHeader(List<String> inputs) {
//        String HEADER = "FN: ";
//        for (int i = 0; i < inputs.size(); i++) {
//            HEADER += String.format("\t%s%d", "Test", i + 1);
//        }
//        HEADER += "\tPoints";
//        System.out.println(HEADER);
//        addHorizontalLine("=", HEADER.length() + HEADER.length() / 3);
//    }
//
//    private static void addHorizontalLine(String string, int length) {
//        for (int i = 0; i < length; i++) {
//            System.out.print(string);
//        }
//        System.out.println();
//    }
}
