package ge.odvali.ebookstore.soap.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDTO {
	private String name;

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}
}
