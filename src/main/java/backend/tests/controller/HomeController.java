package backend.tests.controller;

import backend.tests.entities.Test;
import backend.tests.service.TestQuestionsService;
import backend.tests.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
public class HomeController {

    private TestService testService;
    private TestQuestionsService testQuestionsService;

    public HomeController(TestService testService,TestQuestionsService testQuestionsService){
        this.testService = testService;
        this.testQuestionsService = testQuestionsService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/")
    public String processFile(@RequestParam("test")MultipartFile multipartFile) throws IOException {
        File fileTest = saveFile(multipartFile);
        Test test = new Test();
        test.setName(fileTest.getName().replace(".xlsx",""));
        test = testService.save(test);
        testQuestionsService.save(test);
        return "home";
    }

    public File saveFile(MultipartFile file){
        try {
            String systemPath = File.separator + "tests";
            Files.createDirectories(Paths.get(systemPath));
            systemPath = systemPath + File.separator + file.getOriginalFilename();
            Files.deleteIfExists(Paths.get(systemPath));
            byte[] bytes = file.getBytes();
            File test = new File(systemPath);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(test));
            stream.write(bytes);
            stream.close();
            return test;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
