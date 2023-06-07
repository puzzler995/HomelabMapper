package dev.puzzler995.HomelabMapper.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Server {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String ipAddress;
    private String operatingSystem;
    private String icon;
    private Server host;

    public boolean isVirtualMachine() {
        return host != null;
    }
}
