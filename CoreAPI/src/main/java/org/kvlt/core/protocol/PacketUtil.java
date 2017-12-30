package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class PacketUtil {

    public static String readString(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readInt()];
        byteBuf.readBytes(bytes);
        return new String(bytes, CharsetUtil.UTF_8);
    }

    public static void writeString(String string, ByteBuf byteBuf){
        byte[] bytes = string.getBytes(CharsetUtil.UTF_8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

}
