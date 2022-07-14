package repo;

import com.example.assignmentXML.Book;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepoServices {

    public static <T> List<T> readData() {
//        T t = new T();
//        Field[] fields =  t.getClass().getDeclaredFields();
//        String entities = "";
//        for (Field field : fields) {
//            System.out.printf("%s %s %s%n",
//                    Modifier.toString(field.getModifiers()),
//                    field.getType().getSimpleName(),
//                    field.getName()
//            );
//            entities+= field.getName();
//        }
        return null;
    }

    public static List<Book> readBook() {
        Book book = new Book();
        Field[] fields =  book.getClass().getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());
        }
        try {
            List<Book> books = new ArrayList<Book>();
            Book tempBook = null;
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("db/books.xml"));
            while (reader.hasNext()) {
                reader.next();
                if (reader.getEventType() == XMLStreamReader.START_ELEMENT) {
                    if (reader.getLocalName().equalsIgnoreCase("book")) {
                        tempBook = new Book();
                        if (reader.getAttributeCount() > 0) {
                            String id = reader.getAttributeValue(null, "id");
                            tempBook.setId(id);
                        }
                    }
                    if (reader.getLocalName().equalsIgnoreCase("author")) {
                        tempBook.setAuthor(reader.getElementText());
                    }
                    if (reader.getLocalName().equalsIgnoreCase("title")) {
                        tempBook.setTitle(reader.getElementText());
                    }
                    if (reader.getLocalName().equalsIgnoreCase("genre")) {
                        tempBook.setGenre(reader.getElementText());
                    }
                    if (reader.getLocalName().equalsIgnoreCase("price")) {
                        tempBook.setPrice(reader.getElementText());
                    }
                    if (reader.getLocalName().equalsIgnoreCase("publish_date")) {
                        tempBook.setPublish_date(reader.getElementText());
                    }
                    if (reader.getLocalName().equalsIgnoreCase("description")) {
                        tempBook.setDescription(reader.getElementText());
                    }
                }
                if (reader.getEventType() == XMLStreamReader.END_ELEMENT) {
                    if (reader.getLocalName().equalsIgnoreCase("book")) {
                        books.add(tempBook);
                    }
                }
            }
            return books;
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(RepoServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
