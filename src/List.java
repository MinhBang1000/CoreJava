public class List {
    private int size;
    private int[] data;
    public List(){
        this.size = 0;
        this.data = new int[50];
    }

    public void makeNullList(){
        this.size = 0;
    }

    public void pushList(int x){
        this.data[this.size] = x;
        this.size ++;
    }

    public int elementAt(int index){
        return this.data[index-1];
    }

    public boolean emptyList(){
        return this.size==0;
    }

    public int getSize(){
        return this.size;
    }

    public void printList(){
        if (this.emptyList()){
            System.out.println("This list is empty !!!");
        }else{
            System.out.println("This list have elements as: ");
            for (int i=1;i<=this.size;i++){
                System.out.println(""+this.elementAt(i));
            }
        }
    }
}
