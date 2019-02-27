package ru.cs.ifmo.ligos.db.entities;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public", catalog = "ligos")
public class UsersEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Email
	@Column(name = "email", nullable = false, length = 254)
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UsersEntity that = (UsersEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(email, that.email) &&
				Objects.equals(password, that.password) &&
				Objects.equals(name, that.name) &&
				Objects.equals(surname, that.surname) &&
				Objects.equals(patronymic, that.patronymic) &&
				Objects.equals(phoneNumber, that.phoneNumber) &&
				Objects.equals(city, that.city) &&
				Objects.equals(gender, that.gender) &&
				Objects.equals(birthday, that.birthday) &&
				Arrays.equals(photo, that.photo) &&
				Objects.equals(raiting, that.raiting) &&
				Objects.equals(authType, that.authType);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, email, password, name, surname, patronymic, phoneNumber, city, gender, birthday, raiting, authType);
		result = 31 * result + Arrays.hashCode(photo);
		return result;
	}
}
