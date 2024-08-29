package homework.accessment02.service;

import homework.accessment02.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private final List<Contact> contacts;

    public ContactService() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int id, String newPhoneNumber) {
        boolean contactFound = false;

        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                contact.setPhone(newPhoneNumber);
                contactFound = true;
                break;
            }
        }

        if (!contactFound) {
            System.out.println("Không tìm thấy liên hệ!");
        }
    }

    public void deleteContact(int id) {
        contacts.removeIf(contact -> contact.getId() == id);
    }

    public Contact findContact(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Không có thông tin liên lạc nào.");
            return;
        }
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
