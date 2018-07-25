package org.asite.automation.common.utils;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.*;


// TODO: Auto-generated Javadoc
/**
 * The Class JsonUtils.
 * @author jasminprajapati
 */
public class JsonUtils {
	
	/** The obj. */
	JSONObject obj = new JSONObject();

	/**
	 * Gets the HTTP response JSON object.
	 *
	 * @param response the response
	 * @return the HTTP response JSON object
	 * @throws JSONException the JSON exception
	 * @throws ParseException the parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public JSONObject getHTTPResponseJSONObject(HttpResponse response) throws JSONException, ParseException, IOException {
		return new JSONObject(EntityUtils.toString(response
				.getEntity()));
	}
}
