public enum Provider {
    /**
     * Taxonomy packages providers.
     */
    EBA("EBA"),
    EDINET("EDINET"),
    CMFCLCI("CMFCLCI"),
    CIPC("CIPC");

    private final String value;

    Provider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
