package lab.yegorov;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by AdminPC on 14.02.14.
 */

/*
Задание на лабораторную работу:
1.	Разработать в программе следующие классы:
- класс, содержащий функцию main;
- класс для методов работы со строками;
- класс для методов тестирования, производный от класса основной программы.
2. 	Создать объекты классов программы и тестирования в функции main().
    Все классы описать внутри отдельного пакета.
    Тесты должны запускаться вместе c тестами остальных лабораторных работ.
3.	Выполнить и протестировать программу.

Variant 3.	Задана строка a. Преобразовать каждое слово в строке так,
чтобы все предыдущие  вхождения его последней буквы были заменены на заданный символ b.
Пример
a=”минимум”,b=”.” => rez = “.ини.ум”.
 */

public class Main {
    public static void main(String args[]) {
        ReplaceCharTest t = new ReplaceCharTest();
        t.Test();
        t.writeInputStringAndReplaceChar();
        t.Test();
    }
}

class ReplaceChar {
    private String inputString;
    private char b;

    public ReplaceChar(String inputString, char b) {
        this.inputString = inputString;
        this.b = b;
    }

    public String toConvert() {

        Vector<String> word = new Vector<String>();
        word.addAll(Arrays.asList(inputString.split("[ ,.?!:;—<>()\\\\\\-0-9]")));
        word.removeAll(Arrays.asList("")); //удаляем пустые строки
        word.trimToSize();

        Vector<String> punct = new Vector<String>();
        punct.addAll(Arrays.asList(inputString.split("\\p{L}")));
        punct.removeAll(Arrays.asList("")); //удаляем пустые строки
        punct.trimToSize();

        char endChar;
        int endNum;
        StringBuilder strBuild;
        String rezult = "";

        for(int it = 0; it < word.size(); ++it) {
            endNum  = (word.elementAt(it)).length() - 1;
            endChar = (word.elementAt(it)).charAt(endNum);

            strBuild = new StringBuilder(word.elementAt(it));
            for(int i = endNum-1; i >= 0; --i) {
                if((strBuild.charAt(i)) == endChar)
                    strBuild.setCharAt(i,b);
            }
            word.remove(it);
            word.add(it,strBuild.toString());
        }

        boolean t = isfirstPunct();

        for(int i = 0, j = 0; i+j < word.size() + punct.size();) {
            if(t) {
                if(j < punct.size())
                    rezult += punct.elementAt(j++);
                if(i < word.size())
                    rezult += word.elementAt(i++);
            }
            else {
                if(i < word.size())
                    rezult += word.elementAt(i++);
                if(j < punct.size())
                    rezult += punct.elementAt(j++);
            }
        }

        return rezult;
    }
    private boolean isfirstPunct() {
        char[] m = " ,.?!:;—()<>0123456789".toCharArray();
        for(int i=0; i<m.length; ++i) {
            if(m[i]==inputString.charAt(0))
                return true;
        }
        return false;
    }
    public String getInputString() {
        return inputString;
    }
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public char getReplaceChar() {
        return b;
    }
    public void setReplaceChar(char b) {
        this.b = b;
    }
}

class ReplaceCharTest extends ReplaceChar {
    public ReplaceCharTest() {
        super("Java — объектно-ориентированный язык программирования, разработанный компанией Sun Microsystems " +
              "(в последующем приобретённой компанией Oracle)",'.');
    }
    public void writeInputStringAndReplaceChar() {
        Scanner cin = new Scanner(System.in);
        try {
            System.out.print("Введите строку для тестирования:\n>>> ");
            setInputString(cin.nextLine());

            System.out.print("\nВведите символ для замены: (при вводе строки будет использован первый символ)\n>>> ");
            setReplaceChar(cin.nextLine().charAt(0));

        }catch (Exception e) {
            //
            System.out.println("exception...");
            //
            Runtime.getRuntime().halt(0);
            System.exit(0);
        }
    }
    public void Test() {
        System.out.println("Input string (replace symbol: "+getReplaceChar()+"): \n"+getInputString());
        System.out.println("Output string: \n"+toConvert());
    }
}