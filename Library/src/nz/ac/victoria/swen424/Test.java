package nz.ac.victoria.swen424;

/*Generated by MPS */

import java.util.List;
import nz.ac.victoria.swen424.weather.WeatherValues;
import nz.ac.victoria.swen424.UsageProfile;
import nz.ac.victoria.swen424.mainTypes.ElProducer;
import nz.ac.victoria.swen424.mainTypes.ElTransformer;
import nz.ac.victoria.swen424.mainTypes.ElConsumer;
import nz.ac.victoria.swen424.mainTypes.ElGrid;
import java.util.LinkedList;
import nz.ac.victoria.swen424.weather.Weather;
import nz.ac.victoria.swen424.weather.WeatherEnum;
import nz.ac.victoria.swen424.weather.WindSpeedEnum;
import nz.ac.victoria.swen424.mainTypes.ProducitionMethodeType;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLEventWriter;
import java.io.FileOutputStream;
import javanet.staxutils.IndentingXMLEventWriter;
import javax.xml.stream.XMLEventFactory;
import nz.ac.victoria.swen424.mainTypes.MainBaseType;

public class Test {
  /*package*/ List<WeatherValues> _weather;
  /*package*/ List<UsageProfile> _usageProfiles;
  /*package*/ List<ElProducer> _producers;
  /*package*/ List<ElTransformer> _transformers;
  /*package*/ List<ElConsumer> _consumers;
  /*package*/ List<ElGrid> _grids;

  public Test() {
    WeatherValues wv;
    _weather = new LinkedList<WeatherValues>();
    wv = new WeatherValues("Auckland", false);
    wv.AppendWeather(new Weather(WeatherEnum.ClearSky, WindSpeedEnum.Flat));
    _weather.add(wv);

    _usageProfiles = new LinkedList<UsageProfile>();
    _usageProfiles.add(new UsageProfile("yester", 45, 18, 92, 55, 99, false));

    ElTransformer toAdd;
    _transformers = new LinkedList<ElTransformer>();
    toAdd = new ElTransformer("Tran", 200, 0, 99);
    _transformers.add(toAdd);
    toAdd = new ElTransformer("Tran2", 200, 0, 99);
    _transformers.add(toAdd);

    ElProducer producer;
    _producers = new LinkedList<ElProducer>();
    producer = new ElProducer("Dam", 0, 1000, ProducitionMethodeType.Conventional, "Tran", "Auckland");
    producer.connectTransformer(findTransformer("Tran"));
    producer.connectWeather(findWeather("Auckland"));
    _producers.add(producer);

    ElConsumer consumer;
    _consumers = new LinkedList<ElConsumer>();
    consumer = new ElConsumer("House", 1000, "Tran2", "yester");
    consumer.connectUsageProfile(findUsageProfile("yester"));
    consumer.connectTransformer(findTransformer("Tran2"));
    _consumers.add(consumer);

    ElGrid grid;
    _grids = new LinkedList<ElGrid>();
    grid = new ElGrid("Auck", 200, 80, 0, "Tran", "Tran2");
    grid.connectTransformer(findTransformer("Tran"));
    grid.connectTransformer(findTransformer("Tran2"));
    _grids.add(grid);
    System.out.println("");

  }

  public UsageProfile findUsageProfile(String profile) {
    for (UsageProfile prof : _usageProfiles) {
      if (profile == prof.GetName()) {
        return prof;
      }
    }
    return null;
  }

  public ElTransformer findTransformer(String trans) {
    for (ElTransformer tran : _transformers) {
      if (trans == tran.getName()) {
        return tran;
      }
    }
    return null;
  }

  public WeatherValues findWeather(String weath) {
    for (WeatherValues weat : _weather) {
      if (weath == weat.GetName()) {
        return weat;
      }
    }
    return null;
  }

  public void writeXML() throws Exception {
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    XMLEventWriter xmlWriter = outputFactory.createXMLEventWriter(new FileOutputStream("output.xml"));
    xmlWriter = new IndentingXMLEventWriter(xmlWriter);
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();

    xmlWriter.add(eventFactory.createStartDocument());
    xmlWriter.add(eventFactory.createStartElement("", "", "powerGridSimulator"));

    writeLayoutXML(xmlWriter);

    MainBaseType.Simulate(1, 24, _producers, _consumers, _transformers, _grids, xmlWriter);

    xmlWriter.add(eventFactory.createEndElement("", "", "powerGridSimulator"));
    xmlWriter.add(eventFactory.createEndDocument());
    xmlWriter.close();
  }

  private void writeLayoutXML(XMLEventWriter xmlWriter) throws Exception {
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    xmlWriter.add(eventFactory.createStartElement("", "", "modelDefinition"));

    // Export Weather 
    for (WeatherValues weather : _weather) {
      weather.writeHeaderData(xmlWriter);
    }

    // Export Usage Profiles 
    for (UsageProfile usageProfile : _usageProfiles) {
      usageProfile.writeHeaderData(xmlWriter);
    }

    // Export Producers 
    for (ElProducer producer : _producers) {
      producer.writeHeaderData(xmlWriter);
    }

    for (ElGrid grid : _grids) {
      grid.writeHeaderData(xmlWriter);
    }
    for (ElTransformer transformer : _transformers) {
      transformer.writeHeaderData(xmlWriter);
    }

    // TODO For all the types 

    xmlWriter.add(eventFactory.createEndElement("", "", "modelDefinition"));
  }

  public static void main(String[] args) throws Exception {
    Test simulator = new Test();
    simulator.writeXML();
  }
}
