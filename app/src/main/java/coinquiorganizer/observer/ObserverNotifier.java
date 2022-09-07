package coinquiorganizer.observer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.smtpmailsender.COMailSender;

public class ObserverNotifier {

    public static void sendWelcomeMessageToRoomMates(Context context,ArrayList<CoinquilinoEntity> subscribers){
        for(CoinquilinoEntity it : subscribers){
            COMailSender mailSender = new COMailSender();
            mailSender.setAllData(context,it.getEmail(),"CoinquiOrganizer - Benvenuto!","<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "<head>\n" +
                    "\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0;\">\n" +
                    "\t<meta name=\"format-detection\" content=\"telephone=no\" />\n" +
                    "\t<style>\n" +
                    "\t\t/* Reset styles */\n" +
                    "\t\tbody {\n" +
                    "\t\t\tmargin: 0;\n" +
                    "\t\t\tpadding: 0;\n" +
                    "\t\t\tmin-width: 100%;\n" +
                    "\t\t\twidth: 100% !important;\n" +
                    "\t\t\theight: 100% !important;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\tbody,\n" +
                    "\t\ttable,\n" +
                    "\t\ttd,\n" +
                    "\t\tdiv,\n" +
                    "\t\tp,\n" +
                    "\t\ta {\n" +
                    "\t\t\t-webkit-font-smoothing: antialiased;\n" +
                    "\t\t\ttext-size-adjust: 100%;\n" +
                    "\t\t\t-ms-text-size-adjust: 100%;\n" +
                    "\t\t\t-webkit-text-size-adjust: 100%;\n" +
                    "\t\t\tline-height: 100%;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\ttable,\n" +
                    "\t\ttd {\n" +
                    "\t\t\tmso-table-lspace: 0pt;\n" +
                    "\t\t\tmso-table-rspace: 0pt;\n" +
                    "\t\t\tborder-collapse: collapse !important;\n" +
                    "\t\t\tborder-spacing: 0;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\timg {\n" +
                    "\t\t\tborder: 0;\n" +
                    "\t\t\tline-height: 100%;\n" +
                    "\t\t\toutline: none;\n" +
                    "\t\t\ttext-decoration: none;\n" +
                    "\t\t\t-ms-interpolation-mode: bicubic;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t#outlook a {\n" +
                    "\t\t\tpadding: 0;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.ReadMsgBody {\n" +
                    "\t\t\twidth: 100%;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.ExternalClass {\n" +
                    "\t\t\twidth: 100%;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.ExternalClass,\n" +
                    "\t\t.ExternalClass p,\n" +
                    "\t\t.ExternalClass span,\n" +
                    "\t\t.ExternalClass font,\n" +
                    "\t\t.ExternalClass td,\n" +
                    "\t\t.ExternalClass div {\n" +
                    "\t\t\tline-height: 100%;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t/* Rounded corners for advanced mail clients only */\n" +
                    "\t\t@media all and (min-width: 560px) {\n" +
                    "\t\t\t.container {\n" +
                    "\t\t\t\tborder-radius: 8px;\n" +
                    "\t\t\t\t-webkit-border-radius: 8px;\n" +
                    "\t\t\t\t-moz-border-radius: 8px;\n" +
                    "\t\t\t\t-khtml-border-radius: 8px;\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t/* Set color for auto links (addresses, dates, etc.) */\n" +
                    "\t\ta,\n" +
                    "\t\ta:hover {\n" +
                    "\t\t\tcolor: #127DB3;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.footer a,\n" +
                    "\t\t.footer a:hover {\n" +
                    "\t\t\tcolor: #999999;\n" +
                    "\t\t}\n" +
                    "\t</style>\n" +
                    "</head>\n" +
                    "<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\n" +
                    "\tbackground-color: #F0F0F0;\n" +
                    "\tcolor: #000000;\" bgcolor=\"#F0F0F0\" text=\"#000000\">\n" +
                    "\t<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\">\n" +
                    "\t\t<tr>\n" +
                    "\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\" bgcolor=\"#F0F0F0\">\n" +
                    "\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\n" +
                    "\tmax-width: 560px;\" class=\"wrapper\">\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\n" +
                    "\t\t\tpadding-top: 20px;\n" +
                    "\t\t\tpadding-bottom: 20px;\">\n" +
                    "\t\t\t\t\t\t\t<div style=\"display: none; visibility: hidden; overflow: hidden; opacity: 0; font-size: 1px; line-height: 1px; height: 0; max-height: 0; max-width: 0;\n" +
                    "\t\t\tcolor: #F0F0F0;\" class=\"preheader\">\n" +
                    "\t\t\t\t\t\t\t\tAvailable on&nbsp;GitHub and&nbsp;CodePen. Highly compatible. Designer friendly. More than 50%&nbsp;of&nbsp;total email opens occurred on&nbsp;a&nbsp;mobile device&nbsp;— a&nbsp;mobile-friendly design is&nbsp;a&nbsp;must for&nbsp;email campaigns.</div>\n" +
                    "\t\t\t\t\t\t\t<img border=\"0\" vspace=\"0\" hspace=\"0\" src=\"drawable/androidtel.png\" width=\"520\" height=\"350\" alt=\"Logo\" title=\"Logo\" style=\"\n" +
                    "\t\t\t\tcolor: #000000;\n" +
                    "\t\t\t\tfont-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t</table>\n" +
                    "\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" bgcolor=\"#FFFFFF\" width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\n" +
                    "\tmax-width: 560px;\" class=\"container\">\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\n" +
                    "\t\t\tpadding-top: 25px;\n" +
                    "\t\t\tcolor: #46a6b4;\n" +
                    "\t\t\tfont-family: sans-serif;\" class=\"header\">\n" +
                    "\t\t\t\t\t\t\tCoinquiOrganizer ti da il benvenuto!\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\n" +
                    "\t\t\tpadding-top: 20px;\" class=\"hero\"><img border=\"0\" vspace=\"0\" hspace=\"0\" src=\"drawable/img1.png\" alt=\"Please enable images to view this content\" width=\"560\" style=\"\n" +
                    "\t\t\twidth: 100%;\n" +
                    "\t\t\tmax-width: 560px;\n" +
                    "\t\t\tcolor: #000000; font-size: 13px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\n" +
                    "\t\t\tpadding-top: 25px; \n" +
                    "\t\t\tcolor: #000000;\n" +
                    "\t\t\tfont-family: sans-serif;\" class=\"paragraph\">\n" +
                    "\t\t\t\t\t\t\tHai ricevuto questa e-mail perché hai registrato la tua casa su CoinquiOrganizer e le tue relative credenziali o sei stato registrato da qualche tuo coinquilino!\n" +
                    "\t\t\t\t\t\t\tSe fosse il secondo caso immagino ti starai chiedendo di cosa si tratta: è un'app che ti aiuterà a gestire le faccende domestiche, divisione dei soldi spesi, ed ha a disposizione dei post-it per farti annotare tutte le cose da fare assieme i tuoi coinquilini.\n" +
                    "\t\t\t\t\t\t\tIn pratica non dovrai più tenere a mente le pulizie da fare, i soldi che avete speso in comune o conservare mille scontrini per casa o post-it!\n" +
                    "\t\t\t\t\t\t\tPerciò cosa aspetti? Scorri giù ed accedi con le tue credenziali:\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\n" +
                    "\t\t\tpadding-top: 25px;\n" +
                    "\t\t\tpadding-bottom: 5px;\" class=\"button\"><a href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\n" +
                    "\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t\t</a>\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\n" +
                    "\t\t\tpadding-top: 25px;\" class=\"line\">\n" +
                    "\t\t\t\t\t\t\t<hr color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%;\" class=\"list-item\">\n" +
                    "\t\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: inherit; margin: 0; padding: 0; border-collapse: collapse; border-spacing: 0;\">\n" +
                    "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0;\n" +
                    "\t\t\t\t\tpadding-top: 30px;\n" +
                    "\t\t\t\t\tpadding-right: 20px;\"><img border=\"0\" vspace=\"0\" hspace=\"0\" style=\"padding: 0; margin: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\n" +
                    "\t\t\t\t\tcolor: #000000;\" src=\"drawable/user1.png\" alt=\"H\" width=\"50\" height=\"50\"></td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"font-size: 17px; font-weight: 400; line-height: 160%; border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\n" +
                    "\t\t\t\t\tpadding-top: 25px;\n" +
                    "\t\t\t\t\tcolor: #000000;\n" +
                    "\t\t\t\t\tfont-family: sans-serif;\" class=\"paragraph\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<b style=\"color: #46a6b4;\">USERNAME:</b><br />\n" +
                    "\t\t\t\t\t\t\t\t\t\t" + it.getUsername() +".\n" +
                    "\t\t\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0;\n" +
                    "\t\t\t\t\tpadding-top: 30px;\n" +
                    "\t\t\t\t\tpadding-right: 20px;\"><img border=\"0\" vspace=\"0\" hspace=\"0\" style=\"padding: 0; margin: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\n" +
                    "\t\t\t\t\tcolor: #000000;\" src=\"drawable/lock2.png\" alt=\"D\" width=\"50\" height=\"50\"></td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"font-size: 17px; font-weight: 400; line-height: 160%; border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\n" +
                    "\t\t\t\t\tpadding-top: 25px;\n" +
                    "\t\t\t\t\tcolor: #000000;\n" +
                    "\t\t\t\t\tfont-family: sans-serif;\" class=\"paragraph\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<b style=\"color: #ef6330;\">PASSWORD:</b><br />\n" +
                    "\t\t\t\t\t\t\t\t\t\t"+ it.getPassword() + ".\n" +
                    "\t\t\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t\t\t</table>\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\n" +
                    "\t\t\tpadding-top: 25px;\" class=\"line\">\n" +
                    "\t\t\t\t\t\t\t<hr color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-bottom: 3px; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 18px; font-weight: 300; line-height: 150%;\n" +
                    "\t\t\tpadding-top: 5px;\n" +
                    "\t\t\tcolor: #000000;\n" +
                    "\t\t\tfont-family: sans-serif;\" class=\"subheader\">\n" +
                    "\t\t\t\t\t\t\tSviluppato da: Pierpaolo Sestito\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\n" +
                    "\t\t\tpadding-top: 20px;\" class=\"hero\"><img border=\"0\" vspace=\"0\" hspace=\"0\" src=\"drawable/profilo11.png\" alt=\"Please enable images to view this content\" width=\"560\" style=\"\n" +
                    "\t\t\twidth: 100%;\n" +
                    "\t\t\tmax-width: 560px;\n" +
                    "\t\t\tcolor: #000000; font-size: 13px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t</table>\n" +
                    "\t\t\t\t\n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>");
            mailSender.execute();
        }
    }

    public static void sendWhatsAppWelcomeMessage(Context context,String groupID){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?id="+groupID+"&text=Benvenuto"));
        context.startActivity(intent);
    }
}
