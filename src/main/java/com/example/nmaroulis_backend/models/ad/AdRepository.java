package com.example.nmaroulis_backend.models.ad;

import com.example.nmaroulis_backend.models.ad.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findByUserId(Long adId);
    Optional<Ad> findByIdAndUserId(Long id, Long adId);

}
