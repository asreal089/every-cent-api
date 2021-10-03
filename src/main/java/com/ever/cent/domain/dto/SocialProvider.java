package com.ever.cent.domain.dto;

public enum SocialProvider {

	LOCAL("LOCAL"), GOOGLE("google");
	 
    private String providerType;
 
    public String getProviderType() {
        return providerType;
    }
 
    SocialProvider(final String providerType) {
        this.providerType = providerType;
    }
}
