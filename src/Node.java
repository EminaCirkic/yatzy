/**
 * Created by Emina on 2/4/2017.
 */
public class Node {
    String name;
    int ID;
    int category;
    int score;
    int result;

    Node leftChild;
    Node rightChild;


    Node(String name,int ID,int category,int score){

        this.ID=ID;
        this.name=name;
        this.category=category;
        this.score=score;
    }


    public void inorder() {
        if (leftChild != null)
            leftChild.inorder();
        System.out.println(name+"- Category "+category+" : "+score+" points   ");
        if (rightChild != null) {
            rightChild.inorder();
        }
    }

    public int  sumUpper(Node thisNode, int ID, int category ) {
        if (thisNode == null)
            return 0;
        else if(thisNode.category<=category){
                    if(thisNode.ID!=ID)
                        result= 0+ sumUpper(thisNode.leftChild,ID,category) + sumUpper(thisNode.rightChild,ID,category);
                    else
                        result =thisNode.score+ sumUpper(thisNode.leftChild,ID,category) + sumUpper(thisNode.rightChild,ID,category);}
        else result= 0+ sumUpper(thisNode.leftChild,ID,category) + sumUpper(thisNode.rightChild,ID,category);

        return result;
    }

    public int  sumLower(Node thisNode, int ID, int category ) {
        if (thisNode == null)
            return 0;
        else if(thisNode.category>=category){
            if(thisNode.ID!=ID)
                result= 0+ sumLower(thisNode.leftChild,ID,category) + sumLower(thisNode.rightChild,ID,category);
            else
                result =thisNode.score+ sumLower(thisNode.leftChild,ID,category) + sumLower(thisNode.rightChild,ID,category);}
        else result= 0+ sumLower(thisNode.leftChild,ID,category) + sumLower(thisNode.rightChild,ID,category);

        return result;
    }

    //prints info to screen
    public  String toString(){
        return name+"- Category "+category+" : "+score+" points   ";
    }

}
