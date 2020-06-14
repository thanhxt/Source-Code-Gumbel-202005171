package de.hsmannheim.inf.pr2.ads;

import java.util.Comparator;

public class AVLTree<E extends Comparable<E>> extends TreeNode<E> {
    public AVLTree(E v) {
        super(v);
    }

    public AVLTree(E value, TreeNode<E> left, TreeNode<E> right) {
        super(value, left, right);
    }

    AVLTree<E> left = null;
    AVLTree<E> right = null;
    AVLTree<E> root;


    private AVLTree<E> rotateRight(){
        var temp = this.root.left;
        this.root.left = temp.root.right;
        temp.root.right = this;
        return temp;
    }

    private AVLTree<E> rotateLeft(){
        var temp = this.root.right;
        this.root.right = temp.root.left;
        temp.root.left = this;
        return temp;
    }


    private AVLTree<E> rotateDoubleRight(){
        this.root.right = this.root.right.rotateRight();
        return this.rotateRight();
    }

    private AVLTree<E> rotateDoubleLeft(){
        this.root.left = this.root.left.rotateLeft();
        return this.rotateLeft();
    }
    public AVLTree<E> insert(E v){
        if(this.root == null){
            return this.root = new AVLTree<E>(v,null,null);

        }
        var parent = root;
        var node = root;
        while(node!=null){
            parent = node;
            if(v.compareTo(node.getValue()) < 0){
                node = node.left;
            } else if(v.compareTo(node.getValue()) > 0){
                node = node.right;
            }
        }

        if(v.compareTo(parent.getValue()) < 0){
            parent.left = new AVLTree<E>(v, null, null);
        } else{
            parent.right = new AVLTree<E>(v, null,null);
        }

        if(v.compareTo(this.root.value) < 0){
            this.root.left = this.root.left.insert(v);
            if ((this.root.left.height() - this.root.right.height() == 2)) {
                if(v.compareTo(this.root.left.value) < 0){
                    return this.rotateRight();
                } else
                    return this.rotateDoubleRight();
            }
        } else if(v.compareTo(this.root.value) > 0){
            this.root.right = this.root.right.insert(v);
            if((this.root.right.height() - this.root.left.height()) == 2){
                if(v.compareTo(this.root.value) > 0){
                    return this.rotateLeft();
                } else
                    return this.rotateDoubleLeft();
            }
        }
        return this.root;

    }

    public static void main(String[] args) {
        var s = new AVLTree<Integer>(null,null,null);

        s.insert(6);
        s.insert(5);
        System.out.println(s.height());

       // s.insert(4);
      //  s.insert(2);
    }

}
