//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.internet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.internet.switchbt.R.id;
import com.internet.switchbt.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


/**
 * We use @SuppressWarning here because our java code
 * generator doesn't know that there is no need
 * to import OnXXXListeners from View as we already
 * are in a View.
 * 
 */
@SuppressWarnings("unused")
public final class ReleaseSiteDateItemView_
    extends ReleaseSiteDateItemView
    implements HasViews, OnViewChangedListener
{

    private boolean alreadyInflated_ = false;
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    public ReleaseSiteDateItemView_(Context context) {
        super(context);
        init_();
    }

    public ReleaseSiteDateItemView_(Context context, AttributeSet attrs) {
        super(context, attrs);
        init_();
    }

    public static ReleaseSiteDateItemView build(Context context) {
        ReleaseSiteDateItemView_ instance = new ReleaseSiteDateItemView_(context);
        instance.onFinishInflate();
        return instance;
    }

    /**
     * The mAlreadyInflated_ hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     * 
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated_) {
            alreadyInflated_ = true;
            inflate(getContext(), layout.view_release_site_data_item, this);
            onViewChangedNotifier_.notifyViewChanged(this);
        }
        super.onFinishInflate();
    }

    private void init_() {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    public static ReleaseSiteDateItemView build(Context context, AttributeSet attrs) {
        ReleaseSiteDateItemView_ instance = new ReleaseSiteDateItemView_(context, attrs);
        instance.onFinishInflate();
        return instance;
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        text_week = ((TextView) hasViews.findViewById(id.text_week));
        view_flag = ((View) hasViews.findViewById(id.view_flag));
        image_point = ((ImageView) hasViews.findViewById(id.image_point));
        text_date = ((TextView) hasViews.findViewById(id.text_date));
        init();
    }

}