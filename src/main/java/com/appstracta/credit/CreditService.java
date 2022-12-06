package com.appstracta.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditService implements ICreditService {

	private final ICreditRepository repository;

	@Override
	public List<Credit> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Credit> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Credit save(Credit credit) {
		return repository.save(credit);
	}

	@Override
	public Credit update(Credit credit, Long id) {
		Credit creditFind = repository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found"));
		BeanUtils.copyProperties(credit, creditFind);

		return repository.save(credit);
	}

	@Override
	public void delete(Long id) {
		Credit creditFind = repository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found"));

		repository.delete(creditFind);
	}

}
