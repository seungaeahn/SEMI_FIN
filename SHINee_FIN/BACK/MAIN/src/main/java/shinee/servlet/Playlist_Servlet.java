package shinee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlaylistServlet
 */
@WebServlet("/PlaylistServlet")
public class Playlist_Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JDBC 불러오기
		
	// JDBC 불러오기
	
			String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String jdbcUsername = "shinee";
			String jdbcPassword = "shinee";
			try {
				//드라이버 로딩
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				//DB 연결
				Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				
	           
	            String user_id = request.getParameter("user_id");
	            String playlist_name = request.getParameter("playlist_name");
	            String image = request.getParameter("image");
	        	
	        	
	           
	         
				//SELECT로 회원정보가져오기
					//sql 생성 
		            String sql =  "SELECT user_id, playlist_name, image FROM playlist_info WHERE user_id=?";
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1, user_id);
		            //실행 => 결과 저장 
		            preparedStatement.executeUpdate();
				
				
		           
			
				//성공할 경우 이동할 페이지 설정해주고 다시 전송
		       
				response.sendRedirect("mypageMain.jsp");
				
			} catch (SQLException e) {
				// 실패할 경우 이동할 페이지 설정
				response.sendRedirect("mypageMain.jsp");
				System.out.println("플레이리스트 정보불러오기에 실패하였습니다.");
				e.printStackTrace();
			}
		}

}