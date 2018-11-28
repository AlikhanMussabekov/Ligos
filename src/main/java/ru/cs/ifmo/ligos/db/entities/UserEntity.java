package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "USER", schema = "public", catalog = "ligos")
public class UserEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private Integer id;

	@Basic
	@Column(name = "email", nullable = false, length = 254)
	private String email;

	@Basic
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Basic
	@Column(name = "surname", nullable = true, length = 255)
	private String surname;

	@Basic
	@Column(name = "patronymic", nullable = true, length = 255)
	private String patronymic;

	@Basic
	@Column(name = "phone_number", nullable = true, length = 11)
	private String phoneNumber;

	@Basic
	@Column(name = "city", nullable = true, length = 255)
	private String city;

	@Basic
	@Column(name = "gender", nullable = true, length = 7)
	private String gender;

	@Basic
	@Column(name = "birthday", nullable = true)
	private Timestamp birthday;

	@Basic
	@Column(name = "photo", nullable = true, length = -1)
	private String photo;

	@Basic
	@Column(name = "raiting", nullable = true)
	private Short raiting;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<ChatEntity> chatsById;

	@OneToMany(mappedBy = "userByUserid2")
	private Collection<ChatEntity> chatsById_0;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<GoalsListEntity> goalsListsById;

	@OneToOne(mappedBy = "userByUserid")
	private SectionReviewsEntity sectionReviewsById;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<SectionUsersEntity> sectionUsersById;

	@OneToMany(mappedBy = "userByCaptainid")
	private Collection<TeamEntity> teamsById;

	@OneToMany(mappedBy = "userByUserid")
	private Collection<TeamUserEntity> teamUsersById;

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}


	public Collection<ChatEntity> getChatsByIdFirst() {
		return chatsById;
	}

	public void setChatsById(Collection<ChatEntity> chatsById) {
		this.chatsById = chatsById;
	}

	public Collection<ChatEntity> getChatsByIdSecond() {
		return chatsById_0;
	}

	public void setChatsById_0(Collection<ChatEntity> chatsById_0) {
		this.chatsById_0 = chatsById_0;
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

	public Collection<TeamUserEntity> getTeamUsersById() {
		return teamUsersById;
	}

	public void setTeamUsersById(Collection<TeamUserEntity> teamUsersById) {
		this.teamUsersById = teamUsersById;
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
