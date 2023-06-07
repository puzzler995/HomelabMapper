package dev.puzzler995.HomelabMapper.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Application {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String url;
    private String icon;

    public Application(String name, String description, String url, String icon) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.icon = icon;
    }
}
