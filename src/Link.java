/**
 * Created by Emina on 2/4/2017.
 */
public class Link {

    int data;
    Link next;
    int result;

    Link(int data, Link next){
        this.data=data;
        this.next=next;
    }


    public int sumRec() {
        if (this.next != null){  // stoppvillkor
            return this.data + next.sumRec();//beräkning i anrop samt förändring i anrop
        }
        else {// genom förflyttning till nästa link
            return this.data; // slut på rekursion returnera värdet
        }
    }

}
