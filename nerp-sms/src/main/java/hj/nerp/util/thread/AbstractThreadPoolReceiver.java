package hj.nerp.util.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hj.nerp.util.ThreadPoolContainer;

public abstract class AbstractThreadPoolReceiver extends Thread {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractThreadPoolReceiver.class);
	protected ThreadPoolContainer container;

	public abstract void doPoolContainerData(Object data);

	public AbstractThreadPoolReceiver() {
		super();
		container = null;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (container.dataList) {
				while (container.dataList.isEmpty()) {
					try {
						container.dataList.wait();
						doPoolContainerData(container.dataList.remove(0));
					} catch (InterruptedException e) {
						e.printStackTrace();
						// TODO log AbstractThreadPoolReceiver was interrupted!
						logger.debug("AbstractThreadPoolReceiver was interrupted!");
					}
				}
			}
		}
	}

	public ThreadPoolContainer getContainer() {
		return container;
	}

	public void setContainer(ThreadPoolContainer container) {
		this.container = container;
	}

}
