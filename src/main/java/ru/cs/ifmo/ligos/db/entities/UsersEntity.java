package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users", schema = "public", catalog = "ligos")
public class UsersEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "user_id_seq", sequenceName = "users_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@Email
	@Column(name = "email", nullable = false, length = 254, unique = true)
	private String email;

	@JsonIgnore
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

	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@Column(name = "raiting", nullable = true)
	private Short raiting;

	@JsonIgnore
	@Column(name = "auth_type", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private AuthType authType;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "chat_users",
			joinColumns = @JoinColumn(name = "usersid", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "chatid", referencedColumnName = "id"))
	private Set<UsersEntity> chats;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private TrainerEntity trainer;

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<AttendanceEntity> attendance = new HashSet<>();

}
