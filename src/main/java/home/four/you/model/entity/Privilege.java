package home.four.you.model.entity;

import home.four.you.security.auth.authorization.AuthorityPrivilege;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Privilege model.
 */
@Entity
@Table(name = "privileges")
@Getter
@Setter
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@Enumerated(EnumType.STRING)
	private AuthorityPrivilege name;

	@ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
	private Set<Role> roles = new HashSet<>();
}
