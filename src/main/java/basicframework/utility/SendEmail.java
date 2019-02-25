package basicframework.utility;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import basicframework.base.ConfigfileReader;

public class SendEmail {
	
	public static void mailTrigger(String reportingDirectory,ConfigfileReader configfileReader,
			String[] testCaseStatus,List<String> failedTestCases) throws AddressException, MessagingException{
		
		String reportingzipfolder=compressedZipFolder(reportingDirectory);
		
		final String userName= configfileReader.getUserId();
		final String password= configfileReader.getPassword();
		
		Properties props= new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "hostName/hosturl");
		props.put("mail.smtp.port", "25");
		
		Session session = Session.getInstance(props,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(userName,password);
			}
		});
		
		
		// create a default MimeMessage object
		Message message = new MimeMessage(session);
		
		// set from and To information
		message.setFrom(new InternetAddress("from mail id"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(configfileReader.getEmailTo()));
		
		message.setSubject(configfileReader.getApplicationName()+" Automation Execution Result");
		
		
		// create the Message Body Part
		BodyPart messageBodyPart = new MimeBodyPart();
		
		String htmlBody="</Br>Hi All, please  find attached result</Br>";
		
		messageBodyPart.setContent(htmlBody,"text/html");
		
		//create Multipart message
		Multipart multipart = new MimeMultipart();
		
		// set text message part
		multipart.addBodyPart(messageBodyPart);
		
		// part 2nd is attachment
		
		
		//send the complete message parts
		message.setContent(multipart);
		
		//send message 
		Transport.send(message);
		
		System.out.println("sent message sucessfully.......");
	}
	
	@SuppressWarnings("finally")
	public static String compressedZipFolder(String reportingDirectory){
		reportingDirectory=reportingDirectory.substring(0,reportingDirectory.length()-1);
		String zipName= reportingDirectory+".zip";
		
		try {
			ZipUnZipFolders.Zipfolder(Paths.get(reportingDirectory), Paths.get(zipName));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return zipName;
		}
	}

}
