package com.qa.rest;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.qa.persistence.domain.Developer;
import com.qa.persistence.dto.DeveloperDTO;
import com.qa.services.DeveloperService;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
	
	public DeveloperService service;
	
	@Autowired
	public DeveloperController(DeveloperService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<DeveloperDTO> createDev(@RequestBody Developer dev) {
		return new ResponseEntity<DeveloperDTO>(service.createDev(dev), HttpStatus.CREATED);
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<DeveloperDTO> readID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<DeveloperDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@PutMapping("/update")
	public ResponseEntity<DeveloperDTO> updateDeveloper(@PathParam("id") Long id, @RequestBody Developer dev) {
		return new ResponseEntity<>(service.updateDev(id, dev), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DeveloperDTO> deleteDev(@PathVariable("id") Long id) {
		return service.removeDev(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
