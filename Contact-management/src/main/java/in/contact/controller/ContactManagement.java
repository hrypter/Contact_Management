package in.contact.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import in.contact.model.Contact;
import in.contact.repository.ContactRepository;


@Controller
public class ContactManagement {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactManagement(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
	
	/*
	 * @GetMapping("/fragment") public String fragment(Model m1) { Contact Obj = new
	 * Contact(); m1.addAttribute("contact",Obj); return "fragment"; }
	 * 
	 * @PostMapping("/fragment") public String
	 * handelRegisterBtn(@Valid @ModelAttribute("contact") Contact contactform,
	 * BindingResult result, Model m1) { if (result.hasErrors()) { return
	 * "fragment"; } contactRepository.save(contactform); m1.addAttribute("msg",
	 * "Registration successful..."); return "fragment.html";
	 * 
	 * }
	 */
	 
 
    @GetMapping("/contact")
    public String getAllContact(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }

	
	  @PostMapping("/contact/new")
	  @ResponseBody 
	  public ResponseEntity<String> saveContact(@ModelAttribute  Contact contact)   { 
		  contactRepository.save(contact); 
	  return ResponseEntity.ok("contact created successfully"); 
	  }
	 

    @GetMapping("/contact/edit/{id}")
    @ResponseBody
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/favorite")
    public ResponseEntity<List<Contact>> getFavContact() {
        List<Contact> favorite = contactRepository.findByIsFavorite(true);
        if (favorite != null) {
            return ResponseEntity.ok(favorite);
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
    
	/*
	 * @GetMapping("/favorite/edit/{isFavorite}")
	 * 
	 * @ResponseBody public
	 * ResponseEntity<Contact>getContactByisFavorite(@PathVariable boolean
	 * isFavorite){ Contact favorite = (Contact)
	 * contactRepository.findByIsFavorite(true); if (favorite != null) { return
	 * ResponseEntity.ok(favorite); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */
    
    @PostMapping("/favorite/{id}")
    @ResponseBody
    public ResponseEntity<String> manageFavorite(@PathVariable long id, @ModelAttribute Contact contact) {
        Contact existingFavorite = contactRepository.findById(id).orElse(null);
        if (existingFavorite.isFavorite==false) {
            existingFavorite.setFavorite(true);
            contactRepository.save(existingFavorite);
            return ResponseEntity.ok("contact updated successfully");
        } else {
        	 existingFavorite.setFavorite(false);
             contactRepository.save(existingFavorite);
             return ResponseEntity.ok("contact updated successfully"); } 
    }
 
    @PostMapping("/contact/edit/{id}")
    @ResponseBody
    public ResponseEntity<String> updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            existingContact.setFirstname(contact.getFirstname());
            existingContact.setLastname(contact.getLastname());
            existingContact.setEmail(contact.getEmail());
            existingContact.setPhone(contact.getPhone());
            existingContact.setUsername(contact.getUsername());
            existingContact.setPassword(contact.getPassword());
            existingContact.setAddress(contact.getAddress());
            contactRepository.save(existingContact);
            
            return ResponseEntity.ok("contact updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/contact/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
