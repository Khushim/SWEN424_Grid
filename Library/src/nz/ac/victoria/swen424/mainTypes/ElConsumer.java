package nz.ac.victoria.swen424.mainTypes;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;

import nz.ac.victoria.swen424.UsageProfile;

public class ElConsumer extends MainBaseType{
	private int _maxConsumption;
	private String _connectName;
	private String _usageName;
	private ElTransformer _connect;
	private UsageProfile _usageProfile;
	
	
public ElConsumer(String name, int maxConsumption, String connectName, String usageName){
	super(name);
	_maxConsumption = maxConsumption;
	_connectName = connectName;
	_usageName = usageName;
	// TODO: Real reference
	//_usageProfile = new UsageProfile("test");
	}

public void connectTransformer(ElTransformer connect){
	_connect = connect;
	connect.addLeftConnection(this);
//	if(connect.decreaseUsage(_maxConsumption, this) == true){
//		if(connect.getLeftNet() == 1 || connect.getRightNet() == 1){
//			if(_connect == null){ _connect = connect; System.out.println("Consumer " + _name + " connected to transformer");}
//			else{ System.out.println("Could not connect transformer to grid as it would exceed the maximum capacity of 1"); }
//		}
//		else{ System.out.println("Could not connect transformer due to a difference in voltage levels");}
//	}
}

public void connectUsageProfile(UsageProfile usage){
	_usageProfile = usage;
}

public String getTransName(){
	return _connectName;
}
public String getUsageName(){
	return _usageName;
}

public int getMaxConsumption(){
	return _maxConsumption;
}

@Override
public String getData() {
	// TODO show the data related to the consumer
	// _name, _minConsumption & _maxConsumption
	return _name;
}

@Override
public void writeHeaderData(XMLEventWriter xmlWriter) throws XMLStreamException {
	// TODO Auto-generated method stub
	
}

@Override
SimulationStatus Simulate(int time) throws Exception {

	_simStat = new SimulationStatus();
	_simStat.type = this;
	_simStat.minElectricity = 0;
	_simStat.maxElectricity = -_maxConsumption;
	
	int dayTime = time % 24;
	
	if (dayTime < 7)
	{
		_simStat.currentElectricity = -_maxConsumption * _usageProfile.GetNight() / 100.0;
	}
	else if (dayTime < 12)
	{
		_simStat.currentElectricity = -_maxConsumption * _usageProfile.GetMorning() / 100.0;
	}
	else if (dayTime < 14)
	{
		_simStat.currentElectricity = -_maxConsumption * _usageProfile.GetMidday() / 100.0;		
	}
	else if (dayTime < 18)
	{
		_simStat.currentElectricity = -_maxConsumption * _usageProfile.GetAfternoon() / 100.0;
	}
	else
	{
		_simStat.currentElectricity = -_maxConsumption * _usageProfile.GetEavening() / 100.0;
	}	
	
	return _simStat;
}

@Override
public void writeSimulationData(XMLEventWriter xmlWriter) throws XMLStreamException {
	XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	
	xmlWriter.add(eventFactory.createStartElement("", "", "consumer"));
	xmlWriter.add(eventFactory.createAttribute("id", _guid.toString()));
	xmlWriter.add(eventFactory.createAttribute("name", _name));
	xmlWriter.add(eventFactory.createAttribute("consumption", Double.toString(-_simStat.currentElectricity)));
	xmlWriter.add(eventFactory.createAttribute("usage", Double.toString(_simStat.getUsage())));
	xmlWriter.add(eventFactory.createAttribute("maxConsumption", Integer.toString(_maxConsumption)));
	xmlWriter.add(eventFactory.createEndElement("", "", "consumer")); // </consumer>
}
}
