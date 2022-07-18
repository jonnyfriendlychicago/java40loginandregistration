package com.jonfriend.java40loginandregistration.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jonfriend.java40loginandregistration.models.LoginUserMdl;
import com.jonfriend.java40loginandregistration.models.UserMdl;
import com.jonfriend.java40loginandregistration.repositories.UserRpo;

@Service
public class UserSrv{
    
    @Autowired
    private UserRpo userRpo;
    
    // TO-DO: Write register and login methods!
    public UserMdl register(
    		UserMdl newUser
    		, BindingResult result) {
        
    	Optional<UserMdl> potentialUser = userRpo.findByEmail(newUser.getEmail());
    	
    	// Reject if email is taken (present in database)
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "An account with that email already exists!");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
    	
    	
    	
    	String passwordEntered = newUser.getPassword(); //try to usethis later 
//    	TO-DO: Additional validations!
// 		TO-DO - Reject values or register if no errors:
        
        // Reject if email is taken (present in database)
        // Reject if password doesn't match confirmation
        
        // Return null if result has errors
    
        // Hash and set password, save user to database
        
    	// Reject if password doesn't match confirmation
        
        
        // Return null if result has errors
        if(result.hasErrors()) {
            // Exit the method and go back to the controller 
            // to handle the response
            return null;
        }
        
     // Hash and set password, save user to database
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
    	return userRpo.save(newUser);
    }
    
    public UserMdl login(
    		LoginUserMdl newLogin
    		, BindingResult result) {
    	
    	Optional<UserMdl> potentialUser = userRpo.findByEmail(newLogin.getEmail());
    	
    	
    	// Find user in the DB by email
        // Reject if NOT present
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "User not found!");
    		return null;
    	}
    	
    	// User exists, retrieve user from DB
    	UserMdl user = potentialUser.get();
    	
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    		result.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	
    	// Return null if result has errors
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	
    	String passwordEntered = newLogin.getPassword();// maybe incor this later ?
    	
    	potentialUser.isPresent(); //notsureif needed 
    	
    	
    	// TO-DO: Additional validations!
    	
    	// TO-DO - Reject values:
        
    	// Find user in the DB by email
        // Reject if NOT present
        
        // Reject if BCrypt password match fails
    
        // Return null if result has errors
        // Otherwise, return the user object
//    	if(result.hasErrors()) {
//    	    // Exit the method and go back to the controller 
//    	    // to handle the response
//    	    return null;
//    	}
    	return user;
    }
    
    public UserMdl findById(Long id) {
    	Optional<UserMdl> potentialUser = userRpo.findById(id);
    	if(potentialUser.isPresent()) {
    		return potentialUser.get();
    	}
    	return null;
    }
    
    
}
