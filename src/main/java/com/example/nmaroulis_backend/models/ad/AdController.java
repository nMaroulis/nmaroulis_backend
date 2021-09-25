package com.example.nmaroulis_backend.models.ad;

import java.util.List;

import com.example.nmaroulis_backend.models.ResourceNotFoundException;
import com.example.nmaroulis_backend.models.ad.Ad;
import com.example.nmaroulis_backend.models.ad.AdRepository;
import com.example.nmaroulis_backend.models.user.User;
import com.example.nmaroulis_backend.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users/{userId}/ads")
    public List<Ad> getAllAdsByUserId(@PathVariable (value = "userId") Long userId){
        return adRepository.findAll();//, pageable);
    }

    @GetMapping("/users/{userId}/my_ads")
    public List<Ad> getMyAdsByUserId(@PathVariable (value = "userId") Long userId){
        return adRepository.findByUserId(userId);//, pageable);
    }

    @PostMapping("/users/{userId}/ads")
    public Ad createAd(@PathVariable (value = "userId") Long userId,
                           Ad ad) {

        return userRepository.findById(userId).map(user -> {
            ad.setUser(user);
            return adRepository.save(ad);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/ad/{adId}")
    public Ad updateAd(@PathVariable (value = "userId") Long userId,
                           @PathVariable (value = "adId") Long adId,
                           @Valid @RequestBody Ad adRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }

        return adRepository.findById(adId).map(ad -> {
            return adRepository.save(ad);
        }).orElseThrow(() -> new ResourceNotFoundException("AdId " + adId + "not found"));
    }

    @DeleteMapping("/users/{userId}/ads/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable (value = "userId") Long userId,
                                        @PathVariable (value = "adId") Long adId) {
        return adRepository.findByIdAndUserId(adId, userId).map(ad -> {
            adRepository.delete(ad);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + adId + " and userId " + userId));
    }


}
