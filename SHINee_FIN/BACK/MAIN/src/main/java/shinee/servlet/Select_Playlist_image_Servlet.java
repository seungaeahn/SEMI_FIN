
package shinee.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.sql.BLOB;

//�÷��̸���Ʈ�� ��ǥ������ �������� Servlet
@WebServlet("/ImageServlet")
public class Select_Playlist_image_Servlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser ="shinee";
		String jdbcPW = "shinee";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPW);
			
			//sql
			String sql = "SELECT image From playlist_info";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Blob Image = rs.getBlob("image");
				if (Image != null) {
					byte[] imageData = Image.getBytes(1,(int)Image.length());
					String base64Image = Base64.getEncoder().encodeToString(imageData);					
					String imageBase64 = "data:image/jpeg;base64, " + base64Image;
				    response.getWriter().print(imageBase64);
				}else { //null���ΰ�� ó��
					System.out.println("�̹������� �����ϴ�.");
					
					
				}
				// �̹��� �����͸� Base64�� ���ڵ� DB���� �����;��ϱ⶧���� 
				
				
					//response.setContentType("image/jpeg");
					//response.getOutputStream().write(imageData);
				 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}