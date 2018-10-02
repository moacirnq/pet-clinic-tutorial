package guru.springframework;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.model.Owner;
import guru.springframework.repositories.OwnerRepository;
import guru.springframework.repositories.PetRepository;
import guru.springframework.repositories.PetTypeRepository;
import guru.springframework.services.springdatajpa.OwnerSDJpaService;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	
	@Mock
	private OwnerRepository ownerRepository;
	
	@Mock
	private PetRepository petRepository;
	
	@Mock
	private PetTypeRepository petTypeRepository;
	
	@InjectMocks
	private OwnerSDJpaService service;
	
	String lastName = "Smith";
	Long ownerId = 1L;
	Owner returnOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(ownerId).lastName(lastName).build();
	}
	
	@Test
	void findByLastName() {
		when(service.findByLastName(any())).thenReturn(returnOwner);;
		Owner owner = service.findByLastName(lastName);
		
		assertNotNull(owner);
		assertEquals(lastName, owner.getLastName());
	}
	
	@Test
	void findByIdFound() {
		Long notFoundId = 2L;
		
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(returnOwner));
		
		Owner foundOwner = service.findById(ownerId);

		assertNotNull(foundOwner);
	}
	
	@Test
	void findByIdNotFound() {
		Long notFoundId = 2L;
		
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner notFoundOwner = service.findById(notFoundId);
		
		assertNull(notFoundOwner);
	}
	
	@Test
	void saveWithId() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = service.save(ownerToSave);
		
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());
	}
	
	@Test
	public void delete() { 
		service.delete(new Owner());
		
		verify(ownerRepository).delete(any());
	}

	@Test
	public void deleteById() { 
		service.deleteById(1L);
		
		verify(ownerRepository).deleteById(any());
	}

}
