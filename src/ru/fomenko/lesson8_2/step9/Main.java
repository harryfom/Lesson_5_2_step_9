package ru.fomenko.lesson8_2.step9;

/*
 По историческим причинам на разных платформах принят разный способ обозначения конца строки в текстовом файле.
 На Unix-системах конец строки обозначается символом с кодом 10 ('\n'), на Windows — двумя последовательными
 символами с кодами 13 и 10 ('\r' '\n').

 Напишите программу, которая будет преобразовывать переводы строк из формата Windows в формат Unix. Данные в
 формате Windows подаются программе в System.in, преобразованные данные должны выводиться в System.out. На этот
 раз вам надо написать программу полностью, т.е. объявить класс (с именем Main — таково ограничение проверяющей
 системы), метод main, прописать все import'ы.

 Требуется заменить все вхождения пары символов '\r' и '\n' на один символ '\n'. Если на входе встречается
 одиночный символ '\r', за которым не следует '\n', то символ '\r' выводится без изменения.

 Кодировка входных данных такова, что символ '\n' представляется байтом 10, а символ '\r' — байтом 13. Поэтому
 программа может осуществлять фильтрацию на уровне двоичных данных, не преобразуя байты в символы.

 Из-за буферизации данных в System.out в конце вашей программы надо явно вызвать System.out.flush(). Иначе часть
 выведенных вами данных не будет видна проверяющей системе.

 Пример

 Из System.in зачитаны следующие байты: 65 13 10 10 13. Внимание! Это не строка "65 13 10 10 13", а последовательность чисел, возвращаемая методом System.in.read().

 В System.out должны быть выведены байты: 65 10 10 13
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        test();
        convert(System.in,System.out);
    }

    private static void test() throws IOException {
        System.out.println("5.2 Потоки байт – Шаг 9");
        byte[] test = new byte[]{65, 13, 10, 10, 13};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(10);

        convert(new ByteArrayInputStream(test),outputStream );
        byte[] out = outputStream.toByteArray();
        for (int i: out) {
            System.out.println(i);
        }
    }

    private static void convert(InputStream inputStream,OutputStream outputStream) throws IOException {
        int buf1 = inputStream.read();
        int buf2;

        while(buf1 != -1){
            buf2 = inputStream.read();
            if (!(buf2 == 10 && buf1 == 13)){
                outputStream.write(buf1);
            }
            buf1 = buf2;
        }
        outputStream.flush();
    }
}
