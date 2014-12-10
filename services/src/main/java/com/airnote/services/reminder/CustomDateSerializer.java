package com.airnote.services.reminder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer extends JsonDeserializer<Date> {
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String dateAsText = jp.getText();
        try {
            date = sdf.parse(dateAsText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
