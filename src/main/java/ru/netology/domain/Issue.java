package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.obj.Obj;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    private int id;
    private long time;
    private int commentsQuantity;
    private String title;
    private String author;
    private String text;
    private boolean opened;
    private boolean newed;

    public Issue(int id, long time, String title, String author, String text, boolean opened, boolean newed) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.author = author;
        this.text = text;
        this.opened = opened;
        this.newed = newed;
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
