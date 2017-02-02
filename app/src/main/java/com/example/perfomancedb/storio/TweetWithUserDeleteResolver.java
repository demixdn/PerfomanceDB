package com.example.perfomancedb.storio;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Date: 29.09.2016
 * Time: 12:25
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public final class TweetWithUserDeleteResolver extends DeleteResolver<TweetWithUser> {
    @NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull TweetWithUser tweetWithUser) {
        // We can even reuse StorIO methods
        storIOSQLite
                .delete()
                .objects(asList(tweetWithUser.tweet(), tweetWithUser.user()))
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(2);

        affectedTables.add(TweetsTable.TABLE);
        affectedTables.add(UsersTable.TABLE);

        return DeleteResult.newInstance(2, affectedTables);
    }
}
