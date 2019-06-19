package com.canberkdurmus.libra;

import com.canozgen.genericrv.items.GenericRecyclerItem;

public class ItemTimeline implements GenericRecyclerItem {
    public int postId;
    public int userId;
    public String username;
    public String postText;

    public ItemTimeline(int postId, String username, int userId, String postText) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.postText = postText;
    }
}
