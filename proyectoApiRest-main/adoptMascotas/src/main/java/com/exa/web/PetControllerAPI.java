package com.exa.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exa.entities.Pet;
import com.exa.repository.PetRepository;


@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class PetControllerAPI {

	@Autowired  
	private PetRepository petRep;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable("id") Long id) {
		Optional<Pet> pet = petRep.findById(id);
		if(pet.isPresent()) {
			return new ResponseEntity<>(pet.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Iterable<Pet>> getAllPets(@PathVariable("id") Long id) {
//		return new ResponseEntity<>(petRep.findAll(), HttpStatus.OK);
//	}
	
	@PostMapping(path = "/pets", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Pet postPet(@RequestBody Pet pet) {
		return petRep.save(pet);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deletePet(@PathVariable("id") Long id) {
		petRep.deleteById(id);
	}
	
	@GetMapping("/recent")
	public Iterable<Pet> getRecentPets(){
		PageRequest page = 	PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return petRep.findAll(page).getContent();
	}
	
	
	
}
