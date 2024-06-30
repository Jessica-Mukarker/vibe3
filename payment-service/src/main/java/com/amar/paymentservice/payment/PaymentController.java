package com.amar.paymentservice.payment;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class PaymentController {

	private final PaymentRepository repository;
	private final PaymentModelAssembler assembler;

	PaymentController(PaymentRepository repository, PaymentModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/payments")
	CollectionModel<EntityModel<Payment>> all() {

		List<EntityModel<Payment>> payments = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(payments, linkTo(methodOn(PaymentController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/payments")
	Payment newPayments(@RequestBody Payment newPayment) {
		return repository.save(newPayment);
	}

	// Single item

	@GetMapping("/payments/{id}")
	Payment one(@PathVariable Integer id) {

		return repository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
	}

	

	@DeleteMapping("/payments/{id}")
	void deletePatient(@PathVariable Integer id) {
		repository.deleteById(id);
	}

}
