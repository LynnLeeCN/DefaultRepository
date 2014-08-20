package hj.nerp.sms.service;

import hj.nerp.sms.vo.SmsBean;
import hj.nerp.util.thread.AbstractThreadPoolReceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsReceiver extends AbstractThreadPoolReceiver {

	Logger logger = LoggerFactory.getLogger(SmsReceiver.class);

	public SmsReceiver() {
			
	}

	@Override
	public void doPoolContainerData(Object data) {
		logger.debug("SmsReceiver->ThreadId:" + Thread.currentThread().getId());
		SmsBean bean = (SmsBean)data;
		System.out.println(bean);

	}

}
