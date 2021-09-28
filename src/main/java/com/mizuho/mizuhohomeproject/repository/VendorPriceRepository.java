package com.mizuho.mizuhohomeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mizuho.mizuhohomeproject.entity.VendorPrice;

@Repository
public interface VendorPriceRepository extends JpaRepository<VendorPrice,Long>{

}
