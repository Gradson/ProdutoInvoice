package br.com.gradson.atech.jms;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class JmsSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void toQueue(List<Object> objects, final JmsQueueType queue) {
		if(!CollectionUtils.isEmpty(objects)) {
			objects.forEach(obj -> toQueue(obj, queue));
		}
	}
	
	public void toQueue(final Object object, final JmsQueueType queue) {
		if(object != null) {
			jmsTemplate.convertAndSend(queue.name(), object);
		}
	}
	
	public boolean hasElementsQueued(JmsQueueType queue) {
		return (boolean) jmsTemplate.browse(queue.name(), new BrowserCallback<Object>() {

			@Override
			public Object doInJms(Session session, QueueBrowser browser) throws JMSException {
				return browser.getEnumeration().hasMoreElements();
			}
		});
	}
}
