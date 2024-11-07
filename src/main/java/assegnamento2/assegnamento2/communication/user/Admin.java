package assegnamento2.assegnamento2.communication.user;

import assegnamento2.assegnamento2.communication.product.BakeryShop;
import java.util.List;

public final class Admin extends Employee {

    // Constructors
    public Admin(final String name, final String surname, final String username, final String password) {
        super(name, surname, username, password);
    }

    public Admin() {}

    public Admin(Admin a) {
        super(a.getName(), a.getSurname(), a.getUsername(), a.getPassword());
    }

    // Method to add an employee
    public boolean addEmployee(final List<Employee> list, String name, String surname, String username, String password) {
        for (Employee i : list) {
            if (i.getUsername().equals(username)) return true; // Username already exists
        }
        list.add(new Employee(name, surname, username, password));
        return false; // Employee added successfully
    }

    // Method to remove an employee by username
    public boolean rmvEmployee(final List<Employee> list, String username) {
        for (Employee e : list) {
            if (e.getUsername().equals(username)) {
                list.remove(e);
                return false; // Employee removed successfully
            }
        }
        return true; // Employee not found
    }

    // Method to add a product
    public int addProduct(final List<BakeryShop> list, String name, int id, String producer, float price, int amount) {
        if (amount <= 0) return 1; // Invalid amount
        if (price <= 0) return 2; // Invalid price
        for (BakeryShop i : list) {
            if (i.getId() == id) return 3; // Product with this ID already exists
        }
        list.add(new BakeryShop(name, id, producer, price, amount));
        return 0; // Product added successfully
    }

    // Method to remove a product by ID
    public int rmvProduct(final List<BakeryShop> list, int id) {
        for (BakeryShop e : list) {
            if (e.getId() == id) {
                list.remove(e);
                return 0; // Product removed successfully
            }
        }
        return 1; // Product not found
    }
}
