package shinee.vo;

//플레이리스트에 존재하는 노래들에 관한 정보
public class Playlist_song_VO {
	public int playlistId;
	public int songId;
	private String title;
	private String artist;
	
	public Playlist_song_VO(int playlistId, String title, String artist) {
		this.playlistId=playlistId;
		this.title = title;
		this.artist = artist;
	}
	
	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	
	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}