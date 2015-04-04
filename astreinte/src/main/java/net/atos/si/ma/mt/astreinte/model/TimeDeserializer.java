package net.atos.si.ma.mt.astreinte.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class TimeDeserializer extends JsonDeserializer<Date> {
	private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:00");

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		try {
			return formatter.parse(jsonparser.getText());
		} catch (ParseException e) {
			return new Date();
		}
	}

}
