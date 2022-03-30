package fi.ohjelmistoprojekti1.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Employee;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;


// This class is used by spring security to authenticate and authorize user
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final EmployeeRepository repository;

	@Autowired
	public UserDetailServiceImpl(EmployeeRepository emprepository) {
		this.repository = emprepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	Employee currentemployee = repository.findByUsername(username);
		if (currentemployee == null){
            throw new UsernameNotFoundException(username + " was not found");
		}
        UserDetails employee = new org.springframework.security.core.userdetails.User(
			username, currentemployee.getHashpassword(), 
        	AuthorityUtils.createAuthorityList(currentemployee.getRole().getDescription())
			);

        return employee;
    }   
} 

