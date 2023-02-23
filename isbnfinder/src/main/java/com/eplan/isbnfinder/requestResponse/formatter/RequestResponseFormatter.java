package com.eplan.isbnfinder.requestResponse.formatter;

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
		return csvRequest.split(",");
	}

}
