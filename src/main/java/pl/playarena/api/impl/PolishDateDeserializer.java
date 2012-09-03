package pl.playarena.api.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class PolishDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = jp.getText();
        try {
            return format.parse(date);
        } catch (ParseException pe) {
            throw new IOException(pe);
        }
    }

}
