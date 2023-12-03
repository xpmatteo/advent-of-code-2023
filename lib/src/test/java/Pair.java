import java.util.function.Function;

public record Pair<L, R>(L left, R right) {

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public static <A, B, C> Function<A, Pair<A, C>> split(Function<A, B> f, Function<A, C> g) {
        return a -> new Pair(f.apply(a), g.apply(a));
    }

    public static <A, B, C, D> Function<Pair<A,C>, Pair<B,D>> cross(Function<A,B> f, Function<C,D> g) {
        return acPair -> Pair.of(f.apply(acPair.left()), g.apply(acPair.right()));
    }
}
