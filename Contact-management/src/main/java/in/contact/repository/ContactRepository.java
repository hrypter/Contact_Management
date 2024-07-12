package in.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import in.contact.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByIsFavorite(boolean isFavorite);
}
