package com.example.nmaroulis_backend.models;


import org.springframework.data.jpa.repository.JpaRepository;

interface RoleRepository extends JpaRepository<User, Long> {

}