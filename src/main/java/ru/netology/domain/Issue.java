package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.obj.Obj;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    private int id;
    private int commentsQuantity;
    private String title;
    private String author;
    private String text;
    private boolean opened = true;
    private boolean newed = true;

    private long time;

    public Issue(int id, String title, String author, String text) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.text = text;
    }

    public Issue(int id, String title, String author, String text, Long time,Set<Obj> objs) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.text = text;
        this.time = time;
        this.objs = objs;
    }

    public Issue(int id, String author) {
        this.id = id;
        this.author = author;
    }

    public Issue(int id, boolean opened) {
        this.id = id;
        this.opened = opened;
    }

    public Issue(int id, Set<Obj> objs) {
        this.id = id;
        this.objs = objs;
    }

    public Issue(int id, long time) {
        this.id = id;
        this.time = time;
    }

    public Issue(int id, int commentsQuantity) {
        this.id = id;
        this.commentsQuantity = commentsQuantity;
    }

    private Set<Obj> objs = new HashSet<>();

    public boolean accept(Obj o){
        return objs.add(o);
    }

    public boolean acceptAll(Collection<? extends Obj> c){
        return objs.addAll(c);
    }

    public Set<Obj> findAll(){
        return objs;
    }
}
