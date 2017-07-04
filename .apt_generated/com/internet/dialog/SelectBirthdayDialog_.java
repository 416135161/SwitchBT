//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.internet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import com.internet.switchbt.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SelectBirthdayDialog_
    extends SelectBirthdayDialog
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.dialog_select_birthday, container, false);
        }
        return contentView_;
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static SelectBirthdayDialog_.FragmentBuilder_ builder() {
        return new SelectBirthdayDialog_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        datePicker = ((DatePicker) hasViews.findViewById(com.internet.switchbt.R.id.datePicker));
        {
            View view = hasViews.findViewById(com.internet.switchbt.R.id.text_qd);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectBirthdayDialog_.this.onImage1Click();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.switchbt.R.id.view);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectBirthdayDialog_.this.clickView();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.switchbt.R.id.text_qx);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectBirthdayDialog_.this.onImage2Click();
                    }

                }
                );
            }
        }
        initView();
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public SelectBirthdayDialog build() {
            SelectBirthdayDialog_ fragment_ = new SelectBirthdayDialog_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}
