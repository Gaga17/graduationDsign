package study.cjj.eloan.base.constant;

import java.math.BigDecimal;

/**
 * 系统中的常量
 * @author cjj
 * @date 2019年3月19日
 */
public class BidConst {

	public static final int DISP_SCALE = 2;// 显示给前台精度
	public static final int SCALE = 4;// 数据库保存精度
	public static final int CAL_SCALE = 8;// 数据计算过程中的精度

	public static final BigDecimal ZERO = new BigDecimal("0.0000");
	public static final BigDecimal BORROW_LIMIT = new BigDecimal("2000.0000");	//初始额度
	
	public static final String DEFALUT_ADMIN = "admin";	//默认管理员
	public static final String DEFALUT_ADMIN_PASSWORD = "1111";	//管理员默认密码
	
	

}
