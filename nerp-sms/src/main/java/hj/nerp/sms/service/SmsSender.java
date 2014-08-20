package hj.nerp.sms.service;

import hj.nerp.dao.IBaseDao;
import hj.nerp.dao.vo.Ctlm1342;
import hj.nerp.sms.vo.SmsBean;
import hj.nerp.util.thread.AbstractThreadPoolSender;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmsSender extends AbstractThreadPoolSender {
	Logger logger = LoggerFactory.getLogger(SmsSender.class);
	private IBaseDao baseDao;
	private static final String PREPARATION_SEND_STRP_0 = " update ctlm1342 set date_plansend=getdate() where convert(varchar(10),date_plansend ,121)='1900-01-01' and flag_send='N'";
	private static final String PREPARATION_SEND_STRP_1 = " update ctlm1342 set flag_send = 'S' where flag_send='N' and convert(varchar(10),date_plansend ,121)<>'1900-01-01' and datediff(s,date_plansend,getdate())>-10 ";
	private static final String PREPARATION_SEND_STRP_2 = "from hj.nerp.dao.vo.Ctlm1342 where flag_send = 'S'";

	public SmsSender() {
		super();
//		baseDao = getBaseDaoByApplicationContext("spring/config/applicationContext.xml",
//				"baseDao");
	}

	@Override
	public List<?> getPoolContainerData() {
		logger.debug("SmsSender->ThreadId:" + Thread.currentThread().getId());
		List<SmsBean> smsList = new ArrayList<SmsBean>();
		List<Ctlm1342> ctlm1342List = baseDao.find(PREPARATION_SEND_STRP_2);
		for (Ctlm1342 ctlm1342 : ctlm1342List) {
			SmsBean bean = new SmsBean();
			bean.setIdSms(ctlm1342.getIdSms());
			bean.setVarMobile(ctlm1342.getVarMobile());
			bean.setVarContent(ctlm1342.getVarContent());
			smsList.add(bean);
			System.out.println(ctlm1342);
		}
		return smsList;
	}

	private IBaseDao getBaseDaoByApplicationContext(
			String applicationContextPath, String beanName) {
		IBaseDao baseDao;
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					applicationContextPath);
			baseDao = (IBaseDao) context.getBean(beanName);
		} catch (Exception e) {
			baseDao = null;
			e.printStackTrace();
		}
		return baseDao;
	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
