package code.structures;

public enum MatrixObject {
    NEO,AGENT,HOSTAGE,PAD,PILL,TELEPHONE_BOOTH,EMPTY;

    @Override
    public String toString() {
        if(this==NEO)
            return "NEO";
        if(this==AGENT)
            return "A";
        if(this==HOSTAGE)
            return "H";
        if(this==PAD)
            return "PAD";
        if(this==PILL)
            return "PILL";
        if(this==TELEPHONE_BOOTH)
            return "TB";
            return " ";


    }
}
