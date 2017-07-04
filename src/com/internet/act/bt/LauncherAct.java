package com.internet.act.bt;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import com.internet.basic.BasicActivity;
import com.internet.switchbt.R;

@EActivity(R.layout.act_launcher)
public class LauncherAct extends BasicActivity {

	@AfterViews
	void init() {
		startWelcomeAct();
	}

	@UiThread(delay = 800)
	void startWelcomeAct() {
		LoginAct_.intent(this).start();
		finish();
	}
}
