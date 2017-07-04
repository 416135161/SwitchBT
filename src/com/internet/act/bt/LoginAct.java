/**  
 * @author ningsj@shishike.com
 * @Title: LoginAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-20 上午10:06:05 
 * @version V1.0  
 */
package com.internet.act.bt;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.EditText;

import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.LoginPost;
import com.internet.switchbt.R;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-20 上午10:06:05
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EActivity(R.layout.act_login)
public class LoginAct extends BasicActivity {
	@ViewById
	EditText mEdit_mobile, mEdit_pwd;

	private String mMobileNo;

	private String mPwd;

	@AfterViews
	void init() {
		// mEdit_mobile.setText("");
		// mEdit_pwd.setText("");
	}

	@Click(R.id.btn_login)
	void login() {
		mMobileNo = mEdit_mobile.getEditableText().toString();
		if (TextUtils.isEmpty(mMobileNo)) {
			showToast("请填写手机号码！");
			return;
		}

		mPwd = mEdit_pwd.getEditableText().toString();
		if (TextUtils.isEmpty(mPwd)) {
			showToast("请输入密码！");
			return;
		}

		doLogin();
	}

	@Click(R.id.text_regist)
	void regist() {

		finish();
	}

	@Click(R.id.text_cancel)
	void cancel() {
		doBack();
	}

	@Click(R.id.text_find_password)
	void findPassword() {

	}

	@Background
	void doLogin() {
		showLoading();
		boolean state = false;
		try {
			state = ApiManager.getDefault().login(
					new LoginPost(mMobileNo, mPwd));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (state) {
			goToMainAct();
		}

	}

	@UiThread
	void goToMainAct() {
		int roleId = MainApplication.getInstance().getLoginResponseEntity()
				.getRole_id();
		if (roleId == 5) {
			TownsListAct_.intent(this).start();
		} else if (roleId == 2) {
			VillagesListAct_.intent(this).start();
		} else if (roleId == 3) {
			PoorPeopleListAct_.intent(this).start();
		}

		finish();
	}

}
