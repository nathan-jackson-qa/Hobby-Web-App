package com.qa.rest;

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
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.Game;
import com.qa.persistence.dto.GameDTO;
import com.qa.services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

	public GameService service;
	
	@Autowired
	public GameController(GameService service) {
		this.service = service;
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<GameDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<GameDTO> readByID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readByID(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<GameDTO> createGame(@RequestBody Game game) {
		return new ResponseEntity<GameDTO>(this.service.createGame(game), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<GameDTO> updateGame(@PathVariable("id") Long id, @RequestBody Game game) {
		return new ResponseEntity<>(this.service.updateGame(id, game), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GameDTO> deleteGame(@PathVariable("id") Long id) {
		return service.deleteGame(id) ?
				new ResponseEntity<GameDTO>(HttpStatus.NO_CONTENT) :
					new ResponseEntity<GameDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
