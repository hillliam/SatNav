package satnav;

/* This collection represents a hashmap that contain pairs of strings
 * You must implement this hashmap using open addressing and full resize rehashing
 * with quadratic probing
 */
// Refer to week 6 and 7 materials
public class HashMap {

    // Your basic data - you must use the HashPair object which has been predefined for you
    private long noofitems;   // total number of items in the hash map
    private nodePair[] data;  // array of data where the items are stored
    // tip: to get the max length of an array, you use the length function on it

    /* Creates a hashmap with a specified inital capacity */
    public HashMap(int initlen) {
        noofitems = 0;
        data = new nodePair[initlen];
    }

    /* Adds the pair <key, value> to the hash map */
    public void AddItem(int key, Node value) {
        int index = HashFunction(key); // start index
        int a = 0;
        //nodePair item = new nodePair(key, value);

        // Check load factor here and resize if over 0.7.  Use full rehashing
        double items = noofitems;
        double length = data.length;
        double load = (items / length) * 100.0f;
        if (load > 70) {
            grow();
        }
        // Place item into data, but check and resolve collisions first using 
        // quadratic probing. y= a*x^2+b*x+c x = colitions x = colitions b = ? c = ?
        while (data[Math.abs((index + a) % data.length)] != null) {
            a++; // miss
        }
        data[Math.abs((index + a) % data.length)] = new nodePair(key, value);
        noofitems++;
    }

    private void grow() { // full grow
        //System.out.println("growing " + data.length);
        nodePair newarray[] = new nodePair[data.length + 1000]; // grow substantionaly to avoid over displacment
        for (int i = 0; i != data.length; i++) {
            if (data[i] != null) {
                int a = 0;
                int location = Math.abs((i + a) % newarray.length); // rehash key 
                if (newarray[location] == null) {
                    newarray[location] = data[i]; //hit
                } else {
                    while (newarray[Math.abs((i + a) % newarray.length)] != null) {
                        a++; // increase number of misses
                    }
                    newarray[Math.abs((i + a) % newarray.length)] = data[i];
                }
            }
        }
        data = newarray;
    }
    /* Function used to create a hash from the key String passed in */

    private int HashFunction(int key) {
        // Provide a basic hash function that mashes all the ascii values of key
        // into an index and return that
        return key;
    }

    /* Returns the value associated with the key stored in the hash map
     * If the key is not in the hash map then null should be returned
     */
    public Node GetValue(int key) {
        // Returns the item associated with key - remember you will need to 
        // implement the same collision resolution formular here - quadratic probing y= a*x^2+b*x+c a = colitions x = rand b = ? c = ?
        int index = HashFunction(key);
        int a = 0;
        //System.out.println(index + quadraticprobing(1, 12, 33, a));
        if (data[Math.abs((index + a) % data.length)] == null) {
            return null; // miss
        } else {
            while (data[Math.abs((index + a) % data.length)] != null) {
                if (data[Math.abs((index + a) % data.length)].GetKey() == key) {
                    break; // hit
                } else {
                    a++; // miss
                }
            }
            if (data[Math.abs((index + a) % data.length)] == null) {
                return null; // miss
            } else {
                return data[Math.abs((index + a) % data.length)].GetValue();
            }
        }
    }

    /* if you want extra internal information about the state of your stack when
     * tested, update the following toString method to dump any information you are 
     * interested in */
    public String toString() {
        String result = "";
        int index = 0;
        for (nodePair a : data) {
            if (a != null) {
                result += a.GetKey() + ' ' + index + '\n';
            }
            index++;
        }
        return result;
    }
}
