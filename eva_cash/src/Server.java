public class Server {
    public Cache cache;

    public Server(int cacheSize) {
        this.cache = new Cache(cacheSize);
    }

    public long getFibonacciNumber(int value) {
        cache.updateHistory(value);
        if(cache.checkKey(value)) {
            return cache.getValueByKey(value);
        } else {
            long fib_number = Fibonacci.fibonacci(value);
            cache.addValue(value, fib_number);

            return fib_number;
        }
    }
}
