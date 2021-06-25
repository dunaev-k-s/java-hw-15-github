package ru.netology.domain.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Assignee extends Obj {
    private String participant;

    public Assignee() {
    }

    public Assignee(int id, String participant) {
        super(id);
        this.participant = participant;
    }
}
