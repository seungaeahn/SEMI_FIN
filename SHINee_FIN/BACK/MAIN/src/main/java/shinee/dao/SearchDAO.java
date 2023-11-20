package shinee.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shinee.vo.Music_info_VO;
import shinee.vo.Playlist_info_VO;
import shinee.vo.User_info_VO;
	
	//��� �˻�â ��� ����ϴ� Ŭ����
	public class SearchDAO {
	
		private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String jbdcUsername = "shinee";
		private static final String jdbcPassword = "shinee";
		
		Connection connection = null;
		
		//�뷡�˻� �޼���
		public ArrayList<Music_info_VO> getSearchMusics(String searchText){
			
			String searchSQL = null;

			Connection connection = null;
			PreparedStatement searchState = null;
			ResultSet resultSet = null;
			
			//2. �迭 ���鷯�ӽ��ϴ� ����
			ArrayList<Music_info_VO> musicList = new ArrayList<>();
			
			try {
				
				if(searchText != null && !searchText.equals("") ){
					searchSQL = "SELECT * FROM music_info WHERE song_name LIKE ? OR artist_name LIKE ?";
	            } else if(searchText == null || searchText.equals("")) {
	            	return musicList;
	            }
				
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
				
				searchState = connection.prepareStatement(searchSQL);
				
				searchState.setString(1,"%"+searchText+"%");
				searchState.setString(2,"%"+searchText+"%");
				
				resultSet = searchState.executeQuery();
		
				while(resultSet.next()) {
					
					Music_info_VO music_info = new Music_info_VO();
					
					music_info.setSong_id(resultSet.getString("song_id"));
					music_info.setSong_name(resultSet.getString("song_name"));
					music_info.setArtist_name(resultSet.getString("artist_name"));
					music_info.setGenre(resultSet.getString("genre"));
					
					//�̹��� ��������
					Blob blob = resultSet.getBlob("album_img");
					byte[] imageData = blob.getBytes(1, (int) blob.length());
					String imageBase64 = java.util.Base64.getEncoder().encodeToString(imageData);
					String album_image = "data:image/jpeg;base64, " + imageBase64;
					
					music_info.setAlbum_img(album_image);

					//1. �迭�� ��ü �������� �ϴ� �迭 �����ðԿ�
					//3. �����ӽ��ϴ� ����ٰԿ�
					musicList.add(music_info);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return musicList;
			
		}

		//�ø��˻� �޼���
		public ArrayList<Playlist_info_VO> getSearchPlaylist(String searchText){
			
			String searchSQL = null;

			Connection connection = null;
			PreparedStatement searchState = null;
			ResultSet resultSet = null;
			
			//2. �迭 ���鷯�ӽ��ϴ� ����
			ArrayList<Playlist_info_VO> playlistList = new ArrayList<>();
			
			try {
				
				if(searchText != null && !searchText.equals("") ){
					searchSQL = "SELECT playlist_id, user_id, playlist_name, image FROM playlist_info WHERE playlist_name LIKE ?";
	            } else if(searchText == null || searchText.equals("")) {
	            	return playlistList;
	            }
				
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
				
				searchState = connection.prepareStatement(searchSQL);
				
				searchState.setString(1,"%"+searchText+"%");
				resultSet = searchState.executeQuery();
		
				while(resultSet.next()) {
					
					Playlist_info_VO playlist_info = new Playlist_info_VO();
					
					playlist_info.setPlaylist_id(resultSet.getInt("playlist_id"));
					playlist_info.setUser_id(resultSet.getString("user_id"));
					playlist_info.setPlaylist_name(resultSet.getString("playlist_name"));
					
					//�̹��� ��������
					Blob blob = resultSet.getBlob("image");
					byte[] imageData = blob.getBytes(1, (int) blob.length());
					String imageBase64 = java.util.Base64.getEncoder().encodeToString(imageData);
					String playlist_image = "data:image/jpeg;base64, " + imageBase64;
					
					playlist_info.setImage(playlist_image);

					//1. �迭�� ��ü �������� �ϴ� �迭 �����ðԿ�
					//3. �����ӽ��ϴ� ����ٰԿ�
					playlistList.add(playlist_info);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return playlistList;
			
		}
	
		//�����˻� �޼���
		public ArrayList<User_info_VO> getSearchUsers(String searchText){
			
			String searchSQL = null;

			Connection connection = null;
			PreparedStatement searchState = null;
			ResultSet resultSet = null;
			
			//2. �迭 ���鷯�ӽ��ϴ� ����
			ArrayList<User_info_VO> userList = new ArrayList<>();
			
			try {
				
				searchSQL = "SELECT user_id, user_nickname, profile_img FROM user_info WHERE user_id LIKE ? OR user_nickname LIKE ?";
				
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
				
				searchState = connection.prepareStatement(searchSQL);
				
				searchState.setString(1,"%"+searchText+"%");
				searchState.setString(2,"%"+searchText+"%");
				
				resultSet = searchState.executeQuery();
		
				
				while(resultSet.next()) {
					
					User_info_VO user_info = new User_info_VO();
					
					user_info.setUser_id(resultSet.getString("user_id"));
					user_info.setUser_nickname(resultSet.getString("user_nickname"));
					
					Blob blob = resultSet.getBlob("profile_img");
					String profile_image;
					
					if(blob != null) {
						byte[] imageData = blob.getBytes(1, (int) blob.length());
						String imageBase64 = java.util.Base64.getEncoder().encodeToString(imageData);
						profile_image = "data:image/jpeg;base64, " + imageBase64;
					} else {
						profile_image = null;
					}
					
					user_info.setProfile_img(profile_image);

					//1. �迭�� ��ü �������� �ϴ� �迭 �����ðԿ�
					//3. �����ӽ��ϴ� ����ٰԿ�
					userList.add(user_info);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return userList;
			
		}
		
		//music_id�̴� �޼���
		public String getMusic_id(String searchText){
			
			String searchSQL = null;

			Connection connection = null;
			PreparedStatement searchState = null;
			ResultSet resultSet = null;
			
			try {
				
				if(searchText != null && !searchText.equals("") ){
					searchSQL = "SELECT song_id FROM music_info WHERE song_id =?";
	            } else if(searchText == null || searchText.equals("")) {
	            	return null;
	            }
				
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
				
				searchState = connection.prepareStatement(searchSQL);
				
				searchState.setString(1,searchText);
				
				resultSet = searchState.executeQuery();
		
				if(resultSet.next()) {
					
					String song_id = resultSet.getString("song_id");
					
					return song_id;
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
		}
	
	}