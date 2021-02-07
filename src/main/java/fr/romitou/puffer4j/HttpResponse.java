package fr.romitou.puffer4j;

public enum HttpResponse {
    HTTP_200("SUCCESSFUL"),
    HTTP_204("NO CONTENT"),
    HTTP_400("BAD REQUEST"),
    HTTP_401("UNAUTHORIZED"),
    HTTP_403("FORBIDDEN"),
    HTTP_404("NOT FOUND"),
    HTTP_500("INTERNAL SERVER ERROR");

    public final String label;

    HttpResponse(String label) {
        this.label = label;
    }
}
