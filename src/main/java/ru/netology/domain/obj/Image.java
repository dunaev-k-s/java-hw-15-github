package ru.netology.domain.obj;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Image extends Obj {
    private String title;
    private String url;

    public Image() {
    }

    public Image(int id, String title, String url) {
        super(id);
        this.title = title;
        this.url = url;
    }
}
