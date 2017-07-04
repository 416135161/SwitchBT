package com.internet.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.internet.db.DBManager;
import com.internet.db.SpHelper;
import com.internet.http.data.response.LoginResponse.LoginResponseEntity;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.util.JsonUtil;

public class MainApplication extends Application {

	private static MainApplication mInstance = null;
	private UserInfoVO userInfo;
	public double mLatitude;
	public double mLongitude;
	
	private LoginResponseEntity loginResponseEntity;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		DBManager.getDefault().init(this);
		SpHelper.getDefault().init(this);
	}

	public static MainApplication getInstance() {
		return mInstance;
	}

	public UserInfoVO getUserInfo() {
		if (userInfo == null) {
			String json = SpHelper.getDefault().getString(SpHelper.USER_INFO);
			if (json == null)
				return null;
			userInfo = JsonUtil.jsonToObject(json, UserInfoVO.class);
		}
		return userInfo;
	}

	public void setUserInfo(UserInfoVO userInfo) {
		this.userInfo = userInfo;
		SpHelper.getDefault().putString(SpHelper.USER_INFO,
				JsonUtil.objectToJson(userInfo));

	}
	
	

	public LoginResponseEntity getLoginResponseEntity() {
		return loginResponseEntity;
	}

	public void setLoginResponseEntity(LoginResponseEntity loginResponseEntity) {
		this.loginResponseEntity = loginResponseEntity;
	}

	public static boolean isConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conn.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}

}
