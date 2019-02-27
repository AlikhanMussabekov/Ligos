package ru.cs.ifmo.ligos.db.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "users", schema = "public", catalog = "ligos")
public class UsersEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Email
	@Column(name = "email", nullable = false, length = 254, unique = true)
	private String email;

	@Column(name = "password", nullable = true, length = 255)
	private String password;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "surname", nullable = true, length = 255)
	private String surname;

	@Column(name = "patronymic", nullable = true, length = 255)
	private String patronymic;

	@NumberFormat(pattern = "# (###) ###-##-##")
	@Column(name = "phone_number", nullable = true, length = 11)
	private String phoneNumber;

	@Column(name = "city", nullable = true, length = 255)
	private String city;

	@Column(name = "gender", nullable = true, length = 7)
	private String gender;

	@Column(name = "birthday", nullable = true)
	private Date birthday;

	@Lob
	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@Column(name = "raiting", nullable = true)
	private Short raiting;

	@Column(name = "auth_type", nullable = false, length = 6)
	private String authType;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "chat_users",
			joinColumns = @JoinColumn(name = "usersid", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "chatid", referencedColumnName = "id"))
	private Set<UsersEntity> chats;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userid")
	private TrainerEntity trainer;
}
