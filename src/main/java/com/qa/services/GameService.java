package com.qa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.Game;
import com.qa.persistence.dto.GameDTO;
import com.qa.persistence.repos.GameRepo;


@Service
public class GameService {
	
	private GameRepo repo;
	private ModelMapper mapper;
	
	public GameService(GameRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private GameDTO mapToDTO(Game game) {
		return this.mapper.map(game, GameDTO.class);
	}

	public GameDTO createGame(Game game) {
		return mapToDTO(this.repo.save(game));
	}
	
	public List<GameDTO> readAll() {
		List<Game> games = repo.findAll();
		
		List<GameDTO> gamesDTO = games.stream().map(this::mapToDTO).collect(Collectors.toList());
		return gamesDTO;
	}
	
	public GameDTO readByID(Long id) {
		Game game = this.repo.findById(id).orElseThrow(EntityNotFoundException::new);
		return mapToDTO(game);
	}
	
	public GameDTO updateGame(Long id, Game updatedGame) {
		Optional<Game> existingOptional = this.repo.findById(id);
        Game game = existingOptional.get();
		game.setTitle(updatedGame.getTitle());
		game.setDeveloper(updatedGame.getDeveloper());
		game.setPlatforms(updatedGame.getPlatforms());
		game.setGenre(updatedGame.getGenre());
		
		return mapToDTO(repo.save(game));
	}
	
	public boolean deleteGame(Long id) {
		repo.deleteById(id);
		boolean exists = repo.existsById(id);
		return !exists;
	}
}
