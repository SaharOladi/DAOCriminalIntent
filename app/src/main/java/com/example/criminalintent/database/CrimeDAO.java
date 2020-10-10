package com.example.criminalintent.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDAO {

    @Update
    void updateCrime(Crime crime);


    @Insert
    void insertCrime(Crime crime);

    @Delete
    void deleteCrime(Crime crime);

    @Insert
    void insertCrimes(Crime... crimes);

    @Query("SELECT * FROM crimeTable")
    List<Crime> getCrimes();

    @Query("SELECT * FROM crimeTable WHERE uuid=:inputId")
    Crime getCrime(UUID inputId);

    @Query("SELECT * FROM crimeTable ORDER BY id ASC LIMIT 1")
    Crime getLastCrime();


}
