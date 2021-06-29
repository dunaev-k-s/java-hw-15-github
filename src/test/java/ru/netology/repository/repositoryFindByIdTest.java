package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class repositoryFindByIdTest {
    IssueRepository repository = new IssueRepository();
    Issue one   = new Issue(1,"one");
    Issue two   = new Issue(2,"two");
    Issue three = new Issue(3,"three");
    Issue four  = new Issue(4,"four");
    Issue five  = new Issue(5,"five");
    Issue six   = new Issue(6,"six");
    Issue seven = new Issue(7,"seven");
    Issue eight = new Issue(8,"eight");
    Issue nine  = new Issue(9,"nine");
    Issue ten   = new Issue(10,"ten");

    @BeforeEach
    public void setUp(){
        repository.save(one);
        repository.save(two  );
        repository.save(three);
        repository.save(four );
        repository.save(five );
        repository.save(six  );
        repository.save(seven);
        repository.save(eight);
        repository.save(nine );
        repository.save(ten  );
    }

    @Test
    public void shouldFindIfExist(){

        assertEquals(eight, repository.findById(8));

    }

    @Test
    public void shouldFindIfNotExist(){

        assertEquals(null, repository.findById(15));

    }
}
