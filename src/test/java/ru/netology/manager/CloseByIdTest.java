package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CloseByIdTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    Issue one = new Issue(1, true);
    Issue two = new Issue(2, false);

    @BeforeEach
    public void setUp() {
        repository.save(one);
        repository.save(two);

    }

    @Test
    public void shouldClosedByIdIfOpen() {
        manager.closeIssueById(one.getId());
        assertFalse(one.isOpened());
    }

    @Test
    public void shouldClosedByIdIfClosed() {
        manager.closeIssueById(two.getId());
        assertFalse(two.isOpened());
    }

    @Test
    public void shouldClosedByIdIfNotExist() {
        manager.closeIssueById(3);

        assertEquals(List.of(new Issue(1,true),new Issue (2,false)),repository.findAll());
    }
}
