package assegnamento2.assegnamento2.communication.user;

import assegnamento2.assegnamento2.communication.product.BakeryShop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Client extends Person {

    private String address;
    private final List<BakeryShop> shop = new ArrayList<>();

    // Constructors
    public Client() { }

    public Client(final String name, final String surname, final String username, final String password, final String address) {
        super(name, surname, username, password);
        this.setAddress(address);
    }

    public Client(final Client registerClient) {
        super(registerClient.getName(), registerClient.getSurname(), registerClient.getUsername(), registerClient.getPassword());
        this.address = registerClient.getAddress();
    }

    // Getters and Setters
    public String getAddress() {
        return this.address;
    }

    public Object getShop() {
        return this.shop;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    // Search for a product based on criteria
    public Object searchProduct(final List<BakeryShop> list, String name, String producer, String min, String max) throws IOException {
        float minPrice = 0, maxPrice = 0;
        List<BakeryShop> elprint = new ArrayList<>();

        // Parse minimum price
        if (!min.equals("")) {
            try {
                minPrice = Float.parseFloat(min);
            } catch (NumberFormatException ignored) {}
        }

        // Parse maximum price
        if (!max.equals("")) {
            try {
                maxPrice = Float.parseFloat(max);
            } catch (NumberFormatException ignored) {}
        }

        // Filter products based on name and producer
        for (BakeryShop e : list) {
            if (name.equalsIgnoreCase(e.getName()) || name.equals("")) {
                elprint.add(e);
                if (!producer.equalsIgnoreCase(e.getProducer()) && !producer.equals("")) {
                    elprint.remove(e);
                }
            }
        }

        // Filter products based on price range
        float finalMinPrice = minPrice;
        elprint.removeIf(n -> n.getPrice() <= finalMinPrice);
        if (maxPrice != 0) {
            float finalMaxPrice = maxPrice;
            elprint.removeIf(n -> n.getPrice() >= finalMaxPrice);
        }

        return elprint;
    }

    // Order a product based on its ID and quantity
    public int orderProduct(final List<BakeryShop> elDev, final List<BakeryShop> buyElDev, String ID, String amount) {
        int id = 0;
        try {
            id = Integer.parseInt(ID);
        } catch (NumberFormatException ignored) {}

        for (BakeryShop i : elDev) {
            if (i.getId() == id) {
                int qnt = Integer.parseInt(amount);
                if (qnt > 0 && qnt <= i.getAmount()) {
                    if (i.getAmount() == qnt) {
                        addOrder(new BakeryShop(i));
                        i.setAmount(i.getAmount() - qnt);
                        buyElDev.add(elDev.remove(elDev.indexOf(i)));
                    } else {
                        int a = i.getAmount();
                        i.setAmount(qnt);
                        addOrder(new BakeryShop(i));
                        i.setAmount(a - qnt);
                    }
                    return 0; // Order successful
                } else return 1; // Invalid quantity
            }
        }
        return 2; // Product not found
    }

    // Add a product to the client's order list
    public void addOrder(final BakeryShop e) {
        for (BakeryShop i : this.shop) {
            if (i.getId() == e.getId()) {
                i.setAmount(i.getAmount() + e.getAmount());
                return;
            }
        }
        this.shop.add(e);
    }
}
