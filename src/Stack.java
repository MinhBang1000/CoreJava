public class Stack {
    private int size;
    private int[] data;

    public Stack(){
        this.size = 0;
        this.data = new int[50];
    }

    public void makeNullStack(){
        this.size = 0;
    }

    public void pushStack(int x){
        this.data[this.size] = x;
        this.size++;
    }

    public void popStack(){
        this.size--;
    }

    public int topStack(){
        return this.data[this.size-1];
    }

    public boolean emptyStack(){
        return this.size==0;
    }

}
