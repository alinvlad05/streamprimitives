import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

//Working with Primitives

//API=a set of functions and procedures allowing the creation of applications
//equivalent=a thing that is equal to or corresponds with another in value, amount, function, meaning, etc.
//finite=limited in size or extent.
//accumulator=a thing that accumulates something.
//accumulate=to collect a large number of things over a long period of time
//reduction=the action or fact of making something smaller or less in amount, degree, or size.
//running total=a total that is continually adjusted to take account of further items.
//convenience=the quality of being useful, easy, or suitable for someone.
//terribly=very; extremely.
//compute=calculate (a figure or amount).
//common=occurring, found, or done often; prevalent.
//provide=make available for use; supply.

//Up until now, we have been using wrapper classes when we needed primitives to go into streams.
//We did this with the Collections API so it would feel natural.
//With streams, there are also equivalents that work with the int, double, and long primitives.
//Let's take a look at why this is needed.
//Suppose that we want to calculate the sum of numbers in a finite stream:

class Main{
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        //java.util.stream.ReferencePipeline$Head@4554617c
        //Stream<T> of:T... values – the elements of the new stream
        //Returns a new sequential ordered stream whose elements are the specified values.
        System.out.println(stream.reduce(0, (a, b) -> a + b));//6
        //T reduce:T identity value for the accumulating function,
        //java.util.function.BinaryOperator<T> accumulator function for combining two values
        //Perform a reduction on the elements of this stream, using the provided identity value
        //and an associative accumulation function, and returns the reduced value.
        //Return the result of the reduction.
        //BinaryOperator<Type> extends BiFunction<Type,Type,Type>     Type apply(Type, Type)
        //change (apply) (use) two parameters into a value and returning it
        //both parameters and return value must be the same type

        //Not bad. It wasn't hard to write a reduction. We started the accumulator with zero.
        //We then added each number to that running total as it came up in the stream.
        //There is another way of doing that:
        Stream<Integer> stream2 = Stream.of(1, 2, 3);
        //java.util.stream.ReferencePipeline$Head@6d311334
        System.out.println(stream2.mapToInt(x -> x).sum());//6
        //java.util.stream.IntStream mapToInt:java.util.function.ToIntFunction mapper function to apply to each element
        //Returns a new IntStream consisting of the results of applying the given function to the elements of this stream.
        //This is an intermediate operation.
        //ToIntFunction<Type>         int applyAsInt(Type);
        //change(applyAsInt) (use) one parameter(Type) into a value(int) and returning it.
        //int sum()
        //Returns the sum of elements in this stream.
        //This is a special case of a reduction.This is a terminal operation.

        //This time, we converted our Stream<Integer> to an IntStream and asked the IntStream to calculate the sum for us.
        //The primitive streams know how to perform certain common operations automatically.
        //So far, this seems like a nice convenience but not terribly important.
        //Now think about how you would compute an average.
        //You need to divide the sum by the number of elements.
        //The problem is that streams allow only one pass.
        //Java recognizes that calculating an average is a common thing to do,
        //and it provides a method to calculate the average on the stream classes for primitives:
        IntStream intStream = IntStream.of(1, 2, 3);
        //java.util.stream.IntPipeline$Head@3b9a45b3
        //IntStream of:int... values – the elements of the new stream
        //Returns a new sequential ordered stream whose elements are the specified values.
        //interface IntStream=A sequence of primitive int-valued elements supporting sequential and parallel aggregate operations.
        //This is the int primitive specialization of Stream.
        OptionalDouble avg = intStream.average();
        System.out.println(avg);//OptionalDouble[2.0]
        //class OptionalDouble=A container object which may or may not contain a double value.
        //If a value is present, isPresent() will return true and getAsDouble() will return the value.
        //java.util.OptionalDouble average()
        //Return an OptionalDouble describing the arithmetic mean(average element) of elements of this stream,
        //or an empty optional if this stream is empty.
        //This is a special case of a reduction. This is a terminal operation.
        System.out.println(avg.getAsDouble());//2.0
        //double getAsDouble()
        //If a value is present in this OptionalDouble, returns the value, otherwise throws NoSuchElementException.
        //Return the value held by this OptionalDouble
        //Throws:NoSuchElementException – if there is no value present

        //Not only is it possible to calculate the average, but it is also easy to do so.
        //Clearly primitive streams are important.
        //We will look at creating and using such streams, including optionals and functional interfaces.

        //Creating Primitive Streams

        //API=a set of functions and procedures allowing the creation of applications
        //common=occurring, found, or done often; prevalent.
        //case=each of the two forms, capital or minuscule, in which a letter of the alphabet may be written or printed.
        //capital=(of a letter of the alphabet) large in size and of the form used to begin sentences and names.
        //concept=an abstract idea.
        //equivalent=a thing that is equal to or corresponds with another in value, amount, function, meaning, etc.
        //regular=usual or ordinary

        //Here are three types of primitive streams:
        //IntStream: Used for the primitive types int, short, byte, and char
        //LongStream: Used for the primitive type long
        //DoubleStream: Used for the primitive types double and float
        
        //Why doesn't each primitive type have its own primitive stream?
        //These three are the most common, so the API designers went with them.

        //When you see the word stream on the exam, pay attention to the case.
        //With a capital S or in code, Stream is the name of a class that contains an Object type.
        //With a lowercase s, a stream is a concept that might be a Stream, DoubleStream, IntStream, or LongStream.
        //Some of the methods for creating a primitive stream are equivalent to how we created the source for a regular Stream.
        //You can create an empty stream with this:
        DoubleStream empty = DoubleStream.empty();
        //java.util.stream.DoublePipeline$Head@27d6c5e0
        //interface DoubleStream=A sequence of primitive double-valued elements supporting sequential and parallel aggregate operations.
        //This is the double primitive specialization of Stream.
        //DoubleStream empty()
        //Returns an empty sequential DoubleStream.

        //Another way is to use the of() factory method from a single value or by using the varargs overload:
        DoubleStream oneValue = DoubleStream.of(3.14);
        //java.util.stream.DoublePipeline$Head@4f3f5b24
        //DoubleStream of:double t – the single element
        //Returns a singleton sequential DoubleStream containing a single element.
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        //java.util.stream.DoublePipeline$Head@15aeb7ab
        //DoubleStream of:double... values – the elements of the new stream
        //Returns a new sequential ordered stream whose elements are the specified values.
        oneValue.forEach(System.out::println);//3.14
        //void forEach:java.util.function.DoubleConsumer action to perform on the elements
        //Performs an action for each element of this stream.
        //This is a terminal operation.
        //DoubleConsumer    void accept(double)
        //do something with (accept) a parameter(double) but not return anything.
        //void println:double x – The double to be printed.
        //Prints a double and then terminate the line.
        System.out.println();
        varargs.forEach(System.out::println);
        //1.0
        //1.1
        //1.2
        
        //It works the same way for each type of primitive stream.
        //You can also use the two methods for creating infinite streams, just like we did with Stream:
        DoubleStream random = DoubleStream.generate(Math::random);
        //java.util.stream.DoublePipeline$Head@2f4d3709
        //DoubleStream generate:java.util.function.DoubleSupplier s for generated elements
        //Return an infinite sequential unordered stream where each element is generated by the provided DoubleSupplier.
        //This is suitable for generating constant streams, streams of random elements, etc.
        //Return a new infinite sequential unordered DoubleStream
        //DoubleSupplier    double getAsDouble()
        //getAsDouble(generate,supply) result(double) without taking any input.
        //double random()
        //Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
        DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
        //java.util.stream.DoublePipeline$Head@34a245ab
        //DoubleStream iterate:double seed – the initial element,
        //java.util.function.DoubleUnaryOperator f – a function to be applied to the previous element to produce a new element
        //Returns an infinite sequential ordered DoubleStream produced by iterative application of a function f to an initial element seed,
        //producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
        //The first element (position 0) in the DoubleStream will be the provided seed.
        //For n > 0, the element at position n, will be the result of applying the function f to the element at position n - 1.
        //DoubleUnaryOperator     double applyAsDouble(double)
        //change (applyAsDouble) (use) one parameter(double) into a value(double) and returning it
        //return value must be the same type
        random.limit(3).forEach(System.out::println);
        //0.7810029111223202
        //0.7145987681819456
        //0.6760304473813519
        //DoubleStream limit:long maxSize – the number of elements the stream should be limited to
        //Returns a new stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
        //This is a short-circuiting intermediate operation.
        //void forEach:java.util.function.DoubleConsumer action to perform on the elements
        //Performs an action for each element of this stream.
        //This is a terminal operation.
        //DoubleConsumer    void accept(double)
        //do something with (accept) a parameter(double) but not return anything.
        //void println:double x – The double to be printed.
        //Prints a double and then terminate the line.
        System.out.println();
        fractions.limit(3).forEach(System.out::println);
        //0.5
        //0.25
        //0.125

        //intermediate=coming between two things in time, place
        //Because the streams are infinite, we added a limit intermediate operation so that the output doesn't print values forever.
        //The first stream calls a static method on Math to get a random double.
        //Because the numbers are random, your output will obviously be different.
        //The second stream keeps creating smaller numbers, dividing the previous value by two each time.
        //The output from when we ran this code was as follows:
        //0.07890654781186413
        //0.28564363465842346
        //0.6311403511266134
        //
        //0.5
        //0.25
        //0.125

        //You don't need to know this for the exam, but the Random class provides a method to get primitives streams of random numbers directly.
        //Fun fact! For example, ints() generates an infinite stream of int primitives.
        Random random2=new Random();
        //java.util.Random@34c45dca
        //class Random=An instance of this class is used to generate a stream of pseudorandom numbers.
        IntStream x=random2.ints();
        //java.util.stream.IntPipeline$Head@52cc8049
        //interface IntStream=A sequence of primitive int-valued elements supporting sequential and parallel aggregate operations.
        //This is the int primitive specialization of Stream.
        //java.util.stream.IntStream ints()
        //Returns an effectively unlimited stream of pseudorandom int values.
        x.limit(3).forEach(System.out::println);
        //1507670506
        //1393128070
        //199538050
        //IntStream limit:long maxSize – the number of elements the stream should be limited to
        //Returns a new stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
        //This is a short-circuiting stateful intermediate operation.
        //void forEach:java.util.function.IntConsumer action to perform on the elements
        //Performs an action for each element of this stream.
        //This is a terminal operation.
        //IntConsumer       void accept(int)
        //do something with (accept) a parameter(int) but not return anything.
        //void println:int x – The int to be printed.
        //Prints an integer and then terminate the line.
        

        //per=for each
        //provide=make available for use; supply.
        //closed=(of a set) containing all its limit points.
        //inclusive=including the limits specified
        //plain=simple or basic in character.
        
        //When dealing with int or long primitives, it is common to count.
        //Suppose that we wanted a stream with the numbers from 1 through 5.
        //We could write this using what we've explained so far:

        IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
        //java.util.stream.SliceOps$2@506e1b77
        //IntStream iterate:int seed – the initial element,
        //java.util.function.IntUnaryOperator f – a function to be applied to the previous element to produce a new element
        //Returns a new infinite sequential ordered IntStream produced by iterative application of a function f
        //to an initial element seed, producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
        //The first element (position 0) in the IntStream will be the provided seed.
        //For n > 0, the element at position n, will be the result of applying the function f to the element at position n - 1.
        //IntUnaryOperator        int applyAsInt(int)
        //change (applyAsInt) (use) one parameter(int) into a value(int) and returning it
        //return value must be the same type
        //IntStream limit:long maxSize – the number of elements the stream should be limited to
        //Returns a new stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
        //This is a short-circuiting intermediate operation.
        count.forEach(System.out::println);
        //1
        //2
        //3
        //4
        //5
        //void forEach:java.util.function.IntConsumer action to perform on the elements
        //Performs an action for each element of this stream.
        //This is a terminal operation.
        //IntConsumer       void accept(int)
        //do something with (accept) a parameter(int) but not return anything.
        //void println:int x – The int to be printed.
        //Prints an integer and then terminate the line.



        //This code does print out the numbers 1–5, one per line.
        //However, it is a lot of code to do something so simple.
        //Java provides a method that can generate a range of numbers:
        
        IntStream range = IntStream.range(1, 6);
        //java.util.stream.IntPipeline$Head@9807454
        //IntStream range:int startInclusive – the (inclusive) initial value,
        //int endExclusive – the exclusive upper bound
        //Return a sequential ordered IntStream from startInclusive (inclusive) to endExclusive (exclusive) by an incremental step of 1.
        range.forEach(System.out::println);
        //1
        //2
        //3
        //4
        //5

        //This is better.
        //The range() method indicates that we want the numbers 1–6, not including the number 6.
        //However, it still could be clearer. We want the numbers 1–5.
        //We should be able to type the number 5, and we can do so as follows:
        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        //java.util.stream.IntPipeline$Head@1ddc4ec2
        //IntStream rangeClosed:int startInclusive – the (inclusive) initial value,
        //int endInclusive – the inclusive upper bound
        //Returns a sequential ordered IntStream from startInclusive (inclusive) to endInclusive (inclusive) by an incremental step of 1.
        rangeClosed.forEach(System.out::println);
        //1
        //2
        //3
        //4
        //5

        //Even better. This time we expressed that we want a closed range, or an inclusive range.
        //This method better matches how we express a range of numbers in plain English.

        //map=be associated with or linked to (an equivalent group).
        //The final way to create a primitive stream is by mapping from another stream type.
        //Table 4.6 shows that there is a method for mapping between any stream types.;

        //Table 4.6 Mapping methods between types of streams

        //Source Stream Class       To Create Stream       To Create DoubleStream      To Create IntStream     To Create LongStream
        
        //Stream                    map                    mapToDouble                 mapToInt                mapToLong
        //DoubleStream              mapToObj               map                         mapToInt                mapToLong
        //IntStream                 mapToObj               mapToDouble                 map                     mapToLong
        //LongStream                mapToObj               mapToDouble                 mapToInt                map


        Stream<String> objS = Stream.of("penguin", "fish");
        //Stream<T type of stream elements> of:T... values elements of the new stream
        //Return a new sequential ordered stream whose elements are the specified values.
        Stream<String> objSCopy = objS.map(s -> s);
        //Stream<R element type of the new stream> map:Function mapper to apply to each element
        //Return a new Stream of results of applying the given [Function] to each element of the stream.
        //Function<Type, Returntype>       Returntype apply(Type);
        //change(apply) (use) one parameter into a value and returning it
        //return value can be a different type
        IntStream intS = objS.mapToInt(s -> s.length());
        //IntStream mapToInt:ToIntFunction mapper to apply to each element
        //Return a new IntStream consisting of the results of applying the given [ToIntFunction] to the elements of this stream.
        //ToIntFunction<Type>         int applyAsInt(Type);
        //change(applyAsInt) (use) one parameter(Type) into a value(int) and returning it.
        //int length()
        //Return the length of this string.
        IntStream intSCopy = intS.map(s -> s);
        //IntStream map:IntUnaryOperator mapper to apply to each element
        //Return a new IntStream of results of applying the given [IntUnaryOperator] function to each element of the stream.
        //IntUnaryOperator        int applyAsInt(int)
        //change (applyAsInt) (use) one parameter(int) into a value(int) and returning it
        //return value must be the same type
        Stream<Integer> objS1=intS.mapToObj(s -> s);
        //Stream<U element type of the new stream> mapToObj:IntFunction mapper to apply to each element
        //Return a new object-valued Stream consisting of the results of applying the given [IntFunction] to the elements of this stream.
        //IntFunction<Returntype>           Returntype apply(int);
        //change(apply) (use) one parameter(int) into a value and returning it
        //return value can be a different type
        DoubleStream doubleS1 = intS.mapToDouble(s -> s);
        //DoubleStream mapToDouble:IntToDoubleFunction mapper function to apply to each element
        //Return a new DoubleStream consisting of the results of applying the given [IntToDoubleFunction] to the elements of this stream.
        //IntToDoubleFunction        double applyAsDouble(int)
        //change(applyAsDouble) (use) one parameter(int) into a value(double) and returning it.
        LongStream longS1 = intS.mapToLong(s -> s);
        //LongStream mapToLong:IntToLongFunction mapper to apply to each element
        //Return a new LongStream consisting of the results of applying the given [IntToLongFunction] to the elements of this stream.
        //IntToLongFunction          long applyAsLong(int)
        //change(applyAsLong) (use) one parameter(int) into a value(long) and returning it.
        DoubleStream doubleS = objS.mapToDouble(s -> s.length());
        //DoubleStream mapToDouble:ToDoubleFunction mapper to apply to each element
        //Return a new DoubleStream consisting of the results of applying the given [ToDoubleFunction] to the elements of this stream.
        //ToDoubleFunction<Type>      double applyAsDouble(Type);
        //change(applyAsDouble) (use) one parameter(Type) into a value(double) and returning it.
        //int length()
        //Return the length of this string.
        DoubleStream doubleSCopy = doubleS.map(s -> s);
        //DoubleStream map:DoubleUnaryOperator mapper function to apply to each element
        //Return a new DoubleStream consisting of the results of applying the given [DoubleUnaryOperator] function to the elements of this stream.
        //DoubleUnaryOperator     double applyAsDouble(double)
        //change (applyAsDouble) (use) one parameter(double) into a value(double) and returning it
        //return value must be the same type
        Stream<Double> objS2=doubleS.mapToObj(s -> s);
        //Stream<U element type of the new stream> mapToObj:DoubleFunction mapper function to apply to each element
        //Return an object-valued Stream consisting of the results of applying the given [DoubleFunction] to the elements of this stream.
        //DoubleFunction<Returntype>       Returntype apply(double);
        //change(apply) (use) one parameter(double) into a value and returning it
        //return value can be a different type
        IntStream intS2 = doubleS.mapToInt(s -> (int) s);
        //IntStream mapToInt:DoubleToIntFunction mapper function to apply to each element
        //Return an IntStream consisting of the results of applying the given [DoubleToIntFunction] to the elements of this stream.
        //DoubleToIntFunction        int applyAsInt(double)
        //change(applyAsInt) (use) one parameter(double) into a value(int) and returning it.
        LongStream longS2 = doubleS.mapToLong(s -> (long) s);
        //LongStream mapToLong:DoubleToLongFunction mapper function to apply to each element
        //Return a new LongStream consisting of the results of applying the given [DoubleToLongFunction] to the elements of this stream.
        //DoubleToLongFunction       long applyAsLong(double)
        //change(applyAsLong) (use) one parameter(double) into a value(long) and returning it.
        LongStream longS = objS.mapToLong(s -> s.length());
        //LongStream mapToLong:ToLongFunction mapper function to apply to each element
        //Return a LongStream consisting of the results of applying the given [ToLongFunction] to the elements of this stream.
        //ToLongFunction<Type>        long applyAsLong(Type);
        //change(applyAsLong) (use) one parameter(Type) into a value(long) and returning it.
        //int length()
        //Return the length of this string.
        LongStream longSCopy = longS.map(s -> s);
        //LongStream map:LongUnaryOperator mapper function to apply to each element
        //Return a new LongStream consisting of the results of applying the given [LongUnaryOperator] function to the elements of this stream.
        //LongUnaryOperator       long applyAsLong(long)
        //change (applyAsLong) (use) one parameter(long) into a value(long) and returning it
        //return value must be the same type
        Stream<Long> objS3=longS.mapToObj(s -> s);
        //Stream<U element type of the new stream> mapToObj:LongFunction mapper to apply to each element
        //Return an object-valued Stream consisting of the results of applying the given [LongFunction] to the elements of this stream.
        //LongFunction<Returntype>          Returntype apply(long);
        //change(apply) (use) one parameter(long) into a value and returning it
        //return value can be a different type
        IntStream intS3 = longS.mapToInt(s -> (int) s);
        //IntStream mapToInt:LongToIntFunction mapper to apply to each element
        //Return an IntStream consisting of the results of applying the given [LongToIntFunction] to the elements of this stream.
        //LongToIntFunction          int applyAsInt(long)
        //change(applyAsInt) (use) one parameter(long) into a value(int) and returning it.
        DoubleStream doubleS3 = longS.mapToDouble(s -> s);
        //DoubleStream mapToDouble:LongToDoubleFunction mapper to apply to each element
        //Returns a new DoubleStream consisting of the results of applying the given [LongToDoubleFunction] to the elements of this stream.
        //LongToDoubleFunction       double applyAsDouble(long)
        //change(applyAsDouble) (use) one parameter(long) into a value(double) and returning it.

        //obviously=in a way that is easily perceived or understood; clearly.
        //compatible=(of two things) able to exist or occur together without problems or conflict.
        //provide=make available for use; supply.
        //intuitive=easy to use and understand.
        //actual=existing in fact; real.
        //target=an objective or result towards which efforts are directed.
        //expect=regard (something) as likely to happen.

        //Obviously, they have to be compatible types for this to work.
        //Java requires a mapping function to be provided as a parameter, for example:
        Stream<String> objStreamEX = Stream.of("penguin", "fish");
        //Stream<T> of:T... values – the elements of the new stream
        //Returns a new sequential ordered stream whose elements are the specified values.
        IntStream intStreamEX = objStreamEX.mapToInt(s -> s.length());
        //java.util.stream.IntStream mapToInt:java.util.function.ToIntFunction mapper function to apply to each element
        //Returns an IntStream consisting of the results of applying the given function to the elements of this stream.
        //This is an intermediate operation.
        //IntFunction<Returntype>           Returntype apply(int);
        //change(apply) (use) one parameter(int) into a value and returning it
        //return value can be a different type
        //int length()
        //Returns the length of this string.

        //This function takes an Object, which is a String in this case.
        //The function returns an int. The function mappings are intuitive here.
        //They take the source type and return the target type.
        //In this example, the actual function type is ToIntFunction.
        //Table 4.7 shows the mapping function names.
        //As you can see, they do what you might expect.

        //Table 4.7 Function parameters when mapping between types of streams

        //Source Stream Class     To Create Stream       To Create DoubleStream     To Create IntStream    To Create LongStream

        //Stream                  Function               ToDoubleFunction           ToIntFunction          ToLongFunction
        //DoubleStream            DoubleFunction         DoubleUnaryOperator        DoubleToIntFunction    DoubleToLongFunction
        //IntStream               IntFunction            IntToDoubleFunction        IntUnaryOperator       IntToLongFunction
        //LongStream              LongFunction           LongToDoubleFunction       LongToIntFunction      LongUnaryOperator

        //pattern=a repeated decorative design.
        //You do have to memorize Table 4.6 and Table 4.7. It's not as hard as it might seem.
        //There are patterns in the names if you remember a few rules.
        //For Table 4.6, mapping to the same type you started with is just called map().
        //When returning an object stream, the method is mapToObj().
        //Beyond that, it's the name of the primitive type in the map method name.
        //For Table 4.7, you can start by thinking about the source and target types.
        //When the target type is an object, you drop the To from the name.
        //When the mapping is to the same type you started with, you use a unary operator instead of a function for the primitive streams.
        //You can also create a primitive stream from a Stream using flatMapToInt(), flatMapToDouble(), or flatMapToLong().
        //For example, IntStream ints = list.stream().flatMapToInt(x -> IntStream.of(x));
        

        List<Integer> list = new ArrayList<>();
        list.add(1);//[1]
        IntStream ints = list.stream().flatMapToInt(y -> IntStream.of(y));
        //Stream<E> stream()
        //Return a sequential Stream with this collection as its source.

        //IntStream flatMapToInt:Function mapper to apply to each element which produces a stream of new values
        //Returns an IntStream consisting of the results of replacing each element of this stream with the contents of a
        //mapped stream produced by applying the provided mapping function to each element.
        //Each mapped stream is closed after its contents have been placed into this stream.
        //Function<Type, Returntype>       Returntype apply(Type);
        //change(apply) (use) one parameter into a value and returning it
        //return value can be a different type

        //IntStream of:int t single element
        //Return a singleton sequential IntStream containing a single element.

        DoubleStream doubles = list.stream().flatMapToDouble(y -> DoubleStream.of(y));
        //Stream<E> stream()
        //Return a sequential Stream with this collection as its source.

        //DoubleStream flatMapToDouble:Function mapper to apply to each element which produces a stream of new values
        //Return an DoubleStream consisting of the results of replacing each element of this stream with the
        //contents of a mapped stream produced by applying the provided mapping function to each element.
        //Each mapped stream is closed after its contents have placed been into this stream.
        //Function<Type, Returntype>       Returntype apply(Type);
        //change(apply) (use) one parameter into a value and returning it
        //return value can be a different type

        //DoubleStream of:double t single element
        //Return a singleton sequential DoubleStream containing a single element.

        LongStream longs = list.stream().flatMapToLong(y -> LongStream.of(y));
        //Stream<E> stream()
        //Return a sequential Stream with this collection as its source.
        
        //LongStream flatMapToLong:Function mapper to apply to each element which produces a stream of new values
        //Returns an LongStream consisting of the results of replacing each element of this stream with the
        //contents of a mapped stream produced by applying the provided mapping function to each element.
        //Each mapped stream is closed after its contents have been placed into this stream.
        //Function<Type, Returntype>       Returntype apply(Type);
        //change(apply) (use) one parameter into a value and returning it
        //return value can be a different type
        
        //LongStream of:long t single element
        //Return a singleton sequential LongStream containing a single element.
    }
}