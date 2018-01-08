package org.kvlt.core.protocol;

/**
 * ID всех пакетов
 */
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
    public static final int PLAYER_PRELOGIN_PACKET = 15;
    public static final int ID_PACKET = 16;

    public static final int MESSAGE_PACKET = 14;
    public static final int PM_PACKET = 17;
    public static final int REPLY_PACKET = 18;

    public static final int EMAIL_ADD_PACKET = 19;
    public static final int EMAIL_VERIFY_PACKET = 20;
    public static final int PASSWORD_RECOVERY_PACKET = 21;
    public static final int EMAIL_CHANGE_PACKET = 26;
    public static final int EMAIL_CHANGE_VERIFY_PACKET = 27;

    public static final int PWD_PACKET = 22;

    public static final int KICK_PACKET = 12;
    public static final int BAN_PACKET = 23;
    public static final int MUTE_PACKET = 25;

    public static final int BROADCAST_PACKET = 24;

    public static final int HUB_PACKET = 28;
    public static final int PLAYER_TRANSFER_PACKET = 29;

    public static final int PREMIUM_LIST_PACKET = 30;
    public static final int PREMIUM_PLAYER_PACKET = 31;

}
