package com.canberkdurmus.libra;

import com.canozgen.genericrv.items.GenericRecyclerItem;

public class ItemSearch implements GenericRecyclerItem {
    public int userId;
    public String username;
    public String bio;
    public boolean friend;

    public ItemSearch(String username, int userId, String bio, boolean friend) {
        this.userId = userId;
        this.username = username;
        this.bio = bio;
        this.friend = friend;
    }
}
