package pathfinding;

import java.util.Arrays;
import java.util.List;

import graph.Node;

public class Heap {
  Node[] items;
  int currentItemCount;

  public Node[] getItems() {
    return items;
  }
  
  public Heap(int maxHeapSize) {
    items = new Node[maxHeapSize];
  }

  public void add(Node item) {
    items[currentItemCount] = item;
    sortUp(item);
    currentItemCount++;
  }

  public Node remove() {
    Node firstItem = items[0];
    currentItemCount--;
    items[0] = items[currentItemCount];
    items[0].setHeapIndex(0);
    sortDown(items[0]);
    return firstItem;
  }

  public void updateItem(Node item) {
    sortUp(item);
    sortDown(item);
  }
  
  public int count() {
    return currentItemCount;
  }
  
  public boolean contains(Node item) {
    return items[item.getHeapIndex()].equals(item);
  }
  
  private void sortDown(Node item) {
    while (true) {
      int childIndexLeft  = item.getHeapIndex() * 2 + 1;
      int childIndexRight = item.getHeapIndex() * 2 + 2;
      int swapIndex = 0;

      if (childIndexLeft < currentItemCount) {
        swapIndex = childIndexLeft;
        if (childIndexRight < currentItemCount)
          if (items[childIndexLeft].compareTo(items[childIndexRight]) < 0)
            swapIndex = childIndexRight;

        if (item.compareTo(items[swapIndex]) == 0)
          swap(item, items[swapIndex]);
        else
          return;
      } else
        return;
    }
  }

  private void sortUp(Node item) {
    int parentIndex = (item.getHeapIndex()- 1) / 2;
    while (true) {
      Node parentItem = items[parentIndex];
      if (item.compareTo(parentItem) > 0)
        swap(item, parentItem);
      else
        break;

      parentIndex = (item.getHeapIndex() - 1) / 2;
    }
  }

  private void swap(Node itemA, Node itemB) {
    items[itemA.getHeapIndex()] = itemB;
    items[itemB.getHeapIndex()] = itemA;
    int itemAIndex = itemA.getHeapIndex();
    itemA.setHeapIndex(itemB.getHeapIndex());
    itemB.setHeapIndex(itemAIndex);
  }
  
  public List<Node> toList() {
    return Arrays.asList(items);
  }
}