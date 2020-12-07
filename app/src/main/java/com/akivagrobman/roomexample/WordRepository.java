package com.akivagrobman.roomexample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private final WordDao wordDao;
    private final LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordRoomDatabase database = WordRoomDatabase.getDatabase(application);
        wordDao = database.wordDao();
        allWords = wordDao.getAlphabetizedWords();
    }

    LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> wordDao.insert(word));
    }

    void deleteWord(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> wordDao.deleteWord(word));
    }

    void deleteAll() {
        WordRoomDatabase.databaseWriteExecutor.execute(wordDao::deleteAll);
    }
}
