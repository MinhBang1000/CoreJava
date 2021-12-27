public class Queue {
    private int[] data;
    private int front;
    private int rear;
    /**
     * Constructor will make null this queue instead of make null function
     */
    public Queue(){
        this.front = 0;
        this.rear = -1;
        this.data = new int[50];
    }

    public void makeNullQueue(){
        this.front = 0;
        this.rear = -1;
    }

    public void pushQueue(int x){
        this.rear++;
        this.data[this.rear] = x;
    }

    public void popQueue(){
        this.front++;
    }

    public int topQueue(){
        return this.data[this.front];
    }

    public boolean emptyQueue(){
        return this.front>this.rear;
    }

}
