package com.qa.services;

import java.util.ArrayList;
import java.util.List;

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
		
		List<GameDTO> gamesDTO = new ArrayList<GameDTO>();
		for(int i = 0; i < games.size(); i++ ) {
			repo.save(games.get(i));
			gamesDTO.add(mapToDTO(games.get(i)));
		}
		return gamesDTO;
	}
	
	public GameDTO readByID(Long id) {
		Game game = this.repo.findById(id).orElseThrow(EntityNotFoundException::new);
		return mapToDTO(game);
	}
	
	public GameDTO updateGame(Long id, Game updatedGame) {
		Game game = this.repo.findById(id).orElseThrow(EntityNotFoundException::new);
		game.setTitle(updatedGame.getTitle());
		game.setPlatform(updatedGame.getPlatform());
		game.setDeveloperID(updatedGame.getDeveloperID());
		game.setGenre(updatedGame.getGenre());
		
		return mapToDTO(repo.save(game));
	}
	
	public boolean deleteGame(Long id) {
		repo.deleteById(id);
		boolean exists = repo.existsById(id);
		return !exists;
	}
}
