package com.example.criminalintent.database;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

public class Converters {

    @TypeConverter
    public static Date LongToDate(Long timeStamp){
        return new Date(timeStamp);
    }

    @TypeConverter
    public static long DateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public static UUID stringToUUID(String value){
        return UUID.fromString(value);
    }

    @TypeConverter
    public static String UUIDToString(UUID uuid){
        return uuid.toString();

    }

}
