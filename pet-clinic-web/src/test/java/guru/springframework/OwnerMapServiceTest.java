package guru.springframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.model.Owner;
import guru.springframework.services.map.OwnerServiceMap;
import guru.springframework.services.map.PetServiceMap;
import guru.springframework.services.map.PetTypeServiceMap;

class OwnerMapServiceTest {
	
	private OwnerServiceMap ownerMapService;
	private String lastName = "Smith";
	private Long ownerId = 1L;

	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = ownerMapService.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	void testFindById() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testSaveExistingId() {
		Long owner2Id = 2L;
		Owner owner2 = Owner.builder().id(owner2Id).build();
		Owner savedOwner = ownerMapService.save(owner2);
		
		assertEquals(owner2Id, savedOwner.getId());
	}
	
	@Test
	void saveNoId() {
		Owner savedOwner = ownerMapService.save(Owner.builder().build());
		
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void testDeleteByIdLong() {
		ownerMapService.deleteById(ownerId);
		
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testDeleteOwner() {
		Owner owner = ownerMapService.findById(ownerId);
		ownerMapService.delete(owner);
		
		assertEquals(0, ownerMapService.findAll().size());
	}
	

	@Test
	void testDeleteUnexistingOwner() {
		Owner owner = new Owner();
		ownerMapService.delete(owner);
		
		assertEquals(1, ownerMapService.findAll().size());
	}

	@Test
	void testFindByLastName() {
		Owner smith = ownerMapService.findByLastName(lastName);
		assertNotNull(smith);
		assertEquals(ownerId, smith.getId());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		Owner notFound = ownerMapService.findByLastName("NotFound");
		assertNull(notFound);
	}

}
