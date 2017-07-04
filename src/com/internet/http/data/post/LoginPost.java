/**  
 * @author ningsj@shishike.com
 * @Title: LoginPost.java 
 * @Package com.internet.yousheng.http.data.post 
 * @Description: TODO
 * @date 2015-4-16 下午2:31:59 
 * @version V1.0  
 */
package com.internet.http.data.post;

import com.google.gson.annotations.SerializedName;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-16 下午2:31:59
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class LoginPost {
	@SerializedName(value = "username")
	public String username;// 手机号
	@SerializedName(value = "password")
	public String password;// 密码

	public LoginPost(String mobileNo, String password) {
		super();
		this.username = mobileNo;
		this.password = password;
	}


	public String getMobileNo() {
		return username;
	}


	public void setMobileNo(String mobileNo) {
		this.username = mobileNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
