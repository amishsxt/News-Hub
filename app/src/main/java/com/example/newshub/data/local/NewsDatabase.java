package com.example.newshub.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.newshub.data.local.dao.NewsDao;
import com.example.newshub.data.local.entities.NewsArticle;
import com.example.newshub.data.local.utils.DateConverter;

@Database(entities = {NewsArticle.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class NewsDatabase extends RoomDatabase {

    private static final String db_name = "NewsDataBase";
    private static NewsDatabase instance;

    public static synchronized NewsDatabase getDB(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context, NewsDatabase.class, db_name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract NewsDao newsDao();
}
