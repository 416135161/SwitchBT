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
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GetTownPost;
import com.internet.http.data.response.GetTownResponse;
import com.internet.http.data.vo.SiteVO;
import com.internet.http.data.vo.TownVO;
import com.internet.switchbt.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_towns_list)
public class TownsListAct extends BasicActivity implements OnScrollListener {

	@ViewById
	HeaderView view_header;

	@ViewById
	ImageView image_search;

	@ViewById
	EditText edit_search;

	@ViewById
	GridView listView;

	String mKeyword = "";

	SelectSiteAdapter adapter;

	SiteVO selectSite;

	int page = 1;
	int pageSize = 20;
	boolean hasMore = false;

	@AfterViews
	void init() {
		view_header.setTitle("精准扶贫");

		adapter = new SelectSiteAdapter(this);

		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);
		getSiteList();
	}

	@Click(R.id.image_search)
	void clickEnsureSearch() {
		String keyword = edit_search.getEditableText().toString();
		if (TextUtils.isEmpty(keyword)) {
			showToast("搜索条件不能为空！");
			return;
		}
		mKeyword = keyword;
		page = 1;
		getSiteList();
	}

	@Background
	void getSiteList() {
		showLoading(null);
		try {
			GetTownPost data = new GetTownPost();
			if (MainApplication.getInstance().getLoginResponseEntity() != null) {
				data.admin_id = MainApplication.getInstance()
						.getLoginResponseEntity().getAdmin_id();
			}
			GetTownResponse response = ApiManager.getDefault()
					.getTownList(data);
			freshList(response);
		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
	}

	@UiThread
	void freshList(GetTownResponse response) {
		if (response != null && response.getData() != null) {
			adapter.addList(response.getData());
		}
	}

	@ItemClick(R.id.listView)
	void onItemClick(int position) {
		VillagesListAct_.IntentBuilder_ build = VillagesListAct_.intent(this);
		build.get().putExtra("data", (TownVO) adapter.getItem(position));
		build.start();
	}

	class SelectSiteAdapter extends BaseAdapter {

		private List<TownVO> list;
		private Context context;

		public SelectSiteAdapter(Context context) {
			super();
			this.list = new ArrayList<TownVO>();
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

		public void addList(List<TownVO> list) {

			this.list.addAll(list);
			notifyDataSetChanged();
		}

		public void addItem(TownVO bean) {

			if (list == null) {
				list = new ArrayList<TownVO>();
			}

			list.add(bean);
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			view = LayoutInflater.from(context).inflate(
					R.layout.list_item_town, null);
			TextView tv_1 = (TextView) view.findViewById(R.id.text1);
			TextView tv_2 = (TextView) view.findViewById(R.id.text2);
			TownVO bean = list.get(position);
			tv_1.setText(bean.getTown_name());
			String str = "<font color='red'>" + bean.getHeads_number()
					+ "</font>" + "<font color='black'>" + "户/" + "</font>"
					+ "<font color= 'red'>" + bean.getFamily_number()
					+ "</font>" + "<font color='black'>" + "人" + "</font>";
			tv_2.setText(Html.fromHtml(str));
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
				getSiteList();
				// showToast("KKKKKKKKKK");
			}

		}
	}

}