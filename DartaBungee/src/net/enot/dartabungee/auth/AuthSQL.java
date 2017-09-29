package net.enot.dartabungee.auth;

import net.enot.dartasystem.sql.SQLConnection;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Енот on 30.04.2017.
 */
public class AuthSQL {

    private String table;
    private SQLConnection connection;

    public AuthSQL(String table){
        this.table = table;
        connection = new SQLConnection("s3.huesos228.com", "root", "JH87ipal56yhkd9kGFtyFDG123", "Proxy");
        String req = "CREATE TABLE IF NOT EXISTS `" + table + "` (" + "`ID` INTEGER AUTO_INCREMENT PRIMARY KEY, `Username` VARCHAR(16) NOT NULL UNIQUE, `Password` VARCHAR(255) NOT NULL, `IP` VARCHAR(15) NOT NULL, `RegIP` VARCHAR(15) NOT NULL, `Session` LONG NOT NULL, `Email` VARCHAR(50), `Server` VARCHAR(50), `LastLogin` LONG NOT NULL, `License` BOOLEAN NOT NULL);";
        connection.execute(req);
    }

    public void insertWhenRegister(AuthPlayer authPlayer){
        String name = authPlayer.getName();
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);
        String password = authPlayer.getPassword();
        String ip = authPlayer.getCurrentIP();
        String req = "INSERT INTO `" + table + "`(`Username`, `Password`, `IP`, `RegIP`, `Session`, `Email`, `Server`, `LastLogin`, `License`) VALUES ('" + name + "','" + password + "','" + ip + "','" + ip + "','" + (authPlayer.isDisableSession() ? 0 : System.currentTimeMillis()/1000) + "','" + "Test1" + "','" + player.getServer().getInfo().getName() + "','" + System.currentTimeMillis()/1000 + "','" + (authPlayer.isLicense() ? 1 : 0) + "');";
        connection.execute(req);
    }

    public void updateWhenLogin(AuthPlayer authPlayer){
        String name = authPlayer.getName();
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);
        String password = authPlayer.getPassword();
        String ip = authPlayer.getCurrentIP();
        String req = "UPDATE `" + table + "` SET `Password`='" + password + "',`IP`='" + ip + "',`Session`='" + (authPlayer.isDisableSession() ? 0 : System.currentTimeMillis()/1000) + "',`Email`='Test1',`Server`='" + player.getServer().getInfo().getName() + "',`LastLogin`='" + System.currentTimeMillis()/1000 + "',`License`='" + (authPlayer.isLicense() ? 1 : 0) + "' WHERE `Username`='" + name + "';";
        connection.execute(req);
    }

    public boolean loadAuthPlayer(AuthPlayer authPlayer){
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `" + table + "` WHERE `Username` = '" + authPlayer.getName() + "';");
            if (rs.next()){
                String password = rs.getString("Password");
                String server = rs.getString("Server");
                String ip = rs.getString("IP");
                String mail = rs.getString("Email");
                boolean license = rs.getBoolean("License");
                long session = rs.getLong("Session");

                authPlayer.setSession((System.currentTimeMillis()/1000 - session) <= 86400 && ip.equals(authPlayer.getCurrentIP()));
                authPlayer.setPassword(password);
                authPlayer.setServer(server);
                authPlayer.setLicense(license);
                authPlayer.setMail(mail);

                authPlayer.setRegistered(true);
            } else {
                authPlayer.setRegistered(false);
            }
            rs.close();
            statement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
