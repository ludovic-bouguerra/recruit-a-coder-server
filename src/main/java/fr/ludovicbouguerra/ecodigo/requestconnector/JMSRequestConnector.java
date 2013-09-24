package fr.ludovicbouguerra.ecodigo.requestconnector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.swing.event.EventListenerList;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.messageprotocol.LanguageNotFound;
import fr.ludovicbouguerra.ecodigo.messageprotocol.MessageProtocol;
import fr.ludovicbouguerra.ecodigo.messageprotocol.ParametersException;

public class JMSRequestConnector implements IRequestConnector, MessageListener {

	private EventListenerList listeners = new EventListenerList();

	private static int ackMode = Session.AUTO_ACKNOWLEDGE;
	private static String messageQueueName;
	private static String messageBrokerUrl;

	private Session session;
	private boolean transacted = false;
	private MessageProducer replyProducer;
	private MessageProtocol messageProtocol;

	public void init(Properties properties) {
		messageBrokerUrl = properties.getProperty("ecodigo.messenging.url");
		messageQueueName = properties
				.getProperty("ecodigo.messenging.queuename");
		ackMode = Session.AUTO_ACKNOWLEDGE;
	}

	@Override
	public void onMessage(Message message) {
		try {
			Message response = null;

			response = this.session.createMessage();
			
			try {
				response.setStringProperty("response-type",
						"ok");
				response.setStringProperty("response",
						this.messageProtocol.handleProtocolMessage(message));

			} catch (UnexpectedResult e) {
				response.setStringProperty("response-type",
					"UnexpectedResult");
				response.setStringProperty("response",
						e.getMessage());

			} catch (LanguageNotFound e) {
				System.out.println("ok");

			} catch (ParametersException e) {
				System.out.println("ol");
			}

			// Set the correlation ID from the received message to be the
			// correlation id of the response message
			// this lets the client identify which message this is a response to
			// if it has more than
			// one outstanding message to the server
			response.setJMSCorrelationID(message.getJMSCorrelationID());
			System.out.println("Avant envoyée");

			// Send the response to the Destination specified by the JMSReplyTo
			// field of the received message,
			// this is presumably a temporary queue created by the client
			this.replyProducer.send(message.getJMSReplyTo(), response);
			System.out.println("Réponse envoyée");
		} catch (JMSException e) {
			// Handle the exception appropriately

		}
	}

	@Override
	public void listen() {
		try {
			// This message broker is embedded
			BrokerService broker = new BrokerService();
			broker.setPersistent(false);
			broker.setUseJmx(false);
			broker.addConnector(messageBrokerUrl);
			broker.start();

		} catch (Exception e) {
			// Handle the exception appropriately
		}

		// Delegating the handling of messages to another class, instantiate it
		// before setting up JMS so it
		// is ready to handle messages
		this.messageProtocol = new MessageProtocol();
		this.setupMessageQueueConsumer();
	}

	private void setupMessageQueueConsumer() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				messageBrokerUrl);
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			this.session = connection.createSession(this.transacted, ackMode);
			Destination adminQueue = this.session.createQueue(messageQueueName);

			// Setup a message producer to respond to messages from clients, we
			// will get the destination
			// to send to from the JMSReplyTo header field from a Message
			this.replyProducer = this.session.createProducer(null);
			this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Set up a consumer to consume messages off of the admin queue
			MessageConsumer consumer = this.session.createConsumer(adminQueue);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			// Handle the exception appropriately
		}

	}

	protected void fireNewRequest(IRequest request) {
		for (IRequestListener req : getRequestListeners()) {
			req.onNewRequest(request);
		}
	}

	public IRequestListener[] getRequestListeners() {
		return listeners.getListeners(IRequestListener.class);
	}

	@Override
	public void addRequestListener(IRequestListener requestListener) {
		listeners.add(IRequestListener.class, requestListener);

	}
}
