package hello;


import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
	 public Iterable<Person> findByFirstName(String firstName);
	 public Iterable<Person> findByLastName(String lastName);
}
