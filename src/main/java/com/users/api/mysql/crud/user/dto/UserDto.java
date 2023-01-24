package com.users.api.mysql.crud.user.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.users.api.mysql.crud.user.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 6612846463297860255L;

	private long id;

	@Size(min = 2, max = 255)
	@NotBlank(message = "firstName is mandatory")
	private String firstName;

	@Size(min = 2, max = 255)
	@NotBlank(message = "lastName is mandatory")
	private String lastName;

	@JsonProperty("email")
	@NotBlank(message = "email is mandatory")
	@Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}$", message = "email must be a valid email")
	private String userName;

	@NotBlank(message = "password is mandatory")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "email must be a valid password")
	private String password;

	public static UserDto from(final User source) {

		if (source == null) {
			return null;
		}

		final var instance = new UserDto();
		BeanUtils.copyProperties(source, instance);
		return instance;
	}

}