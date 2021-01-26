package ge.odvali.ebookstore.soap;

public class SampleSoapImpl implements SampleSoapInterface {
    @Override
    public String getName(String name) {
        return name;
    }
}
