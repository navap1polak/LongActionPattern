package nava.polak.onik.buisness;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public  class ItemListManager<E extends Item> {
    private final List<E> itemList;
    private final ReentrantLock mutex;
    private  Semaphore consumerSemaphore;
    private  Semaphore producerSemaphore;
    private final int maxSize;

    public ItemListManager(int maxSize){
        this.maxSize = maxSize;
        itemList = new ArrayList<>();
        this.mutex = new ReentrantLock();
        resetSemaphores();
    }

    private void resetSemaphores() {
        this.consumerSemaphore = new Semaphore(0);
        this.producerSemaphore = new Semaphore(maxSize);
    }


    public void addItem(E item) throws InterruptedException {

        // wait to get permit to add new item. If list reaches the limit it will be blocked
        producerSemaphore.acquire();

        // exclusive lock on itemList
        mutex.lock();

        // add item
        System.out.println("Adding item " +item.getName() + " " + item.getAction());
        itemList.add(item);

        // release the lock
        mutex.unlock();

        //  signal the consumer that new item added and can be retrieved
        consumerSemaphore.release();
    }

    public List<E> getAllItemsInList() throws InterruptedException {
        // wait to get permit. If list is empty it will be blocked
        consumerSemaphore.acquire();

        // lock since we are going to change the list
        mutex.lock();

        // Since we need to empty the list and return its content before, prepare
        //a clon of the list
        List<E> clonedList = new ArrayList<E>(itemList);

        //clear the list, all items are consumed
        itemList.clear();

        //need to reset the semaphore to the initial state
        producerSemaphore.release(clonedList.size());
       resetSemaphores();

        // release the lock
        mutex.unlock();

        return clonedList;
    }

}
