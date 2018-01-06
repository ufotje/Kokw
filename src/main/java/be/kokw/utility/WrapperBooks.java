package be.kokw.utility;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Gifted;
import be.kokw.bean.books.GiftedFor;

import java.util.List;

public class WrapperBooks {
    private List<Book> books;
    private List<Gifted> gifteds;
    private List<GiftedFor> giftedFors;

    public WrapperBooks(List<Book> books, List<Gifted> gifteds, List<GiftedFor> giftedFors) {
        this.books = books;
        this.gifteds = gifteds;
        this.giftedFors = giftedFors;
    }
}
