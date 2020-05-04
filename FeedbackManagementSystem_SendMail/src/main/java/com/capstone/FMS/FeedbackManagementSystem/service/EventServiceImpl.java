package com.capstone.FMS.FeedbackManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.FMS.FeedbackManagementSystem.Model.Event;
import com.capstone.FMS.FeedbackManagementSystem.Repository.EventRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class EventServiceImpl implements EventService {

	Session mailSession;
	private static String[] columns = { "event_id", "base_location", "beneficiary_name", "council_name", "event_name",
			"event_description", "event_date", "employee_id", "employee_name", "volunteer_hours", "travel_hours",
			"lives_impacted", "business_unit", "event_status", "iiep_category" };
	List<Event> output = new ArrayList<Event>();

	@Autowired
	public EventRepository eventRepo;

	@Override
	public Flux<Event> getAllEventList() {
		Flux<Event> events = eventRepo.findAll();
		return events;
	}

	@Override
	public Flux<Event> searchEventsById(String eventId) {
		return eventRepo.findAllByEvent_id(eventId);
	}

	@Override
	public Flux<Event> searchEventsByVH(int volunteerHours) {
		return eventRepo.findAllByVolunteer_hours(volunteerHours);
	}
	
	@Override
	public Mono<Event> findEventById(int id) {
		return eventRepo.findById((long) id);
	}

	@Override
	public Mono<String> SendMail() {
		generateExcel();
		System.out.println("SimpleEmail Start");
		String smtpHostServer = "smtp.gmail.com";
		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHostServer);
		
		props.put("mail.smtp.port", 587);

		props.put("mail.smtp.starttls.enable", true);

		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		props.put("mail.smtp.auth", true); 
											
		Authenticator auth = new Authenticator() {			
													
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("srik636@gmail.com", "passwordsrinu");
			}
		};
		Session session = Session.getInstance(props, auth);

		send(session, "anil.becb@gmail.com", "Subject host", "Mail sent from javax");

		return Mono.just("Report has been sent successfully. Please check your email");
	}

	public static void send(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("srik636@gmail.com"));

			msg.setReplyTo(InternetAddress.parse(toEmail, false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Second part is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "EventsData.xlsx";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			msg.setContent(multipart);

			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("Email sent !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generateExcel() {
		Flux<Event> values = eventRepo.findAll();
		values.subscribe((next) -> {
			output.add(next);
		});
		copyToExcel(output);
	}

	private void copyToExcel(List<Event> output) {

		Workbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Events");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		int rowNum = 1;
		for (Event eve : output) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(eve.getEvent_id());

			row.createCell(1).setCellValue(eve.getBase_location());

			row.createCell(2).setCellValue(eve.getBeneficiary_name());

			row.createCell(3).setCellValue(eve.getCouncil_name());

			row.createCell(4).setCellValue(eve.getEvent_name());

			row.createCell(5).setCellValue(eve.getEvent_description());

			Cell dateOfBirthCell = row.createCell(6);
			dateOfBirthCell.setCellValue(eve.getEvent_date());
			dateOfBirthCell.setCellStyle(dateCellStyle);

			row.createCell(7).setCellValue(eve.getEmployee_id());

			row.createCell(8).setCellValue(eve.getEmployee_name());

			row.createCell(9).setCellValue(eve.getVolunteer_hours());

			row.createCell(10).setCellValue(eve.getTravel_hours());

			row.createCell(11).setCellValue(eve.getLives_impacted());

			row.createCell(12).setCellValue(eve.getBusiness_unit());

			row.createCell(13).setCellValue(eve.getEvent_status());

			row.createCell(14).setCellValue(eve.getIiep_category());
		}

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("JsonExcel.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close(); // Closing the workbook
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
