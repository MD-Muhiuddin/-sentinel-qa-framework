package com.ui.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// @JsonIgnoreProperties(ignoreUnknown = true) ensures that if you add 
// a new key to JSON (e.g., "dbPort") but forget to add it here, 
// the code won't crash.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Environment {
    
    private String url;
    private String username;
    private String password;
    private int timeout;
    @JsonProperty("MAX_NUMBER_OF_ATTEMPTS")
    private int MAX_NUMBER_OF_ATTEMPTS;

    // Getters and Setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public int getTimeout() { return timeout; }
    public void setTimeout(int timeout) { this.timeout = timeout; }
    
	public int getMAX_NUMBER_OF_ATTEMPTS() {
		return MAX_NUMBER_OF_ATTEMPTS;
	}
	public void setMAX_NUMBER_OF_ATTEMPTS(int mAX_NUMBER_OF_ATTEMPTS) {
		MAX_NUMBER_OF_ATTEMPTS = mAX_NUMBER_OF_ATTEMPTS;
	}
}