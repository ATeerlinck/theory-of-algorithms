
public class Main {
    public static void main(String[] args){
        int[] A = new int[]{-8, 11, 10, -80, 12, -40, 16, -4};
        QuickSort(A, 0, A.length-1, 2);
        System.out.println("Question 2: Primitive partition last number pivot");
        System.out.println(A);
        A = new int[]{-400, -1100, -450, -500, -620, -800, -640, -400, -8, 11, 10, -80, 12, -40, 16, -4};
        QuickSort(A, 0, A.length-1, 1);
        System.out.println("Question 3: Primitive partition first number pivot");
        System.out.println(A);
    }
    
    public static int GeneratePivotFirst(int A[], int left, int right){
        return left;
    }
    public static int GeneratePivotLast(int A[], int left, int right){
        return right;
    }
    public static int GeneratePivotMid(int A[], int left, int right){
        int mid = (left+right)/2;
        return mid;
    }
    public static int GeneratePivotRand(int A[], int left, int right){
        int range = (left+right)/2;
        return (int) (Math.random()*range) + left;
    }

    public static int GeneratePivot(int A[], int left, int right, int type){
        switch (type) {
            case 1: return GeneratePivotFirst(A, left, right);
            case 2: return GeneratePivotLast(A, left, right);
            case 3: return GeneratePivotMid(A, left, right);
            case 4: return GeneratePivotRand(A, left, right);
            default: return GeneratePivotFirst(A, left, right);
        }
    }
    
    public static int PrimitivePartition(int A[], int left, int right, int pivotIndex){
        int pivot = A[pivotIndex], partitionIndex = left-1;
        for (int k = left; k<right; k++){
            if(A[k] <= pivot) partitionIndex++;
        }
        int temp = A[pivotIndex];
        A[pivotIndex] = A[partitionIndex];
        A[partitionIndex] = temp;
        int i = left, j = right;
        while(i<j){
            if (A[i] <= pivot) i++;
            else if (A[j] > pivot) j--;
            else{
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; j++;
            }
        }
        return partitionIndex;
    }


    public static void QuickSort(int A[], int left, int right, int type){
        if (left < right){
            int pivotIndex = GeneratePivot(A,left, right, type);
            int partitionIndex = PrimitivePartition(A, left, right, pivotIndex);
            QuickSort(A, left, partitionIndex-1, type);
            QuickSort(A, partitionIndex+1, right, type);
        }
    }

    public static int QuickSelect(int A[], int left, int right, int k, int type){
        if (left == right){
            return left;
        }
        int pivotIndex = GeneratePivot(A, left, right, type);
        int partitionIndex = PrimitivePartition(A, left, right, pivotIndex);
        if (k == partitionIndex+1) return partitionIndex;
        else if (k<partitionIndex+1) return QuickSelect(A, left, partitionIndex-1, k, type);
        else return QuickSelect(A, partitionIndex+1, right, k, type);
    }

    public static int QuickSelectMoM(int A[], int left, int right, int k){
        if ((right - left + 1)<=5){
            InsertionSort(A);
            return k-1;
        }
        int n = (right-left+1);
        int[] medians = new int[n/5];
        for(int i = left,r = 0; i <= right; i+=5){
            int j = Math.min(i+4, right);
            int[] section = new int[]{A[i],A[i+1],A[i+2],A[i+3],A[i+4]};
            InsertionSort(section);
            medians[r++] = section[(i+j)/2];
        }
        int mom = medians[QuickSelectMoM(medians, 0, (int)Math.ceil(n/5)-1,(int) Math.ceil(n/5)/2)];
        int momIndex = 0;
        for (int i = left; i<=right; i+=5){
            int j = Math.min(i+4, right);
            momIndex = (i+j)/2;
            if (A[momIndex] == mom) break;
        }
        int partitionIndex = PrimitivePartition(A, left, right, momIndex);
        if (k == partitionIndex+1) return partitionIndex;
        else if (k<partitionIndex+1) return QuickSelectMoM(A, left, partitionIndex-1, k);
        else return QuickSelectMoM(A, partitionIndex+1, right, k);
    }
    
    public static void InsertionSort(int A[]){
        
    }

    public static int OnePassPartition(int A[], int left, int right, int pivotIndex){
        int temp = A[pivotIndex];
        A[pivotIndex] = A[right];
        A[right] = temp;
        int i = left, j = right-1, pivot = A[right];
        while(i<=j){
            if(A[i] <= pivot)i++;
            else if(A[j]> pivot)j--;
            else{
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; j++;
            }
        }
        int partitionIndex = i;
        temp = A[partitionIndex];
        A[partitionIndex] = A[right];
        A[right] = temp;
        return partitionIndex;
    }

    public static int[] TwoIndexPartition(int A[], int left, int right, int pivotIndex){
        int temp = A[pivotIndex];
        A[pivotIndex] = A[right];
        A[right] = temp;
        int i = left, j = right-1, pivot = A[right];
        while(i<=j){
            if(A[i] <= pivot)i++;
            else if(A[j]> pivot)j--;
            else{
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; j++;
            }
        }
        int partitionIndex = i;
        temp = A[partitionIndex];
        A[partitionIndex] = A[right];
        A[right] = temp;
        i = left;
        while(i<=j){
            if(A[i] < pivot)i++;
            else if(A[j] == pivot)j--;
            else{
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; j++;
            }
        }
        int lowerPartitionIndex = i, upperPartitionIndex = partitionIndex;
        return new int[] {lowerPartitionIndex, upperPartitionIndex};
    }
    public static int[] OnePassTwoIndexPartition(int A[], int left, int right, int pivotIndex){
        int i = left, lowerPartitionIndex = left, upperPartitionIndex = right;
        int pivot = A[pivotIndex];
        while(i<=upperPartitionIndex){
            if(A[i]<pivot){
                int temp = A[lowerPartitionIndex];
                A[lowerPartitionIndex] = A[i];
                A[i] = temp;
                i++;
                lowerPartitionIndex++;
            }
            else if(A[i]>pivot){
                int temp = A[upperPartitionIndex];
                A[upperPartitionIndex] = A[i];
                A[i] = temp;
                upperPartitionIndex--;
            }
            else i++;
        }
        return new int[] {lowerPartitionIndex, upperPartitionIndex};
    }
    public static void TwoIndexQuickSort(int A[], int left, int right){
        if (left < right){
            int pivotIndex = GeneratePivotMid(A,left, right);
            int[] partitionIndexes = OnePassTwoIndexPartition(A, left, right, pivotIndex);
            TwoIndexQuickSort(A, left, partitionIndexes[0]-1);
            TwoIndexQuickSort(A, partitionIndexes[1]+1, right);
        }
    }
    public static int TwoIndexQuickSelect(int A[], int left, int right, int k){
        if (left == right){
            return left;
        }
        int pivotIndex = GeneratePivotMid(A, left, right);
        int[] partitionIndexes = OnePassTwoIndexPartition(A, left, right, pivotIndex);
        if (k >= partitionIndexes[0]+1 && k<=partitionIndexes[1]+1) return partitionIndexes[0];
        else if (k<partitionIndexes[0]+1) return TwoIndexQuickSelect(A, left, partitionIndexes[0]-1, k);
        else return TwoIndexQuickSelect(A, partitionIndexes[1]+1, right, k);
    }
}
