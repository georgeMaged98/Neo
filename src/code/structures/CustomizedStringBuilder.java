package code.structures;

public class CustomizedStringBuilder {
    private StringBuilder sb;
    private String sep;

    public CustomizedStringBuilder(String sep) {
        this.sb = new StringBuilder();
        this.sep = sep;
    }

    public void append(String str) {

        if (sb.length() != 0)
            sb.append(sep);
        sb.append(str);
    }

    public String getString() {
        return sb.toString();
    }

    public void clear() {
        sb.setLength(0);
    }
}
