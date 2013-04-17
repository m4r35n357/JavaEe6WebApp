/*jslint white: true, browser: true, safe: true */

"use strict";

window.onload = function () {
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setErrorHandler(errorHandler);
	dwr.engine.setPollStatusHandler(updatePollStatus);
	updatePollStatus(true);
	Tabs.init('tabList', 'tabContents');
	Clock.setClockStatus();
}

function errorHandler (message, ex) {
	dwr.util.setValue("error", "<font color='red'>Cannot connect to server. Initializing retry logic.</font>", { escapeHtml : false });
	setTimeout(function() {
		dwr.util.setValue("error", "");
	}, 5000);
}

function updatePollStatus (pollStatus) {
	dwr.util.setValue("pollStatus", pollStatus ? "<font color='green'>Online</font>" : "<font color='red'>Offline</font>", { escapeHtml : false });
}

function enableUpdates (enabled) {
	if (!enabled) {
		dwr.util.setValue("clockDisplay", "This tab/window does not have updates enabled.");
	}
	Clock.setEnabledAttribute(enabled);
}

function setClockStatus (clockStatus) {
	dwr.util.setValue("clockStatus", clockStatus ? "Clock started" : "Clock stopped");
}
