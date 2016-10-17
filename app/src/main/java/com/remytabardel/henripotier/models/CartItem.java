package com.remytabardel.henripotier.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.remytabardel.henripotier.services.database.Database;

import java.util.Calendar;

/**
 * @author Remy Tabardel
 */

@Table(database = Database.class)
public class CartItem extends BaseModel {
    @PrimaryKey
    String isbn;
    @Column
    int quantity;
    @Column
    long addedTime;
    @ColumnIgnore
    Book book;

    public CartItem() {
    }

    public CartItem(String isbn, int quantity) {
        this.isbn = isbn;
        this.quantity = quantity;
        this.addedTime = Calendar.getInstance().getTimeInMillis();
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int nb) {
        quantity += nb;
    }

    public long getAddedTime() {
        return addedTime;
    }

    public Book getBook() {
        if (book == null) {
            book = SQLite.select()
                    .from(Book.class)
                    .where(Book_Table.isbn.eq(isbn))
                    .querySingle();
        }
        return book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return isbn.equals(cartItem.isbn);
    }
}