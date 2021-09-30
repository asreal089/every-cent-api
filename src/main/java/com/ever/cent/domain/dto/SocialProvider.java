package com.ever.cent.domain.dto;

public enum SocialProvider {

	GOOGLE("google"), LOCAL("LOCAL");
	 
    private String providerType;
 
    public String getProviderType() {
        return providerType;
    }
 
    SocialProvider(final String providerType) {
        this.providerType = providerType;
    }
}
