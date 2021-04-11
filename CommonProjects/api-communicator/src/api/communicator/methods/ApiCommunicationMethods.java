package api.communicator.methods;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import api.communicator.utils.Constants;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public class ApiCommunicationMethods extends AbstractApiCommunicationMethods {
	private static final Logger logger = LoggerFactory.getLogger(ApiCommunicationMethods.class);
	
	@Override
	public MainResponseObject postRequest(MainRequestObject requestContent, String uri) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		String jsonRequest = null;
		HttpRequest request = null;
		String token = null;
		try {
			token = getToken(Constants.BASE_URI + Constants.AUTH_FOR_TOKEN_CALL, Constants.USERNAME, Constants.PASSWORD);
			jsonRequest = parseObjectToJson(requestContent);
			logger.info(logger.isInfoEnabled() ? "Going to post with request with url: [" +uri+ "] and requestedContent: [" +jsonRequest+ "]": null);
			request = HttpRequest.newBuilder().uri(URI.create(uri)).POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();
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
	
	private String getToken(String uri, String username, String password) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).setHeader(Constants.CONTENT_TYPE, "application/json").POST(HttpRequest.BodyPublishers.ofString(getCredentialsInJson(username, password))).build();
		HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
		return new JSONObject(response.body()).getString("token");
	}
	
	private String getCredentialsInJson(String username, String password) {
		logger.info(logger.isInfoEnabled() ? "Going to get credentials to fetching token for api authentication ...": null);
		HashMap<String, String> newMap = new HashMap<>();
		newMap.put("username", username);
		newMap.put("password", password);
		return new JSONObject(newMap).toString();
	}
	
	
}
