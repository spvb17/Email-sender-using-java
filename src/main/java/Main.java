import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class Main
{
    public static void main(String[] args) throws MessagingException, IOException
    {
        String fromAddress;         //sender's address
        String fromAddressPassword; //sender address password
        String toAddress;           //recipient address
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(fromAddress, "spvb17"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        //we can add more recipients, the message will be sent to everyone
        msg.setSubject("Hello");

        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart attachment1 = new MimeBodyPart();
        attachment1.attachFile("src/main/resources/359d877d4ff2a0e8c634324d2ee0ab3f.jpg");
        MimeBodyPart attachment2 = new MimeBodyPart();
        attachment2.attachFile("src/main/resources/abqfh2ny.jpg");
        MimeBodyPart messageBody = new MimeBodyPart();
        messageBody.setContent("<h1>Mail sender project</h1>", "text/html");
        mimeMultipart.addBodyPart(messageBody);
        mimeMultipart.addBodyPart(attachment1);
        mimeMultipart.addBodyPart(attachment2);
        msg.setContent(mimeMultipart);

        Transport transport = mailSession.getTransport();
        transport.connect(fromAddress, fromAddressPassword);
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Mail sent");
        transport.close();
    }
}