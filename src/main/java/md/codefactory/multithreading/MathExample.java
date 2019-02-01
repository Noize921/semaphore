package md.codefactory.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MathExample {

    public static CompletableFuture<Double> log(Double n) {
        return CompletableFuture.supplyAsync(() -> Math.log10(n));
    }

    public static CompletableFuture<Double> factorial(int n) {
        return CompletableFuture.supplyAsync(() -> new Double(fact(n)));
    }

    private static int fact(int n) {
        return (n == 0) ? 1 : n * fact(n - 1);
    }

    public static void main(String[] args) {
        int n = 9;

        CompletableFuture<Double> result =
                factorial(n)
                        .thenCombine(log(new Double(n)), (f, l) -> f + l)
                        .thenApply(partial -> partial + 7);
//                        .thenApply(res -> "Result = "  + res);


        CompletableFuture<Double> result2 =
                factorial(n)
                    .thenCompose(f -> log(f))
                    .thenApply(partial -> partial + 7);

        futureResult(result);
        futureResult(result2);
    }

    private static void futureResult(CompletableFuture<Double> text){
        try {
            System.out.println(text.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
