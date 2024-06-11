package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipAsiakasService {
    @Autowired
    VipAsiakasRepository vipAsiakasRepo;

    public VipAsiakas findById(Long id) {
        return vipAsiakasRepo.findById(id).orElse(null);
    }

    public void deleteVipAsiakas(Long vipAsiakasId) {
        vipAsiakasRepo.deleteById(vipAsiakasId);
    }
}
