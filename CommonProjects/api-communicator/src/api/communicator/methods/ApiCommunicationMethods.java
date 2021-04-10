package api.communicator.methods;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public class ApiCommunicationMethods extends AbstractApiCommunicationMethods {
	private static final Logger logger = LoggerFactory.getLogger(ApiCommunicationMethods.class);
	
	@Override
	public MainResponseObject postRequest(MainRequestObject requestContent, String url) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		String jsonRequest = null;
		HttpRequest request = null;
		
		try {
			jsonRequest = parseObjectToJson(requestContent);
			logger.info(logger.isInfoEnabled() ? "Going to post with request with url: [" +url+ "] and requestedContent: [" +jsonRequest+ "]": null);
			request = HttpRequest.newBuilder().uri(URI.create("https://httpbin.org/post")).POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();
			HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
			if (null != response) {
				logger.info(logger.isInfoEnabled() ? "Response received as status: [" +response.statusCode()+ "] and responseContent: [" +response.body()+ "]": null);
				return parseJsontoObject(response.body());
			}
			logger.info(logger.isInfoEnabled() ? "Problem seems to be appeared while calling api so returning null ...": null);
			return null;
		} catch (IOException e) {
			logger.warn("##Exception## while posting request to api ...");
			throw new BaseException(e);
			
		} catch (InterruptedException e) {
			logger.warn("##Exception## while posting request to api ...");
			throw new BaseException(e);
		}
	}
	
	public String parseObjectToJson(MainRequestObject request) throws Exception {
		// Creating the ObjectMapper object
		com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		return mapper.writeValueAsString(request);
	}
	
	private static MainResponseObject parseJsontoObject(String response)
    {
        Gson gson = new Gson();
        // Converting json to object
        return gson.fromJson(response, MainResponseObject.class);
    }
}
