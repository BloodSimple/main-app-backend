package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String fromAddress = "isa.mejl.12345@gmail.com";

    public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String toAddress = user.getEmail();
        String senderName = "Blood Simple Developers";
        String subject = "Please verify your registration";
        /*
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "<i>Blood Simple developers<i>.";
        */
        String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><!--[if gte mso 9]><xml> <o:OfficeDocumentSettings> <o:AllowPNG/> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings></xml><![endif]--> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <meta name=\"x-apple-disable-message-reformatting\"> <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]--> <title></title> <style type=\"text/css\"> @media only screen and (min-width: 620px) { .u-row { width: 600px !important; } .u-row .u-col { vertical-align: top; } .u-row .u-col-100 { width: 600px !important; }}@media (max-width: 620px) { .u-row-container { max-width: 100% !important; padding-left: 0px !important; padding-right: 0px !important; } .u-row .u-col { min-width: 320px !important; max-width: 100% !important; display: block !important; } .u-row { width: 100% !important; } .u-col { width: 100% !important; } .u-col > div { margin: 0 auto; }}body { margin: 0; padding: 0;}table,tr,td { vertical-align: top; border-collapse: collapse;}.ie-container table,.mso-container table { table-layout: fixed;}* { line-height: inherit;}a[x-apple-data-detectors='true'] { color: inherit !important; text-decoration: none !important;}table, td { color: #000000; } #u_body a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_heading_6 .v-font-size { font-size: 27px !important; } #u_content_heading_1 .v-font-size { font-size: 27px !important; } #u_content_heading_4 .v-font-size { font-size: 27px !important; } #u_content_button_1 .v-container-padding-padding { padding: 10px 10px 40px !important; } #u_content_button_1 .v-size-width { width: 65% !important; } #u_content_heading_5 .v-font-size { font-size: 27px !important; } } </style> <!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css2?family=Arvo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]--></head><body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #5e1313;color: #000000\"> <!--[if IE]><div class=\"ie-container\"><![endif]--> <!--[if mso]><div class=\"mso-container\"><![endif]--> <table id=\"u_body\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #5e1313;width:100%\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr style=\"vertical-align: top\"> <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\"> <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #5e1313;\"><![endif]--> <div class=\"u-row-container\" style=\"padding: 0px;background-color: #5e1313\"> <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #5e1313;\"> <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\"> <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #5e1313;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #5e1313;\"><![endif]--> <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"background-color: #5e1313;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]--><div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\"> <div style=\"background-color: #5e1313;height: 100%;width: 100% !important;\"> <!--[if (!mso)&(!IE)]><!--><div style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]--> <table id=\"u_content_heading_6\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"> <tbody> <tr> <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px;font-family:'Open Sans',sans-serif;\" align=\"left\"> <h1 class=\"v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: Arvo; font-size: 26px;\"><br />Dear [[name]],</h1> </td> </tr> </tbody></table><table id=\"u_content_heading_1\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"> <tbody> <tr> <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px;font-family:'Open Sans',sans-serif;\" align=\"left\"> <h1 class=\"v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: Arvo; font-size: 26px;\">Please <strong>verify </strong>your registration!</h1> </td> </tr> </tbody></table><table id=\"u_content_heading_4\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"> <tbody> <tr> <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px;font-family:'Open Sans',sans-serif;\" align=\"left\"> <h3 class=\"v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: Arvo; font-size: 18px;\">Please <strong>click </strong>the button below to verify your registration:</h3> </td> </tr> </tbody></table><table id=\"u_content_button_1\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"> <tbody> <tr> <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px;font-family:'Open Sans',sans-serif;\" align=\"left\"> <!--[if mso]><style>.v-button {background: transparent !important;}</style><![endif]--><div align=\"center\"> <!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"[[URL]]\" style=\"height:37px; v-text-anchor:middle; width:230px;\" arcsize=\"67.5%\" stroke=\"f\" fillcolor=\"#ffffff\"><w:anchorlock/><center style=\"color:#5e1313;font-family:'Open Sans',sans-serif;\"><![endif]--> <a href=\"[[URL]]\" target=\"_blank\" class=\"v-button v-size-width\" style=\"box-sizing: border-box;display: inline-block;font-family:'Open Sans',sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #5e1313; background-color: #ffffff; border-radius: 25px;-webkit-border-radius: 25px; -moz-border-radius: 25px; width:46%; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;\"> <span style=\"display:block;padding:10px 20px;line-height:120%;\"><strong><span style=\"font-size: 14px; line-height: 16.8px;\">Verify</span></strong></span> </a> <!--[if mso]></center></v:roundrect><![endif]--></div> </td> </tr> </tbody></table><table id=\"u_content_heading_5\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"> <tbody> <tr> <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px;font-family:'Open Sans',sans-serif;\" align=\"left\"> <h4 class=\"v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: Arvo; font-size: 13px;\">Thank you, <br /><em>Blood Simple developers</em>.</h4> </td> </tr> </tbody></table> <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]--> </div></div><!--[if (mso)|(IE)]></td><![endif]--> <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]--> </div> </div></div> <!--[if (mso)|(IE)]></td></tr></table><![endif]--> </td> </tr> </tbody> </table> <!--[if mso]></div><![endif]--> <!--[if IE]></div><![endif]--></body></html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());
        String verifyURL = siteURL + "/api/verifyUserAccount?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendSuccessfulReservationEmail(User user, Appointment appointment)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress= user.getEmail();
        String senderName= "Blood Simple";
        String subject = "Your reservation is successful.";
        String content = "Dear [[name]],<br>"
                + "You made reservation for an appointment:<br>"
                + "Start time:<br>"
                + appointment.getStartTime() + "<br>"
                + "Duration:<br>"
                + appointment.getDuration() + "<br>"
                + "Medical center:<br>"
                + appointment.getMedicalCenter().getName() + "<br>"
                + appointment.getMedicalCenter().getAddress() + "<br>"
                + "Blood Simple.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());

        helper.setText(content, true);

        mailSender.send(message);

    }




}
