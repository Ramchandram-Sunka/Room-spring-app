package com.roommanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roommanagement.demo.repository.model.User;

public interface RoomUserRepository extends JpaRepository<User, String> {

}
