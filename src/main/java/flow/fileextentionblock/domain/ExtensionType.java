package flow.fileextentionblock.domain;

public enum ExtensionType {
    FIXED("F"), CUSTOM("C");

    private final String value;

    ExtensionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
