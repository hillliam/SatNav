package satnav;

/* This data structure represents a queue collection.
 * You should hold the underlying data using an efficient resizable collection
 * data structure
 */
// Refer to week 5 materials
public class intStack {

    private intNode start;
    // Default Constructor

    public intStack() {
        start = null;
    }

    /* Return the number of items contained within this data structure */
    public int GetNoOfItems() {
        if (start == null) {
            return 0;
        } else {
            return start.itemcount();
        }
    }

    public boolean find(int item) {
        if (start == null) {
            return false;
        } else {
            return start.find(item);
        }
    }

    // Returns true if the queue is empty else false if there are items available
    public boolean IsEmpty() {
        return start == null;
    }

    // Gets the next item from the stack
    // or null if there are no more items
    public int Pop() {
        if (start == null) {
            return -1;
        } else {
            intNode item = start;
            start = start.getNextindex();
            return item.getItem();
        }
    }

    // Adds value to the stack
    public void Push(int value) {
        intNode item = new intNode(value);
        if (start == null) {
            start = item;
        } else {
            item.setNextindex(start);
            start = item;
        }
    }

    /* if you want extra internal information about the state of your stack when
     * tested, update the following toString method to dump any information you are 
     * interested in */
    public String toString() {
        if (start != null) {
            String output;
            output = start.toString();
            return output;
        }
        return null;
    }
}
