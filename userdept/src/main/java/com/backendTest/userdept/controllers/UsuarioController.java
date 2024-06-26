package com.backendTest.userdept.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendTest.userdept.entities.Usuario;
import com.backendTest.userdept.exception.ErrorResponse;
import com.backendTest.userdept.repositories.UsuarioRepository;

/**
 * Controller REST para gerenciar operações relacionadas a usuários.
 */
@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	/**
	 * Recupera todos os usuários.
	 *
	 * @return lista de todos os usuários
	 */
	@GetMapping
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	/**
	 * Recupera um usuário pelo seu ID.
	 *
	 * @param id o ID do usuário
	 * @return o usuário encontrado
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		return repository.findById(id)
				.map(usuario -> ResponseEntity.ok(usuario))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}

	/**
	 * Insere um novo usuário.
	 *
	 * @param usuario o usuário a ser inserido
	 * @return o usuário inserido
	 */
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
		Usuario result = repository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	/**
	 * Atualiza um usuário existente.
	 *
	 * @param id      o ID do usuário a ser atualizado
	 * @param usuario os novos dados do usuário
	 * @return o usuário atualizado
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
		return repository.findById(id)
				.map(existingUser -> {
					existingUser.setNome(usuario.getNome());
					existingUser.setDepartamento(usuario.getDepartamento());
					Usuario updatedUser = repository.save(existingUser);
					return ResponseEntity.ok(updatedUser);
				})
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}

	/**
	 * Deleta um usuário pelo seu ID.
	 *
	 * @param id o ID do usuário a ser deletado
	 * @return a resposta HTTP sem conteúdo
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * Trata exceções de NoSuchElementException.
	 *
	 * @param e a exceção lançada
	 * @return a resposta de erro
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
		ErrorResponse response = new ErrorResponse("Usuário não encontrado", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Trata exceções de EmptyResultDataAccessException.
	 *
	 * @param e a exceção lançada
	 * @return a resposta de erro
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		ErrorResponse response = new ErrorResponse("Usuário não encontrado", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Trata exceções genéricas.
	 *
	 * @param e a exceção lançada
	 * @return a resposta de erro
	 */
	public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
		ErrorResponse response = new ErrorResponse("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
