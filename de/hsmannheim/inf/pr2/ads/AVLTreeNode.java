package de.hsmannheim.inf.pr2.ads;


import com.sun.jdi.IntegerType;
import org.jetbrains.annotations.NotNull;

public class AVLTreeNode<E extends Comparable<E>> extends TreeNode<E> {

    protected SearchTreeNode<E> root;

    public AVLTreeNode(E v) {
        super(v);
    }

    public AVLTreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
        super(value, left, right);
    }

    public boolean isSearchTree(TreeNode<E> root, E min, E max){
        if(root == null)
            return true;

        //wenn
        if(root.value.compareTo(min) < 0 || root.value.compareTo(max) > 0)
            return false;

        return isSearchTree(root.left, min, root.value) && isSearchTree(root.right, max, root.value);
    }

    public E maxValue(){
        E maxValue = root.value;
        var node = root;
        //Iteriert rechts durch
        while(node.right != null){
            maxValue = node.right.value;
            node = (SearchTreeNode<E>) node.right;
        }
        return maxValue;
    }

    public E minValue() {

        E minValue = root.value;
        var node = root;

        //iteriert links durch
        while (node.left != null) {

            minValue = node.left.value;
            node = (SearchTreeNode<E>) node.left;
        }
        return minValue;
    }

    public void isSearch(){
        System.out.println(isSearchTree(root,minValue(),maxValue()));
    }


    public E min(TreeNode<E> root , E min){
        if(root == null)
            return min;

        if(root.left != null){
            return min(root.left, root.value);
        }
        return min;
    }

    public E max(TreeNode<E> root, E max){
        if(root == null)
            return max;

        if(root.right != null){
            return max(root.right, root.value);
        }
        return max;
    }

    public static void main(String[] args) {
        var s = new AVLTreeNode<Integer>(10,
                    new AVLTreeNode<Integer>(-10,
                            new AVLTreeNode<Integer>(-20),
                            new AVLTreeNode<Integer>(0)),
                    new AVLTreeNode<Integer>(19,
                            new AVLTreeNode<Integer>(17),
                            null)
                    );

        s.isSearch();
        var tree = new AVLTreeNode<Integer>(10,
                new AVLTreeNode<Integer>(-10,
                        new AVLTreeNode<Integer>(-20),
                        new AVLTreeNode<Integer>(4,
                                new AVLTreeNode<Integer>(5),

                                new AVLTreeNode<Integer>(6))),
                new AVLTreeNode<Integer> (7));

        tree.isSearch();
        //System.out.println(s.isSearchTree(s.root,s.minValue(),s.maxValue()));
    }
}
