package guru.springframework.services.map;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.model.Specialty;
import guru.springframework.model.Vet;
import guru.springframework.services.SpecialtyService;
import guru.springframework.services.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

	private SpecialtyService specialityService;
	
	@Autowired
	public VetServiceMap(SpecialtyService specialityService) {
		this.specialityService = specialityService;
	}
	
	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet object) {
		
		if (object.getSpecialties().size() > 0) {
			object.getSpecialties().forEach(speciality -> {
				if(speciality.getId() == null) {
					Specialty savedSpeciality = specialityService.save(speciality);
				}
			});
		}
		
		return super.save(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}
	
}
