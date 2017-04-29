/**
 * Created by Emina on 2/4/2017.
 */
public class Tree {

    Node root;//root values


    public Tree(){
        root = null;
    }

    public void addNode(String name,int ID,int category,int score){
        // Create a new Node and initialize it
        Node newNode = new Node( name,ID, category, score);
        // If there is no root this becomes root
        if (root == null) {
            root = newNode;
        } else {
            // Set root as the Node we will start
            // with as we traverse the tree
            Node focusNode = root;
            // Future parent for our new Node
            Node parent;
            while (true) {
                // root is the top parent so we start
                // there
                parent = focusNode;
                // Check if the new node should go on
                // the left side of the parent node
                if (category < focusNode.category) {
                    // Switch focus to the left child
                    focusNode = focusNode.leftChild;
                    // If the left child has no children
                    if (focusNode == null) {
                        // then place the new node on the left of it
                        parent.leftChild = newNode;
                        return; // All Done
                    }
                } else { // If we get here put the node on the right
                    focusNode = focusNode.rightChild;
                    // If the right child has no children
                    if (focusNode == null) {
                        // then place the new node on the right of it
                        parent.rightChild = newNode;
                        return; // All Done
                    }
                }
            }
        }
    }

    public int sum(int ID,int category){
        int result=0;

        if(root!=null)
            if(category<7)
                result=root.sumUpper(root,ID,category);
            else
                result=root.sumLower(root,ID,category);

        return result;

    }

    public void inorder() {
        if (root != null)
            root.inorder();
    }




    public Node findID(int ID){

        Node focusNode = root;

        while (focusNode.ID != ID) {

            if (ID < focusNode.ID) {

                focusNode = focusNode.leftChild;

            } else {

                focusNode = focusNode.rightChild;
            }

            if (focusNode == null)
                return null;

        }
        return focusNode;

    }


    public Node findCategory(int category) {
        Node focusNode = root;

        while (focusNode.category != category) {

            if (category < focusNode.category) {

                focusNode = focusNode.leftChild;

            } else {

                focusNode = focusNode.rightChild;
            }

            if (focusNode == null)
                return null;

        }

        return focusNode;

    }

    public Node findScore(int score) {
        Node focusNode = root;

        while (focusNode.category != score) {

            if (score < focusNode.category) {

                focusNode = focusNode.leftChild;

            } else {

                focusNode = focusNode.rightChild;
            }

            if (focusNode == null)
                return null;

        }

        return focusNode;

    }

    public int maxElem(Node focusnode) {
        int max = focusnode.score;
        if(focusnode.leftChild != null) {
            max = Math.max(max, maxElem(focusnode.leftChild));
        }
        if(focusnode.rightChild != null) {
            max = Math.max(max, maxElem(focusnode.rightChild));
        }
        return max;
    }

}
