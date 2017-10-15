package net.enot.dartabungee.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Енот on 12.05.2017.
 */
public class RegexUtil {

    public static boolean isMail(String mail){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public static boolean isNick(String nick){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(nick);
        return matcher.matches();
    }

}
