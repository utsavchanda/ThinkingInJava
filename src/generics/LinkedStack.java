package generics;

/**
 * Created by utsav on 13/2/16.
 */
public class LinkedStack<T> {
    private static class Node<U>{
        U item;
        Node<U> next;
        Node(){
            item = null;
            next = null;
        }
        Node(U item, Node<U> next){
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return (item==null && next==null);
        }
    }
    private Node<T> top = new Node<T>();
    public void push(T item){
        top = new Node<T>(item, top);
    }
    public T pop(){
        T result = top.item;
        if(!top.end())
            top = top.next;
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<String>();
        for (String s : "Phrasers on stun!".split(" ")){
            linkedStack.push(s);
        }
        String s;
        while ((s= linkedStack.pop()) != null)
            System.out.println(s);
    }

}
