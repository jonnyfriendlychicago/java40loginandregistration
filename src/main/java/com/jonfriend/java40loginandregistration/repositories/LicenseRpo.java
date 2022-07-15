package com.jonfriend.java40loginandregistration.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java40loginandregistration.models.LicenseMdl;

@Repository
public interface LicenseRpo extends CrudRepository<LicenseMdl, Long> {
	List<LicenseMdl> findAll(); 
}
