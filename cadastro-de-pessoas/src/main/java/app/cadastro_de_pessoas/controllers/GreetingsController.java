package app.cadastro_de_pessoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.cadastro_de_pessoas.model.Usuario;
import app.cadastro_de_pessoas.repository.UsuarioRepository;



/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/save/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {

		Usuario usuario = new Usuario();
		usuario.setNome(name);
		usuario.setIdade(20);

		usuarioRepository.save(usuario);

		return "Hello " + name + "!";
	}

	@GetMapping(value = "listar")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);

	}

	@PostMapping(value = "salvar")
	@ResponseBody

	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "deletar")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam(name = "id") Long id) {

		usuarioRepository.deleteById(id);

		return new ResponseEntity<String>("o usuario de id " + id + " foi exluido", HttpStatus.OK);

	}

	@GetMapping(value = "buscar-usuario")
	@ResponseBody
	public ResponseEntity<Usuario> buscar(@RequestParam(name = "id") Long id) {

		Usuario usuario = usuarioRepository.findById(id).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {

		if (usuario.getId() == null) {

			return new ResponseEntity<String>("Nenhum id informado", HttpStatus.OK);

		} else {

			Usuario user = usuarioRepository.saveAndFlush(usuario);

			return new ResponseEntity<Usuario>(user, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "buscar-por-nome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome) {

		List<Usuario> listUser = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(listUser, HttpStatus.OK);

	}
	
}
