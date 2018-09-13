# rpn
RPN implementation

 
A simple RPN App for calculation on Arithmetic operation.

1. Performs the set of Arithmetic operation including sqrt
2. The input must have a space in between, else the calculator will fail with the previous state.
3. The response is in String with the right expanded representation, instead of the engineering(exponential) representation.
4. The stack stores the full representation of the number, however, the response is given to the 10 decimal spaces.
5. The Class need to be instantiated for each specific stream of operation, since its not Thread safe.
6. use input c (the only char) to exit the input retrieval
