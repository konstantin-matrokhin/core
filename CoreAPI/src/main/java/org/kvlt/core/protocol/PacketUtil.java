package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

import java.util.Iterator;

public class PacketUtil {

    /**
     * Используйте эту константу для записи вместо пустых строк или null-значений
     */
    public static final String EMPTY_STRING = "_";

    /**
     * Чтение строки и буфера
     * @param byteBuf буфер для чтения
     * @return готовая строка
     */
    public static String readString(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readInt()];
        byteBuf.readBytes(bytes);
        return new String(bytes, CharsetUtil.UTF_8);
    }

    /**
     * Запись строки в буфер
     * @param string исходная строка
     * @param byteBuf буфер для записи
     */
    public static void writeString(String string, ByteBuf byteBuf){
        if (string == null) throw new NullPointerException("string cannot be null!");
        byte[] bytes = string.getBytes(CharsetUtil.UTF_8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    /**
     * Запись строки из <b>Iterable</b> в буфер
     * считывать нужно как массив строк
     * @param iterable
     * @param byteBuf
     */
    public static void writeStrings(Iterable<? super String> iterable, ByteBuf byteBuf) {
        Iterator iterator = iterable.iterator();
        if (!iterator.hasNext()) throw new ArrayIndexOutOfBoundsException("array must be not empty!");

        short size = (short) iterable.spliterator().getExactSizeIfKnown();
        if (size < 1) throw new ArrayIndexOutOfBoundsException("array must be not empty!");

        byteBuf.writeShort(size);
        while (iterator.hasNext()) {
            String next = (String) iterator.next();
            writeString(next, byteBuf);
        }
    }

    /**
     * Запись строки в массив строк. Метод аналогичен <b>writeStrings</b>
     * @see PacketUtil#readString(ByteBuf)
     * @param array
     * @param byteBuf
     */
    public static void writeStringArray(String[] array, ByteBuf byteBuf) {
        byteBuf.writeShort(array.length);

        for (String s: array) {
            writeString(s, byteBuf);
        }
    }

    /**
     * Чтения из буфера массива строк
     * @param byteBuf
     * @return
     */
    public static String[] readStringArray(ByteBuf byteBuf) {
        short length = byteBuf.readShort();
        String[] array = new String[length];

        for (int i = 0; i < length; i++) {
            array[i] = readString(byteBuf);
        }

        return array;
    }

    /**
     * Записывает массив чисел в буффер.
     * TODO: сделать универсальную запись массива
     * @param array
     * @param byteBuf
     */
    public static void writeIntArray(int[] array, ByteBuf byteBuf) {
        byteBuf.writeShort(array.length);

        for (int element: array) {
            byteBuf.writeInt(element);
        }
    }

    /**
     * Чтение массива строк
     * @param byteBuf
     * @return
     */
    public static int[] readIntArray(ByteBuf byteBuf) {
        short length = byteBuf.readShort();
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = byteBuf.readInt();
        }

        return array;
    }

}
