package code;

public class Pos {
    int x;
    int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;
        Pos pos = (Pos) o;
        return x == pos.x && y == pos.y;
    }


    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
