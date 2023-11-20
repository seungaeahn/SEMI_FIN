
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

//플레이리스트의 대표사진을 가져오는 Servlet
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
				}else { //null값인경우 처리
					System.out.println("이미지값이 없습니다.");
					
					
				}
				// 이미지 데이터를 Base64로 인코딩 DB에서 가져와야하기때문에 
				
				
					//response.setContentType("image/jpeg");
					//response.getOutputStream().write(imageData);
				 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}