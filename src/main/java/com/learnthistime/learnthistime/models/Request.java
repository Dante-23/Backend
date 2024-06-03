package com.learnthistime.learnthistime.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "request_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"from_user", "to_user"})})
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @Nonnull
    @JoinColumn(name = "from_user", referencedColumnName = "userName")
    private User mFromUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @Nonnull
    @JoinColumn(name = "to_user", referencedColumnName = "userName")
    private User mToUser;

    public Request(User fromUser, User toUser) {
        mFromUser = fromUser;
        mToUser = toUser;
    }

    @Nonnull
    public User getmFromUser() {
        return mFromUser;
    }

    public void setmFromUser(@Nonnull User mFromUser) {
        this.mFromUser = mFromUser;
    }

    @Nonnull
    public User getmToUser() {
        return mToUser;
    }

    public void setmToUser(@Nonnull User mToUser) {
        this.mToUser = mToUser;
    }
}
