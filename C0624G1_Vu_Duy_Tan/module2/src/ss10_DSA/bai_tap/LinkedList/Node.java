package ss10_DSA.bai_tap.LinkedList;

class Node<E> {
    E data;
    Node<E> next;

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return this.data;
    }
}
