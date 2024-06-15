package com.learnthistime.learnthistime.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})})
public class User implements UserDetails {
	@Nonnull
	@Column(length = 50)
	private String name;

	@Id
	@Nonnull
	@Column(length = 10, unique = true)
	private String userName;

	@Nonnull
	private String password;

	@OneToMany(mappedBy = "mUser")
	private List<Todo> mTodos = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "friends_info",
			joinColumns = {
				@JoinColumn(name = "user_1", referencedColumnName = "userName")
			},
			inverseJoinColumns = {
				@JoinColumn(name = "user_2", referencedColumnName = "userName")
			}
	)
	private Set<User> mFriends;

    @ManyToMany(mappedBy = "mToUser")
    private List<Request> mReceivedRequests;

    @ManyToMany(mappedBy = "mFromUser")
    private List<Request> mSentRequests;

//	public User(String name, String userName, String password) {
//		this.name = name;
//		this.userName = userName;
//		this.password = password;
//	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void addFriend(final User user) {
		mFriends.add(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
