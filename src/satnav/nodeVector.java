package satnav;

/* This class defines the behaviour of a standard Vector.  The items in the 
 * collection are String objects. */
// Refer to week 3 materials
public class nodeVector {

    private Node array[];
    private int itemcount;
    private final int growammount = 2000;
    private boolean sorted;

    // Default Constructor
    public nodeVector() {
        array = new Node[0];
        itemcount = 0;
        sorted = false;
    }

    public nodeVector(boolean sort) {
        array = new Node[0];
        itemcount = 0;
        sorted = sort;
    }

    /* Returns the maximum capacity of the vector before it will need to resize again
     * This is not the number of items in the vector, but the current max capacity - i.e. 
     * the length of the underly data array.  This function is also not the number of
     * remaining free slots, but the absolute maximum capacity of the the vector in its
     * current configuration */
    public int GetCapacity() {
        return array.length;
    }

    /* Returns the number of items in the vector - this is not the max capacity but
     * number of valid items in the collection */
    public int GetNoOfItems() {
        return itemcount;
    }

    /* Returns the String value held at index (base zero) or null if the index
     * is out of bounds */
    public Node GetItemByIndex(int index) {
        if (index > itemcount || index < 0) {
            return null;
        } else {
            return array[index];
        }
    }

    /* Returns the index of the first item found in the collection that matches the
     * value passed in, or -1 if no such item exists.  String matches are not case 
     * sensitive.  You will need to use linear search */
    public int FindItem(Node search) {
        for (int i = 0; i <= itemcount-1; i++) {
            if (search == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public Node Findlowest() {
        if (GetNoOfItems() != 0) {
            Node a = array[0];
            for (int i = 1; i <= itemcount; i++) {
                if (a.getHdistance() < array[i].getHdistance()) {
                    a = array[i];
                }
            }
            return a;
        }
        return null;
    }
    /* Adds value to the end of the Vector collection of data items.  The order that 
     * the items are added must be maintained. */

    public void AddItem(Node value) {
        if (itemcount == array.length) {
            Node newarray[] = new Node[array.length + growammount];
            for (int i = 0; i != itemcount; i++) {
                newarray[i] = array[i];
            }
            array = newarray;
        }
        array[itemcount] = value;
        itemcount++;
    }

    /* Inerts value into the data structure at index (base zero) or at the end
     * if there are less items in the data structure than index suggests */
    public void InsertItem(int index, Node value) {
        if (itemcount > index) {
            if (itemcount == array.length) {
                Node newarray[] = new Node[array.length + growammount];
                boolean found = false;
                int i = 0;
                while (i != itemcount) {
                    if (index == i) {
                        newarray[i] = value;
                        found = true;
                    }
                    if (!found) {
                        newarray[i] = array[i];
                    } else {
                        newarray[i + 1] = array[i];
                    }
                    i++;
                }
                array = newarray;
                itemcount++;
            } else {
                boolean found = false;
                int i = itemcount;
                while (i >= 0) {
                    if (index == i) {
                        array[i] = value;
                        found = true;
                    }
                    if (!found) {
                        array[i] = array[i - 1];
                    } else {
                        break;
                    }
                    i--;
                }
                itemcount++;
            }
        } else {
            AddItem(value);
        }
    }

    /* Removes the item at index from the data structure - if index is out of
     * bounds then the data structure remains unchanged 
     * Items need to be shifted to ensure the used portion of the array always
     * contains valid items.
     */
    public void DeleteItem(int index) {
        if (itemcount > index) {
            for (int i = index; i <= itemcount; i++) {
                if (i != this.GetCapacity() - 1) {//bugfix
                    array[i] = array[i + 1];
                }
            }
            itemcount--;
        }
    }

    public int binarysearch(int search) {
        return BinarySearch(search, 0, itemcount);
    }

    private int BinarySearch(int id, int low, int high) {
        int midle = (high + low) / 2;
        if (low <= high) {
            if (array[midle].getid() < id) {
                return BinarySearch(id, midle+1, high);
            } else if (array[midle].getid() > id) {
                return BinarySearch(id, low, midle-1);
            } else {
                return midle;
            }
        } else {
            return -1;
        }
    }
    
    public Node findbynode(int id) {
        if (sorted) {
            int a = binarysearch(id);
            if (a == -1) {
                return null;
            } else {
                return array[a];
            }
        }
        for (int i = 0; i <= itemcount; i++) {
            if (array[i].getid() == id) {
                return array[i];
            }
        }
        return null;
    }
    
    public int findbynode(Node a) {
        if (sorted) {
            return binarysearch(a.getid());
        }
        for (int i = 0; i <= itemcount; i++) {
            if (array[i] == a) {
                return i;
            }
        }
        return -1;
    }

    public Node[] getarray() {
        return array;
    }

    /* if you want extra internal information about the state of your vector when
     * tested, update the following toString method to dump any information you are 
     * interested in */
    public String toString() {
        String output = "";
        for (int i = 0; i <= itemcount - 1; i++) {
            output += array[i] + ", ";
        }
        return output;
    }

    public void Sort() {
        quicksort(0, itemcount-1);
    }

    private void quicksort(int low, int high) {
        int lowpoint = low;
        int highpoint = high;
        Node mid = array[low + (high - low) / 2];
        while (lowpoint <= highpoint) {// stop at cross over 
            while (array[lowpoint].getid() < mid.getid()) {// <
                lowpoint++;
            }
            while (array[highpoint].getid() > mid.getid()) {// >
                highpoint--;
            }
            if (lowpoint <= highpoint) {
                Node temp = array[lowpoint];
                array[lowpoint] = array[highpoint];
                array[highpoint] = temp;
                lowpoint++;
                highpoint--;
            }
        }
        if (low < highpoint) { // highpoint to high sorted
            quicksort(low, highpoint);
        }
        if (lowpoint < high) { // low to lowpoint sorted
            quicksort(lowpoint, high);
        }
    }
}
