import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<String> regularOrders;
    private Queue<String> priorityOrders;

    public OrderQueue() {
        this.regularOrders = new LinkedList<>();
        this.priorityOrders = new LinkedList<>();
    }

    public void addRegularOrder(String order) {
        regularOrders.add(order);
    }

    public void addPriorityOrder(String order) {
        priorityOrders.add(order);
    }

    public String processOrder() {
        if (!priorityOrders.isEmpty()) {
            return priorityOrders.poll();
        } else if (!regularOrders.isEmpty()) {
            return regularOrders.poll();
        } else {
            throw new IllegalStateException("No orders to process.");
        }
    }

    public boolean cancelOrder(String order) {
        if (priorityOrders.remove(order)) {
            return true;
        } else if (regularOrders.remove(order)) {
            return true;
        }
        return false;
    }

    public void displayOrders() {
        System.out.println("Priority Orders: " + priorityOrders);
        System.out.println("Regular Orders: " + regularOrders);
    }

    public boolean orderExists(String order) {
        return orderExistsInQueue(priorityOrders, order) || orderExistsInQueue(regularOrders, order);
    }

    private boolean orderExistsInQueue(Queue<String> queue, String order) {
        if (queue.isEmpty()) {
            return false;
        }
        String currentOrder = queue.poll();
        boolean exists = currentOrder.equals(order) || orderExistsInQueue(queue, order);
        queue.add(currentOrder); 
        return exists;
    }
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        orderQueue.addRegularOrder("Burger");
        orderQueue.addRegularOrder("Fries");
        orderQueue.addPriorityOrder("VIP Steak");
        orderQueue.addPriorityOrder("VIP Lobster");

        System.out.println("Initial Orders:");
        orderQueue.displayOrders();

        System.out.println("\nProcessing Order: " + orderQueue.processOrder());
        System.out.println("Processing Order: " + orderQueue.processOrder());

        System.out.println("\nOrders after processing:");
        orderQueue.displayOrders();

        System.out.println("\nOrder 'Fries' exists: " + orderQueue.orderExists("Fries"));
        System.out.println("Order 'Pizza' exists: " + orderQueue.orderExists("Pizza"));

        System.out.println("\nCanceling 'Fries': " + orderQueue.cancelOrder("Fries"));
        System.out.println("Canceling 'Pizza': " + orderQueue.cancelOrder("Pizza"));

        System.out.println("\nOrders after cancellation:");
        orderQueue.displayOrders();
    }
}


