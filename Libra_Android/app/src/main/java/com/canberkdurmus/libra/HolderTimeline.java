package com.canberkdurmus.libra;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.canozgen.genericrv.viewholders.GenericViewHolder;
import com.canozgen.genericrv.viewholders.ViewHolderClickEventListener;

public class HolderTimeline extends GenericViewHolder<ItemTimeline> {
    // Define your views
    private TextView postText;
    //private TextView postId;
    private TextView username;

    // You don't need to worry about constructor.
    public HolderTimeline(@NonNull View itemView, ViewHolderClickEventListener clickEventListener) {
        super(itemView, clickEventListener);
    }

    // Initialize your views.
    @Override
    public void init() {
        postText = (TextView) init(R.id.postText);
        // postId = (TextView) init(R.id.postId);
        username = (TextView) init(R.id.username);
    }

    // Assign click listeners.
    @Override
    public void assign() {
        assignClickEvent(postText);
    }

    // Bind your item to the view.
    @Override
    public void bind(ItemTimeline item, int position) {
        String id = "" + item.postId;
        String uid = "" + item.username;
        postText.setText(item.postText);
        //postId.setText(id);
        username.setText(uid);
    }
}
