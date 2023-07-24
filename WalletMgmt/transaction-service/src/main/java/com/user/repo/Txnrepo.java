package com.user.repo;

import com.user.model.TxnService;
import com.user.service.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Txnrepo extends JpaRepository<TxnService ,Integer> {
    @Transactional
    @Modifying
    @Query("update TxnService t set t.transactionStatus = ?2 where t.externalId = ?1")
    void updateTransaction(String externalTxnId, TransactionStatus transactionStatus);
}
