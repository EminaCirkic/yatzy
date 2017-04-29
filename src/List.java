/**
 * Created by Emina on 2/4/2017.
 */
public class List {

    private Link first;


    public List(){
        first=null;
    }


    public void addSort(int data){
        if(isEmpty() || first.data > data)
            addFirst(data);
        else{
            Link help =first;
            while (help.next != null && help.next.data < data)
                help = help.next;
            help.next = new Link(data,help.next);
        }

    }


    public void addFirst(int data){
        first=new Link(data, first);
    }


    public boolean isEmpty(){
        return first==null;
    }


    public boolean find(int number){
        Link current=first;
        boolean count=false;
        while (current != null)
        {
            if (current.data == number){
                count=true;
                break;
            }
            current = current.next;
        }
        return count;
    }


    public int counting(int number)
    {
        Link current=first;
        int count = 0;
        while (current != null)
        {
            if (current.data == number)
                count++;
            current = current.next;
        }
        return count;
    }


    public int sumUp(int number)
    {
        Link current=first;
        int count = 0;
        while (current != null)
        {
            if (current.data == number)
                count+=current.data;
            current = current.next;
        }
        return count;
    }


    public int sumRec(){
        if(isEmpty())
            return 0;
        return first.sumRec();
    }


    public void removeFirst(){ if(! isEmpty()){ first = first.next; }}


    public void removeAll(){while (!isEmpty()) {removeFirst();}}


    public void removeAt(int data) {
        if (this.first==null)
            return;
            if (this.first.data == data) {
                this.first = this.first.next;
                return;
            }
            Link help1 = first;
            Link help2 = first.next;
            while (help2 != null && help2.data != data) {
                help1 = help2;
                help2 = help2.next;
            }
            if( help2 != null)
                help1.next = help2.next;
    }


    public void print(){
        Link help = first; //hjälpreferensen till första linken
        while(help != null){// så länge det finns linkar kvar
            System.out.println(help.data);
            help = help.next;//flytta fram till nästa link
        }
    }


    // Returns the element at the specified index from the list.
    // Precondition: 0 <= index < size
    // Throws a NullPointerException if index >= size.
    public int get(int index) {
        Link current = goTo(index);
        return current.data;
    }


    // Returns a reference to the node object representing the index'th element
    // in the list.  Used as a helper by many of the public methods.
    private Link goTo(int index) {
        Link current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }


}
