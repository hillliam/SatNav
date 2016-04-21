/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satnav;

/**
 *
 * @author b4026826
 */
public class heap {

    private Node[] array = new Node[1];
    private int length = 0;

    public boolean IsEmpty() // returns true if the queue is empty
    {
        return length == 0;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public int left(int index) {
        return 2 * index + 1;
    }

    public int right(int index) {
        return 2 * index + 2;
    }

    public int GetNoOfItems() // returns the number of items in the heap
    {
        return length;
    }

    public void grow() {
        if (length == array.length) {
            Node[] newarray = new Node[length + 1];
            System.arraycopy(array, 0, newarray, 0, length);
            array = newarray;
        }
    }

    public void heapdown(int index) {
        int parent = index;
        int newnode;
        int left = left(parent);
        int right = right(parent);
        if (left >= length) {

        } else if (right >= length) {
            if (array[parent].getdistance() < array[left].getdistance()) {
                Node tmp = array[left];
                array[left] = array[parent];
                array[parent] = tmp;
            }
        } else {
            if (array[left].getdistance() < array[right].getdistance()) {
                newnode = left;
            } else {
                newnode = right;
            }
            if (array[parent].getdistance() > array[newnode].getdistance()) {
                Node tmp = array[newnode];
                array[newnode] = array[parent];
                array[parent] = tmp;
                heapdown(newnode);
            }
        }
    }

    public void heapup(int newnode) {
        int parent = parent(newnode);
        while (parent >= 0) {
            if (array[parent].getdistance() > array[newnode].getdistance()) {
                Node tmp = array[newnode];
                array[newnode] = array[parent];
                array[parent] = tmp;
            } else {
                break;
            }
            parent = parent(newnode);
        }
    }

    public int FindItem(Node c) { // to treverse properley
        int parent = 0;
        int value = c.getid();
        for (int i = 0; i != length; i++) {
            if (array[i] != null) {
                if (value == array[i].getid()) {
                    return 1;
                }
            }
        }
        return -1;
    }

    public void Push(Node value) // adds an item to the heap
    {
        grow();
        array[length] = value;
        length++;
        if (length > 1) {
            heapup(length - 1);
        }
    }

    public Node Pop() // returns and removes the item at the top of the heap
    {
        if (length == 0) {
            return null;
        } else {
            Node value = array[0];
            array[0] = array[length - 1];

            length--;
            if (length > 1) {
                heapdown(0);
            }
            return value;
        }
    }
}
