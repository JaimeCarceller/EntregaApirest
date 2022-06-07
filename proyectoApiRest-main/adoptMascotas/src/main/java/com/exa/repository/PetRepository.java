package com.exa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.exa.entities.Pet;


public interface PetRepository extends  PagingAndSortingRepository<Pet, Long>{

}
