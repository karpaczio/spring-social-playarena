package pl.playarena.api.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.web.client.DefaultResponseErrorHandler;
import pl.playarena.api.impl.exception.InvalidRecipientException;
import pl.playarena.api.impl.exception.MessageException;

public class PlayarenaErrorHandler extends DefaultResponseErrorHandler {
    
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String errorDetails = extractErrorDetailsFromResponse(response);
        if (errorDetails == null) {
            handleUncategorizedError(response, errorDetails);
        }

        handlePlayarenaError(response.getStatusCode(), errorDetails);

        handleUncategorizedError(response, errorDetails);
    }

    void handlePlayarenaError(HttpStatus statusCode, String errorDetails) {
        String message = errorDetails;

        if (statusCode == HttpStatus.SERVICE_UNAVAILABLE){
            //OAuth
            if (message.contains("[OAuth client error: invalid_request] The request is missing a required parameter, includes an unsupported parameter or parameter value, or is otherwise malformed.")) {
                throw new InvalidAuthorizationException(message);
            }
            if (message.contains("[OAuth client error: unauthorized_client] The client is not authorised to request an authorization code using this method.")) {
                throw new NotAuthorizedException(message);
            }
            if (message.contains("[OAuth client error: invalid_request] Invalid authorization code")) {
                throw new InvalidAuthorizationException(message);
            }
            if (message.contains("Invalid access_token")) {
                throw new InvalidAuthorizationException(message);
            }
            //OAuth user
            if(message.contains("No user defined")){
                throw new InvalidAuthorizationException(message);
            }
            if(message.contains("User do not exist")){
                throw new InvalidAuthorizationException(message);
            }
            //messages
            if(message.contains("Title is too long")){
                throw new MessageException(message);
            }
            if(message.contains("Content is too long")){
                throw new MessageException(message);
            }
            if(message.contains("Recipient is not friend")){
                throw new InvalidRecipientException(message);
            }
            if(message.contains("User is not a member of selected team")){
                throw new InvalidRecipientException(message);
            }
            if(message.contains("Team not found")){
                throw new InvalidRecipientException(message);
            }
            if(message.contains("User is not a member of any team")){
                throw new InvalidRecipientException(message);
            }
            if(message.contains("Team not selected")){
                throw new InvalidRecipientException(message);
            }
        }
    }

    private void handleUncategorizedError(ClientHttpResponse response, String errorDetails) {
        try {
            super.handleError(response);
        } catch (Exception e) {
            if (errorDetails != null) {
                throw new UncategorizedApiException(errorDetails, e);
            } else {
                throw new UncategorizedApiException("No error details from Playarena", e);
            }
        }
    }

    /*
     * Attempts to extract Playarena error details from the response.
     * Returns null if the response doesn't match the expected JSON error response.
     */
    @SuppressWarnings("unchecked")
    private String extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        String json = readFully(response.getBody());

        try {
            Map<String, Object> responseMap = mapper.<Map<String, Object>>readValue(json, new TypeReference<Map<String, Object>>() {
            });
            if (responseMap.containsKey("error") && ((Integer)responseMap.get("error") > 0) && responseMap.containsKey("error_message")) {
                return (String) responseMap.get("error_message");
            }
        } catch (JsonParseException e) {
            return null;
        }
        return null;
    }

    private String readFully(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        return sb.toString();
    }
}
