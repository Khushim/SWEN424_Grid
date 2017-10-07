package nz.ac.victoria.swen424.mainTypes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import java.util.Set;
import javax.xml.stream.XMLStreamException;

public class ElTransformer extends MainBaseType
{
	private int _maxcapacity;
	private int _usage;
	private int _efficiency;
	private Set<MainBaseType> connections;

	public ElTransformer(String name, int maxcapacity, int usage, int efficiency){
		super(name);
		_maxcapacity = maxcapacity;
		_usage = usage;
		_efficiency = efficiency;
		connections = new HashSet<MainBaseType>();
	}
	
	public void addConnection(MainBaseType object)
	{
		connections.add(object);
	}
	
	public Set<MainBaseType> getConnections()
	{
		return connections;
	}
	
	public String getName(){
		return _name;
	}
	
//	public Boolean increaseUsage(int increment, MainBaseType connect){
//		if(_usage+(increment*(_efficiency)/100) > _maxcapacity){
//			System.out.println("Cannot exceed maximum capacity of transformer");
//			return false;
//		}
//		_usage += (increment*(_efficiency/100));
//		addConnection(connect);
//		return true;
//	}
//	
//	public Boolean decreaseUsage(int increment, MainBaseType connect){
//		if(_usage-(increment*(_efficiency/100)) < 0){
//			System.out.println("Cannot fall below 0 usage on transformer");
//			return false;
//		}
//		_usage -= (increment*(_efficiency/100));
//		addConnection(connect);
//		return true;
//	}
	
//	public void addConnection(MainBaseType connect){
//		connections.add(connect);
//	}
//	
//	public void removeConnection(MainBaseType remove){
//		connections.remove(remove);
//	}
//	
//	public Set<MainBaseType> getConnections(){
//		return connections;
//	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeHeaderData(XMLEventWriter xmlWriter) throws XMLStreamException
	{
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		
		xmlWriter.add(eventFactory.createStartElement("", "", "transformer"));
		xmlWriter.add(eventFactory.createAttribute("id", _guid.toString()));
		xmlWriter.add(eventFactory.createAttribute("name", _name));
		xmlWriter.add(eventFactory.createAttribute("maxCapacity", Integer.toString(_maxcapacity)));
		xmlWriter.add(eventFactory.createAttribute("efficiency", Integer.toString(_efficiency)));
		
		// Write connection
		xmlWriter.add(eventFactory.createStartElement("", "", "connections"));
		for (MainBaseType mainBaseType : connections)
		{
			if(mainBaseType instanceof ElProducer)
			{
				xmlWriter.add(eventFactory.createStartElement("", "", "producer"));
			}
			else if (mainBaseType instanceof ElConsumer)
			{
				xmlWriter.add(eventFactory.createStartElement("", "", "consumer"));
			}

			xmlWriter.add(eventFactory.createAttribute("id", mainBaseType.GetGuid().toString()));
			xmlWriter.add(eventFactory.createAttribute("name", mainBaseType.GetName()));
			xmlWriter.add(eventFactory.createAttribute("side", "left"));

			if(mainBaseType instanceof ElProducer)
			{
				xmlWriter.add(eventFactory.createEndElement("", "", "producer"));
			}
			else if (mainBaseType instanceof ElConsumer)
			{
				xmlWriter.add(eventFactory.createEndElement("", "", "consumer"));
			}
		}
		
		for (ElGrid grid : connections)
		{
			xmlWriter.add(eventFactory.createStartElement("", "", "grid"));
			xmlWriter.add(eventFactory.createAttribute("id", grid.GetGuid().toString()));
			xmlWriter.add(eventFactory.createAttribute("name", grid.GetName()));
			xmlWriter.add(eventFactory.createAttribute("side", "right"));
			xmlWriter.add(eventFactory.createEndElement("", "", "grid"));
		}
		xmlWriter.add(eventFactory.createEndElement("", "", "connections")); // </connections>
		
		xmlWriter.add(eventFactory.createEndElement("", "", "transformer")); // </transformer>
	}

	@Override
	SimulationStatus Simulate(int time) throws Exception
	{
		_simStat = new SimulationStatus();
		_simStat.type = this;
		_simStat.maxElectricity = _maxcapacity;
		_simStat.minElectricity = 0;
		
		double elUsage = 0;
		
		for (MainBaseType mainBaseType : connections)
		{
			elUsage += mainBaseType._simStat.currentElectricity;
		}
		
		_simStat.loss = Math.abs(elUsage) * (100 - _efficiency) / 100 ;
		_simStat.currentElectricity = elUsage - _simStat.loss;
		if(_simStat.currentElectricity > _maxcapacity)
		{
			_simStat.isOk = false;
		}
		
		return _simStat;
	}

	@Override
	public void writeSimulationData(XMLEventWriter xmlWriter) throws XMLStreamException
	{
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		
		xmlWriter.add(eventFactory.createStartElement("", "", "transformer"));
		xmlWriter.add(eventFactory.createAttribute("id", _guid.toString()));
		xmlWriter.add(eventFactory.createAttribute("name", _name));
		xmlWriter.add(eventFactory.createAttribute("isOk", Boolean.toString(_simStat.isOk)));
		xmlWriter.add(eventFactory.createAttribute("loss", Double.toString(_simStat.loss)));
		xmlWriter.add(eventFactory.createAttribute("currentUsage", Double.toString(_simStat.currentElectricity)));
		xmlWriter.add(eventFactory.createAttribute("usage", Double.toString(_simStat.getUsage())));
		xmlWriter.add(eventFactory.createAttribute("maxCapacity", Integer.toString(_maxcapacity)));
		xmlWriter.add(eventFactory.createEndElement("", "", "transformer")); // </transformer>
	}
}
