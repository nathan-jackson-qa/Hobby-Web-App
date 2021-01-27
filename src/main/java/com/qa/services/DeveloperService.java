package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.Developer;
import com.qa.persistence.dto.DeveloperDTO;
import com.qa.persistence.repos.DeveloperRepo;

@Service
public class DeveloperService {

	private DeveloperRepo repo;
	private ModelMapper mapper;
	
	public DeveloperService(DeveloperRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private DeveloperDTO mapToDTO(Developer dev) {
		return this.mapper.map(dev, DeveloperDTO.class);
	}
	
	public DeveloperDTO createDev(Developer dev) {
		return mapToDTO(this.repo.save(dev));
	}
	
	public DeveloperDTO readById(Long id) {
		Developer dev = this.repo.findById(id).orElseThrow(EntityNotFoundException::new);
		return mapToDTO(dev);
	}
	
	public List<DeveloperDTO> readAll() {
		List<Developer> listOfDevelopers = repo.findAll();
		
		List<DeveloperDTO> listOfDevelopersDTO = listOfDevelopers.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		return listOfDevelopersDTO;
	}
	
	public DeveloperDTO updateDev(Long id, Developer updatedDev) {
		Developer dev = this.repo.findById(id).orElseThrow(EntityNotFoundException::new);
		dev.setName(updatedDev.getName());
		return mapToDTO(repo.save(dev));
	}
	
	public boolean removeDev(Long id) {
		repo.deleteById(id);
		boolean exists = repo.existsById(id);
		return !exists;
	}
}
