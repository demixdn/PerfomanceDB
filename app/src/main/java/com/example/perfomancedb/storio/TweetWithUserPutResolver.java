package com.example.perfomancedb.storio;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Date: 29.09.2016
 * Time: 12:21
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public final class TweetWithUserPutResolver extends PutResolver<TweetWithUser> {

    @NonNull
    @Override
    public PutResult performPut(@NonNull StorIOSQLite storIOSQLite, @NonNull TweetWithUser tweetWithUser) {
        // We can even reuse StorIO methods
        final PutResults<Object> putResults = storIOSQLite
                .put()
                .objects(asList(tweetWithUser.tweet(), tweetWithUser.user()))
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(2);

        affectedTables.add(TweetsTable.TABLE);
        affectedTables.add(UsersTable.TABLE);

        // Actually, it's not very clear what PutResult should we return here…
        // Because there is no table for this pair of tweet and user
        // So, let's just return Update Result
        return PutResult.newUpdateResult(putResults.numberOfUpdates(), affectedTables);
    }
}
