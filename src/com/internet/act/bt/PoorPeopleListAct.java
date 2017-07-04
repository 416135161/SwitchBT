package com.internet.act.bt;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GetPoorPeoplePost;
import com.internet.http.data.response.GetPoorPeopleResponse;
import com.internet.http.data.vo.PoorPeopleVO;
import com.internet.http.data.vo.SiteVO;
import com.internet.http.data.vo.VillageVO;
import com.internet.switchbt.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_poor_people_user_list)
public class PoorPeopleListAct extends BasicActivity implements
		OnScrollListener {

	@ViewById
	HeaderView view_header;

	@ViewById
	ImageView image_search;

	@ViewById
	EditText edit_search;

	@ViewById
	ListView listView;

	String mKeyword = "";

	SelectSiteAdapter adapter;

	SiteVO selectSite;

	int page = 1;
	int pageSize = 20;
	boolean hasMore = false;
	VillageVO villageVO;

	@AfterViews
	void init() {
		if (getIntent().getSerializableExtra("data") != null) {
			villageVO = (VillageVO) getIntent().getSerializableExtra("data");
			view_header.setTitle(villageVO.getVillage_name());
		} else {
			view_header.setTitle(MainApplication.getInstance().getLoginResponseEntity().getVillage_name());
		}

		adapter = new SelectSiteAdapter(this);

		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);
		getSiteList(null);
	}

	@Click(R.id.image_search)
	void clickEnsureSearch() {
		String keyword = edit_search.getEditableText().toString();
		if (TextUtils.isEmpty(keyword)) {
			showToast("搜索条件不能为空！");
			return;
		}
		adapter.clearList();
		getSiteList(keyword);
	}

	@Background
	void getSiteList(String keyword) {
		boolean isSearch = !TextUtils.isEmpty(keyword);
		showLoading(null);
		try {
			GetPoorPeoplePost data = new GetPoorPeoplePost();

			if (villageVO != null) {
				data.setVillage_id(villageVO.getVillage_id());
			} else {
				data.setVillage_id(MainApplication.getInstance()
						.getLoginResponseEntity().getVillage_id());
			}

			data.head_name = keyword;

			GetPoorPeopleResponse response = ApiManager.getDefault()
					.getPoorPeopleList(data, isSearch);
			freshList(response);
		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
	}

	@UiThread
	void freshList(GetPoorPeopleResponse response) {
		if (response != null && response.getData() != null) {

			adapter.addList(response.getData());

		}
	}

	class SelectSiteAdapter extends BaseAdapter {

		private List<PoorPeopleVO> list;
		private Context context;

		public SelectSiteAdapter(Context context) {
			super();
			this.list = new ArrayList<PoorPeopleVO>();
			this.context = context;
		}

		@Override
		public int getCount() {
			if (list == null || list.isEmpty()) {
				return 0;
			}
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public void clearList() {
			this.list.clear();
			notifyDataSetChanged();
		}

		public void addList(List<PoorPeopleVO> list) {

			this.list.addAll(list);
			notifyDataSetChanged();
		}

		public void addItem(PoorPeopleVO bean) {

			if (list == null) {
				list = new ArrayList<PoorPeopleVO>();
			}

			list.add(bean);
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {

			if (view == null) {
				view = LayoutInflater.from(context).inflate(
						R.layout.view_poor_people_item, null);
			}

			TextView tv_0 = (TextView) view.findViewById(R.id.text0);
			TextView tv_1 = (TextView) view.findViewById(R.id.text1);
			TextView tv_2 = (TextView) view.findViewById(R.id.text2);

			PoorPeopleVO bean = list.get(position);
			tv_0.setText(bean.getHead_name());
			tv_1.setText(bean.getHead_attribute());
			tv_2.setText(bean.getTown_name() + bean.getVillage_name()
					+ bean.getDictionary_name());

			return view;
		}

	}

	private int visibleLastIndex = 0; // 最后的可视项索引

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
		int lastIndex = itemsLastIndex; // 加上底部的loadMoreView项
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == lastIndex) {
			// 如果是自动加载,可以在这里放置异步加载数据的代码
			if (hasMore) {
				getSiteList(null);
				// showToast("KKKKKKKKKK");
			}

		}
	}

}