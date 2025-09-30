package com.examenparcial1.universidadapp.utils; // O el paquete que prefieras para los convertidores

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
}
