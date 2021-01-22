package com.qa.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDTO {

	private Long id;
	private int developerID;
	private String genre;
	private String platform;
	private String title;
}
