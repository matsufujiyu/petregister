package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Pet;
import com.example.model.Tag;
import com.example.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	private PetService petService;

	//登録されているpetリストを表示
	@GetMapping
	public List<Pet> getAllpet() {
		return petService.getAllpet();

	}

	//登録されているpetリストを１件表示
	@GetMapping("/{id}")
	public Pet getPet(@PathVariable("id") long id) {
		return petService.getPet(id);

	}

	//petリストの登録
	@PostMapping
	public void add(@RequestBody Pet pet) {
		petService.addPet(pet);
	}

	//リストの更新
	@PutMapping
	public void updatePet(@RequestBody Pet pet,
			@PathVariable("id") long id) {
		petService.updatePet(id, pet);
	}

	//リスト削除
	@DeleteMapping("/{id}")
	public void deletePet(@PathVariable("id") long id) {
		petService.deletePet(id);
	}

	//statusで絞り込み
	@GetMapping("/findByStatus")
	public List<Pet> findPetByStatus(@RequestParam String status) {
		final String[] statues = status.split(",");
		final List<Pet> result = new ArrayList<>();
		for (final Pet pet : petService.pets) {
			for (final String s : statues) {
				if (s.equals(pet.getStatus())) {
					result.add(pet);
				}
			}
		}
		return result;
	}

	//tagで絞込
	@GetMapping("/findByTags")
	public List<Pet> findPetByTags(@RequestParam String Tags,@RequestParam String name, String[] tags) {

		final List<Pet> result = new ArrayList<>();
		for (final Pet pet : petService.pets) {
			if (null != pet.getTags()) {
				for (final Tag tag : pet.getTags()) {
					for (final String tagListString : tags) {
						if (tagListString.equals(tag.getName())) {
							result.add(pet);
						}
					}
				}
			}
		}
		return result;
	}
}
