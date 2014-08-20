package hj.nerp.util;

import hj.nerp.util.thread.AbstractThreadPoolReceiver;
import hj.nerp.util.thread.AbstractThreadPoolSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolContainer {
	private static final Logger logger = LoggerFactory
			.getLogger(ThreadPoolContainer.class);
	public ArrayList<Object> dataList;
	private AbstractThreadPoolSender sender;
	private static Map<String, ThreadPoolContainer> registedPoolContainer = new HashMap<String, ThreadPoolContainer>();

	private ThreadPoolContainer() {
		sender = null;
		dataList = new ArrayList<Object>();
	}

	public static AbstractThreadPoolSender getThreadPoolSender(String key,
			String threadPoolSenderClassName,
			String threadPoolReceiverClassName, long intervalTime) {
		AbstractThreadPoolSender sender = null;
		synchronized (registedPoolContainer) {
			if (registedPoolContainer.containsKey(key)) {
				sender = ((ThreadPoolContainer) (registedPoolContainer.get(key))).sender;
				logger.debug("Get thread pool sender by key: " + key);
			} else {
				ThreadPoolContainer container = new ThreadPoolContainer();
				sender = container.strartSenderAndReceiverThread(
						threadPoolSenderClassName, threadPoolReceiverClassName,
						intervalTime);
				registedPoolContainer.put(key, container);
				logger.debug("Could not get thread pool sender by key: " + key);
				logger.debug("New a ThreadPoolContainer by: "
						+ threadPoolSenderClassName + "&"
						+ threadPoolReceiverClassName);
			}
		}
		return sender;
	}

	private AbstractThreadPoolSender strartSenderAndReceiverThread(
			String senderClassName, String receiverClassName, long intervalTime) {
		Object senderObj = getClassByName(senderClassName);
		Object receiverObj = getClassByName(receiverClassName);
		if (senderObj != null && receiverObj != null
				&& senderObj instanceof AbstractThreadPoolSender
				&& receiverObj instanceof AbstractThreadPoolReceiver) {
			AbstractThreadPoolSender sender = (AbstractThreadPoolSender) senderObj;
			sender.setContainer(this);
			sender.setIntervalTime(intervalTime);
			AbstractThreadPoolReceiver receiver = (AbstractThreadPoolReceiver) receiverObj;
			receiver.setContainer(this);
			sender.start();
			receiver.start();
			// TODO log senderClassName,receiverClassName Thread started!
			logger.debug(senderClassName + "&" + receiverClassName
					+ "Thread started!");
			return sender;
		} else {
			return null;
		}

	}

	private Object getClassByName(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
