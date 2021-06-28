package barrier_server.server;

import barrier_server.BarrierService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


public class BarrierServer {

	  public static BarrierHandler handler;

	  public static BarrierService.Processor processor;

	  private static final Logger logger = LogManager.getLogger(BarrierServer.class);

	  public static void main(String [] args) {

	  	logger.trace("Entering application.");

	  	try {
	      handler = new BarrierHandler();
	      processor = new BarrierService.Processor(handler);

	      Runnable simple = new Runnable() {
	        public void run() {
	          simple(processor);
	        }
	      };      
	     
	      new Thread(simple).start();
	    } catch (Exception x) {
	      x.printStackTrace();
	    }
	  }

	  public static void simple(BarrierService.Processor processor) {
	    try {
	      TServerTransport serverTransport = new TServerSocket(9090);
	      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

	      server.serve();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	 
	}