package net.enot.dartasystem.utils;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

/**
 * Created by Енот on 23.04.2017.
 */
public class ByteBufUtil {

    public static String readString(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readInt()];
        byteBuf.readBytes(bytes);
        return new String(bytes, CharsetUtil.UTF_8);
    }

    public static void writeString(ByteBuf byteBuf, String text){
        byte[] bytes = text.getBytes(CharsetUtil.UTF_8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

}
