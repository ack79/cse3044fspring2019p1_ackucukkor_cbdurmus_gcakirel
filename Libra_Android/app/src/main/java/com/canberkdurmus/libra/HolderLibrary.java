package com.canberkdurmus.libra;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.canozgen.genericrv.viewholders.GenericViewHolder;
import com.canozgen.genericrv.viewholders.ViewHolderClickEventListener;

public class HolderLibrary extends GenericViewHolder<ItemLibrary> {
    // Define your views
    private TextView vBookName;
    private TextView vBookAuthor;
    private TextView vBookDesc;
    private Button removeButton;

    // You don't need to worry about constructor.
    public HolderLibrary(@NonNull View itemView, ViewHolderClickEventListener clickEventListener) {
        super(itemView, clickEventListener);
    }

    // Initialize your views.
    @Override
    public void init() {
        vBookName = (TextView) init(R.id.libraryBookName);
        vBookAuthor = (TextView) init(R.id.libraryBookAuthor);
        vBookDesc = (TextView) init(R.id.libraryBookDesc);
        removeButton = (Button) init(R.id.removeBookButton);
    }

    // Assign click listeners.
    @Override
    public void assign() {
        assignClickEvent(removeButton);
    }

    // Bind your item to the view.
    @Override
    public void bind(ItemLibrary item, int position) {
        String bookName = "" + item.bookName;
        vBookName.setText(bookName);
        vBookAuthor.setText("Author: " + item.bookAuthor);
        vBookDesc.setText("Description: " + item.bookDesc);
    }
}
