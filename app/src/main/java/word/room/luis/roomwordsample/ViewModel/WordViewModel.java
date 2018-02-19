package word.room.luis.roomwordsample.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import word.room.luis.roomwordsample.Database.Entity.WordEntity;
import word.room.luis.roomwordsample.Repository.WordRepository;


public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<WordEntity>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return mAllWords;
    }

    public void insert(WordEntity word) {
        mRepository.insert(word);
    }
}
