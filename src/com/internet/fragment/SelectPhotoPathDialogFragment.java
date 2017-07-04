package com.internet.fragment;

import com.internet.switchbt.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 *
 * Created by ningshijie on 2016/4/20.
 */
public class SelectPhotoPathDialogFragment extends DialogFragment implements View.OnClickListener {


    private TextView mTextCamera, mTextAlbum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = super.onCreateView(inflater, container, savedInstanceState);
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_select_photo_path_dlg, container, false);
        }
        mContentView.setOnClickListener(this);
        mTextCamera = (TextView) mContentView.findViewById(R.id.text_camera);
        mTextCamera.setOnClickListener(this);
        mTextAlbum = (TextView) mContentView.findViewById(R.id.text_album);
        mTextAlbum.setOnClickListener(this);

        return mContentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text_camera:
                if (getActivity() instanceof OnSelectPhotoPathClickListener) {
                    ((OnSelectPhotoPathClickListener) getActivity()).selectCamera();
                }
                break;
            case R.id.text_album:
                if (getActivity() instanceof OnSelectPhotoPathClickListener) {
                    ((OnSelectPhotoPathClickListener) getActivity()).selectAlbum();
                }
                break;

        }
        this.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }

    public static SelectPhotoPathDialogFragment.FragmentBuilder builder() {
        return new SelectPhotoPathDialogFragment.FragmentBuilder();
    }

    public static class FragmentBuilder {

        private Bundle args;

        private FragmentBuilder() {
            args = new Bundle();
        }

        public SelectPhotoPathDialogFragment build() {
            SelectPhotoPathDialogFragment fragment = new SelectPhotoPathDialogFragment();
            fragment.setArguments(args);
            return fragment;
        }

    }

    public interface OnSelectPhotoPathClickListener {

        void selectAlbum();

        void selectCamera();

    }
}