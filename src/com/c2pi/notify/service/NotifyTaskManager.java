package com.c2pi.notify.service;

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
import com.c2pi.notify.entity.Tasks;

public class NotifyTaskManager {
	
	public String addnotifytask(int emp_id, Integer[] emptask, Date period_date, String created_by){
		String res="";	
		NotifyTaskDAO ntdao = new NotifyTaskDAO();
		
		try {
			res=ntdao.addnotifytask(emp_id, emptask, period_date, created_by);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<EmployeesTaskNotification> getetnlist( String login_id) {

		ArrayList<EmployeesTaskNotification> etnlist = null;
		NotifyTaskDAO ntfdao = new NotifyTaskDAO();

		try {
			etnlist = ntfdao.getetnlist(login_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return etnlist;
	}		
	
	public ArrayList<Tasks> getemptasklist( String login_id) {

		ArrayList<Tasks> emptasklist = null;
		NotifyTaskDAO ntfdao = new NotifyTaskDAO();

		try {
			System.out.println("getemptasklist ...");
			emptasklist = ntfdao.getemptasklist(login_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emptasklist;
	}	
	
	public String sendnotifyemail(Integer[] emptask){
		String res="SUCCESS";
		
		final String from="shailendrarsingh@gmail.com";
		final String password="Q1az2wsx";
//		int cnt=0;
		String to="shailendrarsingh@gmail.com";
		String subject="Test - Notify Tasks";
		
		NotifyTaskDAO ntd = new NotifyTaskDAO();
		String taskname=ntd.getTaskName(emptask);
		String body="Dear Sir, This is to inform that I have completed these tasks.  ";
		body=body+taskname;
//		while (cnt < emptask.length){
//			body = body +" , "+ emptask[cnt];
//			cnt=cnt+1;
//		}
			body = body + " Regards, Shailendra";

		Properties properties = new Properties();
			   {
		      properties.put("mail.smtp.host", "smtp.gmail.com");
		      properties.put("mail.smtp.socketFactory.port", "465");
//			  properties.put("mail.smtp.host", "BE-EXCH-02.clear2pay.com");
//			  properties.put("mail.smtp.socketFactory.port", "25");
			  properties.put("mail.smtp.socketFactory.class",
		                     "javax.net.ssl.SSLSocketFactory");
		      properties.put("mail.smtp.auth", "true");
		      properties.put("mail.smtp.port", "465");
//		      properties.put("mail.smtp.port", "25");
		   }

			   try
			      {
			         Session session = Session.getDefaultInstance(properties,  
			            new javax.mail.Authenticator() {
			            protected PasswordAuthentication 
			            getPasswordAuthentication() {
			            return new 
			            PasswordAuthentication(from, password);
			            }});

			         Message message = new MimeMessage(session);
			         message.setFrom(new InternetAddress(from));
			         message.setRecipients(Message.RecipientType.TO, 
			            InternetAddress.parse(to));
			         message.setSubject(subject);
			         message.setText(body);
			         Transport.send(message);
			      }
			      catch(Exception e)
			      {
			         res = "ERROR";
			         e.printStackTrace();
			      }

		return res;
	}
}
