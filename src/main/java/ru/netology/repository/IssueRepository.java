package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IssueRepository {
    private List<Issue> issueList = new ArrayList<>();

    public List<Issue> findAll(){
        return issueList;
    }

    public Issue findById(int id){
     List<Issue> i = issueList.stream().filter(issue -> issue.getId() == id).collect(Collectors.toList());
     if (i.size() != 0){
         return i.get(0);
        } else return null;
    }

    public void save(Issue issue){
        issueList.add(issue);
    }
}
