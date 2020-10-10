package com.example.criminalintent.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.criminalintent.model.Crime;

@Database(entities = Crime.class, version = 1)
@TypeConverters({Converters.class})
public abstract class CrimeDataBase extends RoomDatabase {

    public abstract CrimeDAO getCrimeDataBaseDAO();
}
