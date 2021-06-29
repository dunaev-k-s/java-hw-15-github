package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class SortIssuesTest {

    @Nested
    public class SortByTimeIfExistTest {

        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);
        Issue one   = new Issue(1,1624968200557L);
        Issue two   = new Issue(2,1624968201000L);
        Issue three = new Issue(3,1624968202000L);
        Issue four  = new Issue(4,1624968203000L);
        Issue five  = new Issue(5,1624968204000L);
        Issue six   = new Issue(6,1624968209000L);
        Issue seven = new Issue(7,1624968208000L);
        Issue eight = new Issue(8,1624968207000L);
        Issue nine  = new Issue(9,1624968206000L);
        Issue ten   = new Issue(10,1624968205000L);

        @BeforeEach
        public void setUp(){
            repository.save(one  );
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
        public void shouldSortByNewestIfExist(){

            assertEquals(List.of(one,two,three,four,five,ten,nine,eight,seven,six),manager.sortByNewest());
        }

        @Test
        public void shouldSortByOldestIfExist(){

            assertEquals(List.of(six,seven,eight,nine,ten,five,four,three,two,one),manager.sortByOldest());
        }
    }

    @Nested
    public class SortByTimeIfNotExistTest {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);

        @Test
        public void shouldSortByNewestIfNotExist(){
            assertEquals(List.of(),manager.sortByNewest());
        }

        @Test
        public void shouldSortByOldestNotExist(){
            assertEquals(List.of(),manager.sortByOldest());
        }
    }

    @Nested
    public class SortByCommentsIfExistTest {

        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);
        Issue one   = new Issue(1,1);
        Issue two   = new Issue(2,2);
        Issue three = new Issue(3,3);
        Issue four  = new Issue(4,4);
        Issue five  = new Issue(5,5);
        Issue six   = new Issue(6,10);
        Issue seven = new Issue(7,9);
        Issue eight = new Issue(8,8);
        Issue nine  = new Issue(9,7);
        Issue ten   = new Issue(10,6);

        @BeforeEach
        public void setUp(){
            repository.save(one  );
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
        public void shouldSortByLessCommentsIfExist(){

            assertEquals(List.of(one,two,three,four,five,ten,nine,eight,seven,six),manager.sortByLessComments());
        }

        @Test
        public void shouldSortByMoreCommentsIfExist(){

            assertEquals(List.of(six,seven,eight,nine,ten,five,four,three,two,one),manager.sortByMoreComments());
        }
    }

    @Nested
    public class SortByCommentsIfNotExistTest {
        IssueRepository repository = new IssueRepository();
        IssueManager manager = new IssueManager(repository);

        @Test
        public void shouldSortByLessIfNotExist(){
            assertEquals(List.of(),manager.sortByLessComments());
        }

        @Test
        public void shouldSortByMoreNotExist(){
            assertEquals(List.of(),manager.sortByMoreComments());
        }
    }
}
