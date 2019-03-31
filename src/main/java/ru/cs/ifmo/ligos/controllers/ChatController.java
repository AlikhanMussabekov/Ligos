package ru.cs.ifmo.ligos.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.services.ChatService;
import ru.cs.ifmo.ligos.db.services.UserService;
import ru.cs.ifmo.ligos.dto.ChatChannelInitializationDTO;
import ru.cs.ifmo.ligos.dto.ChatMessageDTO;
import ru.cs.ifmo.ligos.dto.EstablishedChatChannelDTO;
import ru.cs.ifmo.ligos.exception.IsSameUserException;

import java.util.List;

@Controller
public class ChatController {

	private final ChatService chatService;
	private final UserService userService;

	@Autowired
	public ChatController(ChatService chatService, UserService userService) {
		this.chatService = chatService;
		this.userService = userService;
	}

	@MessageMapping("/private.chat.{channelId}")
	@SendTo("/topic/private.chat.{channelId}")
	public ChatMessageDTO chatMessage(@DestinationVariable String channelId, ChatMessageDTO message){
		chatService.submitMessage(message);
		return message;
	}

	@RequestMapping(value="/api/private-chat/channel",
			method=RequestMethod.PUT,
			produces="application/json",
			consumes="application/json")
	public ResponseEntity<?> establishChatChannel(@RequestBody ChatChannelInitializationDTO chatChannelInitialization) throws IsSameUserException {
		String channelUuid = chatService.establishChatSession(chatChannelInitialization);
		UsersEntity userOne = userService.getUser(chatChannelInitialization.getUserIdOne());
		UsersEntity userTwo = userService.getUser(chatChannelInitialization.getUserIdTwo());

		EstablishedChatChannelDTO establishedChatChannel = new EstablishedChatChannelDTO(
				channelUuid,
				userOne.getEmail(),
				userTwo.getEmail()
		);

		return ResponseEntity.ok(establishedChatChannel);
	}

	@RequestMapping(value="/api/private-chat/channel/{channelUuid}",
			method=RequestMethod.GET,
			produces="application/json")
	public ResponseEntity<?> getExistingChatMessages(@PathVariable("channelUuid") String channelUuid) {
		List<ChatMessageDTO> messages = chatService.getExistingChatMessages(channelUuid);

		return ResponseEntity.ok(messages);
	}
}