package com.ever.cent.config.security.oauth2.user;

import java.util.Map;

import com.ever.cent.domain.dto.SocialProvider;
import com.ever.cent.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(SocialProvider.GOOGLE.getProviderType())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
