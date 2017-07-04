package com.internet.act.bt;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.internet.basic.BasicActivity;
import com.internet.fragment.SelectPhotoPathDialogFragment;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GerRecordDetailPost;
import com.internet.http.data.response.GerRecordDetailResponse;
import com.internet.http.data.response.GerRecordDetailResponse.Images;
import com.internet.http.data.response.GerRecordDetailResponse.RecordDetail;
import com.internet.switchbt.R;
import com.internet.view.HeaderView;
import com.internet.view.MyGridView;

@EActivity(R.layout.act_record_detail)
public class RecordDeatailAct extends BasicActivity {
	final int DATE_DIALOG = 1;
	@ViewById
	HeaderView view_header;

	@ViewById
	TextView et_title, et_helper, et_address, et_content;
	@ViewById
	TextView tv_date;
	@ViewById
	ImageView image_date, image_add;
	@ViewById
	MyGridView listView;
	GridIconAdapter adapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

	}

	@AfterViews
	void init() {
		view_header.setTitle("帮扶日志");
		adapter = new GridIconAdapter(this);
		listView.setAdapter(adapter);

		doSubmit(getIntent().getStringExtra("data"));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Click(R.id.image_add)
	void clickImageAdd() {
		SelectPhotoPathDialogFragment.builder().build()
				.show(getFragmentManager(), "");
	}

	@SuppressWarnings("deprecation")
	@Click(R.id.image_date)
	void clickDate() {
		showDialog(DATE_DIALOG);
	}

	@Background
	void doSubmit(String id) {
		System.out.println("KKKKKKKKK==" + id);

		showLoading();
		try {
			GerRecordDetailPost data = new GerRecordDetailPost();
			data.id = id;
			GerRecordDetailResponse response = ApiManager.getDefault()
					.getRecordDetail(data);
			refreshView(response);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();

	}

	@UiThread
	void refreshView(GerRecordDetailResponse response) {
		if (response != null && response.getData() != null) {
			RecordDetail recordDetail = response.getData();

			et_title.setText(et_title.getHint() + "  "
					+ recordDetail.getTitle());
			et_helper.setText(et_helper.getHint() + "  "
					+ getIntent().getStringExtra("head_name"));
			et_address.setText(et_address.getHint() + "  "
					+ recordDetail.getHelpAddress());
			et_content.setText(et_content.getHint() + "  "
					+ recordDetail.getContent());
			tv_date.setText(tv_date.getHint() + "  "
					+ recordDetail.getHelpDate());
			if (recordDetail.getImages() != null) {
				for (Images item : recordDetail.getImages()) {
					adapter.addPath(item.getAttachmentUrl());
				}
			}

		}
	}

	public class GridIconAdapter extends BaseAdapter {

		private Context mContext;
		private ArrayList<String> pathList = new ArrayList<String>();

		public GridIconAdapter(Context context) {
			this.mContext = context;

		}

		@Override
		public int getCount() {
			return pathList.size();
		}

		@Override
		public Object getItem(int position) {
			return pathList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_add_photo, null);
				viewHolder = new ViewHolder();
				viewHolder.photoTemp = (ImageView) convertView
						.findViewById(R.id.photo_temp);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			Glide.with(mContext).load(pathList.get(position))
					.into(viewHolder.photoTemp);
			return convertView;
		}

		public void addPath(String path) {
			this.pathList.add(path);
			notifyDataSetChanged();
		}

		public void removeAt(int location) {
			pathList.remove(location);
			notifyDataSetChanged();
		}

		public List<String> getPathList() {
			return pathList;
		}

		private class ViewHolder {

			public ImageView photoTemp;

		}
	}

}