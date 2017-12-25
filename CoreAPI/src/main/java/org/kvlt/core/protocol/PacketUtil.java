package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class PacketUtil {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * Запись строки в <b>ByteBuf</b>
     * @param str строка
     * @param buf буфер
     */
    public static void writeString(String str, ByteBuf buf) {
        byte[] bytes = str.getBytes(UTF8);
        buf.writeShort(str.length());
        buf.writeBytes(bytes);

//        buf.writeShort(str.getBytes(UTF8).length);
//        buf.writeCharSequence(str, UTF8);
    }

    /**
     * Читает строку из буфера
     * @param buf буфер
     * @return строка
     */
    public static String readString(ByteBuf buf) {
        byte[] bytes = new byte[buf.readShort()];
        buf.readBytes(bytes);
        return new String(bytes, UTF8);

//        short length = buf.readShort();
//        return buf.readCharSequence((int) length, UTF8).toString();
    }

}
