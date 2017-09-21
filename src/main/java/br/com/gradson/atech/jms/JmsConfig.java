package br.com.gradson.atech.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class JmsConfig {

	@Bean
	public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
		final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		//factory.setConcurrency("1-3");
		return factory;
	}

	@Bean(name = "connectionFactory")
	public ConnectionFactory connectionFactory(RedeliveryPolicy redeliveryPolicy) {
		final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.schedulerSupport=true");
		activeMQConnectionFactory.setNonBlockingRedelivery(true);
		activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
		activeMQConnectionFactory.setTrustAllPackages(true);
		return activeMQConnectionFactory;
	}
	
	@Bean
	public RedeliveryPolicy redeliveryPolicy() {
		final RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
		redeliveryPolicy.setMaximumRedeliveries(2);
		return redeliveryPolicy;
	}
}