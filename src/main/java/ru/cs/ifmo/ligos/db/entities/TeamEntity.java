package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "team", schema = "public", catalog = "ligos")
public class TeamEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "trainerid", nullable = true)
	private TrainerEntity trainerid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "captainid", nullable = false)
	private UsersEntity captainid;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Lob
	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@ManyToMany(mappedBy = "teams")
	private Set<TournamentDetailsEntity> tournaments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TrainerEntity getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(TrainerEntity trainerid) {
		this.trainerid = trainerid;
	}

	public UsersEntity getCaptainid() {
		return captainid;
	}

	public void setCaptainid(UsersEntity captainid) {
		this.captainid = captainid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TeamEntity that = (TeamEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(trainerid, that.trainerid) &&
				Objects.equals(captainid, that.captainid) &&
				Objects.equals(name, that.name) &&
				Arrays.equals(photo, that.photo);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, trainerid, captainid, name);
		result = 31 * result + Arrays.hashCode(photo);
		return result;
	}
}
