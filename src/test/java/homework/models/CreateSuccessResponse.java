package homework.models;

import lombok.Data;

@Data
public class CreateSuccessResponse {
    private int id;
    private String name, job, createdAt;
}
