package com.example.nmaroulis_backend.title;


import com.example.nmaroulis_backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface TitleRepository extends JpaRepository<User, Long> {

}