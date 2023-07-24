package com.user.repo;

import com.user.model.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface WalletRepo extends JpaRepository<WalletModel,Integer> {
    @Transactional
    @Modifying
    @Query("update WalletModel w set w.balance = w.balance + :amount where w.walletId = :walletId")
    void updateWallet(String walletId, Long amount);

    WalletModel findByWalletId(String WalletId);
}
