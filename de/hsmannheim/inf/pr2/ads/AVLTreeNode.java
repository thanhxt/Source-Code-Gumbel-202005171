package de.hsmannheim.inf.pr2.ads;




public class AVLTreeNode<E extends Comparable<E>> extends TreeNode<E> {


    protected AVLTreeNode<E> left;
    protected AVLTreeNode<E> right;

    public AVLTreeNode(E v) {
        super(v);
    }

    public AVLTreeNode(E value, AVLTreeNode<E> left, AVLTreeNode<E> right) {
        super(value, left, right);
        this.left = left;
        this.right = right;
    }

    private AVLTreeNode<E> rotateRight(){
        var temp = this.left;
        this.left = temp.right;
        temp.right = this;
        return temp;
    }

    private AVLTreeNode<E> rotateLeft(){
        var temp = this.right;
        this.right = temp.left;
        temp.left = this;
        return temp;
    }


    private AVLTreeNode<E> rotateDoubleRight(){
        this.right = this.right.rotateRight();
        return this.rotateRight();
    }

    private AVLTreeNode<E> rotateDoubleLeft(){
        this.left = this.left.rotateLeft();
        return this.rotateLeft();
    }


    /**
     * nicht fertige insert methode
     * erstes Element funktioniert, ab zweitem NullPointerException
     * da TreeNode<E> left und right anstatt AVLTreeNode<E>
     * @param v
     * @return
     */
    private AVLTreeNode<E> insert(E v){

        if(this.value == null)
            this.value = v;

        if(v.compareTo(this.value) < 0){
            this.left = this.left.insert(v);
            if ((this.left.height() - this.right.height() == 2)) {
                if(v.compareTo(this.left.value) < 0){
                    return this.rotateRight();
                } else
                    return this.rotateDoubleRight();
            }
        } else if(v.compareTo(this.value) > 0){
            this.right = this.right.insert(v);
            if((this.right.height() - this.left.height()) == 2){
                if(v.compareTo(this.value) > 0){
                    return this.rotateLeft();
                } else
                    return this.rotateDoubleLeft();
            }
        }
        return this;
    }


    /*public boolean isSearchTree() {
       return isSearchTree(this,minValue(),maxValue());
    }

    public boolean isSearchTree(AVLTreeNode<E> node, E min, E max){
        var result = true;
        if(min!=null)
            result = result && (node.value.compareTo(min)>0);

        if(max!=null)
            result = result && (node.value.compareTo(max)<0);


        if(node == null)
            return true;


        if(this.value.compareTo(min) > 0 || this.value.compareTo(max) < 0)
            return false;

        return isSearchTree(this.left,min, node.value) && isSearchTree(node.right,node.value, max);
    }


    public E maxValue(){
        if(this.getValue()== null)
            return null;
        E maxValue = this.getValue();
        TreeNode<E> node = this;
        //Iteriert rechts durch
        while(node.right != null){
            maxValue = node.right.getValue();
            node = node.right;
        }
        return maxValue;
    }

    public E minValue() {
        if (this.getValue() == null)
                return null;
        E minValue = this.getValue();
        TreeNode<E> node = this;
        //iteriert links durch
        while (node.left != null) {
            minValue = node.left.getValue();
            node = node.left;
        }
        return minValue;
    }



     */

    public boolean isSearchTree(){

        return isSearchTree(null,null);
    }

    /**
     * BST bedingung: Linker Knoten > Value < Rechter Knoten
     * @param min untere Schranke
     * @param max obere Schranke
     * @return true, if Wert an jeder Stelle die BST Bedingung erfüllt
     */
    public boolean isSearchTree(E min, E max){
        var result = true;

        //wenn value value > min
        if(min != null)
            result = (this.value.compareTo(min) > 0) && result;
        //wenn value < max
        if(max != null)
            result = (this.value.compareTo(max) < 0) && result;
        //linker Knoten runter
        if(this.left != null)
            result = result && (this.left.isSearchTree(min,this.value));
        //rechter Knoten runter
        if(this.right != null)
            result = result && (this.right.isSearchTree(this.value,max));
        return result;
    }


    /**
     * (1)check for BST
     * (2)check for balance
     * @return
     */
    public boolean isAVLTree(){
        if(isSearchTree()){
            if(balance() <= 1 || balance() > -1){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {

        /**
         * Da insert Methode nicht fertig gemacht wurde,
         * wird der Baum Manuell erstellt
         */
        var s = new AVLTreeNode<Integer>(10,
                    new AVLTreeNode<Integer>(-10,
                            new AVLTreeNode<Integer>(-20),
                            new AVLTreeNode<Integer>(0)),
                    new AVLTreeNode<Integer>(19,
                            new AVLTreeNode<Integer>(17),
                            null)
                    );

        System.out.println(s.isSearchTree());
        System.out.println(s.isAVLTree());
        System.out.println(s.balance());
        s.printPreorder();
        System.out.println("LevelOrder");
        s.printLevelorder();

        var tree = new AVLTreeNode<Integer>(1,
                        new AVLTreeNode<Integer>(3,
                                new AVLTreeNode<Integer>(5),
                                new AVLTreeNode<Integer>(4)),
                         new AVLTreeNode<Integer>(4));

        System.out.println(tree.isSearchTree());
        var d = new AVLTreeNode<Integer>(null,null,null);
        d.insert(10);



        //System.out.println(s.isSearchTree(s.root,s.minValue(),s.maxValue()));
    }
}
