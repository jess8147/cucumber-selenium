package org.asite.automation.common.utils;

import java.io.File;
import java.net.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;
import org.asite.automation.common.base.TestEmailBase;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

// TODO: Auto-generated Javadoc

/**
 * The Class EmailUtils.
 *
 * @author jasminprajapati
 */
public class EmailUtils implements TestEmailBase {

	/** The logger. */
	final private static Logger LOGGER = Logger.getLogger(EmailUtils.class.getName());
	
	/** The sender. */
	private String sender = null;

	/** The recipients. */
	private List<String> recipients;

	/** The subject. */
	private String subject;

	/** The content. */
	private String content;

	/** The attachments. */
	private String[] attachments;

	/** The properties. */
	private Properties properties;

	/** The add management users flag. */
	private boolean addManagementUsersFlag = false;

	/**
     * Instantiates a new Email utils.
     */
    public EmailUtils() {setEmailProperties();}

	/**
	 * Sets the email properties.
	 */
	private void setEmailProperties() {
		properties = System.getProperties();
		/*properties.put("mail.smtp.host", ResourceHandler.getPropertyValue("mail.smtp.host"));
		properties.put("mail.smtp.port", Integer.parseInt(ResourceHandler.getPropertyValue("mail.smtp.port")));*/
		properties.put("mail.smtp.auth", false);
		/*properties.put("mail.smtp.auth", true);*/
	}

	/**
	 * Gets the email properties.
	 *
	 * @return the email properties
	 */
	private Properties getEmailProperties() {
		return properties;
	}

    /**
     * Send email.
     *
     * @param sender      the sender
     * @param recipients  the recipients
     * @param subject     the subject
     * @param content     the content
     * @param attachments the attachments
     */
    public void sendEmail(String sender, List<String> recipients, String subject, String content, String...attachments) {
		this.sender = sender;
		this.recipients = recipients;
		this.subject = subject;
		this.content = content;
		this.attachments = attachments;
		this.sendEmail();
	}

	/**
	 * Send failure email.
	 *
	 * @param screenShotPath the screen shot path
	 * @param scenarioTitle the scenario title
	 */
	public void sendFailureEmail(String screenShotPath, String scenarioTitle) {

		addManagementUsersFlag = false;

		sendEmail(getEmailSender(),
				  getEmailReceivers(),
				  getFailureEmailSubject(scenarioTitle),
				  getFailureEmailContent(screenShotPath),
				  screenShotPath);
	}
	
	/**
	 * Send initiation email.
	 */
	public void sendInitiationEmail() {

		addManagementUsersFlag = false;

		sendEmail(getEmailSender(),
				  getEmailReceivers(),
				  getInitiationEmailSubject(),
				  AdoddleCommonStringPool.INITIATION_EMAIL_CONTENT);
	}

    /**
     * Send reporting email.
     */
    public void sendReportingEmail() {
		addManagementUsersFlag = false;
		sendEmail(getEmailSender(),
				getEmailReceivers(),
                getReportingEmailSubject(),
				getReportingEmailContent());
	}

	/**
	 * Gets the text message body part.
	 *
	 * @param text the text
	 * @return the text message body part
	 * @throws MessagingException the messaging exception
	 */
	private static BodyPart getTextMessageBodyPart(String text) throws MessagingException {
        BodyPart part = new MimeBodyPart();
        part.setText(text);
        return part;
    }

    /**
     * Gets the attachment body part.
     *
     * @param file the file
     * @return the attachment body part
     * @throws MessagingException the messaging exception
     */
    private static BodyPart getAttachmentBodyPart(File file) throws MessagingException {
        BodyPart part = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        part.setDataHandler(new DataHandler(source));
        part.setFileName(file.getName());
        return part;
    }

    /**
     * Send email.
     */
    private void sendEmail() {
		File attachment = null;

		if(attachments.length > 0)
			 attachment = new File(new ListUtils().getArrayList(attachments).get(0));

		/* create a session with an Authenticator*/
		/*Session session = Session.getInstance(getEmailProperties(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ResourceHandler.getPropertyValue("mail.smtp.user"), ResourceHandler.getPropertyValue("mail.smtp.gatepass"));
			}
		});*/

		Session session = Session.getInstance(getEmailProperties());

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			for(String recipient : this.recipients)
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			if(addManagementUsersFlag)
				addManagementUsers(message);
			message.setSubject(subject);
			message.setText(content);
			message.setSentDate(new Date());
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(getTextMessageBodyPart(content != null ? content : AdoddleCommonStringPool.EMPTY_STRING));
			if (attachment != null && attachment.isFile())
				multiPart.addBodyPart(getAttachmentBodyPart(attachment));
			message.setContent(multiPart);
			Transport.send(message);
			LOGGER.info("Email sent successfully with subject :"+subject+" to "+recipients.toString());
		} catch (Throwable mex) {
			mex.printStackTrace();
			LOGGER.error("Email sending failed with subject :"+subject);
			AutomationAssert.verifyTrue(mex.getLocalizedMessage(),false);
		}
	}

	/**
	 * Gets the application URL.
	 *
	 * @return the application URL
	 */
	private String getApplicationURL() {
		return ResourceUtils.getApplicationURL();
	}

	/**
	 * Gets the application.
	 *
	 * @return the application
	 */
	private String getApplication() {
		return new ResourceUtils().getApplicationModule();
	}

	/**
	 * Gets the email sender.
	 *
	 * @return the email sender
	 */
	private String getEmailSender() {
		return ResourceHandler.getPropertyValue("mail.smtp.sender");
	}

	/**
	 * Gets the email receivers.
	 *
	 * @return the email receivers
	 */
	private List<String> getEmailReceivers() {
    	return Arrays.asList(ResourceHandler.getPropertyValue("mail.smtp.recipient1"));
	}

	/**
	 * Gets the initiation email subject.
	 *
	 * @return the initiation email subject
	 */
	private String getInitiationEmailSubject() {
		return AdoddleCommonStringPool.INITIATION_EMAIL_SUBJECT + AdoddleCommonStringPool.COLON_STRING + ResourceHandler.getPropertyValue("application.test.module") + " - " + AdoddleCommonAppMethods.dataCenter;
	}

    /**
     * Gets the reporting email subject.
     *
     * @return the reporting email subject
     */
	private String getReportingEmailSubject() {
		if(new ResourceUtils().getApplicationModule().contains("Adoddle"))
			return AdoddleCommonStringPool.COMPLETION_EMAIL_SUBJECT + AdoddleCommonStringPool.COLON_STRING + ResourceHandler.getPropertyValue("application.test.module") + " - " + AdoddleCommonAppMethods.dataCenter;
		else
			return AdoddleCommonStringPool.COMPLETION_EMAIL_SUBJECT + AdoddleCommonStringPool.COLON_STRING + ResourceHandler.getPropertyValue("application.test.module") + " - " + ClassicCommonAppMethods.dataCenter;
	}

    /**
     * Gets the reporting email content.
     *
     * @return the reporting email content
     */
	private String getReportingEmailContent() {
		String reportingText = "Automation Report is available at: ";
	    String ip = ResourceHandler.loadProperty("selenium.server.hub.ip");
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // *EDIT*
                    if (addr instanceof Inet6Address || iface.getDisplayName().contains("VirtualBox")) continue;

                    ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
		return reportingText + AdoddleCommonStringPool.PATH_SPLIT_SLASH  + ip + "\\" + System.getProperty("user.dir").replace(":","$") + "\\" + getCopiedReportLocation() + "\\" + "feature-overview.html";
	}
	
	
	/**
	 * Gets the copied report location.
	 *
	 * @return the copied report location
	 */
	public String getCopiedReportLocation() {
		return ResourceHandler.getPropertyValue("application.test.dcs") + "\\" + new DateUtils().getUserCurrentDate("EE-MM-dd-yyyy");
	}

	/**
	 * Gets the failure email subject.
	 *
	 * @param scenarioTitle the scenario title
	 * @return the failure email subject
	 */
	private String getFailureEmailSubject(String scenarioTitle) {

		/* The data center. */
		String dataCenter = getApplication().contains("Adoddle") ? AdoddleCommonAppMethods.dataCenter : ClassicCommonAppMethods.dataCenter;

		subject = (getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA1_HOST) ?
				AdoddleCommonStringPool.ENV_QA1: (getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA2_HOST)) ?
				AdoddleCommonStringPool.ENV_QA2: AdoddleCommonStringPool.ENV_LIVE) +
				AdoddleCommonStringPool.HIPHEN_STRING + dataCenter;

		subject = getApplication() +
				AdoddleCommonStringPool.COLON_STRING +
				AdoddleCommonStringPool.SPACE_STRING +
				scenarioTitle +
				AdoddleCommonStringPool.SPACE_STRING +
				StringUtils.getEnclosedString(subject);

		return subject;
	}

	/**
	 * Gets the failure email content.
	 *
	 * @param screenShotPath the screen shot path
	 * @return the failure email content
	 */
	private String getFailureEmailContent(String screenShotPath) {

		return (AdoddleCommonStringPool.NEWLINE_STRING + AdoddleCommonStringPool.NEWLINE_STRING) +
				AdoddleCommonStringPool.AUTO_SCREENSHOT_PATH +
				AdoddleCommonStringPool.COLON_STRING +
				AdoddleCommonStringPool.SPACE_STRING +
				screenShotPath;
	}

	/**
	 * Adds the external users.
	 *
	 * @param message the message
	 * @throws MessagingException the messaging exception
	 */
	private static void addManagementUsers(MimeMessage message) throws MessagingException {
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				ResourceHandler.getPropertyValue("mail.smtp.recipient2")));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				ResourceHandler.getPropertyValue("mail.smtp.recipient3")));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				ResourceHandler.getPropertyValue("mail.smtp.recipient4")));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(
				ResourceHandler.getPropertyValue("mail.smtp.cc")));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("ritesh@asite.com"));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("rbutola@asite.com"));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("nayanpriyamodi@asite.com"));
	}

	/*
	 * Adds the load test users.
	 *
	 * @param message the message
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 *
	private static void addLoadTestUsers(MimeMessage message) throws MessagingException {
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("priyampatel@asite.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("aparikh@asite.com"));
	}*/
}