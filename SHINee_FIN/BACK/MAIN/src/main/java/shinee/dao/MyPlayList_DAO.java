package shinee.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Part;

import shinee.vo.Playlist_info_VO;

public class MyPlayList_DAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "shinee";
	private static final String jdbcPassword = "shinee";
	
	//������
	public MyPlayList_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//user_id�� �ش��ϴ� ������� ��� �÷��̸���Ʈ���� ��ȯ�ϴ� �޼���
	public List<Playlist_info_VO> getAllPlaylists(String user_id){
		List<Playlist_info_VO> playlists = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM playlist_info WHERE user_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, user_id);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int playlistId = resultSet.getInt("playlist_id");
				String playlistName = resultSet.getString("playlist_name");
				String user_ID = resultSet.getString("user_id");
				Blob Image = resultSet.getBlob("image");
				byte[] imageData = Image.getBytes(1, (int) Image.length());
				String imageBase64 = Base64.getEncoder().encodeToString(imageData);
				String image = "data:image/jpeg;base64," + imageBase64;

				Playlist_info_VO playlist = new Playlist_info_VO(playlistId,playlistName,user_ID,image);
				playlists.add(playlist);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return playlists;

	}//getAllPlaylists()
	
	//�Է��� playlist_id�� �ش��ϴ� ��� ������ �������� �޼���
	public Playlist_info_VO getPlaylist_info(int playlistId) {
		Playlist_info_VO playlist = null;
		//select�ؼ� �ϳ��� ����
		Connection connection;
		try {
			connection =DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM playlist_info WHERE playlist_id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1,playlistId);
			ResultSet resultSet = ps.executeQuery();
			
			//new playlist �̿��ؼ� �� ��������
			if(resultSet.next()) {
				playlistId = resultSet.getInt("playlist_id");
				String playlistName = resultSet.getString("playlist_name");
				String user_id = resultSet.getString("user_id");
				Blob Image = resultSet.getBlob("Image");
				byte[] imageData = Image.getBytes(1, (int) Image.length());
				String imageBase64 = Base64.getEncoder().encodeToString(imageData);
				String image = "data:image/jpeg;base64," + imageBase64;
				playlist = new Playlist_info_VO(playlistId, playlistName,user_id,image);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return playlist;
		
	} //getPlaylist_info()

}