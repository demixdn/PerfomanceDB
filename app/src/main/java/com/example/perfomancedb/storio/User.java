package com.example.perfomancedb.storio;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Date: 29.09.2016
 * Time: 11:47
 *
 * @author Aleks Sander
 * Project PerfomanceDB
 *
 */
@StorIOSQLiteType(table = UsersTable.TABLE)
public class User {
    /**
     * If object was not inserted into db, id will be null
     */
    @Nullable
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_ID, key = true)
    Long id;

    // For example: "artem_zin", without "@".
    @NonNull
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_NICK)
    String nick;

    // leave default constructor for AutoGenerated code!
    User() {

    }

    @NonNull
    public static User newUser(@Nullable Long id, @NonNull String nick) {
        User user = new User();
        user.id = id;
        user.nick = nick;
        return user;
    }

    @Nullable
    public Long id() {
        return id;
    }

    @NonNull
    public String nick() {
        return nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return nick.equals(user.nick);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + nick.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                '}';
    }
}