package domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Newsletter {

    private Long id;

    @NonNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Newsletter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
