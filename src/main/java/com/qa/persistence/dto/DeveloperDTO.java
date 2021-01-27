package com.qa.persistence.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeveloperDTO {
	private Long id;
	private String name;
	private List<GameDTO> games;
}
