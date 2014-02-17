package lab.yegorov;


import java.util.Arrays;
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
        ReplaceChar t = new ReplaceChar("минимум -   привев 1111 minimum Hello,,--\\  world tvtvvvht", '.');//Hello,,--\  world tvtvvvht
        System.out.println("минимум -   привев 1111 minimum Hello,,--\\  world tvtvvvht");
        System.out.println(t.toConvert());
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
        word.addAll(Arrays.asList(inputString.split("[ ,.?!:;\\\\\\-0-9]")));
        word.removeAll(Arrays.asList("")); //удаляем пустые строки
        word.trimToSize();

        Vector<String> punct = new Vector<String>();
        punct.addAll(Arrays.asList(inputString.split("\\p{L}")));
        punct.removeAll(Arrays.asList("")); //удаляем пустые строки
        punct.trimToSize();

        char endChar;
        int endNum;
        StringBuilder strBuild;
        String rezult = new String("");

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

        for(int i = 0, j = 0; i < word.size(); ++i) {
            if(j<punct.size())
                rezult += word.elementAt(i) + punct.elementAt(j++);
            else
                rezult += word.elementAt(i);
        }

        return rezult;
    }
}

class ReplaceCharTest extends ReplaceChar {
    public ReplaceCharTest() {
        super("sometext",'a');
    }
    //TODO testing
}