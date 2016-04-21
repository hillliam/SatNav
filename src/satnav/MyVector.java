package satnav;

/* This class defines the behaviour of a standard Vector.  The items in the 
 * collection are String objects. */
// Refer to week 3 materials
public class MyVector {

    private int array[];
    private int itemcount;
    private int growammount;

    // Default Constructor
    public MyVector() {
        array = new int[0];
        itemcount = 0;
        growammount = 15;
    }

    /* Sets how much this vector should grow by when it needs resizing.  A sensible
     * default growby value should be used in your implementation, but this method will
     * override that default.  Note, you should not grow the vector here, just remember 
     * what it will grow by when required. */
    public void SetGrowBy(int growby) {
        if (growby == 0) {
            growammount = 2;
        } else {
            growammount = growby;
        }
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
    public int GetItemByIndex(int index) {
        if (index > itemcount || index < 0) {
            return 0;
        } else {
            return array[index];
        }
    }

    /* Returns the index of the first item found in the collection that matches the
     * value passed in, or -1 if no such item exists.  String matches are not case 
     * sensitive.  You will need to use linear search */
    public int FindItem(String search) {
        for (int i = 0; i <= itemcount; i++) {
            if (search.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    /* Adds value to the end of the Vector collection of data items.  The order that 
     * the items are added must be maintained. */
    public void AddItem(int value) {
        if (itemcount == array.length) {
            int newarray[] = new int[array.length + growammount];
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
    public void InsertItem(int index, int value) {
        if (itemcount > index) {
            if (itemcount == array.length) {
                int newarray[] = new int[array.length + growammount];
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
            for (int i = index; i <= itemcount - 1; i++) {
                if (i != this.GetCapacity() - 1) {//bugfix
                    array[i] = array[i + 1];
                }
            }
            itemcount--;
        }
    }

    public int[] getarray() {
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
}
