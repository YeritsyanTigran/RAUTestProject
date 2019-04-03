package backend.tests.service;

import backend.tests.entities.Test;
import backend.tests.entities.TestQuestion;
import backend.tests.repository.TestQuestionsRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
public class TestQuestionsService {

    private TestQuestionsRepository testQuestionsRepository;

    public TestQuestionsService(TestQuestionsRepository testQuestionsRepository){
        this.testQuestionsRepository = testQuestionsRepository;
    }

    public void save(Test test) {
        String systemPath = Paths.get(File.separator + "tests" + File.separator + test.getName() + ".xlsx").toString();

        try(InputStream inputStream = new FileInputStream(systemPath)){
            Iterator<Row> rowIterator = WorkbookFactory.create(inputStream).getSheetAt(0).iterator();

            while(rowIterator.hasNext()){
               TestQuestion testQuestion = convertToTestQuestion(rowIterator.next());
               testQuestion.setTestId(test.getId());
               testQuestionsRepository.save(testQuestion);
            }
        }catch (Exception e){
           throw new RuntimeException(e);
        }

    }

    private TestQuestion convertToTestQuestion(Row row){
        Iterator<Cell> cellIterator = row.cellIterator();
        TestQuestion testQuestion = new TestQuestion();

        String question = cellIterator.next().getStringCellValue();
        String answers = cellIterator.next().getStringCellValue();

        testQuestion.setQuestion(question);
        testQuestion.setAnswers(answers);

        return testQuestion;
    }
}
