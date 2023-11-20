package shinee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//새로운 플레이리스트를 생성하는 Servlet
@WebServlet("/UploadServlet")
@MultipartConfig(
		fileSizeThreshold =1024*1024,//파일데이터를 디스크에 기록을 시작하기 전에 메모리에 보유되는 최대크기(1MB)
		maxFileSize = 1024*1024 *5,//업로드할 파일의 최대 크기(5MB)
		maxRequestSize =1024*1024*10,//요청 데이터의 최대 크기(10MB)
		location="/org"//파일이 디스크에 저장될 임시 디렉터리
		)
public class Create_Playlist_Servlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL="jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser ="shinee";
		String jdbcPW = "shinee";
		
		String title = request.getParameter("title");
		String user_id = (String)request.getParameter("user_idd");
		Part imagePart = request.getPart("image");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPW);
			String sql = "INSERT INTO playlist_info(playlist_id,playlist_name,user_id,image)"+
			"VALUES(playlist_seq.nextval,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, title);
			ps.setString(2, user_id);
			ps.setBinaryStream(3, imagePart.getInputStream(),(int) imagePart.getSize());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("MyPlaylist.jsp");
	}

}