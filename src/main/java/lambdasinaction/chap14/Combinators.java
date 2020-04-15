package lambdasinaction.chap14;

import java.util.function.Function;

/**
 * repeat(1,f) = x->2*x
 * repeat(n,f) = compose(f,n-1)
 * compose(f,n-1) = g(repeat(n-1,f)) = 2 * repeat(n-1,f)
 * f(x) = x->2*x
 * g(x) = f(x) = x->2*x
 * g(f(x)) = 2*x
 * repeat(0,f)= x->x
 * 推理:
 * f(x) = x->2*x
 * repeat(3,f)= compose(f,repeat(2,f)) = f(repeat(2,f))= repeat(2,f)->2*repeat(2,f)
 * repeat(2,f)= compose(f,repeat(1,f)) = 2*repeat(1,f)
 * repeat(1,f)= compose(f,repeat(0,f)) = 2*repeat(0,f)
 * repeat(0,f)= x->x
 * x=10
 */
public class Combinators {

    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));
    }

    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }
}
