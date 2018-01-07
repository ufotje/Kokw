package be.kokw.repositories.magazines;

import be.kokw.bean.magazines.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("tradeRepo")
public interface TradeRepo extends JpaRepository<Trade,Integer> {

    @Transactional
    Trade findTradeByNameMag(String name);
}
