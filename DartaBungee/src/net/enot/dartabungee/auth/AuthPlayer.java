package net.enot.dartabungee.auth;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.auth.codes.Code;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Енот on 01.05.2017.
 */
public class AuthPlayer {

    private static Map<String, AuthPlayer> players = new ConcurrentHashMap<>();

    public static boolean containsAuthPlayer(String name) {
        return players.containsKey(name.toLowerCase());
    }

    public static AuthPlayer getAuthPlayer(String name) {
        return players.get(name.toLowerCase());
    }

    private boolean registered;
    private boolean authorized = false;

    private String password;
    private String name;
    private String currentIP;
    private String server;
    private String mail;
    private boolean license;
    private boolean newPassword = false;
    private boolean disableSession = false;
    private boolean session;

    private Map<Integer, Code> codes = new HashMap<>();

    public AuthPlayer(String name, String currentIP) {
        this.name = name;
        this.currentIP = currentIP;
        players.put(name.toLowerCase(), this);
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getMail(){
        return mail;
    }

    public boolean hasMail(){
        return mail != null;
    }

    public void setNewPassword(boolean newPassword){
        this.newPassword = newPassword;
    }

    public boolean isNewPassword(){
        return newPassword;
    }

    public String getName() {
        return name;
    }

    public String getCurrentIP() {
        return currentIP;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getServer() {
        return server;
    }

    public void setLicense(boolean license){
        this.license = license;
    }

    public boolean isLicense(){
        return license;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public void setDisableSession(){
        this.disableSession = true;
    }

    public boolean isDisableSession(){
        return disableSession;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public boolean isRegistered() {
        return registered;
    }

    public boolean isSession() {
        return session;
    }

    public void remove() {
        if (!registered && authorized) {
            DartaBungee.getInstance().getAuthSQL().insertWhenRegister(this);
        } else if (authorized) {
            DartaBungee.getInstance().getAuthSQL().updateWhenLogin(this);
        }
        players.remove(name.toLowerCase());
    }

}
