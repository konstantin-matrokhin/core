package org.kvlt.core.protocol;

public class Packets {

    public static final int PROXY_CONNECT_PACKET = 1;
    public static final int PROXY_DISCONNECT_PACKET = 3;
    public static final int PROXY_MOTD_PACKET = 13;

    public static final int SERVER_CONNECT_PACKET = 2;
    public static final int SERVER_DISCONNECT_PACKET = 4;

    public static final int PLAYER_LOGIN_PACKET = 5;
    public static final int PLAYER_JOIN_PACKET = 6;
    public static final int PLAYER_QUIT_PACKET = 7;
    public static final int PLAYER_SWITCH_SERVER_PACKET = 8;
    public static final int PLAYER_CHAT_PACKET = 9;
    public static final int PLAYER_AUTH_PACKET = 10;
    public static final int PLAYER_REG_PACKET = 11;
    public static final int PLAYER_KICK_PACKET = 12;
    public static final int PLAYER_PRELOGIN_PACKET = 15;

    public static final int MESSAGE_PACKET = 14;

}
