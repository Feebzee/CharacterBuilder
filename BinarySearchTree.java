import java.util.*;

class Node implements Comparable<Node>{
    Species val;
    Node left;
    Node right;
    public Node(Species val){
        this.val = val;
        left = null;
        right = null;
    }
    @Override
    public int compareTo(Node o) {
        //compares by the average power range of the species
        int pt = Power.getAvgSpeciesPower(this.val);
        int po = Power.getAvgSpeciesPower(o.val);
        return Integer.compare(pt,po);
    }
}
public class BinarySearchTree implements Comparable<Node>,Iterable<String>{
    private Node root;
    private int size;
    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }
    public BinarySearchTree(Species[] specs){
        for(Species s : specs){
            add(s);
            size++;
        }
    }
    public Node getRoot() {
        return root;
    }
    public int getSize(){
        return size;
    }

    public boolean add(Species val){
        if(root == null) {
            root.val = val;
        }else{
            Node parent = null;
            Node current = root;
            while(current != null) {
                if(val.compareTo(current.val) < 0){
                    parent = current;
                    current = current.left;
                }else if(val.compareTo(current.val) > 0){
                    parent = current;
                    current = current.right;
                }else{
                    return false;
                }
            }
            if(val.compareTo(parent.val) < 0){
                parent.left = new Node(val);
            }else{
                parent.right = new Node(val);
            }
            size++;
            return true;
        }
        return false;
    }
    public boolean search(Species val) {
        Node current = root;
        while(current != null) {
            if(val.compareTo(current.val) < 0){
                current = current.left;
            }else if(val.compareTo(current.val) > 0){
                current = current.right;
            }else{
                return true;
            }
        }
        return false;
    }
    public ArrayList<Node> path(Species val){
        ArrayList<Node> p = new ArrayList<>();
        Node current = root;
        while(current != null){
            p.add(current);
            if(val.compareTo(current.val) < 0){
                current = current.left;
            }else if(val.compareTo(current.val) > 0){
                current = current.right;
            }else{
                break;
            }
        }
        return p;
    }
    public boolean delete(Species val){
        Node parent = null;
        Node current = root;
        while(current != null){
            if(val.compareTo(current.val) < 0){
                parent = current;
                current = current.left;
            }else if(val.compareTo(current.val) > 0){
                parent = current;
                current = current.right;
            }else{
                break;
            }
        }
        if(current == null){
            return false;
        }
        if(current.left == null){
            if(parent == null){
                root = current.right;
            }else{
                if(val.compareTo(parent.val) == 0){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }
        }else{
            Node parentRM = current;
            Node rightMost = current.left;
            while(rightMost.right != null){
                parentRM = rightMost;
                rightMost = rightMost.right;
            }
            current.val = rightMost.val;
            if(parentRM.right == rightMost){
                parentRM.right = rightMost.left;
            }else{
                parentRM.left = rightMost.left;
            }
        }
        size--;
        return true;
    }
    public void clear(){
        root = null;
        size = 0;
    }

    //traversals were done non-recursively, because otherwise, it
    //created too many issues for me
    public String inorder(){
        Node current = root;
        String order = "";
        Stack<Node> s = new Stack<>();
        while(current != null || s.size() > 0){
            while(current != null){
                s.push(current);
                current = current.left;
            }
            current = s.pop();
            order += current.val.getSpeciesName() + " ";
            current = current.right;
        }
        return order;
    }
    public String preorder(){
        Node current = root;
        String order = "";
        Stack<Node> s = new Stack<>();
        while(current != null || s.size() > 0){
            while(current != null){
                order += current.val + " ";
                s.push(current);
                current = current.left;
            }
            current = s.pop();
            current = current.right;
        }
        return order;
    }
    public String postorder(){
        Node current = root;
        String order = "";
        Stack<Node> s1 = new Stack<>();
        s1.push(current);
        Stack<Node> s2 = new Stack<>();
        while(!s1.isEmpty()){
            current = s1.pop();
            s2.push(current);
            if(current.left != null){
                s1.push(current.left);
            }
            if(current.right != null){
                s1.push(current.right);
            }
        }
        while(!s2.isEmpty()){
            order += s2.pop().val + " ";
        }
        return order;
    }


    @Override
    public int compareTo(Node o) {
        Species thisChar = this.root.val;
        return thisChar.compareTo(o.val);
    }
    @Override
    public Iterator<String> iterator(){
        //iterates the inorder transversal of the BST
        List<String> i = Arrays.asList(inorder().split(" "));
        return i.iterator();
    }
}