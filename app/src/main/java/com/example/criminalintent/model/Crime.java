package com.example.criminalintent.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "crimeTable")
public class Crime {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long primaryId;

    public void setId(UUID id) {
        mId = id;
    }

    @ColumnInfo(name = "uuid")
    private UUID mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "date")
    private Date mDate;
    @ColumnInfo(name = "solved")
    private boolean mSolved;
    @ColumnInfo(name = "suspect")
    private String mSuspect;
    @ColumnInfo(name = "phone number")
    private String mNumber;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public Crime() {
        this(UUID.randomUUID());
//        mDate = DateUtils.randomDate();
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public Crime(UUID id, String title, Date date, boolean solved, String suspect, String number) {
        mId = id;
        mTitle = title;
        mDate = date;
        mSolved = solved;
        mSuspect = suspect;
        mNumber = number;
    }
}
