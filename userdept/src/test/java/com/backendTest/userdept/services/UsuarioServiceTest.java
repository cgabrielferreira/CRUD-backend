package com.backendTest.userdept.services;

import static org.mockito.ArgumentMatchers.any;
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

import com.backendTest.userdept.entities.Usuario;
import com.backendTest.userdept.repositories.UsuarioRepository;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeFindAll() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();

        verify(usuarioRepository).findAll();
        assert(result.size() == 2);
    }

    @Test
    public void testeFindById() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        verify(usuarioRepository).findById(1L);
        assert(result == usuario);
    }

    @Test
    public void testeFindById_NotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuario result = usuarioService.findById(1L);

        verify(usuarioRepository).findById(1L);
        assert(result == null);
    }

    @Test
    public void testeSave() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.save(usuario);

        verify(usuarioRepository).save(usuario);
        assert(result == usuario);
    }

    @Test
    public void testeDelete() {
        usuarioService.delete(1L);

        verify(usuarioRepository).deleteById(1L);
    }
}
