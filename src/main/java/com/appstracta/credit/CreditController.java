package com.appstracta.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditController {

	private final CreditService service;

	@GetMapping
	public ResponseEntity<List<Credit>> findAll() {
		List<Credit> credits = service.findAll();

		if (credits.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(credits);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Credit>> findById(@PathVariable Long id) {
		Optional<Credit> credit = service.findById(id);

		return ResponseEntity.ok(credit);
	}

	@PostMapping
	public ResponseEntity<Credit> save(@RequestBody Credit credit, HttpServletRequest httpServletRequest) {
		Credit creditSave = service.save(credit);
		URI location = ServletUriComponentsBuilder.fromServletMapping(httpServletRequest).path("/{id}").build().expand(creditSave.getId()).toUri();

		return ResponseEntity.created(location).body(creditSave);
	}

	@PutMapping
	public Credit update(@RequestBody Credit credit, @PathVariable Long id) {
		return service.update(credit, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
