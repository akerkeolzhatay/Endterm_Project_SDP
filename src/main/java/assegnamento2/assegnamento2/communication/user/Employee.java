package assegnamento2.assegnamento2.communication.user;

import assegnamento2.assegnamento2.communication.product.BakeryShop;
import java.util.List;

public class Employee extends Person {

    // Constructors
    public Employee() { }

    public Employee(final String name, final String surname, final String username, final String password) {
        super(name, surname, username, password);
    }

    public Employee(Employee emp) {
        super(emp.getName(), emp.getSurname(), emp.getUsername(), emp.getPassword());
    }

    // Method to add quantity to a product
    public int addAmount(final List<BakeryShop> elDev, final List<BakeryShop> buyElDev, String ID, String amount) {
        int id;
        try {
            id = Integer.parseInt(ID);
        } catch (NumberFormatException e) {
            return 2; // Return 2 if ID is not a valid integer
        }

        // Try to add stock to the product in the available list
        for (BakeryShop i : elDev) {
            if (i.getId() == id) {
                try {
                    int q = Integer.parseInt(amount);
                    if (q > 0) {
                        i.setAmount(i.getAmount() + q);
                        return 0; // Return 0 for success
                    } else {
                        return 1; // Return 1 for invalid (non-positive) quantity
                    }
                } catch (NumberFormatException e) {
                    return 2; // Return 2 if amount is not a valid integer
                }
            }
        }

        // Try to add stock to the product in the sold list (move it back to available)
        for (BakeryShop j : buyElDev) {
            if (j.getId() == id) {
                try {
                    int q = Integer.parseInt(amount);
                    if (q > 0) {
                        j.setAmount(j.getAmount() + q);
                        elDev.add(buyElDev.remove(buyElDev.indexOf(j)));
                        return 0; // Return 0 for success
                    } else {
                        return 1; // Return 1 for invalid (non-positive) quantity
                    }
                } catch (NumberFormatException e) {
                    return 2; // Return 2 if amount is not a valid integer
                }
            }
        }

        return 3; // Return 3 if product ID is not found
    }
}
