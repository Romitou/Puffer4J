package fr.romitou.puffer4j.responses;

public class PufferResponse<T> {

    private PufferPaging paging;

    private T response;

    public PufferPaging getPaging() {
        return paging;
    }

    public void setPaging(PufferPaging paging) {
        this.paging = paging;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
