package app.components;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.User;
import app.repositories.UserRepository;

@Component
public class UserServiceComponent {

	@Autowired
	private UserRepository userRepo;
	
	
	@PostConstruct
	public void init() {
		if (userRepo.count() == 0) {
            User user1 = new User();
            user1.setName("Hannah");
            userRepo.save(user1);

            User user2 = new User();
            user2.setName("Eren");
            userRepo.save(user2);
        }
	}
	
	 public List<User> getAllUsers() {
		 return userRepo.findAll();
	 }
	 
	 
	 public User getUserById(String id) {
		 return userRepo.findByIdNumber(id);
	 }
	 
	 public User addNewUser(String name) {
		 User user = new User();
	     user.setName(name);
	     return userRepo.save(user); 
	 }
	 
	 
	
}
