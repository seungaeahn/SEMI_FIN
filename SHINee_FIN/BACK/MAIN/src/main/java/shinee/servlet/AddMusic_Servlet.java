package shinee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�÷��̸���Ʈ�� �뷡 �߰��ϴ� Servlet
@WebServlet("/AddMusicServlet")
public class AddMusic_Servlet extends HttpServlet {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUser="shinee";
	private static final String jdbcPW="shinee";
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�÷��̸���Ʈ �ʵ�...
		int playlist_id = Integer.parseInt(request.getParameter("playlistField"));
		
		String music_id = (String)request.getParameter("music_data");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPW);
			String insertSQL = "INSERT INTO playlist_song(playlist_id, song_id) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(insertSQL);
			//ps.setString(1, "searchField���� �޾ƿ;��մϴ�!!!���?music_search�� �÷��̸���Ʈ ��ϰű⿡��~!!!!");
			ps.setInt(1, playlist_id);
			ps.setString(2, music_id);
			
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted >0) {
				System.out.println("���ԿϷ�");
			} else {
				System.out.println("���Խ���");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�߰� �� ������ �ٽ� �˻�â���� ����
		response.sendRedirect("music_search.jsp");
	}

}
