package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public", catalog = "ligos")
public class UserEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "email", unique = true,nullable = false, length = 254)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "surname", nullable = true, length = 255)
	private String surname;

	@Column(name = "patronymic", nullable = true, length = 255)
	private String patronymic;

	@Column(name = "phone_number", nullable = true, length = 11)
	private String phoneNumber;

	@Column(name = "city", nullable = true, length = 255)
	private String city;

	@Column(name = "gender", nullable = true, length = 7)
	private String gender;

	@Column(name = "birthday", nullable = true)
	private Timestamp birthday;

	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@Column(name = "raiting", nullable = true)
	private Short raiting;

	/*@OneToMany(mappedBy = "userid")
	private Collection<ChatEntity> chatsById;
*/
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "chat_users",
			joinColumns = { @JoinColumn(name = "usersid")},
			inverseJoinColumns = { @JoinColumn(name = "chatid")})
	private Set<ChatEntity> chats;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<GoalsListEntity> goalsListsById;

	@OneToOne(mappedBy = "userByUserid")
	private SectionReviewsEntity sectionReviewsById;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<SectionUsersEntity> sectionUsersById;

	@OneToMany(mappedBy = "userByCaptainid")
	private Collection<TeamEntity> teamsById;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "team_user",
			joinColumns = { @JoinColumn(name = "usersid")},
			inverseJoinColumns = { @JoinColumn(name = "teamid")})
	private Set<ChatEntity> teams;

	//DELETE
	/*@OneToMany(mappedBy = "userByUserid")
	private Collection<TeamUserEntity> teamUsersById;*/

	@OneToOne(mappedBy = "userByUserid")
	private TrainerEntity trainerById;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<TrainerReviewsEntity> trainerReviewsById;

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

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
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

	public Set<ChatEntity> getChats() {
		return chats;
	}

	public void setChats(Set<ChatEntity> chats) {
		this.chats = chats;
	}

	public Collection<GoalsListEntity> getGoalsListsById() {
		return goalsListsById;
	}

	public void setGoalsListsById(Collection<GoalsListEntity> goalsListsById) {
		this.goalsListsById = goalsListsById;
	}

	public SectionReviewsEntity getSectionReviewsById() {
		return sectionReviewsById;
	}

	public void setSectionReviewsById(SectionReviewsEntity sectionReviewsById) {
		this.sectionReviewsById = sectionReviewsById;
	}

	public Collection<SectionUsersEntity> getSectionUsersById() {
		return sectionUsersById;
	}

	public void setSectionUsersById(Collection<SectionUsersEntity> sectionUsersById) {
		this.sectionUsersById = sectionUsersById;
	}

	public Collection<TeamEntity> getTeamsById() {
		return teamsById;
	}

	public void setTeamsById(Collection<TeamEntity> teamsById) {
		this.teamsById = teamsById;
	}

	public Set<ChatEntity> getTeams() {
		return teams;
	}

	public void setTeams(Set<ChatEntity> teams) {
		this.teams = teams;
	}

	public TrainerEntity getTrainerById() {
		return trainerById;
	}

	public void setTrainerById(TrainerEntity trainerById) {
		this.trainerById = trainerById;
	}

	public Collection<TrainerReviewsEntity> getTrainerReviewsById() {
		return trainerReviewsById;
	}

	public void setTrainerReviewsById(Collection<TrainerReviewsEntity> trainerReviewsById) {
		this.trainerReviewsById = trainerReviewsById;
	}
}
