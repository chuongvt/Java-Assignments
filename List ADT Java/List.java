/*
#Chuong Viet Truong
#CruzID: Chvtruon
#PA 1
*/

public class List{
    private class Node{
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
            next = null;
            prev = null;
        }

        public String toString(){
            return String.valueOf(data);
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

    int front(){
        if(length()>0){
            return front.data;
        }
	    return -1;
    }

    int back(){
        if(length()>0){
            return back.data;
        }
        return -1;
    }

    int get(){
        if(length()>0 && index()>=0){
            return cursor.data;
        }
        return -1;
    }

    boolean equals(List L){
        boolean flag = false;
        Node N = this.front;
        Node M = L.front;
        flag = (this.length == L.length);

        while(flag && N!=null){
            flag = (N.data == M.data);
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

    void prepend(int data){
        Node t = new Node(data);
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

    void append(int data){
        Node t = new Node(data);
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

    void insertBefore(int data){
        if(length()>0 && index()>=0 && indexOfCursor >= 0){
            if(indexOfCursor == 0){
                prepend(data);
            }
            else{
                Node t = new Node(data);
                t.next = cursor;
                t.prev = cursor.prev;
                cursor.prev.next = t;
                cursor.prev = t;
                indexOfCursor++;
                length++;
            }
        }
    }

    void insertAfter(int data){
        if(length()>0 && index()>=0){
            if(indexOfCursor==length()-1){
                append(data);
            }
            else{
                Node t = new Node(data);
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

    List copy(){
        List copy = new List();
        Node t = front;
        while(t != null){
            copy.append(t.data);
            t = t.next;
        }
        return copy;
    }

    List concat(List L){
        List cat = copy();
        Node t = L.front;
        while(t != null){
            cat.append(t.data);
            t = t.next;
        }
        return cat;
    }
}
