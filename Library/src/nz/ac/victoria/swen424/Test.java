package nz.ac.victoria.swen424;

import java.util.List;

import nz.ac.victoria.swen424.weather.WeatherValues;
import nz.ac.victoria.swen424.mainTypes.ElGrid;
import nz.ac.victoria.swen424.mainTypes.ElProducer;
import nz.ac.victoria.swen424.mainTypes.ElTransformer;
import nz.ac.victoria.swen424.mainTypes.ElConsumer;
import nz.ac.victoria.swen424.mainTypes.ProducitionMethodeType;

import java.util.LinkedList;

import nz.ac.victoria.swen424.weather.Weather;
import nz.ac.victoria.swen424.weather.WeatherEnum;
import nz.ac.victoria.swen424.weather.WindSpeedEnum;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLEventWriter;

import java.io.FileOutputStream;

import javanet.staxutils.IndentingXMLEventWriter;

import javax.xml.stream.XMLEventFactory;

public class Test
{
	/*package*/ List<WeatherValues> _weather;
	  /*package*/ List<ElProducer> _producers;
	  /*package*/ List<ElTransformer> _transformers;
	  /*package*/ List<ElConsumer> _consumers;
	  /*package*/ List<ElGrid> _grids;


	  public Test() {
	    WeatherValues wv;
	    _weather = new LinkedList<WeatherValues>();
	    wv = new WeatherValues("Wellington", false);
	    wv.AppendWeather(new Weather(WeatherEnum.Rain, WindSpeedEnum.Flat));
	    wv.AppendWeather(new Weather(WeatherEnum.ClearSky, WindSpeedEnum.HurricaneForce));
	    _weather.add(wv);
	    wv = new WeatherValues("Auckland", true);
	    wv.AppendWeather(new Weather(WeatherEnum.ClearSky, WindSpeedEnum.Flat));
	    _weather.add(wv);

	    _transformers = new LinkedList<ElTransformer>();
	    _transformers.add(new ElTransformer("tran", 80, 20, 100, 4, 1));
	    _transformers.add(new ElTransformer("tran2", 80, 20, 100, 4, 1));

	    
	    _producers = new LinkedList<ElProducer>();
	    ElProducer producer = new ElProducer("prod", 1, 50, ProducitionMethodeType.Wind, "tran");
	    _producers.add(producer);

	    ElConsumer consumer;
	    _consumers = new LinkedList<ElConsumer>();
	    consumer = new ElConsumer("house", 0, 10, "tran", null);
	    _consumers.add(consumer);
	    consumer = new ElConsumer("school", 0, 10, "tran", null);
	    _consumers.add(consumer);

	    for (ElConsumer consumer_ : _consumers) {
	      consumer_.connectTransformer(_transformers.get(0));
	    }

	    _grids = new LinkedList<ElGrid>();
	    ElGrid grid = new ElGrid(200, 10, 100, 4, "Unassigned", "tran", "tran2");
	    _grids.add(grid);
	    System.out.println("");

	    for (ElGrid grid_ : _grids) {
	      grid_.connectTransformer(_transformers.get(0));
	      grid_.connectTransformer(_transformers.get(1));
	    }

	    for (ElProducer producer_ : _producers) {
	      producer_.connectTransformer(_transformers.get(0));
	    }
	  }

	  public void writeXML() throws Exception {
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	    XMLEventWriter xmlWriter = outputFactory.createXMLEventWriter(new FileOutputStream("C:/Data/output.xml"));
	    xmlWriter = new IndentingXMLEventWriter(xmlWriter);
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();

	    xmlWriter.add(eventFactory.createStartDocument());
	    xmlWriter.add(eventFactory.createStartElement("", "", "powerGridSimulator"));

	    writeLayoutXML(xmlWriter);

	    simulateAndExport(xmlWriter);

	    xmlWriter.add(eventFactory.createEndElement("", "", "powerGridSimulator"));
	    xmlWriter.add(eventFactory.createEndDocument());
	    xmlWriter.close();
	  }

	  private void writeLayoutXML(XMLEventWriter xmlWriter) throws Exception {
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	    xmlWriter.add(eventFactory.createStartElement("", "", "modelDefinition"));

	    // Export Producers 
	    xmlWriter.add(eventFactory.createStartElement("", "", "weatherList"));
	    for (WeatherValues weather : _weather) {
	      weather.writeHeaderData(xmlWriter);
	    }
	    xmlWriter.add(eventFactory.createEndElement("", "", "weatherList"));

	    // Export Producers 
	    xmlWriter.add(eventFactory.createStartElement("", "", "producers"));
	    for (ElProducer producer : _producers) {
	      producer.writeHeaderData(xmlWriter);
	    }
	    xmlWriter.add(eventFactory.createEndElement("", "", "producers"));

	    // TODO For all the types 

	    xmlWriter.add(eventFactory.createEndElement("", "", "modelDefinition"));
	  }

	  private void simulateAndExport(XMLEventWriter xmlWriter) throws Exception {
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	    xmlWriter.add(eventFactory.createStartElement("", "", "simulation"));

	    // TODO The fun stuff comes in here 

	    xmlWriter.add(eventFactory.createEndElement("", "", "modelDefinition"));
	  }

	  public void Test(int i) {
	  }

	  public static void main(String[] args) {
	    new Test();
	  }
}
