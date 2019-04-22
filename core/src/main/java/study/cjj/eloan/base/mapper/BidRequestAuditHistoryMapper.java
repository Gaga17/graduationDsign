package study.cjj.eloan.base.mapper;


import java.util.List;

public interface BidRequestAuditHistoryMapper {

    int insert(BidRequestAuditHistory record);

    BidRequestAuditHistory selectByPrimaryKey(Long id);

    /**
     * 列出一个借款相关的所有审核对象
     * @param id
     * @return
     */
	List<BidRequestAuditHistory> listAuditHistorayByBidRequest(Long id);

}