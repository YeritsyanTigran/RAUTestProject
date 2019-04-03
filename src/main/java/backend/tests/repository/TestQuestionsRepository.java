package backend.tests.repository;

import backend.tests.entities.TestQuestion;
import org.springframework.data.repository.CrudRepository;

public interface TestQuestionsRepository extends CrudRepository<TestQuestion,String> {
}
