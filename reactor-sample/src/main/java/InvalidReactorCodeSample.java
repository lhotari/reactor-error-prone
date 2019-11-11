import reactor.core.publisher.Flux;

public class InvalidReactorCodeSample {
    @SuppressWarnings("InvalidPatternSyntax")
    public static void main(String[] args) throws InterruptedException {
        // example from https://projectreactor.io/docs/core/release/reference/index.html#faq.chain
        Flux<String> flux = Flux.just("something", "chain");
        flux.map(secret -> secret.replaceAll(".", "*"));
        flux.subscribe(next -> System.out.println("Received: " + next));

        // demonstrate issue where "inner flux" is ignored
        Flux.range(1, 10)
                .map(n ->
                        Flux.range(1, n * n)
                                .doOnNext(i -> System.out.println("NEVER PRINTED:" + n + " - " + i)))
                .then()
                .subscribe();

        Flux.range(1, 10)
                .flatMapSequential(n ->
                        Flux.range(1, n * n)
                                .doOnNext(i -> System.out.println(n + " - " + i)))
                .then()
                .subscribe();

        // examples above complete asynchronously (and they don't have proper chaining to demonstrate the checks),
        // might need a short delay here to demonstrate the behaviour
        Thread.sleep(1000L);
    }
}
