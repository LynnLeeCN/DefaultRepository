package hj.nerp.util.thread;

import hj.nerp.util.ThreadPoolContainer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public abstract class AbstractThreadPoolSender extends Thread {
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractThreadPoolSender.class);
	protected ThreadPoolContainer container;
	protected long intervalTime;

	public AbstractThreadPoolSender() {
		super();
		container = null;
		intervalTime = 500L;
	}

	public abstract List<?> getPoolContainerData();

	@Override
	public void run() {
		while (true) {
			List<?> dataList = getPoolContainerData();
			try {
				Assert.notEmpty(dataList);
				synchronized (container.dataList) {
					container.dataList.addAll(dataList);
					container.dataList.notifyAll();
				}
				sleep(intervalTime);
			} catch (IllegalArgumentException e) {
				// TODO log AbstractThreadPoolSender doesn't have data to send.
				logger.debug("AbstractThreadPoolSender doesn't have data to send.");
			} catch (InterruptedException e) {
				e.printStackTrace();
				this.interrupt();
				// TODO log AbstractThreadPoolSender was interrupted!
				logger.debug("AbstractThreadPoolSender was interrupted!");

			}
		}
	}

	public void addContainerData(Object obj) {
		synchronized (container.dataList) {
			container.dataList.add(obj);
		}
	}

	public void addContainerDataList(List<Object> objList) {
		synchronized (container.dataList) {
			container.dataList.addAll(objList);
		}
	}

	public ThreadPoolContainer getContainer() {
		return container;
	}

	public void setContainer(ThreadPoolContainer container) {
		this.container = container;
	}

	public long getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(long intervalTime) {
		this.intervalTime = intervalTime;
	}

}
