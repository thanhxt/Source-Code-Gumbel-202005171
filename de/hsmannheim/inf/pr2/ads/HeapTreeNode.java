package de.hsmannheim.inf.pr2.ads;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class HeapTreeNode<E extends Comparable<E>> extends TreeNode<E> implements Iterable<E> {


    HeapTreeNode<E> left;
    HeapTreeNode<E> right;


    public HeapTreeNode(E v) {
        super(v);

    }

    public HeapTreeNode(E value, HeapTreeNode<E> left, HeapTreeNode<E> right) {
        super(value, left, right);
        this.left = left;
        this.right = right;

    }
/*
    public boolean add(E o) {
        //Fall 1 Blatt ist noch leer
        //Conclusion: ein neues Element(Blatt) als neue Wurzel
        if (this.root == null) {
            this.root = new HeapTreeNode<E>(o, null, null);
            return true;
        }

        //Fall 2,3 und 4, Baum ist nicht leer, man muss die richtige Stelle zum Einfügen finden
        var parent = root;
        var node = root;
        while (node != null) { //solange im Baum hinabsteigen, bis...
            parent = node;
            if (o.compareTo(node.getValue()) < 0) {
                // o muss in den linken Unterbaum
                node = (HeapTreeNode<E>) node.left;
                // o muss in den rechten Unterbaum
            } else if (o.compareTo(node.getValue()) > 0) {
                node = (HeapTreeNode<E>) node.right;
            } else { //o ist schon im Baum enthalten, abbrechen
                return false;
            }
        }
        //an dieser Stelle ist klar: o ist nicht im Baum,
        //node ist null und parent Ist der SearchTreeNode, unter dem o einsoriert werden muss
        if (o.compareTo(parent.getValue()) < 0) {
            parent.left = new HeapTreeNode<E>(o, null, null);
        } else {
            parent.right = new HeapTreeNode<E>(o, null, null);
        }
        return true;
    }

 */

    /**
     * minHeap Bedingung: linker Knoten < value > rechter Knoten
     * @return true, if value an jeder Stelle erfüllt
     */
    public boolean isMinHeapTree(){
        var result = true;

        //Wenn linker Knoten < Value
        if(this.left != null){
            result = (this.value.compareTo(this.left.value) <= 0) && result
                    && this.left.isMinHeapTree();
        }
        //Wenn rechter Knoten < Value
        if(this.right != null){
            result = (this.value.compareTo(this.right.value) <= 0) && result
                    && this.right.isMinHeapTree();
        }
        return result;
    }

    /**
     * maxHeap Bedingung: linker Knoten > Value < rechter Knoten
     * @return true, if value an jeder Stelle bedingung erfüllt
     */
    public boolean isMaxHeapTree(){
        var result = true;

        //Wenn rechter Knoten > Value
        if(this.right != null){
            result = (this.value.compareTo(this.right.value) >= 0) && result
                    && this.right.isMaxHeapTree();
        }
        //Wenn linker Knoten > Value
        if(this.left != null){
            result = (this.value.compareTo(this.left.value) >= 0) && result
                    && this.left.isMaxHeapTree();
        }

        return result;
    }

    /**
     * Methode zum füllen des Arrays mithilfe LevelOrdering
     * @param array zurückgegebene array
     * @param index of Array
     */
    public void fill(ArrayList<E> array, int index){
        array.set(index-1, this.value);

        // (i*2)
        if(this.left!=null)
            this.left.fill(array, index*2);
        // (i+1*2)
        if(this.right!=null)
            this.right.fill(array,(index*2+1));

    }

    public ArrayList<E> array(){

        var array = new ArrayList<E>();

        // Liste mit gewünschter Größe wird erstellt
        for(int i = 0; i<this.size();i++)
            array.add(null);

        this.fill(array,1);
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return this.array().iterator();
    }

    public static void main(String[] args) {
        /**         1
         *      2     4
         *   5    6
         */
        var s = new HeapTreeNode<Integer>(1,
                new HeapTreeNode<Integer>(2,
                        new HeapTreeNode<Integer>(5,null,null),
                        new HeapTreeNode<Integer>(6,null,null)
                ),new HeapTreeNode<Integer>(4,null,null));
        System.out.println(s.isMinHeapTree());



        /**         10
         *     -10      19
         *  -20   0   17
         */
        var d = new HeapTreeNode<Integer>(10,
                new HeapTreeNode<>(-10,
                        new HeapTreeNode<>(-20),
                        new HeapTreeNode<>(0)),
                new HeapTreeNode<>(19,
                        new HeapTreeNode<>(17),
                        null)
        );
        System.out.println(d.isMinHeapTree());


        /**
         *          19
         *      17     -10
         *    0   3  -20
         */

        var f = new HeapTreeNode<Integer>(19,
                new HeapTreeNode<>(17,
                        new HeapTreeNode<>(0),
                        new HeapTreeNode<>(3)),
                new HeapTreeNode<>(-10,
                        new HeapTreeNode<>(-20),
                        null)
        );
        System.out.println(f.isMaxHeapTree());

        System.out.println(f.array());
    }


}
