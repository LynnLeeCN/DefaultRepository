package hj.nerp.sms.service.server;

import hj.nerp.util.thread.AbstractThreadPoolSender;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerDbScanner extends AbstractThreadPoolSender {
	Logger log = LoggerFactory.getLogger(ServerDbScanner.class);
	
	public ServerDbScanner() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<?> getPoolContainerData() {
		// TODO Auto-generated method stub
		return null;
	}

}
