package ru.cs.ifmo.ligos.db.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "chat_channel", schema = "public", catalog = "ligos")
@NoArgsConstructor
@AllArgsConstructor
public class ChatChannel {

	@Id
	@NotNull
	private String uuid;

	@OneToOne
	@JoinColumn(name = "userIdOne")
	private UsersEntity userOne;

	@OneToOne
	@JoinColumn(name = "userIdTwo")
	private UsersEntity userTwo;

	public ChatChannel(UsersEntity userOne, UsersEntity userTwo) {
		this.uuid = UUID.randomUUID().toString();
		this.userOne = userOne;
		this.userTwo = userTwo;
	}

}
