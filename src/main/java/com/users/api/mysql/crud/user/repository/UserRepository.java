package com.users.api.mysql.crud.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.api.mysql.crud.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// List<User> fiandAll(final Pageable pagination);

}
