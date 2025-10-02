package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserResponse {
    private String name;
    private String job;
    private String id;
    @JsonProperty("createdAt")
    private String createdAt;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setName(String v) {
        this.name = v;
    }

    public void setJob(String v) {
        this.job = v;
    }

    public void setId(String v) {
        this.id = v;
    }

    public void setCreatedAt(String v) {
        this.createdAt = v;
    }
}
