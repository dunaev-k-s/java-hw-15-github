package ru.netology.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Issue;
import ru.netology.domain.obj.Assignee;
import ru.netology.domain.obj.Label;
import ru.netology.repository.IssueRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class FiltrationIssuesTest {


    @Nested
    public class GetOpenedTest {
        @Mock
        IssueRepository repository;
        @InjectMocks
        IssueManager manager = new IssueManager(repository);
        Issue one   = new Issue(1,false);
        Issue two   = new Issue(2,false);
        Issue three = new Issue(3,false);
        Issue four  = new Issue(4,false);
        Issue five  = new Issue(5,false);
        Issue six   = new Issue(6,true);
        Issue seven = new Issue(7,true);
        Issue eight = new Issue(8,true);
        Issue nine  = new Issue(9,true);
        Issue ten   = new Issue(10,true);

        @Test
        public void shouldGetOpenedIfExist(){
            List<Issue> returned = List.of(one,two,three,four,five,six,seven,eight,nine,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(six,seven,eight,nine,ten),manager.getOpenIssues());
        }

        @Test
        public void shouldGetClosedIfExist(){
            List<Issue> returned = List.of(one,two,three,four,five,six,seven,eight,nine,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(one,two,three,four,five),manager.getCloseIssues());
        }

        @Test
        public void shouldGetOpenIfNotExist(){
            List<Issue> returned = List.of(one,two,three,four,five);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(),manager.getOpenIssues());
        }

        @Test
        public void shouldGetClosedIfNotExist(){
            List<Issue> returned = List.of(six,seven,eight,nine,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(),manager.getCloseIssues());
        }
    }

    @Nested
    public class FiltersByAuthorTest {
        @Mock
        IssueRepository repository;
        @InjectMocks
        IssueManager manager = new IssueManager(repository);
        Issue one   = new Issue(1,"One");
        Issue two   = new Issue(2,"Two");
        Issue three = new Issue(3,"One");
        Issue four  = new Issue(4,"Two");
        Issue five  = new Issue(5,"One");
        Issue six   = new Issue(6,"Two");
        Issue seven = new Issue(7,"One");
        Issue eight = new Issue(8,"Two");
        Issue nine  = new Issue(9,"One");
        Issue ten   = new Issue(10,"Two");

        @Test
        public void shouldGetAuthorsIfExist(){
            List<Issue> returned = List.of(one,two,three,four,five,six,seven,eight,nine,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(two,four,six,eight,ten),manager.getAuthorsIssues("Two"));
        }

        @Test
        public void shouldGetAuthorsIfNotExist(){
            List<Issue> returned = List.of(one,three,five,seven,nine);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(),manager.getAuthorsIssues("Two"));
        }
    }

    @Nested
    public class FiltersByAssigneeTest {

        @Mock
        IssueRepository repository;
        @InjectMocks
        IssueManager manager = new IssueManager(repository);

        Issue one   = new Issue(1,Set.of(new Assignee(1,"Ivanov")));
        Issue two   = new Issue(2,Set.of(new Assignee(2,"Petrov")));
        Issue three = new Issue(3,Set.of(new Assignee(1,"Ivanov")));
        Issue four  = new Issue(4,Set.of(new Assignee(2,"Petrov")));
        Issue five  = new Issue(5,Set.of(new Assignee(1,"Ivanov")));
        Issue six   = new Issue(6,Set.of(new Assignee(2,"Petrov")));
        Issue seven = new Issue(7,Set.of(new Assignee(1,"Ivanov")));
        Issue eight = new Issue(8,Set.of(new Assignee(2,"Petrov")));
        Issue nine  = new Issue(9,Set.of(new Assignee(1,"Ivanov")));
        Issue ten   = new Issue(10,Set.of(new Assignee(2,"Petrov")));

        @Test
        public void shouldGetAssigneeIfExist(){
            List<Issue> returned = List.of(one,two,three,four,five,six,seven,eight,nine,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(one,three,five,seven,nine),manager
                    .getAssigneeIssues(new Assignee(1,"Ivanov")));
        }

        @Test
        public void shouldGetAssigneeIfNotExist(){
            List<Issue> returned = List.of(two,four,six,eight,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(),manager
                    .getAssigneeIssues(new Assignee(1,"Ivanov")));
        }

    }

    @Nested
    public class FiltersByLabelTest {

        @Mock
        IssueRepository repository;
        @InjectMocks
        IssueManager manager = new IssueManager(repository);

        Issue one   = new Issue(1,Set
                .of(new Label(1,"bug","Something isn't working","FF0000")));
        Issue two   = new Issue(2,Set
                .of(new Label(2,"documentation","Improvements or additions to documentation","0075CA")));
        Issue three = new Issue(3,Set
                .of(new Label(1,"bug","Something isn't working","FF0000")));
        Issue four  = new Issue(4,Set
                .of(new Label(2,"documentation","Improvements or additions to documentation","0075CA")));
        Issue five  = new Issue(5,Set
                .of(new Label(1,"bug","Something isn't working","FF0000")));
        Issue six   = new Issue(6,Set
                .of(new Label(2,"documentation","Improvements or additions to documentation","0075CA")));
        Issue seven = new Issue(7,Set
                .of(new Label(1,"bug","Something isn't working","FF0000")));
        Issue eight = new Issue(8,Set
                .of(new Label(2,"documentation","Improvements or additions to documentation","0075CA")));
        Issue nine  = new Issue(9,Set
                .of(new Label(1,"bug","Something isn't working","FF0000")));
        Issue ten   = new Issue(10,Set
                .of(new Label(2,"documentation","Improvements or additions to documentation","0075CA")));

        @Test
        public void shouldGetLabelIfExist(){
            List<Issue> returned = List.of(one,two,three,four,five,six,seven,eight,nine,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(one,three,five,seven,nine),manager
                    .getLabelIssues(new Label(1,"bug","Something isn't working","FF0000")));
        }

        @Test
        public void shouldGetLabelIfNotExist(){
            List<Issue> returned = List.of(two,four,six,eight,ten);
            doReturn(returned).when(repository).findAll();

            assertEquals(List.of(),manager
                    .getLabelIssues(new Label(1,"bug","Something isn't working","FF0000")));
        }
    }
}
