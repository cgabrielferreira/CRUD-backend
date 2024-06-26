package com.backendTest.userdept.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendTest.userdept.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
