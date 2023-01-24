package com.users.api.mysql.crud.user.model;

import org.springframework.beans.BeanUtils;

import com.users.api.mysql.crud.user.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	public static User from(final UserDto source) {

		if (source == null) {
			return null;
		}

		final var instance = new User();
		BeanUtils.copyProperties(source, instance);
		return instance;
	}

}
