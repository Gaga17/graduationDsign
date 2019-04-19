package study.cjj.eloan.base.mapper;

import java.util.List;
import study.cjj.eloan.base.domain.BidRequest;

public interface BidRequestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    List<BidRequest> selectAll();

    int updateByPrimaryKey(BidRequest record);
}