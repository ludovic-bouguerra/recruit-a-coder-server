package fr.ludovicbouguerra.ecodigo.requestconnector;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.ludovicbouguerra.ecodigo.tests.tools.Context;
import fr.ludovicbouguerra.ecodigo.tests.tools.ContextException;

public class JMSRequestConnectorTest {
	
	private IRequestConnector jms;
	private RequestListener requestListener;
	
	
	@Before
	public void init() throws ContextException{
		requestListener = new RequestListener();
		jms = new JMSRequestConnector();
		jms.init(Context.getInstance().getConfiguration());
		jms.addRequestListener(requestListener);
		
	}
	
	@Test
	public void testReceiveRequest() throws JMSException, ContextException{
		/*new Thread(){
			
			public void run(){
				jms.listen();
			}
			
		}.start();
		
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Context.getInstance().getConfiguration().getProperty("ecodigo.messenging.url"));

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue(Context.getInstance().getConfiguration().getProperty("ecodigo.messenging.queuename"));

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages
        Message message = session.createMessage();
        message.setStringProperty("test", "value");
        // Tell the producer to send the message
        System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
        producer.send(message);

        // Clean up
        session.close();
        connection.close();
        
        Assert.assertNotNull(requestListener.getRequest());
        
        */
		
		
	}
		
	
}

class RequestListener implements IRequestListener{

	private IRequest request;
	
	@Override
	public void onNewRequest(IRequest request) {
		this.request = request;
	}
	
	public IRequest getRequest(){
		return request;
	}
	
}