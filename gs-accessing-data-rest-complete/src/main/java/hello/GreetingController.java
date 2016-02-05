package hello;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class GreetingController {

	@Autowired
	private PersonRepository personRepository;
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(path="/greeting",method=RequestMethod.GET)
    public Greeting greetingGokce(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(
    	      path = "/person",
    	      method = RequestMethod.POST,
    	      consumes = MediaType.APPLICATION_JSON_VALUE)
    	  @ResponseBody
    	  public ResponseEntity<?> addUser(@RequestBody Person person) {
    	    Person p=personRepository.save(person);
    	    if(p.getId()>0)
    	    return new ResponseEntity<>(HttpStatus.OK);
    	    else
    	    	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    	  }
    
    @RequestMapping(
  	      path = "/person",
  	      method = RequestMethod.PUT,
  	      consumes = MediaType.APPLICATION_JSON_VALUE)
  	  @ResponseBody
  	  public ResponseEntity<?> updateUser(@RequestBody Person person) {
  	    Person p=personRepository.save(person);
  	    if(p.getId()>0)
  	    return new ResponseEntity<>(HttpStatus.OK);
  	    else
  	    	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  	  }
    
    @RequestMapping(
  	      value = "/person",
  	      method = RequestMethod.GET)
  	  public Iterable<Person> listUser() {
  	    return personRepository.findAll();
  	  }
    
    @RequestMapping(
    	      value = "/person/{id}",
    	      method = RequestMethod.DELETE
    	    )
          @ResponseBody    
    	  public ResponseEntity<?> deleteUserById(@PathVariable("id") long id) {
    	  personRepository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.OK);
    	  }
    
    @RequestMapping(
  	      value = "/person/{id}",
  	      method = RequestMethod.GET
  	    )
        @ResponseBody    
  	  public Person listUserById(@PathVariable("id") long id) {
  	    return personRepository.findOne(id);
  	  }
    
    @RequestMapping(
  	      value = "/person/name/{firstName}",
  	      method = RequestMethod.GET
  	    )
        @ResponseBody    
  	  public Iterable<Person> listUserByFirstName(@PathVariable("firstName") String firstName) {
  	    return personRepository.findByFirstName(firstName);
  	  }
      
    
    @RequestMapping(path="/greeting/{name}",method=RequestMethod.GET)
    public Greeting greetingDogus(@PathVariable("name") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(path="/greeting/{name1}/{name2}",method=RequestMethod.GET)
    public ArrayList<Greeting> greetingOur(@PathVariable("name1") String name1,@PathVariable("name2") String name2) {
        ArrayList<Greeting> list = new ArrayList<Greeting>();
    	list.add(new Greeting(counter.incrementAndGet(),
                            String.format(template, name1)));
    	list.add(new Greeting(counter.incrementAndGet(),
                            String.format(template, name2)));
    	return list;
    }
}
