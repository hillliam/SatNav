package satnav;

/* This class provides a basic pair object to use with the HashMap
 * The attributes are public so you can access them directly or via the get
 * methods
*/
public class nodePair 
{
  private int key;
  private Node value;
  
  public nodePair(int key, Node value)
  {
    this.key=key;
    this.value=value;
  }
  
  public int GetKey()
  {
    return key;
  }
  
  public Node GetValue()
  {
    return value;
  }
}
