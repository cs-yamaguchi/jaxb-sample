package jp.co.comster.jaxb.sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jp.co.comster.jaxb.sample.generate.Book;
import jp.co.comster.jaxb.sample.generate.Items;

public class Sample {

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		InputStream xmlInputStream = new FileInputStream("xml/sample.xml");
		JAXBContext jc = JAXBContext.newInstance(Items.class);
		Unmarshaller u = jc.createUnmarshaller();

		Items items = (Items) u.unmarshal(xmlInputStream);
		xmlInputStream.close();

		for (Book book : items.getBook()) {
			System.out.println(book.getTitle());
			System.out.println(book.getPrice());
			System.out.println(book.getPublisher().getDate());
			System.out.println(book.getPublisher().getEdition());
			book.setTitle("hogehoge");
		}

		OutputStream os = new FileOutputStream("xml/sample_out.xml");
		Marshaller mu = jc.createMarshaller();
		mu.marshal(items, os);

	}

}
