package shinee.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shinee.vo.Playlist_info_VO;

//DB에 존재하는 모든 플레이리스트의 목록을 가져오는 Servlet
@WebServlet("/All_Playlist_Servlet")
public class All_Playlist_Servlet extends HttpServlet {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername="shinee";
	private static final String jdbcPassword="shinee";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection connection =null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			//SQL쿼리
			String sql = "SELECT * FROM playlist_info";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			
			//저장할 ArrayList
			ArrayList<Playlist_info_VO> playlistList = new ArrayList<>(); 
			
			while(resultSet.next()) {
				int playlistId = resultSet.getInt("playlist_id");
				String playlistName = resultSet.getString("playlist_name");
				String user_id = resultSet.getString("user_id");
				Blob Image = resultSet.getBlob("image");
				byte[] imageData = Image.getBytes(1,(int)Image.length());
				String imageBase64 = Base64.getEncoder().encodeToString(imageData);
				String image = "data:image/jpeg;base64," + imageBase64;
				
				response.setContentType("image/jpeg");
				response.getOutputStream().write(imageData);
				Playlist_info_VO playlist = new Playlist_info_VO(playlistId, playlistName, user_id, image);
				//하나씩 add해서 넣어줌
				playlistList.add(playlist);
				
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}