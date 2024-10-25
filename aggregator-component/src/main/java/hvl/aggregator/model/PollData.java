package hvl.aggregator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PollData {

    @Id
    private String id;
    private String data;

    public PollData(String data) {
        this.data = data;
    }

    // Getters and setters
}