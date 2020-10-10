package com.example.criminalintent.repository;

import android.content.ContentValues;
import android.content.Context;

import androidx.room.Room;

import com.example.criminalintent.database.CrimeDAO;
import com.example.criminalintent.database.CrimeDataBase;
import com.example.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

import static com.example.criminalintent.database.CrimeDBSchema.CrimeTable.Cols;

public class CrimeDBRepository implements IRepository {

    private static CrimeDBRepository sInstance;

//    private SQLiteDatabase mDatabase;
    private CrimeDAO mCrimeDAO;
    private Context mContext;

    public static CrimeDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new CrimeDBRepository(context);

        return sInstance;
    }

    private CrimeDBRepository(Context context) {
        mContext = context.getApplicationContext();

        CrimeDataBase crimeDataBase = Room.databaseBuilder(mContext,
                CrimeDataBase.class,
                "crime.db")
                .allowMainThreadQueries()
                .build();
        // dao is interface, so you should create database first and then get database.
        mCrimeDAO = crimeDataBase.getCrimeDataBaseDAO();

//        CrimeDBHelper crimeDBHelper = new CrimeDBHelper(mContext);
//
//        //all 4 checks happens on getDataBase
//        mDatabase = crimeDBHelper.getWritableDatabase();

    }

    @Override
    public List<Crime> getCrimes() {
         return mCrimeDAO.getCrimes();
//        List<Crime> crimes = new ArrayList<>();
//
//        CrimeCursorWrapper crimeCursorWrapper = queryCrimeCursor(null, null);
//
//        if (crimeCursorWrapper == null || crimeCursorWrapper.getCount() == 0)
//            return crimes;
//
//        try {
//            crimeCursorWrapper.moveToFirst();
//
//            while (!crimeCursorWrapper.isAfterLast()) {
//                crimes.add(crimeCursorWrapper.getCrime());
//                crimeCursorWrapper.moveToNext();
//            }
//        } finally {
//            crimeCursorWrapper.close();
//        }
//
//        return crimes;
    }

    @Override
    public Crime getCrime(UUID crimeId) {

        return mCrimeDAO.getCrime(crimeId);
//        String where = Cols.UUID + " = ?";
//        String[] whereArgs = new String[]{crimeId.toString()};
//
//        CrimeCursorWrapper crimeCursorWrapper = queryCrimeCursor(where, whereArgs);
//
//        if (crimeCursorWrapper == null || crimeCursorWrapper.getCount() == 0)
//            return null;
//
//        try {
//            crimeCursorWrapper.moveToFirst();
//            return crimeCursorWrapper.getCrime();
//        } finally {
//            crimeCursorWrapper.close();
//        }
    }

//    private CrimeCursorWrapper queryCrimeCursor(String where, String[] whereArgs) {
//        Cursor cursor = mDatabase.query(
//                CrimeDBSchema.CrimeTable.NAME,
//                null,
//                where,
//                whereArgs,
//                null,
//                null,
//                null);
//
//        CrimeCursorWrapper crimeCursorWrapper = new CrimeCursorWrapper(cursor);
//        return crimeCursorWrapper;
//    }

    @Override
    public void insertCrime(Crime crime) {
        mCrimeDAO.insertCrime(crime);
//        ContentValues values = getContentValues(crime);
//        mDatabase.insert(CrimeDBSchema.CrimeTable.NAME, null, values);
    }

    @Override
    public void updateCrime(Crime crime) {
        mCrimeDAO.updateCrime(crime);
//        ContentValues values = getContentValues(crime);
//        String whereClause = Cols.UUID + " = ?";
//        String[] whereArgs = new String[]{crime.getId().toString()};
//        mDatabase.update(CrimeDBSchema.CrimeTable.NAME, values, whereClause, whereArgs);
    }

    @Override
    public void deleteCrime(Crime crime) {
        mCrimeDAO.deleteCrime(crime);
    }
//        String whereClause = Cols.UUID + " = ?";
//        String[] whereArgs = new String[]{crime.getId().toString()};
//        mDatabase.delete(CrimeDBSchema.CrimeTable.NAME, whereClause, whereArgs);
//    }}

    @Override
    public int getPosition(UUID crimeId) {
        List<Crime> crimes = getCrimes();
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getId().equals(crimeId))
                return i;
        }
        return -1;
    }

    private ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, crime.getId().toString());
        values.put(Cols.TITLE, crime.getTitle());
        values.put(Cols.DATE, crime.getDate().getTime());
        values.put(Cols.SUSPECT, crime.getSuspect());
        values.put(Cols.SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }
}
