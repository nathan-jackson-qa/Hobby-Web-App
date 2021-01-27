package com.qa.persistence.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	private Developer developer;

	@Column(nullable = false)
	private String genre;
	
	@ManyToMany
	@JoinTable(name="Platform_List", joinColumns = @JoinColumn(name="game_ID"), inverseJoinColumns = @JoinColumn(name= "platform_id"))
	@JsonManagedReference
	@Column(nullable = false)
	private List<Platform> platforms;
	
	@Column(nullable = false)
	private String title;
}
