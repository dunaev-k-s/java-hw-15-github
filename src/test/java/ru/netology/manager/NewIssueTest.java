package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.obj.Assignee;
import ru.netology.repository.IssueRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewIssueTest {

    @Nested
    public class NewIssueIfExist {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);
        Issue one = new Issue(1, "one", "Arkady", "issue");

        @BeforeEach
        void setUp() {
            repository.save(one);
        }

        @Test
        public void shouldBeCreatedIfExist() {
            Date date = new Date();
            manager.newIssue("two", "two", "Semen",date.getTime(), new Assignee(), Set.of(), Set.of());

            assertEquals(List.of(one, new Issue(2,
                    "two",
                    "Semen",
                    "two",
                    new Date().getTime(),
                    Set.of(new Assignee()))), repository.findAll());
        }
    }

    @Nested
    public class NewIssueIfNotExist {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);

        @Test
        public void shouldBeCreatedIfNotExist() {
            Date date = new Date();
            manager.newIssue("one", "one", "Semen", date.getTime(), new Assignee(), Set.of(), Set.of());
            assertEquals(List.of(new Issue(1,
                    "one",
                    "Semen",
                    "one",
                    date.getTime(),
                    Set.of(new Assignee()))), repository.findAll());
        }
    }

}



