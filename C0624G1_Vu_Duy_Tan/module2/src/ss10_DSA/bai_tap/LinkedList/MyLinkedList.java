package ss10_DSA.bai_tap.LinkedList;

public class MyLinkedList<E> {
    private Node<E> head;
    private int numNodes = 0;

    // Constructor tạo danh sách với một node đầu tiên
    public MyLinkedList(E data) {
        head = new Node<>(data);
        numNodes++;
    }

    // Phương thức thêm một phần tử mới vào danh sách
    public void add(int index, E element) {
        Node<E> temp = head;
        Node<E> holder;

        for (int i = 0; i < index - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        holder = temp.next;
        temp.next = new Node<>(element);
        temp.next.next = holder;
        numNodes++;
    }

    // Phương thức thêm một phần tử mới vào đầu danh sách
    public void addFirst(E e) {
        Node<E> temp = head;
        head = new Node<>(e);
        head.next = temp;
        numNodes++;
    }

    // Phương thức thêm một phần tử mới vào cuối danh sách
    public void addLast(E e) {
        Node<E> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(e);
        numNodes++;
    }

    // Phương thức xóa 1 phần tử tại vị trí index
    public E remove(int index) {
        if (index < 0 || index >= numNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<E> temp = head;
        if (index == 0) {
            head = head.next;
            numNodes--;
            return temp.getData();
        }

        for (int i = 0; i < index - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        Node<E> removedNode = temp.next;
        temp.next = temp.next.next;
        numNodes--;
        return removedNode.getData();
    }

    // Phương thức xóa 1 phần tử cho trước
    public boolean remove(Object o) {
        if (head == null) return false;

        if (head.getData().equals(o)) {
            head = head.next;
            numNodes--;
            return true;
        }

        Node<E> temp = head;
        while (temp.next != null) {
            if (temp.next.getData().equals(o)) {
                temp.next = temp.next.next;
                numNodes--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Phương thức truy cập một phần tử trong danh sách
    public E get(int index) {
        if (index < 0 || index >= numNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.getData();
    }

    // Phương thức trả về số lượng các phần tử có trong danh sách
    public int size() {
        return numNodes;
    }

    // Phương thức in các phần tử trong danh sách
    public void printList() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Phương thức sao chép danh sách ra một danh sách khác
    public MyLinkedList<E> clone() {
        MyLinkedList<E> cloneList = new MyLinkedList<>(head.getData());
        Node<E> temp = head.next;
        while (temp != null) {
            cloneList.addLast(temp.getData());
            temp = temp.next;
        }
        return cloneList;
    }

    // Phương thức kiểm tra phần tử o có trong danh sách không
    public boolean contains(E o) {
        Node<E> temp = head;
        while (temp != null) {
            if (temp.getData().equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Phương thức trả về vị trí của phần tử o trong danh sách
    public int indexOf(E o) {
        Node<E> temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.getData().equals(o)) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1; // Trả về -1 nếu không tìm thấy phần tử
    }
}
