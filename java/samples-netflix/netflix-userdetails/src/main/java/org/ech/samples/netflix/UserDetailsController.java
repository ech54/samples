package org.ech.samples.netflix;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/details")
public class UserDetailsController {
	
	final private Map<Long, UserDetails> localCache = new ConcurrentHashMap<>();
	
	@RequestMapping(value="/{id}", method=GET)
	public UserDetails get(@PathVariable("id") final Long id) throws IllegalAccessException {
		
		if (Objects.isNull(id)) {
			throw new IllegalAccessException("User 'id' is mandatory.");
		}
		
		return this.localCache.get(id);
		// Do not test the object result.
	}
	
	@RequestMapping(value="/exist/{id}", method=GET)
	public boolean exist(@PathVariable("id") final Long id) {
		if (Objects.isNull(id)) {
			return false;
		}
		return this.localCache.get(id)!=null;
	}
	
// TODO	
//	@RequestMapping(value="/", method=POST)
//	public void add(final UserDetails details) {
//		
//		if (exist(id))
//	}
//	
//	private boolean isExisting()
	
}
