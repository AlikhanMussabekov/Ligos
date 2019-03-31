package ru.cs.ifmo.ligos.db.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.Objects;

@Component
public class UserPresenceService implements ChannelInterceptor {

	@Autowired
	@Lazy
	private UserService userService;

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor stompDetails = StompHeaderAccessor.wrap(message);

		if(stompDetails.getCommand() == null) { return; }

		switch(stompDetails.getCommand()) {
			case CONNECT:
			case CONNECTED:
				toggleUserPresence(Objects.requireNonNull(stompDetails.getUser()).getName().toString(), true);
				break;
			case DISCONNECT:
				toggleUserPresence(Objects.requireNonNull(stompDetails.getUser()).getName().toString(), false);
				break;
			default:
				break;
		}
	}

	private void toggleUserPresence(String userEmail, Boolean isPresent) {
		try {
			UsersEntity user = userService.getUser(userEmail);
			userService.setIsPresent(user, isPresent);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}