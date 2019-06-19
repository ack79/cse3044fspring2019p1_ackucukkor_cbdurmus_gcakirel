package com.canberkdurmus.libra;

import com.canozgen.genericrv.items.GenericRecyclerItem;

public class ItemLibrary implements GenericRecyclerItem {
    public int bookId;
    public String bookName;
    public String bookAuthor;
    public String bookDesc;
    public String bookImg;

    public ItemLibrary(int bookId, String bookName, String bookAuthor, String bookDesc, String bookImg) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDesc = bookDesc;
        this.bookImg = bookImg;
    }
}
