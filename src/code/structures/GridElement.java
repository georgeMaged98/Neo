package code.structures;

public class GridElement {
    @Override
    public String toString() {
        if (matrixObject == MatrixObject.EMPTY || matrixObject == MatrixObject.NEO || matrixObject == MatrixObject.TELEPHONE_BOOTH)
            return matrixObject.toString();
        return matrixObject.toString() + " " + index;
    }

    public GridElement(MatrixObject matrixObject, int index) {
        this.matrixObject = matrixObject;
        this.index = index;
    }

    private MatrixObject matrixObject;
    private int index;


    public MatrixObject getMatrixObject() {
        return matrixObject;
    }

    public void setMatrixObject(MatrixObject matrixObject) {
        this.matrixObject = matrixObject;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
