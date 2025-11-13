package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Cafeteria;
import app.entities.Stall;
import app.repositories.CafeteriaRepository;
import app.repositories.StallRepository;

@Component
public class StallServiceComponent {
	
	@Autowired
	private StallRepository stallRepo;
	
	@Autowired
	private CafeteriaRepository cafeRepo;
	
	@PostConstruct
	public void init()
	{
		
		if (cafeRepo.count() == 0)
		{
			Cafeteria cafe = new Cafeteria();
			cafe.setName("JSEC");
			cafeRepo.save(cafe);
			
			cafe = new Cafeteria();
			cafe.setName("Gonzaga");
			cafeRepo.save(cafe);
		}
		
		if (stallRepo.count()==0)
		{
			Stall stall = makeStall("Mongch", "JSEC"); 
			stallRepo.save(stall);
			
			stall = makeStall("Ondo", "JSEC"); 
			stallRepo.save(stall);
			
			stall = makeStall("Day Off", "Gonzaga"); 
			stallRepo.save(stall);
			
			stall = makeStall("GHE", "Gonzaga"); 
			stallRepo.save(stall);
			
			stall = makeStall("Hunger Buster", "Gonzaga");
			stallRepo.save(stall);
		}
	}

	private Stall makeStall(String name, String cafeName)
	{
		Stall stall = new Stall();
		stall.setName(name);
		
		Cafeteria cafe = cafeRepo.findByName(cafeName); 
		stall.setCafeteria(cafe);
		
		return stall; 
	}
}
