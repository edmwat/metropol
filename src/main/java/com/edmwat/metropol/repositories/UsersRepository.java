package com.edmwat.metropol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmwat.metropol.models.AppUser;

@Repository
public interface UsersRepository extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser> findByUsername(String username);

}
