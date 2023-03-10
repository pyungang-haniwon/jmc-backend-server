package com.example.jmcbackend.store.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final StoreLikeRepository storeLikeRepository;


    @Override
    public Store register(StoreInfoParam parameter, String userId) {


            Store store = Store.builder()
                    .storeName(parameter.getStoreName())
                    .userId(userId)
                    .storeInfo(parameter.getStoreInfo())
                    .storeOpeningDateAndHours(parameter.getStoreOpeningDateAndHours())
                    .categoryId(parameter.getCategoryId())
                    .storeAddress(parameter.getStoreAddress())
                    .storeUrl(parameter.getStoreUrl())
                    .storePhone(parameter.getStorePhone())
                    .storeLikeCount(0L)
                    .storeReviewCount(0L)
                    .storeCreated(LocalDateTime.now())
                    .build();

            storeRepository.save(store);


        return store;
    }

    @Override
    public List<Store> getAllStore() {

        return storeRepository.findAll();
    }

    @Override
    public StoreDto storeInfo(StoreInfoParam storeName) {

        Store byStoreName = storeRepository.findByStoreName(storeName.getStoreName());
        if(byStoreName == null) {
            throw new IllegalStateException("???????????? ?????? ???????????????.");
        }
        Optional<Store> optionalStore = storeRepository.findById(byStoreName.getStoreId());

        Store storeInfo = optionalStore.get();
        Long reviewCount = reviewRepository.countByStoreId(storeInfo.getStoreId());
        Long likeCount = storeLikeRepository.countByStoreId(storeInfo.getStoreId());


            StoreDto dto = StoreDto.builder()
                .storeId(storeInfo.getStoreId())
                .categoryId(storeInfo.getCategoryId())
                .storeInfo(storeInfo.getStoreInfo())
                .storeName(storeInfo.getStoreName())
                .storeAddress(storeInfo.getStoreAddress())
                .storeUrl(storeInfo.getStoreUrl())
                .storeReviewCount(reviewCount)
                .storeLikeCount(likeCount)
                .storePhone(storeInfo.getStorePhone())
                .storeOpeningDateAndHours(storeInfo.getStoreOpeningDateAndHours())
                .build();
        return dto;
    }

    @Override
    public List<Store> getCategoryStoreList(Long categoryId) {
        return storeRepository.findAllByCategoryId(categoryId);
    }
}
