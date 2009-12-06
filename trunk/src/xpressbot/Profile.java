package xpressbot;

import com.google.wave.api.ProfileServlet;

public class Profile extends ProfileServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public String getRobotName() {
		return "XpressBot";
	}

	@Override
	public String getRobotAvatarUrl() {
		return "http://xpressbot.appspot.com/images/laughing.png";
	}

	@Override
	public String getRobotProfilePageUrl() {
		return "http://www.voizle.com/xpressbot.vz";
	}

}
