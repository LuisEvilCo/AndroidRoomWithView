package word.room.luis.roomwordsample.Repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import word.room.luis.roomwordsample.Database.Dao.WordDao;
import word.room.luis.roomwordsample.Database.Entity.WordEntity;
import word.room.luis.roomwordsample.Database.WordRoomDatabase;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<WordEntity>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return mAllWords;
    }

    public void insert(WordEntity word) {
        new insertAsyncTask(mWordDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<WordEntity, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            mAsyncTaskDao.insert(wordEntities[0]);
            return null;
        }
    }
}


