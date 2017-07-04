//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.internet.act.bt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.internet.http.api.ApiException;
import com.internet.http.data.response.GerRecordDetailResponse;
import com.internet.switchbt.R.layout;
import com.internet.view.HeaderView;
import com.internet.view.MyGridView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class RecordDeatailAct_
    extends RecordDeatailAct
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.act_record_detail);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static RecordDeatailAct_.IntentBuilder_ intent(Context context) {
        return new RecordDeatailAct_.IntentBuilder_(context);
    }

    public static RecordDeatailAct_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new RecordDeatailAct_.IntentBuilder_(fragment);
    }

    public static RecordDeatailAct_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new RecordDeatailAct_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        image_date = ((ImageView) hasViews.findViewById(com.internet.switchbt.R.id.image_date));
        et_helper = ((TextView) hasViews.findViewById(com.internet.switchbt.R.id.et_helper));
        et_address = ((TextView) hasViews.findViewById(com.internet.switchbt.R.id.et_address));
        view_header = ((HeaderView) hasViews.findViewById(com.internet.switchbt.R.id.view_header));
        et_title = ((TextView) hasViews.findViewById(com.internet.switchbt.R.id.et_title));
        image_add = ((ImageView) hasViews.findViewById(com.internet.switchbt.R.id.image_add));
        tv_date = ((TextView) hasViews.findViewById(com.internet.switchbt.R.id.tv_date));
        listView = ((MyGridView) hasViews.findViewById(com.internet.switchbt.R.id.listView));
        et_content = ((TextView) hasViews.findViewById(com.internet.switchbt.R.id.et_content));
        {
            View view = hasViews.findViewById(com.internet.switchbt.R.id.image_add);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        RecordDeatailAct_.this.clickImageAdd();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.switchbt.R.id.image_date);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        RecordDeatailAct_.this.clickDate();
                    }

                }
                );
            }
        }
        init();
    }

    @Override
    public void onApiException(final ApiException ex) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.onApiException(ex);
            }

        }
        );
    }

    @Override
    public void showLoading() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.showLoading();
            }

        }
        );
    }

    @Override
    public void showLoading(final String tip) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.showLoading(tip);
            }

        }
        );
    }

    @Override
    public void closeInputKeyboard() {
        handler_.postDelayed(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.closeInputKeyboard();
            }

        }
        , 200L);
    }

    @Override
    public void showToast(final String content) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.showToast(content);
            }

        }
        );
    }

    @Override
    public void fullScreen(final boolean enable) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.fullScreen(enable);
            }

        }
        );
    }

    @Override
    public void closeLoading() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.closeLoading();
            }

        }
        );
    }

    @Override
    public void refreshView(final GerRecordDetailResponse response) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                RecordDeatailAct_.super.refreshView(response);
            }

        }
        );
    }

    @Override
    public void doBack() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    RecordDeatailAct_.super.doBack();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void doSubmit(final String id) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    RecordDeatailAct_.super.doSubmit(id);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, RecordDeatailAct_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, RecordDeatailAct_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, RecordDeatailAct_.class);
        }

        public Intent get() {
            return intent_;
        }

        public RecordDeatailAct_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent_, requestCode);
                } else {
                    if (context_ instanceof Activity) {
                        ((Activity) context_).startActivityForResult(intent_, requestCode);
                    } else {
                        context_.startActivity(intent_);
                    }
                }
            }
        }

    }

}
