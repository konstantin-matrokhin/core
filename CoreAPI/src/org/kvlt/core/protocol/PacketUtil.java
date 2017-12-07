package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class PacketUtil {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static void writeString(String str, ByteBuf buf) {
        buf.writeShort(str.getBytes(UTF8).length);
        buf.writeCharSequence(str, UTF8);
    }

    public static String readString(ByteBuf buf) {
        short length = buf.readShort();
        return buf.readCharSequence((int) length, UTF8).toString();
    }

}
