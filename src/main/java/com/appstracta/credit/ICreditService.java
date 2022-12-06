package com.appstracta.credit;

import java.util.List;
import java.util.Optional;

public interface ICreditService {

	List<Credit> findAll();

	Optional<Credit> findById(Long id);

	Credit save(Credit credit);

	Credit update(Credit credit, Long id);

	void delete(Long id);

}
