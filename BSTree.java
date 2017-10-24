/*
A class that represent Binary Search Tree
@Gigi Xiaowan Guo
*/
class Node{
    public Node(Comparable v){
        value = v;
        left = null;
        right = null;
    }//new node has only the value without any children
    public Node(Comparable v, Node l, Node r){
        value = v;
        left = l;
        right = r;
    }
    public void insert(Comparable v){
        if(v.compareTo(value) < 0){
            if(this.left == null)
                this.left = new Node(v);
            else this.left.insert(v);
        }
        else{
            if(this.right == null)
                this.right = new Node(v);
            else this.right.insert(v);
        }
    }
    
    public void delete(Comparable v, Node parent){
        if(v.compareTo(this.value) < 0){
            if(left != null)left.delete(v,this);
            else return;//value not found
        }// if the value is smaller, delete it from left,
        //if the left is null, then the value is invalid.
        else if(v.compareTo(this.value) > 0){
            if(right != null)right.delete(v,this);
            else return;
        }//same as it in left.
        else{//this is the node to be deleted
            if(left == null && right == null){//the node has no child
                if(parent.left == this) parent.left = null;
                else parent.right = null;
                return;
            }//let this node to be delete 
            if(left != null && right == null){//the node has only left child
                if(parent.left == this)parent.left = left;
                else parent.right = left;
                return;
            }//let the left child replace this node.
            if(left == null && right != null){//the node has only right child
                if(parent.left == this)parent.left = right;
                else parent.right = right;
                return;
            }//let the right child replace this.
            if(left != null && right != null){//the node has two children.
                Comparable minV = right.getMinValue();
                right.delete(minV, this);
                this.value = minV;
                return;
            }//get the min value from right and set the value to the min. 
            //then delete the min value in right.
        }//end delete this node.    
    }//end delete
    private Comparable getMinValue(){//get the min value in this tree, 
    //which is the left->left->left....end;
        if(left == null)return value;
        else return left.getMinValue();
    }
    String inOrder(){
        String ans = "";
        if(left != null)ans += left.inOrder();
        ans += value + " ";
        if(right != null) ans += right.inOrder();
        return ans;
    }
    String preOrder(){
        String ans = "";
        ans += value + " ";
        if(left != null)ans += left.preOrder();
        if(right != null)ans += right.preOrder();
        return ans;
    }
    Comparable value;
    Node left;
    Node right;
}//end Node

public class BSTree{
    Node root;
    
    public BSTree(){
        root = null;
    }//nothing at first
    
    public BSTree(Comparable value){
        root = new Node(value);
    }//initial root with the value if given;
    
    void insert(Comparable v){//if tree is empty, let root be the new node;
        if(root == null)root = new Node(v);
        else root.insert(v);
    }
    
    boolean find(Comparable st){
        Node current = root;
        while(current != null){
            if(current.value == st)return true;
            if(st.compareTo(current.value) < 0)current = current.left;
            else current = current.right;
        }
        return false;
    }
    
    void delete(Comparable st){
        if(root == null)return;
        if(root.value.compareTo(st) == 0) {
            Node tempRoot = new Node(st);
            tempRoot.left = root;
            root.delete(st,tempRoot);
            root = tempRoot.left;
            return;
        }
        root.delete(st, null);
    }
    public String toStringInOrder(){
        if(root == null)return "";
        String str = root.inOrder();
        str = str.substring(0,str.length() - 1);
        return str;
    }
    public String toStringPreOrder(){
        if(root == null)return "";
        String str = root.preOrder();
        str = str.substring(0,str.length() - 1);
        return str;
    }
}
