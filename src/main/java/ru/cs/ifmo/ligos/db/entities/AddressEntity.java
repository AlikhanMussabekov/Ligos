package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "address", schema = "public", catalog = "ligos")
public class AddressEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "country", nullable = false, length = 255)
	private String country;

	@Column(name = "state", nullable = false, length = 255)
	private String state;

	@Column(name = "zip_code", nullable = false, length = 255)
	private String zipCode;

	@Column(name = "city", nullable = false, length = 255)
	private String city;

	@Column(name = "street", nullable = false, length = 255)
	private String street;
}
