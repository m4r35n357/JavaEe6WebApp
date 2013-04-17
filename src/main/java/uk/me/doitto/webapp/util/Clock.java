package uk.me.doitto.webapp.util;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.impl.DaemonThreadFactory;
import org.directwebremoting.ui.dwr.Util;

/**
 * Created by DWR
 * 
 * @author super
 * 
 */
public class Clock implements Runnable {
	/**
	 * Create a schedule to update the clock every second.
	 */
	public Clock() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());
		executor.scheduleAtFixedRate(this, 1, 50, TimeUnit.MILLISECONDS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		if (active) {
			String newTimeString = new Date().toString();
			// We check this has not already been sent to avoid duplicate
			// transmissions
			if (!newTimeString.equals(timeString)) {
				setClockDisplay(newTimeString);
				timeString = newTimeString;
			}
		}
	}

	/**
	 * Called from the client to turn the clock on/off
	 */
	public synchronized void toggle() {
		active = !active;
		setClockStatus();
	}

	private class UpdatesEnabledFilter implements ScriptSessionFilter {
		private String attrName;

		public UpdatesEnabledFilter(String attrName) {
			this.attrName = attrName;
		}

		public boolean match(ScriptSession ss) {
			Object check = ss.getAttribute(attrName);
			return (check != null && check.equals(Boolean.TRUE));
		}
	}

	/**
	 * Call a function on the client for each ScriptSession. passing the clock's
	 * status for display.
	 * 
	 * @param output
	 *            The string to display.
	 */
	public void setClockStatus() {
		Browser.withAllSessions(new Runnable() {
			public void run() {
				ScriptSessions.addFunctionCall("setClockStatus()", active);
//				ScriptSessions.addFunctionCall("updatePollStatus()", active);
			}
		});
	}

	/**
	 * Send the time String to clients that have an UPDATES_ENABLED_ATTR
	 * attribute set to true on their ScriptSession.
	 * 
	 * @param output
	 *            The string to display.
	 */
	public void setClockDisplay(final String output) {
		Browser.withAllSessionsFiltered(new UpdatesEnabledFilter(UPDATES_ENABLED_ATTR), new Runnable() {
			public void run() {
				Util.setValue("clockDisplay", output);
			}
		});
	}

	/**
	 * 
	 * @param enabled
	 */
	public void setEnabledAttribute(Boolean enabled) {
		ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
		scriptSession.setAttribute(UPDATES_ENABLED_ATTR, enabled);
	}

	private static String UPDATES_ENABLED_ATTR = "UPDATES_ENABLED";

	/**
	 * Are we updating the clocks on all the pages?
	 */
	protected transient boolean active = false;

	/**
	 * The last time string
	 */
	protected String timeString = "";

}