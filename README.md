## Some (recursive) methods for `BSTree`

Do all these methods recursively. They will each do some operation on the 
immutable `BSTree` (Binary Search Tree) type defined this week in class.

### Recap: how this all works

In class last week we defined the following binary search tree interface type as a so called ["algebraic type"](https://en.wikipedia.org/wiki/Algebraic_data_type).
This is a compressed form of the BST tree type we ended up modeling last week:

```java
public sealed interface BSTree {
  record Empty()                                       implements BSTree { /* snip */ }
  record NonEmpty(BSTree left, int data, BSTree right) implements BSTree { /* snip */ } // "node"
}
```
So we have a new type we're defining called `BSTree`. It's a normal java interface-type with one important caveat:

> it only supports two possible implementations `Empty` and `NonEmpty`

### whats the deal with these two small records?

If you can't remember what a ```record``` is, give this a [quick read/skim](https://www.infoq.com/articles/exploring-java-records/) and come back.

Basically, there are only two possible implementations of a `BSTree`:

* `Empty`: which represents an empty tree.
* `NonEmpty` which represents a node that stores a `left` subtree, a piece of `data` (of type int), and a `right` subtree.

#### Visual Representation
```
Empty Tree:
empty

NonEmpty Tree:
    10
   /  \
empty empty
```

### Polymorphism with BSTree
Polymorphism allows a BSTree variable to hold either an Empty or NonEmpty instance.

> recall the definition of "polymorphism" a variable with a single declared-type that can take on several (poly) different forms (morphisms) at runtime

For example, consider our `BSTree` type...

```java
BSTree tree1 = new Empty(); // var decl + initialization
System.out.println(tree1); // prints empty
```

currently the `tree1`'s variable's **declared type** is `BSTree` and the variable is getting initialized
to be a `new` emty tree (this is tree1s curren **runtime type**)

But the essence of polymorphism is that a variables **runtime/dynamic** type can change easily.... consider:
```java
tree1 = new NonEmpty(new Empty(), 10, new Empty()); // reassigning/updating var tree1 to be a 
```
Now we've reassigned var `tree1` (which still has **declared type**: `BSTree`) to now have a **runtime type** of: `new NonEmpty(..)`. But as far as Java's type system is (statically) concerned.. i.e.:pre-execution: `tree1` is still just a variable of **declared type** `BSTtree`... and it will be forever. Just realize that it's **dynamic type** can change whenever at runtime.

### Benefits of Sealed Interfaces

Sealed interfaces ensure all possible cases are covered. This removes the need for a default case in switch statements. 


```java
public void printTreeType(BSTree tree) {
    switch (tree) {
        case Empty e     -> System.out.println("The tree is empty.");
        case NonEmpty ne -> System.out.println("The tree has data: " + ne.data());
        // No default case needed (due to the sealed in the BSTree interface)
    }
}
```
So using type-level pattern matching, we can just `switch` on the variable `tree` (of type: `BSTree`) in the `printTreeType(..)` method above and "match" specifically on whatever its **runtime type** happens to be! 

### Some examples:
```java
BSTree emptyTree = BSTree.empty();
BSTree nonEmptyTree = emptyTree.insert(20);

printTreeType(emptyTree);    // Output: The tree is empty.
printTreeType(nonEmptyTree); // Output: The tree has data: 20
```

**Exercise Instructions:**

1. you should be using JDK23 for these + IntelliJ 2024.X (more recent the better)
2. write one or two minimal `@Test`s for each of these 
methods in the green test dir to confirm your implementation is working 
3. do as many of these as you can -- the last one you'll need to use a higher-order 
`Function` 
   * [somewhat OK resource on higher order functions and predicates in java](https://softwarepatternslexicon.com/patterns-java/11/2/) 

Hint: basically all of these methods you can accomplish with a single `return <swich-expression>` that matches on the runtime type of the `BSTree` passed into the method.

### method: `sumAll`

Write a method, `sumAll` that takes a `BSTree` and produces the sum of all the nodes.

```java
BSTree tr = BSTree.empty()
            .insert(10)
            .insert(5)
            .insert(20)
            .insert(15);
int res = Exercises.sumAll(tr);
System.out.println(res); // sum of all nodes is 50
```
### method: `sumLeafs`

Now write a variant, `sumLeafs` that takes a `BSTree` and produces/returns the 
sum of only the leaf nodes in the bst. 

```java
// declaring and initializing a sample bst...
// will be using `var` for decls here on out
var tr = BSTree.empty() 
        .insert(10)
        .insert(5)
        .insert(20)
        .insert(15);
int res = Exercises.sumLeafs(tr);
System.out.println(res); // 20 (sum of leaf nodes 5 and 15)
```
### method: `maxDepth`

Write a method `maxDepth` that takes a `BSTree` and computes the depth (or height) of the tree, 
defined as the number of edges from the root node to the 
deepest leaf node.

```java
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(20)
        .insert(25);
int res = Exercises.depth(tr);
System.out.println(res); // 3
```

### method: `average`

Write a method `average` that takes a `BSTree` as a parameter and returns the 
average of all nodes contained in the tree.

```java
var tr = BSTree.empty()
        .insert(8)
        .insert(2);
int res = Exercises.average(tr);
System.out.println(res); // 5
```

### method: `toList`

Write a method `toList` that takes a `BSTree` and performs a traversal over it
and returns a `TreeSet<Integer>` of the elements in ascending order.

```java
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(20)
        .insert(15);
TreeSet<Integer> res = Exercises.toList(tr);
System.out.println(res); // [5, 10, 15, 20]
```

### method: `pathSum`

Write a method `pathSum` that takes a `BSTree` and returns true only if there 
exists a path from the root node to any leaf whose values on the path down sum 
to a given target value.

```java
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(15)
        .insert(1)
        .insert(7);
// any leaf nodes with a path down that sums to 22?
boolean res = Exercises.pathSum(tr, 22); // True (10 -> 5 -> 7)
System.out.println(res); // true
```

### method: `mirror`

Write a method `mirror` that returns a `BSTree` where the 
left and right children of each node are swapped, creating a 
mirror image of the original tree.

```java 
var tr = BSTree.empty()
        .insert(10)
        .insert(5)
        .insert(15);
BSTree mirrored = Exercises.mirror(tr);
// should print a mirrored preorder traversal
System.out.println(mirrored.preOrder()); 
```

### method: `allSatisfy`

Write a method `allSatisfy` that takes a `BSTree` and a `Function<Integer, Boolean> f`. 
> i.e.: the method takes a function `f : Integer -> Boolean`

The method should return true only if each node in the tree passed satisfies the 
provided function `f`.

```java 
var tr = BSTree.empty()
          .insert(10)
          .insert(5)
          .insert(20)
          .insert(15);

// example 1: check if all nodes are greater than 0
boolean allPositive = Exercises.allSatisfy(tr, (Integer x) -> x > 0);
System.out.println(allPositive); // true, since all nodes are positive

// example 2: check if all nodes are less than 10 
// (can actually omit the `Integer` on the param `x`)
boolean allLessThan10 = Exercises.allSatisfy(tr, x -> x < 10);
System.out.println(allLessThan10); // false, since 10, 20, and 15 are not less than 10

// example 3: check if all nodes are even
boolean allEven = Exercises.allSatisfy(tr, x -> x % 2 == 0);
System.out.println(allEven); // false, since 5 and 15 are odd

// example 4: check if all nodes are divisible by 5
boolean allDivisibleBy5 = Exercises.allSatisfy(tr, x -> x % 5 == 0);
System.out.println(allDivisibleBy5); // true, since all nodes are divisible by 5```
```
