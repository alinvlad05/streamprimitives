# streamprimitives

Check src/Main.java in github.<br/>

Here are three types of primitive streams:<br/>
IntStream: Used for the primitive types int, short, byte, and char<br/>
LongStream: Used for the primitive type long<br/>
DoubleStream: Used for the primitive types double and float<br/>
 
Mapping methods between types of streams<br/>

| Source Stream Class  | To Create Stream | To Create DoubleStream |  To Create IntStream  | To Create LongStream |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| Stream  | map  | mapToDouble  | mapToInt  | mapToLong  |
| DoubleStream  | mapToObj  | map  | mapToInt  | mapToLong  |
| IntStream  | mapToObj  | mapToDouble  | map  | mapToLong  |
| LongStream   | mapToObj  | mapToDouble  | mapToInt  | map  |
                                                

Function parameters when mapping between types of streams <br/>

| Source Stream Class   | To Create Stream | To Create DoubleStream | To Create IntStream | To Create LongStream |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| Stream  | Function  | ToDoubleFunction  | ToIntFunction  | ToLongFunction  |
| DoubleStream  | DoubleFunction  | DoubleUnaryOperator  | DoubleToIntFunction  | DoubleToLongFunction  |
| IntStream  | IntFunction  | IntToDoubleFunction  | IntUnaryOperator  | IntToLongFunction  |
| LongStream  | LongFunction  | LongToDoubleFunction  | LongToIntFunction  | LongUnaryOperator  |              
