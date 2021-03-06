package mine.com.testserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiuyouzone on 2017/5/31.
 */

public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }

    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    public void readFromParcel(Parcel dest){
        bookId = dest.readInt();
        bookName = dest.readString();
    }

//    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
//        @Override public Book createFromParcel(Parcel source) {
//            return new Book(source);
//        }
//
//        @Override public Book[] newArray(int size) {
//            return new Book[size];
//        }
//    };

    private Book(Parcel source) {
        bookId = source.readInt();
        bookName = source.readString();
    }

    public Book(){}


    @Override public String toString() {
        return "ID: " + bookId + ", BookName: " + bookName;
    }
}
