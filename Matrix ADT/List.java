/*
#Chuong Viet Truong
#CruzID: Chvtruon
#PA 3
*/

public class List{
    private class Node{
        Object entry;
        Node next;
        Node prev;

        Node(Object entry){
            this.entry = entry;
            next = null;
            prev = null;
        }

        public boolean equals(Object x){
            boolean eq = false;
            Node node;
            if(x instanceof Node) {
                node = (Node)x;
                eq = (this.entry.equals(node.entry));
            }
            return eq;
        }

        public String toString(){
            return entry.toString();
        }
    }

    private Node front;
    private Node back;
    private Node cursor; // Current highlighted element in list
    private int length;
    private int indexOfCursor;

    // List constructor that creates new empty list
    List(){
        front = null;
        back = null;
        cursor = null;
        length = 0;
        indexOfCursor = -1;
    }

    // Access functions
    int length(){
        return length;
    }

    int index(){
        if(indexOfCursor > length-1){
            return -1;
        }
        else{
            return indexOfCursor;
        }
    }

    Object front(){
        if(length()>0){
            return front.entry;
        }
	    return -1;
    }

    Object back(){
        if(length()>0){
            return back.entry;
        }
        return -1;
    }

    Object get(){
        if(length()>0 && index()>=0){
            return cursor.entry;
        }
        return -1;
    }

    public boolean equals(List L){
        boolean flag = false;
        Node N = this.front;
        Node M = L.front;
        flag = (this.length == L.length);

        while(flag && N!=null){
            flag = (N.entry == M.entry);
            N = N.next;
            M = M.next;
        }
        return flag;
    }

    // Manipulation procedures
    void clear(){
        front = back = cursor = null;
        indexOfCursor = -1;
        length = 0;
    }

    void moveFront(){
        if(length()>0){
            cursor = front;
            indexOfCursor = 0;
        }
    }

    void moveBack(){
        if(length()>0){
            cursor = back;
            indexOfCursor = length - 1;
        }
    }

    void movePrev(){
        if(cursor != null){
	           if(cursor == front){
                   cursor = null;
                   indexOfCursor = -1;
               }
               else{
                   cursor = cursor.prev;
                   indexOfCursor--;
               }
        }
        else{
            indexOfCursor = -1;
        }
    }

    void moveNext(){
        if(cursor != null){
            if(cursor == back){
                cursor = null;
                indexOfCursor = -1;
            }
            else{
                cursor = cursor.next;
                indexOfCursor++;
            }
        }
        else{
            indexOfCursor = -1;
        }
    }

    void prepend(Object entry){
        Node t = new Node(entry);
        if(front == null){
            front = back = t;
        }
        else{
            t.next = front;
            front.prev = t;
            front = front.prev;
        }
        if(indexOfCursor != -1){
            indexOfCursor++;
        }
        length++;
    }

    void append(Object entry){
        Node t = new Node(entry);
        if(front == null){
            front = back = t;
        }
        else{
            t.prev = back;
            back.next = t;
            back = back.next;
        }
        length++;
    }

    void insertBefore(Object entry){
        if(length()>0 && index()>=0 && indexOfCursor >= 0){
            if(indexOfCursor == 0){
                prepend(entry);
            }
            else{
                Node t = new Node(entry);
                t.next = cursor;
                t.prev = cursor.prev;
                cursor.prev.next = t;
                cursor.prev = t;
                indexOfCursor++;
                length++;
            }
        }
    }

    void insertAfter(Object entry){
        if(length()>0 && index()>=0){
            if(indexOfCursor==length()-1){
                append(entry);
            }
            else{
                Node t = new Node(entry);
                t.prev = cursor;
                t.next = cursor.next;
                cursor.next.prev = t;
                cursor.next = t;
                length++;
            }
        }
    }

    void deleteFront(){
        if(length()>0){
            front.next = front;
            front.prev = null;
            if(indexOfCursor != -1){
                indexOfCursor--;
            }
            length--;
        }
    }

    void deleteBack(){
        if(length()>0){
            if(cursor == back){
                indexOfCursor = -1;
            }
            back.prev = back;
            back.next = null;
            length--;
        }
    }

    void delete(){
        if(length()>0 && index()>=0){
            if(front == cursor){
                deleteFront();
            }
            else if(back == cursor){
                deleteBack();
            }
            else{
                cursor.prev.next = cursor.next;
                cursor.next.prev = cursor.prev;
                cursor = null;
                indexOfCursor = -1;
                length--;
            }
        }
    }

    // Other methods
    public String toString(){
        String l = "";
        if(length > 0){
            l = front.toString();
            Node t = null;
            for(t = front.next; t!=null; t=t.next){
                l +=" " + t.toString();
            }
        }
        return l;
    }
}
