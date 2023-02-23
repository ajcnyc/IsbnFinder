package com.eplan.isbnfinder.requestResponse.formatter;

import com.eplan.isbnfinder.validate.Isbn;
import com.google.gson.Gson;

/**
 * Formats requests from the frontend to the backend and responses from the
 * backend to the frontend.
 * 
 * @author Alex Cohen
 *
 */
public class RequestResponseFormatter {
 
	/**
	 * Formats a CSV request (Comma-Separated ISBNs) String from the frontend as a
	 * String array
	 * 
	 * @param csvRequest The CSV request to format (Comma-Separated ISBNs)
	 * @return A String array of ISBNs
	 */
	public String[] formatRequest(String csvRequest) {
		return csvRequest.split(",", -1); // -1 to make slots for trailing empty Strings
	}

	/**
	 * Formats an array of ISBN objects as a JSON String response from the backend
	 * 
	 * @param isbns The array of ISBN objects
	 * @return A JSON String
	 */
	public String formatResponse(Isbn[] isbns) {
		Gson gson = new Gson();
		return gson.toJson(isbns, Isbn[].class);
	}
}
