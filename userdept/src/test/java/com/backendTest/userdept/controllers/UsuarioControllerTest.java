package com.backendTest.userdept.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.backendTest.userdept.entities.Usuario;
import com.backendTest.userdept.repositories.UsuarioRepository;

public class UsuarioControllerTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeFindAll() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> result = controller.findAll();

        verify(repository).findAll();
        assert(result.size() == 2);
    }

    @Test
    public void testeeFindById() {
        Usuario usuario = new Usuario();
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = controller.findById(1L);

        verify(repository).findById(1L);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() == usuario);
    }

    @Test
    public void testetFindById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = controller.findById(1L);

        verify(repository).findById(1L);
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void testeInsert() {
        Usuario usuario = new Usuario();
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = controller.insert(usuario);

        verify(repository).save(usuario);
        assert(response.getStatusCode() == HttpStatus.CREATED);
        assert(response.getBody() == usuario);
    }

    @Test
    public void testeUpdate() {
        Usuario existingUser = new Usuario();
        Usuario updatedUser = new Usuario();
        when(repository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(repository.save(existingUser)).thenReturn(updatedUser);

        ResponseEntity<Usuario> response = controller.update(1L, updatedUser);

        verify(repository).findById(1L);
        verify(repository).save(existingUser);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() == updatedUser);
    }

    @Test
    public void testeUpdate_NotFound() {
        Usuario usuario = new Usuario();
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = controller.update(1L, usuario);

        verify(repository).findById(1L);
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void testeDelete() {
        doNothing().when(repository).deleteById(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        verify(repository).deleteById(1L);
        assert(response.getStatusCode() == HttpStatus.NO_CONTENT);
    }

    @Test
    public void testeDelete_NotFound() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        verify(repository).deleteById(1L);
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }
}
