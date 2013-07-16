package fr.ludovicbouguerra.ecodigo.controller;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.messageprotocol.LanguageNotFound;
import fr.ludovicbouguerra.ecodigo.messageprotocol.MessageProtocol;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class CompilationController implements MessageListener {
	private static int ackMode = Session.AUTO_ACKNOWLEDGE;
	private static String messageQueueName;
	private static String messageBrokerUrl;

	private Session session;
	private boolean transacted = false;
	private MessageProducer replyProducer;
	private MessageProtocol messageProtocol;

	static {
		messageBrokerUrl = "tcp://localhost:61616";
		messageQueueName = "client.messages";
		ackMode = Session.AUTO_ACKNOWLEDGE;
	}

	public CompilationController() {
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

	@Override
	public void onMessage(Message message) {
		try {
			Message response = this.session.createMessage();

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
				

			}

			// Set the correlation ID from the received message to be the
			// correlation id of the response message
			// this lets the client identify which message this is a response to
			// if it has more than
			// one outstanding message to the server
			response.setJMSCorrelationID(message.getJMSCorrelationID());

			// Send the response to the Destination specified by the JMSReplyTo
			// field of the received message,
			// this is presumably a temporary queue created by the client
			this.replyProducer.send(message.getJMSReplyTo(), response);
		} catch (JMSException e) {
			// Handle the exception appropriately

		}
	}

}
