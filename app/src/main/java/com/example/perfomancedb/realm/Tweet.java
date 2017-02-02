package com.example.perfomancedb.realm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Date: 29.09.2016
 * Time: 17:59
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class Tweet extends RealmObject {
    @Nullable
    @PrimaryKey
    Long id;

    @NonNull
    String author;

    @NonNull
    String content;

    public Tweet() {
    }

    public Tweet(@Nullable Long id, @NonNull String author, @NonNull String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    @NonNull
    public static Tweet newTweet(@Nullable Long id, @NonNull String author, @NonNull String content) {
        return new Tweet(id, author, content);
    }

    @NonNull
    public static Tweet newTweet(@NonNull String author, @NonNull String content) {
        return new Tweet(null, author, content);
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @Nullable
    public Long id() {
        return id;
    }

    @NonNull
    public String author() {
        return author;
    }

    @NonNull
    public String content() {
        return content;
    }

    // BTW, you can use AutoValue/AutoParcel to get immutability and code generation for free
    // Check our tests, we have examples!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (id != null ? !id.equals(tweet.id) : tweet.id != null) return false;
        if (!author.equals(tweet.author)) return false;
        return content.equals(tweet.content);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + author.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
