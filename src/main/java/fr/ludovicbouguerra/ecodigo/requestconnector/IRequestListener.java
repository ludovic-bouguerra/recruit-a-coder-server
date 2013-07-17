package fr.ludovicbouguerra.ecodigo.requestconnector;

import java.util.EventListener;

public interface IRequestListener extends EventListener{
	
	public void onNewRequest(IRequest request);
	
}
