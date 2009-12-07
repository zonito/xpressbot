package xpressbot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.google.wave.api.*;

@SuppressWarnings("serial")
public class XpressBotServlet extends AbstractRobotServlet {

	XpressList xl = new XpressList();

	@Override
	public void processEvents(RobotMessageBundle bundle) {
		Wavelet wavelet = bundle.getWavelet();
		if (bundle.wasSelfAdded()) {
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			textView
					.appendMarkup("Hello, I am your XpressBot, Now talk with friends using funny Expression." +
							"For More Information: http://www.voizle.com/xpressbot.vz . " +
							" To Suggest more Xpressions: http://u.voizle.com/ajsktbn");
		}

		for (Event e : bundle.getEvents()) {
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				addExpression(e);
			}
		}
	}

	private void addExpression(Event e) {
		TextView cont = e.getBlip().getDocument();
		String blipText = cont.getText();
		Hashtable<Integer, String> XpressTable = new Hashtable<Integer, String>();
		for (String img : xl.Xpressions) {
			int i = 0;
			while (blipText.indexOf("[" + img + "]", i) != -1) {
				i = blipText.indexOf("[" + img + "]", i);
				XpressTable.put(i, img);
				i++;
			}
		}

		Enumeration<Integer> Xkeys = XpressTable.keys();
		List<Integer> list = new ArrayList<Integer>();
		while (Xkeys.hasMoreElements()) {
			list.add(Xkeys.nextElement());
		}
		Collections.sort(list);
		Collections.reverse(list);

		for (Integer i : list) {
			cont.delete(new Range(i, (2 + i + XpressTable.get(i).length())));

			Element img = new Image();
			img.setProperty("url", "http://xpressbot.appspot.com/expression/"
					+ XpressTable.get(i) + ".gif");
			cont.insertElement(i, img);
		}
	}
}