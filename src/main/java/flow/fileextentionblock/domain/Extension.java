package flow.fileextentionblock.domain;

import lombok.Data;

@Data
public class Extension {
    private Long id;
    private String name;
    private String type;
    private String checked;
}
