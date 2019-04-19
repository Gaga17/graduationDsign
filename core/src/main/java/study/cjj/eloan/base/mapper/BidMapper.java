package study.cjj.eloan.base.mapper;

import java.util.List;
import study.cjj.eloan.base.domain.Bid;

public interface BidMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);

    List<Bid> selectAll();

    int updateByPrimaryKey(Bid record);
}