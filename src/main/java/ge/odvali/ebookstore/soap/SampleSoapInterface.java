package ge.odvali.ebookstore.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SampleSoapInterface {

	@WebMethod(action = "http://odvali.ge/getName")
	String getName(@WebParam(name = "name") String name);
}
