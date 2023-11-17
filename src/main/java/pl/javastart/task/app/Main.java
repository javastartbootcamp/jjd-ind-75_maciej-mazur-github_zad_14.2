package pl.javastart.task.app;

public class Main {

    /*
    Zakładam pojawienie się wyjątku ClassNotFoundException za mało prawdopodobne,
    postanowiłem więc go nie obsłużyć.
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Review review = new Review();
        review.run();
    }
}
