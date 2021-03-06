package satnav;

public class DLLNode {

    private String item;
    private DLLNode nextindex;// null = end
    //private DLLNode backindex;

    public DLLNode(String value) {
        item = value;
        nextindex = null;
        //backindex = null;
    }

    public DLLNode(String value, DLLNode next) {
        item = value;
        nextindex = next;
        //backindex = null;
    }

    /*public DLLNode(Node value, DLLNode before, DLLNode next) {
        item = value;
        nextindex = next;
        //backindex = before;
    }*/

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    public int itemcount() {
        if (getNextindex() == null) {
            return 1;
        } else {
            return (1 + getNextindex().itemcount());
        }
    }
        public boolean find(String index) {
        if (item.equals(index)) {
            return true;
        } else if (this.getNextindex() == null) {
            return false; // end of the line
        } else {
            return getNextindex().find(index);
        }
    }

    public String find(int count, int index) {
        if (index == count) {
            return this.getItem();
        } else if (this.getNextindex() == null) {
            return null; // end of the line
        } else {
            return getNextindex().find(count + 1, index);
        }
    }

    public DLLNode finditem(int count, int index) {
        if (index == count) {
            return this;
        } else if (this.getNextindex() == null) {
            return null; // end of the line
        } else {
            return getNextindex().finditem(count + 1, index);
        }
    }

    public void additem(DLLNode item) {
        if (this.getNextindex() == null) {
            this.setNextindex(item);
        } else {
            getNextindex().additem(item);
        }
    }

    public void insertitem(int count, int findindex, DLLNode item) {
        if (this.getNextindex() == null || count == findindex - 1) {
            item.setNextindex(this.getNextindex());
            this.setNextindex(item);
        } else {
            getNextindex().insertitem(count + 1, findindex, item);
        }
    }

    public void delete(int count, int findindex) {
        if (count == findindex - 1) {
            if (this.getNextindex() != null) {
                DLLNode removed = this.getNextindex();
                if (removed.getNextindex() != null) {
                    this.setNextindex(removed.getNextindex());
                } else {
                    this.setNextindex(null);
                }
            } else {
                this.setNextindex(null);
            }
        } else {
            if (getNextindex() != null) {
                getNextindex().delete(count + 1, findindex);
            }
        }
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the nextindex
     */
    public DLLNode getNextindex() {
        return nextindex;
    }

    /**
     * @param nextindex the nextindex to set
     */
    public void setNextindex(DLLNode nextindex) {
        this.nextindex = nextindex;
    }
    public String toString() {
        if (this.getNextindex() == null)
        {
            return item;
        }
        else
        {
            return item + " -> " + this.getNextindex().toString();
        }
    }
}
