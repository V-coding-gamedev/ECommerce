/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.Properties; // Được sử dụng để cấu hình các thuộc tính cần thiết cho việc gửi email
import javax.mail.*; // Cung cấp các lớp và giao diện để gửi email
import javax.mail.internet.*; // Chứa các lớp hỗ trợ để làm việc với email theo định dạng MIME (Internet email).

public class EmailSender {

    public static void sendEmail(String recipient, String subject, String content) throws MessagingException {
        String host = "smtp.gmail.com";
        final String username = "caohv9@gmail.com";
        final String password = "qwgs ktuk rujz szbn";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); // Yêu cầu xác thực bằng username và password.
        props.put("mail.smtp.starttls.enable", "true"); // Kích hoạt mã hóa STARTTLS để bảo mật giao tiếp.
        props.put("mail.smtp.host", host); // Chỉ định địa chỉ máy chủ SMTP.
        props.put("mail.smtp.port", "587"); // Cổng giao tiếp của máy chủ SMTP (587 là cổng SMTP với TLS)
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // buộc JavaMail sử dụng giao thức cụ thể:

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); // xác thực email gửi đi bằng username và password
            }
        });

        // Message: đại diện cho email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username)); // Đặt địa chỉ email của người gửi.
        /* 
            setRecipients: Đặt danh sách người nhận email.
            Message.RecipientType.TO: Loại người nhận (TO: người nhận chính).
            InternetAddress.parse(recipient): Chuyển chuỗi email thành danh sách địa chỉ email.
        */
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject); // Đặt tiêu đề của email.
        message.setText(content); // Đặt nội dung của email 
        
        Transport.send(message); // Gửi email qua SMTP server đã cấu hình
    }
}
