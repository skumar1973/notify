package com.c2pi.notify.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.c2pi.notify.dao.NotifyTaskDAO;
import com.c2pi.notify.entity.EmployeesTaskNotification;
import com.c2pi.notify.entity.Task;

/**
 * @author Shailendrak
 * 
 */
public class NotifyTaskManager {
	NotifyTaskDAO ntdao = null;

	/**
	 * @param empID
	 * @param empTask
	 * @param periodDate
	 * @param createdBy
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String addNotifyTask(int empID, Integer[] empTask, Date periodDate,
			String createdBy) throws ClassNotFoundException, IOException {
		String res = "";
		ntdao = new NotifyTaskDAO();

		try {
			res = ntdao.addNotifyTask(empID, empTask, periodDate, createdBy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @param loginID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 */
	public ArrayList<EmployeesTaskNotification> getEtnList(String loginID)
			throws ClassNotFoundException, SQLException, IOException {

		ArrayList<EmployeesTaskNotification> etnList = null;
		ntdao = new NotifyTaskDAO();

		etnList = ntdao.getETNList(loginID);

		return etnList;
	}

	public ArrayList<Task> getEmpTaskList(String loginID)
			throws ClassNotFoundException, SQLException, IOException {

		ArrayList<Task> empTaskList = null;
		ntdao = new NotifyTaskDAO();

		System.out.println("getemptasklist ...");
		empTaskList = ntdao.getEmpTaskList(loginID);

		return empTaskList;
	}

	public String sendNotifyEmail(Integer[] empTask, int empID)
			throws SQLException, ClassNotFoundException, IOException {
		String res = "SUCCESS: Email Sent..";

		final String from = "shailendrarsingh@gmail.com";
		final String password = "Q1az2wsx";
		String to = "shailendra1603@yahoo.com";
		String subject = "Test - Notify Tasks";

		ntdao = new NotifyTaskDAO();
		to = ntdao.getMgrEmail(empID);
		System.out.println("email :" + to);
		String taskName = ntdao.getTaskName(empTask);
		String body = "Dear Sir, This is to inform that I have completed these tasks.  ";
		body = body + taskName;
		body = body + " Regards, Shailendra";

		Properties properties = new Properties();
		{
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			// properties.put("mail.smtp.host", "BE-EXCH-02.clear2pay.com");
			// properties.put("mail.smtp.socketFactory.port", "25");
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			// properties.put("mail.smtp.port", "25");
		}

		try {
			Session session = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from, password);
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (Exception e) {
			res = "ERROR: Email Not Sent";
			e.printStackTrace();
		}

		return res;
	}
	public ArrayList<String> getDateList() throws ClassNotFoundException, SQLException, IOException {
			ArrayList<String> dateList= null;
			ntdao = new NotifyTaskDAO();
			dateList=ntdao.getDateList();
			return dateList;
		}
	
}
