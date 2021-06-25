package ru.netology.domain.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Label extends Obj {
    private String title;
    private String description;
    private String color;

    public Label() {
    }

    public Label(int id, String title, String description, String color) {
        super(id);
        this.title = title;
        this.description = description;
        this.color = color;
    }
}
