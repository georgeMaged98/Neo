package code;

public class GridElement {
    @Override
    public String toString() {
        if(matrixObject==MatrixObject.EMPTY||matrixObject==MatrixObject.NEO||matrixObject==MatrixObject.TELEPHONE_BOOTH)
            return matrixObject.toString();
        return matrixObject.toString()+" "+index;
    }

    public GridElement(MatrixObject matrixObject, int index) {
        this.matrixObject = matrixObject;
        this.index = index;
    }

    MatrixObject matrixObject;
    int index;
}
