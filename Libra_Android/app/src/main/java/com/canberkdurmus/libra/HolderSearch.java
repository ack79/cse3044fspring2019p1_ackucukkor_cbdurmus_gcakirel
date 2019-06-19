package com.canberkdurmus.libra;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.canozgen.genericrv.viewholders.GenericViewHolder;
import com.canozgen.genericrv.viewholders.ViewHolderClickEventListener;

public class HolderSearch extends GenericViewHolder<ItemSearch> {
    // Define your views
    private TextView username;
    private TextView bio;
    private Button addButton;

    // You don't need to worry about constructor.
    public HolderSearch(@NonNull View itemView, ViewHolderClickEventListener clickEventListener) {
        super(itemView, clickEventListener);
    }

    // Initialize your views.
    @Override
    public void init() {
        bio = (TextView) init(R.id.searchBio);
        username = (TextView) init(R.id.searchUsername);
        addButton = (Button) init(R.id.addButton);
    }

    // Assign click listeners.
    @Override
    public void assign() {
        assignClickEvent(addButton);
    }

    // Bind your item to the view.
    @Override
    public void bind(ItemSearch item, int position) {
        String uname = "" + item.username;
        username.setText(uname);
        bio.setText("Bio: " + item.bio);
        if (item.friend) {
            addButton.setText("Friend");
        } else {
            addButton.setText("Add");
        }
        //postId.setText(id);
    }
}
