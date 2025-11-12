package factories;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import configuration.ConfigXML;

public class RemoteBLFactory implements BLFactory {
	
	@Override
	public BLFacade createBLFacade() {
		try {
			ConfigXML c = ConfigXML.getInstance();
			
			String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			
			URL url = new URL(serviceName);
			QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
			
			Service service = Service.create(url, qname);
			return service.getPort(BLFacade.class);
		}catch (Exception e) {
			throw new RuntimeException("Error creating remote BLFacade", e);
		}
	}
}
