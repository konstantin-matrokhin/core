/**
 * Created by Енот on 10.05.2017.
 */
public class Test {
    //    public static void main(String args[]){
//
//        String host = "smtp.yandex.ru";
//        String port = "465";
//        String account = "register@lastcraft.net";
//        String password = "JHg87yujkh4";
//
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.socketFactory.port", port);
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.port", port);
//
//        Session session = Session.getInstance(properties, new Authenticator(){
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(account, password);
//            }
//        });
//
//        try {
//            MimeMessage e = new MimeMessage(session);
//            try {
//                e.setFrom(new InternetAddress(account, "LASTCRAFT GOVNO"));
//            }
//            catch (UnsupportedEncodingException var7) {
//                e.setFrom(new InternetAddress(account));
//            }
//            e.setRecipients(Message.RecipientType.TO, InternetAddress.parse("irzakirill@mail.ru"));
//            e.setSubject("Название темы");
//            //e.setSentDate(new Date());
////            String text = "<center><table>  " +
////                    "  <tr>" +
////                    "    <td></td>" +
////                    "    <td rowspan=2><img src=\"//i.stack.imgur.com/XlOi4.png\"></td>" +
////                    "  </tr>  " +
////                    "  <tr>" +
////                    "    <td colspan=2>This is the overlay text</td>" +
////                    "  </tr>  " +
////                    "</table> </center>";
//            String text =
//                            "<table height=\"600\" width=\"400\">" +
//                            "<tr bgcolor=\"#333333\" height=\"75\">" +
//                            "<td><font size=\"5\" color=\"#0d9a63\" face=\"Comic Sans MS\">LastCraft</font></td>" +
//                            "</tr> " +
//                            "<tr bgcolor=\"#c2c2c2\">" +
//                            "<td>Text</td>" +
//                            "</tr>" +
//                            "</table> ";
//            e.setContent(text, "text/html");
//            new Thread(() -> {
//                try {
//                    Transport.send(e);
//                }
//                catch (MessagingException var2) {
//                    var2.printStackTrace();
//                }
//            }
//            ).start();
//        }
//        catch (MessagingException var8) {
//            throw new RuntimeException(var8);
//        }
//
//    }
    public static void main(String[] args) {

    }
}
