package backend.tests.repository;

import backend.tests.entities.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test,String> {
}
