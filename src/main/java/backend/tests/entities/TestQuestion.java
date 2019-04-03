package backend.tests.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "test_question")
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  long testId;

    @Column(name = "question",columnDefinition = "Text",nullable = false)
    private String question;

    @Column(name = "answers",nullable = false)
    private String answers;
}
