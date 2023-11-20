package shinee.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shinee.vo.Playlist_song_VO;

//�÷��̸���Ʈ�� ��� ���ǵ��� Ȯ���ϱ� ���� DAO�Դϴ�.

public class PlaylistMusic_DAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "shinee";
	private static final String jdbcPassword = "shinee";
	
	//������
	public PlaylistMusic_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�÷��̸���Ʈ �� ��� ���� ������ �������� �޼���
	public List<Playlist_song_VO> getAllMusiclists(int playlistId){
		Playlist_song_VO musiclist = null;
		List<Playlist_song_VO> musiclists = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM playlist_song "
					+ "INNER JOIN music_info ON playlist_song.song_id = music_info.song_id "
					+ "INNER JOIN playlist_info ON playlist_song.playlist_id= playlist_info.playlist_id "
					+ "WHERE playlist_song.playlist_id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, playlistId);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				
				int playlist_Id = resultSet.getInt("playlist_id");
				String title = resultSet.getString("song_name");
				String artist = resultSet.getString("artist_name");
				
				musiclist = new Playlist_song_VO(playlist_Id,title,artist);
				
				musiclists.add(musiclist);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return musiclists;
	}//getAllMusicLists()
}