package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.web.customers;

import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.CustomerViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerOrderHistoryController {

  private final CustomerViewRepository customerViewRepository;

  @Autowired
  public CustomerOrderHistoryController(CustomerViewRepository customerViewRepository) {
    this.customerViewRepository = customerViewRepository;
  }

  @RequestMapping(value="/customers/{customerId}", method= RequestMethod.GET)
  public ResponseEntity<CustomerView> getCustomer(@PathVariable String customerId) {
    CustomerView customer = customerViewRepository.findOne(customerId);
    System.out.println("Found customer=" + customer + " for " + customerId);
    if (customer == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else
      return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @RequestMapping(value = {"/customers"},method= RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<CustomerView>>getAllCustomers() {
    List<CustomerView> customers = customerViewRepository.findAll();
    System.out.println("Found customer list size" + customers.isEmpty());
    if (customers.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else
      return new ResponseEntity<>(customers, HttpStatus.OK);
  }


}
