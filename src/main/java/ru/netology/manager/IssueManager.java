package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.obj.Assignee;
import ru.netology.domain.obj.Image;
import ru.netology.domain.obj.Label;
import ru.netology.domain.obj.Obj;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;

public class IssueManager {
    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void newIssue(String title,
                         String text,
                         String author,
                         long time,
                         Assignee assignee,
                         Set<Image> images,
                         Set<Label> labels) {
        int id = repository.findAll().size() + 1;


        Issue newIssue = new Issue(id,  title, author, text, time, new HashSet<>());

        newIssue.accept(assignee);
        newIssue.acceptAll(images);
        newIssue.acceptAll(labels);

        repository.save(newIssue);
    }

    public List<Issue> getOpenIssues(){
        return repository.findAll().stream()
                .filter(Issue::isOpened)
                .collect(Collectors.toList());
    }

    public List<Issue> getCloseIssues(){
        return repository.findAll().stream()
                .filter(issue -> !issue.isOpened())
                .collect(Collectors.toList());
    }

    public List<Issue> getAuthorsIssues(String author){
        return repository.findAll().stream()
                .filter(issue -> issue.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Issue> getAssigneeIssues(Assignee assignee){
        return repository.findAll()
                .stream()
                .filter(issue -> issue.findAll().contains(assignee))
                .collect(Collectors.toList());
    }

    public List<Issue> getLabelIssues(Label label){
        return repository.findAll()
                .stream()
                .filter(issue -> issue.findAll().contains(label))
                .collect(Collectors.toList());
    }

    public List<Issue> sortByNewest(){
        repository.findAll().sort(Comparator.comparingLong(Issue::getTime));
        return repository.findAll();
    }

    public List<Issue> sortByOldest(){
        repository.findAll().sort(Comparator.comparingLong(Issue::getTime).reversed());
        return repository.findAll();
    }

    public List<Issue> sortByLessComments(){
        repository.findAll().sort(Comparator.comparingInt(Issue::getCommentsQuantity));
        return repository.findAll();
    }

    public List<Issue> sortByMoreComments(){
        repository.findAll().sort(Comparator.comparingInt(Issue::getCommentsQuantity).reversed());
        return repository.findAll();
    }

    public void closeIssueById(int id){
        repository.findAll()
                .stream()
                .filter(issue -> issue.getId() == id)
                .forEach(issue -> issue.setOpened(false));
    }
}
