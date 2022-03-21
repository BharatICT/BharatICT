package com.tatvasoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tatvasoft.entity.MstUserRegBo;

@Repository
public interface MstUserRegRepository extends JpaRepository<MstUserRegBo, Integer>{

	@Query("from MstUserRegBo where loginName=?1")
	MstUserRegBo  findByLoginName(String loginName);
}
