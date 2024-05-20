package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.model.Pet;
import com.example.model.Tag;

@Service
public class PetService {
	public static List<Pet> pets = new ArrayList<>();
	private static List<Category> categories = new ArrayList<>();

	static {
		categories.add(createCategory(1, "Dogs"));
		categories.add(createCategory(2, "Cats"));
		categories.add(createCategory(3, "Rabbits"));
		categories.add(createCategory(4, "Lions"));

		pets.add(createPet(1, categories.get(1), "Cat 1", new String[] {
				"url1", "url2" }, new String[] { "tag1", "tag2" }, "available"));
		pets.add(createPet(2, categories.get(1), "Cat 2", new String[] {
				"url1", "url2" }, new String[] { "tag2", "tag3" }, "available"));
		pets.add(createPet(3, categories.get(1), "Cat 3", new String[] {
				"url1", "url2" }, new String[] { "tag3", "tag4" }, "pending"));

		pets.add(createPet(4, categories.get(0), "Dog 1", new String[] {
				"url1", "url2" }, new String[] { "tag1", "tag2" }, "available"));
		pets.add(createPet(5, categories.get(0), "Dog 2", new String[] {
				"url1", "url2" }, new String[] { "tag2", "tag3" }, "sold"));
		pets.add(createPet(6, categories.get(0), "Dog 3", new String[] {
				"url1", "url2" }, new String[] { "tag3", "tag4" }, "pending"));

		pets.add(createPet(7, categories.get(3), "Lion 1", new String[] {
				"url1", "url2" }, new String[] { "tag1", "tag2" }, "available"));
		pets.add(createPet(8, categories.get(3), "Lion 2", new String[] {
				"url1", "url2" }, new String[] { "tag2", "tag3" }, "available"));
		pets.add(createPet(9, categories.get(3), "Lion 3", new String[] {
				"url1", "url2" }, new String[] { "tag3", "tag4" }, "available"));

		pets.add(createPet(10, categories.get(2), "Rabbit 1", new String[] {
				"url1", "url2" }, new String[] { "tag3", "tag4" }, "available"));
	}

	//登録されているpetリストを表示
	public List<Pet> getAllpet() {
		return pets;
	}

	//1件だけ表示
	public Pet getPet(long id) {
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getId().equals(id)) {
				return (Pet) pets.get(i);
			}
		}
		return null;
	}

	//リストの登録
	public void add(Pet pet) {
		pets.add(pet);
	}

	//リストの更新
	public void updatePet(long id , Pet pet) {
		for ( int i=0;i<pets.size();i++) {
			if(pets.get(i).getId().equals(id)) {
				pets.set(i, pet);
			}
		}
	}

	//リストの削除
	public void deletePet(long id) {
		pets.removeIf(i -> i.getId().equals(id));
	}
	
	public Pet findByStatus(String status) {
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getStatus().equals(status)) {
				return (Pet) pets.get(i);
			}
		}
		return null;
	}



	//stautsで絞り込み
	public List<Pet> findPetByStatus(final String status) {
		final String[] statues = status.split(",");
		final List<Pet> result = new ArrayList<>();
		for (final Pet pet : pets) {
			for (final String s : statues) {
				if (s.equals(pet.getStatus())) {
					result.add(pet);
				}
			}
		}
		return result;
	}

	//tagで絞り込み
	public List<Pet> findPetByTags(final List<String> tags) {
		final List<Pet> result = new ArrayList<>();
		for (final Pet pet : pets) {
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


	public void addPet(final Pet pet) {
		if (pets.size() > 0) {
			for (int i = pets.size() - 1; i >= 0; i--) {
				if (pets.get(i).getId() == pet.getId()) {
					pets.remove(i);
				}
			}
		}
		pets.add(pet);
	}


	public static Pet createPet(final Long id, final Category cat, final String name,
			final List<String> urls, final List<Tag> tags, final String status) {
		final Pet pet = new Pet();
		pet.setId(id);
		pet.setCategory(cat);
		pet.setName(name);
		pet.setPhotoUrls(urls);
		pet.setTags(tags);
		pet.setStatus(status);
		return pet;
	}

	private static Pet createPet(final long id, final Category cat, final String name, final String[] urls,
			final String[] tags, final String status) {
		final Pet pet = new Pet();
		pet.setId(id);
		pet.setCategory(cat);
		pet.setName(name);
		if (null != urls) {
			final List<String> urlObjs = new ArrayList<>(Arrays.asList(urls));
			pet.setPhotoUrls(urlObjs);
		}
		final List<Tag> tagObjs = new ArrayList<>();
		int i = 0;
		if (null != tags) {
			for (final String tagString : tags) {
				i = i + 1;
				final Tag tag = new Tag();
				tag.setId(i);
				tag.setName(tagString);
				tagObjs.add(tag);
			}
		}
		pet.setTags(tagObjs);
		pet.setStatus(status);
		return pet;
	}

	private static Category createCategory(final long id, final String name) {
		final Category category = new Category();
		category.setId(id);
		category.setName(name);
		return category;
	}

}
