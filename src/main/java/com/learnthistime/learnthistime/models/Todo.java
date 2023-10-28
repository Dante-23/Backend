package com.learnthistime.learnthistime.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "todo_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"})})
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Nonnull
	@Column(length = 50, unique = true)
	private String name;

	@Nonnull
	@Column(length = 30)
	private String dueDate;
	
	public Todo(String name, String dueDate) {
		this.name = name;
		this.dueDate = dueDate;
	}

	public Todo() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
}
