package shinee.vo;

public class Follow_list_VO {
	private String follower_id;
	private String following_id;
	private String user_nickname;
	private String profile_img;
	

	public Follow_list_VO(String follower_id, String following_id, String user_nickname, String profile_img) {
		this.follower_id = follower_id;
		this.following_id = following_id;
		this.profile_img = profile_img;
		this.user_nickname = user_nickname;
		
	}

	
	
	
	
	public String getUser_nickname() {
		return user_nickname;
	}


	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}





	public String getProfile_img() {
		return profile_img;
	}


	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}


	public Follow_list_VO(String follower_id,String following_id) {
		this.follower_id = follower_id;
		this.following_id = following_id;
	}


	

	public String getFollower_id() { 
		return follower_id;
	}


	public void setFollower_id(String follower_id) {
		this.follower_id = follower_id;
	}


	public String getFollowing_id() {
		return following_id;
	}


	public void setFollowing_id(String following_id) {
		this.following_id = following_id;
	}
	
	
}