package ru.cs.ifmo.ligos.security.oauth2;

import ru.cs.ifmo.ligos.db.entities.AuthType;
import ru.cs.ifmo.ligos.exception.OAuth2AuthenticationProcessingException;
import ru.cs.ifmo.ligos.security.oauth2.providers.GoogleOAuth2UserInfo;
import ru.cs.ifmo.ligos.security.oauth2.providers.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if(registrationId.equalsIgnoreCase(AuthType.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}