package backend.tests.service;

import backend.tests.entities.Test;
import backend.tests.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private TestRepository testRepository;

    public TestService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public Test save(Test test){
        return testRepository.save(test);
    }
}
