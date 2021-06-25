package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> issueList = new ArrayList<>();

    public List<Issue> findAll(){
        return issueList;
    }

    public Issue findById(int id){
        return (Issue) issueList.stream().filter(issue -> issue.getId() == id);
    }

    public void save(Issue issue){
        issueList.add(issue);
    }
}
