package ru.cs.ifmo.ligos.db.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import ru.cs.ifmo.ligos.db.entities.ChatChannel;
import ru.cs.ifmo.ligos.db.entities.ChatMessage;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.ChatChannelRepository;
import ru.cs.ifmo.ligos.db.repositories.ChatMessageRepository;
import ru.cs.ifmo.ligos.dto.ChatChannelInitializationDTO;
import ru.cs.ifmo.ligos.dto.ChatMessageDTO;
import ru.cs.ifmo.ligos.dto.NotificationDTO;
import ru.cs.ifmo.ligos.exception.IsSameUserException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

	private final ChatChannelRepository chatChannelRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;

	private final int MAX_PAGABLE_CHAT_MESSAGES = 100;

	@Autowired
	public ChatService(
			ChatChannelRepository chatChannelRepository,
			ChatMessageRepository chatMessageRepository,
			UserService userService,
			ModelMapper modelMapper) {
		this.chatChannelRepository = chatChannelRepository;
		this.chatMessageRepository = chatMessageRepository;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	private String getExistingChannel(ChatChannelInitializationDTO chatChannelInitializationDTO) {
		List<ChatChannel> channel = chatChannelRepository
				.findExistingChannel(
						chatChannelInitializationDTO.getUserIdOne(),
						chatChannelInitializationDTO.getUserIdTwo()
				);

		return (channel != null && !channel.isEmpty()) ? channel.get(0).getUuid() : null;
	}

	private String newChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO)
			throws BeansException{
		ChatChannel channel = new ChatChannel(
				userService.getUser(chatChannelInitializationDTO.getUserIdOne()),
				userService.getUser(chatChannelInitializationDTO.getUserIdTwo())
		);

		chatChannelRepository.save(channel);

		return channel.getUuid();
	}

	public String establishChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO)
			throws IsSameUserException, BeansException{
		if (chatChannelInitializationDTO.getUserIdOne() == chatChannelInitializationDTO.getUserIdTwo()) {
			throw new IsSameUserException();
		}

		String uuid = getExistingChannel(chatChannelInitializationDTO);

		return (uuid != null) ? uuid : newChatSession(chatChannelInitializationDTO);
	}

	public void submitMessage(ChatMessageDTO chatMessageDTO)
			throws BeansException{
		ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);

		chatMessageRepository.save(chatMessage);

		UsersEntity fromUser = userService.getUser(chatMessage.getAuthorUser().getId());
		UsersEntity recipientUser = userService.getUser(chatMessage.getRecipientUser().getId());

		userService.notifyUser(recipientUser,
				new NotificationDTO(
						"ChatMessageNotification",
						fromUser.getEmail() + " has sent you a message",
						chatMessage.getAuthorUser().getId()
				)
		);
	}

	public List<ChatMessageDTO> getExistingChatMessages(String channelUuid) {
		ChatChannel channel = chatChannelRepository.getChannelDetails(channelUuid);

		List<ChatMessage> chatMessages =
				chatMessageRepository.getExistingChatMessages(
						channel.getUserOne().getId(),
						channel.getUserTwo().getId(),
						PageRequest.of(0, MAX_PAGABLE_CHAT_MESSAGES)
				);

		// TODO: fix this
		List<ChatMessage> messagesByLatest = Lists.reverse(chatMessages);

		//return ChatMessageMapper.mapMessagesToChatDTOs(messagesByLatest);
		List<ChatMessageDTO> chatMessageDTOS = new ArrayList<>();
		messagesByLatest.forEach(m -> chatMessageDTOS.add(modelMapper.map(m, ChatMessageDTO.class)));
		return chatMessageDTOS;
	}
}