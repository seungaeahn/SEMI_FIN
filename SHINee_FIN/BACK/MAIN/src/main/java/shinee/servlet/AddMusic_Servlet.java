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

//플레이리스트에 노래 추가하는 Servlet
@WebServlet("/AddMusicServlet")
public class AddMusic_Servlet extends HttpServlet {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUser="shinee";
	private static final String jdbcPW="shinee";
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//플레이리스트 필드...
		int playlist_id = Integer.parseInt(request.getParameter("playlistField"));
		
		String music_id = (String)request.getParameter("music_data");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPW);
			String insertSQL = "INSERT INTO playlist_song(playlist_id, song_id) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(insertSQL);
			//ps.setString(1, "searchField값을 받아와야합니다!!!어디서?music_search의 플레이리스트 목록거기에서~!!!!");
			ps.setInt(1, playlist_id);
			ps.setString(2, music_id);
			
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted >0) {
				System.out.println("삽입완료");
			} else {
				System.out.println("삽입실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//추가 다 했으면 다시 검색창으로 가렴
		response.sendRedirect("music_search.jsp");
	}

}
