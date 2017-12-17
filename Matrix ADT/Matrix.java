/*
#Chuong Viet Truong
#CruzID: Chvtruon
#PA 3
*/

class Matrix{
    private class Entry{
        int column;
        double value;

        Entry(int column, double value){
            this.column = column;
            this.value = value;
        }

        public boolean equals(Object x){
            boolean eq = false;
            Entry that;
            if(x instanceof Entry){
               that = (Entry) x;
               eq = (this.value == that.value);
            }
            return eq;
        }

        public String toString(){
            return "(" + column + ", " + value + ")";
        }
    }

    private int size;
    private int nnz;
    private List matrixRow[];

    // Constructor
    Matrix(int n){
        if(n < 1){
            throw new RuntimeException("Matrix Error: n must be >= 1");
        }
        size = n;
        nnz = 0;
        matrixRow = new List[n+1];
        for (int i=1; i<=n; i++){
            matrixRow[i] = new List();
        }
    }

    // Access functions
    int getSize(){
        return size;
    }

    int getNNZ(){
        return nnz;
    }

    public boolean equals(Object x){
        if(!(x instanceof Matrix)) {
			throw new RuntimeException("Matrix Error: equals() called on non-matrix parameter");
		}
        Matrix m = (Matrix) x;

        if(this.getSize() != m.getSize() || this.getNNZ() != m.getNNZ()){
            return false;
        }
        else{
			for(int i=1; i<=size; i++) {
                if(this.matrixRow[i].length() != m.matrixRow[i].length()){
                    return false;
                }
                if(!(matrixRow[i].equals(m.matrixRow[i]))){
                    return false;
                }
			}
			return true;
        }
    }

    // Manipulation procedures
    void makeZero(){
        for(int i=1; i<=getSize(); i++){
            matrixRow[i].clear();
        }
        nnz = 0;
    }

    Matrix copy(){
        Matrix matrixCopy = new Matrix(size);

        for(int i=1; i<=getSize(); i++){
            if(matrixRow[i].length() > 0){
                matrixRow[i].moveFront();
                while(matrixRow[i].index() != -1){
                    Entry entry = (Entry) matrixRow[i].get();
                    matrixCopy.changeEntry(i, entry.column, entry.value);
                    matrixRow[i].moveNext();
                }
            }
        }
        return matrixCopy;
    }

    void changeEntry(int i, int j, double x){
        if(i<1 || getSize()<i || j<1 || getSize()<j){
            throw new RuntimeException("Matrix Error: changeEntry() called on invalid parameters");
        }
        Entry entryChange = new Entry(j, x);

        matrixRow[i].moveFront();
        if(x == 0){
            if(matrixRow[i].length() > 0){
                while(matrixRow[i].index() != -1){
                    if(((Entry) matrixRow[i].get()).column == j){
                        matrixRow[i].delete();
                        nnz--;
                        break;
                    }
                    else if(((Entry) matrixRow[i].get()).column < j){
                        matrixRow[i].moveNext();
                    }
                    else{
                        break;
                    }
                }
            }
        }
        else{
            if(matrixRow[i].index() == -1){
                matrixRow[i].append(entryChange);
                nnz++;
            }
            else{
                if(((Entry) matrixRow[i].back()).column < j){
                    matrixRow[i].append(entryChange);
                    nnz++;
                }
                else if(((Entry) matrixRow[i].front()).column > j){
                    matrixRow[i].prepend(entryChange);
                    nnz++;
                }
                else{
                    while(matrixRow[i].index() != -1){
        				if(((Entry) matrixRow[i].get()).column == j){
        					((Entry) matrixRow[i].get()).value = x;
                            break;
        				}
        				if(((Entry) matrixRow[i].get()).column > j){
        					matrixRow[i].insertBefore(entryChange);
        					nnz++;
                            break;
        				}
                        matrixRow[i].moveNext();
        			}
                }
            }
        }
    }

    Matrix scalarMult(double x){
        Matrix mult = new Matrix(size);

        for (int i=1; i<=getSize(); i++){
            if(matrixRow[i].length() != 0){
                matrixRow[i].moveFront();
                while(matrixRow[i].index() != -1){
                    Entry entry = (Entry) matrixRow[i].get();
                    double newValue = entry.value*x;
                    mult.changeEntry(i, entry.column, newValue);
                    matrixRow[i].moveNext();
                }
            }
        }
        return mult;
    }

    Matrix add(Matrix M){
        if(getSize() != M.getSize()){
            throw new RuntimeException("Matrix Error: add() called on unequal matrices");
        }
        Matrix addMatrix = this.copy();
        if(this.equals(M)){
            return this.scalarMult(2.0);
        }

        for(int i=1; i<=getSize(); i++){
            addMatrix.matrixRow[i].moveFront();
            M.matrixRow[i].moveFront();
            while(M.matrixRow[i].index() != -1){
                 if(addMatrix.matrixRow[i].index() != -1){
                    Entry addEntry = ((Entry) addMatrix.matrixRow[i].get());
                    Entry mEntry = ((Entry) M.matrixRow[i].get());
                    if(addEntry.column == mEntry.column){
                        double newValue = addEntry.value+mEntry.value;
                        addMatrix.changeEntry(i, addEntry.column, newValue);
                        addMatrix.matrixRow[i].moveNext();
                        M.matrixRow[i].moveNext();
                    }
                    else if(addEntry.column < mEntry.column){
                        addMatrix.matrixRow[i].moveNext();
                    }
                    else if(addEntry.column > mEntry.column){
                        Entry newEntry = new Entry(mEntry.column, 0+mEntry.value);
                        addMatrix.matrixRow[i].insertBefore(newEntry);
                        addMatrix.nnz++;
                        M.matrixRow[i].moveNext();
                    }
                }
                else{
                    Entry mEntry = ((Entry) M.matrixRow[i].get());
                    Entry newEntry = new Entry(mEntry.column, 0+mEntry.value);
                    addMatrix.matrixRow[i].append(newEntry);
                    addMatrix.nnz++;
                    if(addMatrix.matrixRow[i].length() == 1){
                        addMatrix.matrixRow[i].moveFront();
                        addMatrix.matrixRow[i].moveNext();
                    }
                    else{
                        addMatrix.matrixRow[i].moveNext();
                        addMatrix.matrixRow[i].moveNext();
                    }
                    M.matrixRow[i].moveNext();
                }
            }
        }
        return addMatrix;
    }

    Matrix sub(Matrix M){
        if(getSize() != M.getSize()){
            throw new RuntimeException("Matrix Error: sub() called on unequal matrices");
        }
        Matrix newMatrix = new Matrix(size);
        Matrix subMatrix = this.copy();
        if(this.equals(M)){
            return newMatrix;
        }

        for(int i=1; i<=getSize(); i++){
            subMatrix.matrixRow[i].moveFront();
            M.matrixRow[i].moveFront();
            while(M.matrixRow[i].index() != -1){
                 if(subMatrix.matrixRow[i].index() != -1){
                     Entry subEntry = ((Entry) subMatrix.matrixRow[i].get());
                     Entry mEntry = ((Entry) M.matrixRow[i].get());
                     if(subEntry.column == mEntry.column){
                        double newValue = subEntry.value-mEntry.value;
                        subMatrix.changeEntry(i, subEntry.column, newValue);
                        if(newValue == 0){
                            subMatrix.matrixRow[i].moveFront();
                        }
                        else{
                            subMatrix.matrixRow[i].moveNext();
                        }
                        M.matrixRow[i].moveNext();
                    }
                    else if(subEntry.column < mEntry.column){
                        subMatrix.matrixRow[i].moveNext();
                    }
                    else if(subEntry.column > mEntry.column){
                        Entry newEntry = new Entry(mEntry.column, 0-mEntry.value);
                        subMatrix.matrixRow[i].insertBefore(newEntry);
                        subMatrix.nnz++;
                        M.matrixRow[i].moveNext();
                    }
                }
                else{
                    Entry mEntry = (Entry) M.matrixRow[i].get();
                    Entry newEntry = new Entry(mEntry.column, 0-mEntry.value);
                    subMatrix.matrixRow[i].append(newEntry);
                    subMatrix.nnz++;
                    if(subMatrix.matrixRow[i].length() == 1){
                        subMatrix.matrixRow[i].moveFront();
                        subMatrix.matrixRow[i].moveNext();
                    }
                    else{
                        subMatrix.matrixRow[i].moveNext();
                        subMatrix.matrixRow[i].moveNext();
                    }
                    M.matrixRow[i].moveNext();
                }
            }
        }
        return subMatrix;
    }

    Matrix transpose(){
        Matrix t = new Matrix(size);

		for(int i=1; i<=getSize(); i++) {
			matrixRow[i].moveFront();
			while(matrixRow[i].index() != -1) {
				Entry entry = (Entry)matrixRow[i].get();
				t.changeEntry(entry.column, i, entry.value);
				matrixRow[i].moveNext();
			}
		}
	    return t;
    }

    Matrix mult(Matrix M){
        if(getSize() != M.getSize()){
            throw new RuntimeException("Matrix Error: mult() called on unequal matrices");
        }
        Matrix multMatrix = new Matrix(size);
        Matrix mMatrix = M.transpose();

        for(int i=1; i<=getSize(); i++){
            if(this.matrixRow[i].length() > 0){
                for(int j=1; j<=getSize(); j++){
                    if(mMatrix.matrixRow[j].length() > 0){
                        double dotProduct = this.dot(this.matrixRow[i], mMatrix.matrixRow[j]);
                        multMatrix.changeEntry(i, j, dotProduct);
                    }
                }
            }
        }
        return multMatrix;
    }

    // Other functions
    public String toString(){
        String s = "";

        for(int i=1; i<=getSize(); i++){
            if(matrixRow[i].length() > 0){
                s += (i + ": " + matrixRow[i] + "\n");
            }
        }
        return s;
    }

    // Helper function
    private static double dot(List P, List Q) {
        double dotProduct = 0;

        if(P.length() != 0 && Q.length() != 0){
            P.moveFront();
            Q.moveFront();
            while(P.index() != -1 && Q.index() != -1) {
                Entry pEntry = (Entry)P.get();
                Entry qEntry = (Entry)Q.get();
                if(pEntry.column < qEntry.column) {
                    P.moveNext();
                }
                else if (pEntry.column > qEntry.column) {
                    Q.moveNext();
                }
                else{
                    dotProduct += (pEntry.value * qEntry.value);
                    P.moveNext();
                    Q.moveNext();
                    break;
                }
            }
        }
        return dotProduct;
    }
}
