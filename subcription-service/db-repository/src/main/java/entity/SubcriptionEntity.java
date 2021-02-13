package entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import domain.Gender;

@Entity
@Table(name = "subcription")
public class SubcriptionEntity implements Serializable {

	private static final long serialVersionUID = 3137632651439675234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "first_name")
	private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
	private Gender gender;

	@NotNull
	@Column(name = "birthdate", nullable = false)
	private LocalDate birthdate;

	@NotNull
	@Column(name = "consent", nullable = false)
	private Boolean consent;

	@NotNull
	@Column(name = "newsletter_id", nullable = false)
	private Long newsletterId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Boolean getConsent() {
		return consent;
	}

	public void setConsent(Boolean consent) {
		this.consent = consent;
	}

	public Long getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(Long newsletterId) {
		this.newsletterId = newsletterId;
	}
	
}
