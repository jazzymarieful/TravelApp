package sr.unasat.travelapp.chainofresponsibilities;

public interface Chain {

    public void setNextChain(Chain nextChain);

    public void getReport(String request);
}
