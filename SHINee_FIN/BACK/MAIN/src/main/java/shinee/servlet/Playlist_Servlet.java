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
		// JDBC �ҷ�����
		
	// JDBC �ҷ�����
	
			String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String jdbcUsername = "shinee";
			String jdbcPassword = "shinee";
			try {
				//����̹� �ε�
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				//DB ����
				Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				
	           
	            String user_id = request.getParameter("user_id");
	            String playlist_name = request.getParameter("playlist_name");
	            String image = request.getParameter("image");
	        	
	        	
	           
	         
				//SELECT�� ȸ��������������
					//sql ���� 
		            String sql =  "SELECT user_id, playlist_name, image FROM playlist_info WHERE user_id=?";
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1, user_id);
		            //���� => ��� ���� 
		            preparedStatement.executeUpdate();
				
				
		           
			
				//������ ��� �̵��� ������ �������ְ� �ٽ� ����
		       
				response.sendRedirect("mypageMain.jsp");
				
			} catch (SQLException e) {
				// ������ ��� �̵��� ������ ����
				response.sendRedirect("mypageMain.jsp");
				System.out.println("�÷��̸���Ʈ �����ҷ����⿡ �����Ͽ����ϴ�.");
				e.printStackTrace();
			}
		}

}