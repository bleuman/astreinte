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
	private static SimpleDateFormat formatterMin = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat formatterSec = new SimpleDateFormat(
			"HH:mm:ss");

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		try {
			String text = jsonparser.getText();
			if (text.length() == 5)
				return formatterMin.parse(text);
			if (text.length() == 8)
				return formatterSec.parse(text);
			return new Date();
		} catch (ParseException e) {
			return new Date();
		}
	}

}
