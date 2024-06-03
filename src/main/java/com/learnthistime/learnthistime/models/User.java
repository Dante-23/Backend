package com.learnthistime.learnthistime.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})})
public class User {
	@Nonnull
	@Column(length = 50)
	private String name;
	@Id
	@Nonnull
	@Column(length = 10, unique = true)
	private String userName;

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

	public User() {}
	public User(String name, String userName) {
		this.name = name;
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void addFriend(final User user) {
		mFriends.add(user);
	}
}
