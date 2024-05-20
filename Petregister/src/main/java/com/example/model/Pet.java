package com.example.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class Pet {
	
	private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private String status;
	
	
}
