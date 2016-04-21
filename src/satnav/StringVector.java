package satnav;

/* This class defines the behaviour of a standard Vector.  The items in the 
 * collection are String objects. */
// Refer to week 3 materials
public class StringVector {

    private String array[];
    private int itemcount;
    private int growammount;

    // Default Constructor
    public StringVector() {
        array = new String[0];
        itemcount = 0;
        growammount = 1;
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
    public String GetItemByIndex(int index) {
        if (index > itemcount || index < 0) {
            return null;
        } else {
            return array[index];
        }
    }

    /* Returns the index of the first item found in the collection that matches the
     * value passed in, or -1 if no such item exists.  String matches are not case 
     * sensitive.  You will need to use linear search */
    public int FindItem(String search) {
        if (itemcount != 0) {
            for (int i = 0; i < itemcount; i++) {
                if (search.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /* Adds value to the end of the Vector collection of data items.  The order that 
     * the items are added must be maintained. */
    public void AddItem(String value) {
        if (itemcount == array.length) {
            String newarray[] = new String[array.length + growammount];
            for (int i = 0; i != itemcount; i++) {
                newarray[i] = array[i];
            }
            array = newarray;
        }
        array[itemcount] = value;
        itemcount++;
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

    public String[] getarray() {
        return array;
    }

    /* if you want extra internal information about the state of your vector when
     * tested, update the following toString method to dump any information you are 
     * interested in */
    public String toString() {
        if (itemcount != 0) {
            String output = "";
            for (int i = 0; i <= itemcount - 2; i++) {
                output += array[i] + " -> ";
            }
            output += array[itemcount - 1];
            return output;
        }
        return null;
    }
}
